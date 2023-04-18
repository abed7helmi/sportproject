package org.sid.heartratecollectorservice.dto;

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

    Long idHrSensor ;
    String state;
    Double cardiacFrequency;
    Long  idMember;
}
