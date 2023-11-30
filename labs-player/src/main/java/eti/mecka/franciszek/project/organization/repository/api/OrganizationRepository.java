package eti.mecka.franciszek.project.organization.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import eti.mecka.franciszek.project.organization.entity.Organization;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
}
