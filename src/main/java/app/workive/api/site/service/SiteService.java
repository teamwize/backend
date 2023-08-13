package app.workive.api.site.service;


import app.workive.api.auth.service.SecurityService;
import app.workive.api.base.domain.model.request.PaginationRequest;
import app.workive.api.base.domain.model.response.PagedResponse;
import app.workive.api.base.exception.BaseException;
import app.workive.api.organization.domain.entity.Organization;
import app.workive.api.site.domain.entity.Site;
import app.workive.api.site.domain.request.SiteCreateRequest;
import app.workive.api.site.domain.request.SiteUpdateRequest;
import app.workive.api.site.domain.response.SiteResponse;
import app.workive.api.site.exception.SiteNotFoundException;
import app.workive.api.site.mapper.SiteMapper;
import app.workive.api.site.repository.SiteRepository;
import app.workive.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.List;

import static app.workive.api.user.domain.UserRole.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class SiteService {

    private static final String DEFAULT_NAME = "default";
    private static final String DEFAULT_LANGUAGE = "EN";

    private final SiteRepository siteRepository;

    @Lazy
    private final UserService userService;

    private final SecurityService securityService;


    private final SiteMapper siteMapper;


    public String getSiteTimeZone(long siteId) {
        var timezone = siteRepository.getTimeZoneBySiteId(siteId);
        return timezone != null ? timezone : ZoneId.systemDefault().toString();
    }


    public PagedResponse<SiteResponse> getOrganizationSites(long organizationId, PaginationRequest pagination) {
        var pageRequest = PageRequest.of(pagination.getPageNumber(), pagination.getPageSize(), Sort.by("id").ascending());
        var sites = siteRepository.findByOrganizationId(organizationId, pageRequest);
        var contents = buildResponse(sites.getContent());
        return new PagedResponse<>(
                contents,
                sites.getPageable().getPageNumber(),
                sites.getPageable().getPageSize(),
                sites.getTotalPages(),
                sites.getTotalElements()
        );
    }

    public SiteResponse getSite(long organizationId, long siteId) throws SiteNotFoundException {
        var site = getById(organizationId, siteId);
        return siteMapper.toResponse(site);
    }

    @Transactional(rollbackFor = BaseException.class)
    public SiteResponse createDefaultSite(Long organizationId, String country, String timezone) {
        var request = new SiteCreateRequest()
                .setName(DEFAULT_NAME)
                .setCountryCode(country);
        var site = buildSite(organizationId, request, true);
        site = siteRepository.persist(site);
        return siteMapper.toResponse(site);
    }


    @Transactional(rollbackFor = BaseException.class)
    public SiteResponse createSite(Long organizationId, SiteCreateRequest request) {
        var site = buildSite(organizationId, request, false);
        site = siteRepository.persist(site);
        return siteMapper.toResponse(site);
    }

    @Transactional(rollbackFor = BaseException.class)
    public SiteResponse updateSite(Long organizationId, long siteId, SiteUpdateRequest request)
            throws SiteNotFoundException {
        var site = getById(organizationId, siteId);
        var role = securityService.getAuthenticatedUserRole();

        if (role == ORGANIZATION_ADMIN) {
            site.setName(request.getName())
                    .setAddress(request.getAddress())
                    .setLocation(request.getLocation())
                    .setLanguage(request.getLanguage())
                    .setTimezone(request.getTimezone());
        } else {
            site.setAddress(request.getAddress())
                    .setLocation(request.getLocation())
                    .setLanguage(request.getLanguage())
                    .setTimezone(request.getTimezone());
        }

        site = siteRepository.update(site);
        return getSite(organizationId, site.getId());
    }


    private Site getById(long organizationId, long siteId) throws SiteNotFoundException {
        return siteRepository.findByIdAndOrganizationId(siteId, organizationId)
                .orElseThrow(() -> new SiteNotFoundException(organizationId, siteId));
    }

    private Site buildSite(Long organizationId, SiteCreateRequest request, Boolean isDefault) {
        return new Site()
                .setDefault(isDefault)
                .setName(request.getName())
                .setAddress(request.getAddress())
                .setLocation(request.getLocation())
                .setLanguage(DEFAULT_LANGUAGE)
                .setOrganization(new Organization(organizationId));
    }

    private List<SiteResponse> buildResponse(List<Site> sites) {
        return siteMapper.toResponse(sites);
    }
}
