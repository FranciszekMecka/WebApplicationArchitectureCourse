package eti.mecka.franciszek.project.organization.controller.impl;

import eti.mecka.franciszek.project.organization.controller.api.OrganizationController;
import eti.mecka.franciszek.project.organization.dto.GetOrganizationResponse;
import eti.mecka.franciszek.project.organization.dto.GetOrganizationsResponse;
import eti.mecka.franciszek.project.organization.function.OrganizationToResponseFunction;
import eti.mecka.franciszek.project.organization.function.OrganizationsToResponseFunction;
import eti.mecka.franciszek.project.organization.service.api.OrganizationService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@Log
public class OrganizationDefaultController implements OrganizationController {

    private final OrganizationService service;
    private final OrganizationToResponseFunction organizationToResponse;
    private final OrganizationsToResponseFunction organizationsToResponse;

    public OrganizationDefaultController(
            OrganizationService service,
            OrganizationToResponseFunction organizationToResponse,
            OrganizationsToResponseFunction organizationsToResponse
    ) {
        this.service = service;
        this.organizationToResponse = organizationToResponse;
        this.organizationsToResponse = organizationsToResponse;
    }

    @Override
    public GetOrganizationsResponse getOrganizations() {
        return organizationsToResponse.apply(service.findAll());
    }

    @Override
    public GetOrganizationResponse getOrganization(UUID id) {
        return service.find(id)
                .map(organizationToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteOrganization(UUID id) {
        service.find(id)
                .ifPresentOrElse(
                        organization -> service.delete(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }

}
