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
public class HrSensorDTO {

    private Long idHrSensor ;
    private String state;
    private Double cardiacFrequency;
    private Long  idMember;
}
