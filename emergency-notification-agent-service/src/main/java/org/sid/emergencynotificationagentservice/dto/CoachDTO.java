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
    Long idCoach;
    String coachFirstName;
    Long idMember;
    String memberFirstName;
}
