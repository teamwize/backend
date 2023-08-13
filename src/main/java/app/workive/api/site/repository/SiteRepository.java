package app.workive.api.site.repository;

import app.workive.api.site.domain.entity.Site;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SiteRepository extends BaseJpaRepository<Site, Long> {
    Optional<Site> findByIdAndOrganizationId(long siteId, long organizationId);

    Page<Site> findByOrganizationId(long organizationId, Pageable pageable);

    @Query("from Site s where s.organization.id=:organizationId and s.isDefault=true")
    Site findDefaultSiteByOrganizationId(long organizationId);

    @Query("select s.timezone from Site s where s.id=:id")
    String getTimeZoneBySiteId(long id);

    @Query("select s.organization.id from Site s where s.id=:id")
    Long getSiteOrganizationId(long id);

}
