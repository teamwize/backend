package app.workive.api.site.controller;


import app.workive.api.auth.service.SecurityService;
import app.workive.api.base.domain.model.request.PaginationRequest;
import app.workive.api.base.domain.model.response.PagedResponse;
import app.workive.api.organization.service.OrganizationService;
import app.workive.api.site.domain.request.SiteCreateRequest;
import app.workive.api.site.domain.request.SiteUpdateRequest;
import app.workive.api.site.domain.response.SiteResponse;
import app.workive.api.site.exception.SiteNotFoundException;
import app.workive.api.site.service.SiteService;
import app.workive.api.user.domain.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("sites")
@RequiredArgsConstructor
public class SiteController {

    private final SiteService siteService;

    private final SecurityService securityService;

    @GetMapping
    public PagedResponse<SiteResponse> getOrganizationSites(@ParameterObject @Valid PaginationRequest page) {
       return siteService.getOrganizationSites(securityService.getUserOrganizationId(), page);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SiteResponse createSite(@RequestBody @Valid SiteCreateRequest request){
        return siteService.createSite(securityService.getUserOrganizationId(), request);
    }

    @GetMapping("{siteId}")
    public SiteResponse getSite(@PathVariable Long siteId) throws SiteNotFoundException {
        return siteService.getSite(securityService.getUserOrganizationId(), siteId);
    }

    @PutMapping("{siteId}")
    public SiteResponse updateSite(@PathVariable Long siteId,@RequestBody @Valid SiteUpdateRequest request) throws  SiteNotFoundException {
        return siteService.updateSite(securityService.getUserOrganizationId(), siteId, request);
    }


    @GetMapping("default")
    public SiteResponse getDefaultSite() throws SiteNotFoundException {
        return siteService.getSite(securityService.getUserOrganizationId(), securityService.getSiteId());
    }




}
