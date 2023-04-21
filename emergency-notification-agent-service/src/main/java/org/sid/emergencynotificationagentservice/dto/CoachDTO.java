package org.sid.emergencynotificationagentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoachDTO {
    Long idCoach;
    String coachFirstName;
    Long idMember;
    Date date;

    double cardiacFrequency;
}
