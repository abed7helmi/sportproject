package org.sid.heartratecollectorservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class HrSensorDTO {

    Long idHrSensor ;
    String state;
    double cardiacFrequency;
    Date date;
    Long  idmember;

}
