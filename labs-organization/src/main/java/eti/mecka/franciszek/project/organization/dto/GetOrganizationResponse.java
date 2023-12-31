package eti.mecka.franciszek.project.organization.dto;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetOrganizationResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Achievement {
        private UUID id;
        private String name;
    }

    private UUID id;
    private String name;
    private LocalDate dateOfEstablishment;

    @Singular
    private Map<Integer, Achievement> achievements;

}
