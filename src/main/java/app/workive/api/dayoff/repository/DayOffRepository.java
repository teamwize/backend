package app.workive.api.dayoff.repository;

import app.workive.api.dayoff.domain.entity.DayOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayOffRepository extends JpaRepository<DayOff, Long> {
}
