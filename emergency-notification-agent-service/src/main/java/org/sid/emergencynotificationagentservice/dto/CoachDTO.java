package org.sid.emergencynotificationagentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoachDTO {

    private Long idCoach;
    private Long idMember;
    private String memberFirstName;
    private Double cardiacFrequency;
}
