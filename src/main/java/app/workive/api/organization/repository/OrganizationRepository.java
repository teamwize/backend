package app.workive.api.organization.repository;


import app.workive.api.organization.domain.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findById(Long id);

}
