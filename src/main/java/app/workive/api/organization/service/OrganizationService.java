package app.workive.api.organization.service;


import app.workive.api.base.exception.BaseException;
import app.workive.api.organization.domain.entity.Organization;
import app.workive.api.organization.domain.response.OrganizationResponse;
import app.workive.api.organization.exception.OrganizationNotFoundException;
import app.workive.api.organization.mapper.OrganizationMapper;
import app.workive.api.organization.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    public OrganizationResponse getOrganization(long organizationId) throws OrganizationNotFoundException {
        var organization =  getById(organizationId);
        return organizationMapper.toResponse(organization);
    }

    @Transactional(rollbackFor = BaseException.class)
    public OrganizationResponse registerOrganization(String name) {
        var organization =  createOrganization(name);
        return organizationMapper.toResponse(organization);
    }

    public OrganizationResponse updateOrganization(long organizationId, String name) throws OrganizationNotFoundException {
        var organization = getById(organizationId)
                .setName(name);
        organization = organizationRepository.update(organization);
        return organizationMapper.toResponse(organization);
    }

    private Organization getById(Long organizationId) throws OrganizationNotFoundException {
        return organizationRepository.findById(organizationId)
                .orElseThrow(() -> new OrganizationNotFoundException(organizationId));
    }

    private Organization createOrganization(String name) {
        Organization organization = buildOrganization(name);
        return organizationRepository.persist(organization);
    }

    private Organization buildOrganization(String name) {
        return new Organization()
                .setName(name);
    }


}
