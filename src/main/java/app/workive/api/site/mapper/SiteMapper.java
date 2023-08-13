package app.workive.api.site.mapper;


import app.workive.api.site.domain.entity.Site;
import app.workive.api.site.domain.response.SiteCompactResponse;
import app.workive.api.site.domain.response.SiteResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SiteMapper {
    SiteResponse toResponse(Site entity);

    SiteCompactResponse toSiteCompactResponse(Site site);

    List<SiteResponse> toResponse(List<Site> entities);
}
