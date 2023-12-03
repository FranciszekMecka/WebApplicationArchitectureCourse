package eti.mecka.franciszek.project.organization.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutOrganizationRequest {
    private String firstName;
    private String lastName;
    private String nationality;
    private int jerseyNumber;
    private int age;
    private int height;
    private float weight;
    private LocalDate dateOfBirth;
    private UUID organization;
}