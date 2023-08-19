package app.workive.api.dayoff.repository;

import app.workive.api.dayoff.domain.entity.DayOff;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DayOffRepository extends BaseJpaRepository<DayOff, Long> {

    Optional<DayOff> findByUserIdAndId(Long userId, Long id);

    Page<DayOff> findByOrganizationId(Long organizationId, Pageable page);

    Page<DayOff> findByOrganizationIdAndUserId(Long organizationId, Long userId, Pageable page);
}
