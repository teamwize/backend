package app.workive.api.organization.repository;


import app.workive.api.organization.domain.entity.Organization;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends BaseJpaRepository<Organization, Long> {


}
