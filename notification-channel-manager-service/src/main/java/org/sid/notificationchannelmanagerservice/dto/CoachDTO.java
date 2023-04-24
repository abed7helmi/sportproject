package org.sid.notificationchannelmanagerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoachDTO {

    private Long idCoach;
    private String CoachName;
    private Long idMember;
    private String memberFirstName;
    private Double cardiacFrequency;
    private Integer memberAge;

}