package app.workive.api.organization.mapper;

import app.workive.api.base.config.DefaultMapperConfig;
import app.workive.api.organization.domain.entity.Organization;
import app.workive.api.organization.domain.response.OrganizationCompactResponse;
import app.workive.api.organization.domain.response.OrganizationResponse;
import org.mapstruct.Mapper;


@Mapper(config = DefaultMapperConfig.class)
public interface OrganizationMapper {
    OrganizationResponse toResponse(Organization organization);

    OrganizationCompactResponse toOrganizationCompactResponse(Organization organization);
}