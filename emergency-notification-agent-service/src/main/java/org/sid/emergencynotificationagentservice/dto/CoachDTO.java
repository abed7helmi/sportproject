package org.sid.emergencynotificationagentservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String CoachName;
    private Long idMember;
    private String memberFirstName;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Double cardiacFrequency;
    private Integer memberAge;

}
