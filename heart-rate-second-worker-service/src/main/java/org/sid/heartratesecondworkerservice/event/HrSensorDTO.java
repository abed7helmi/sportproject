package org.sid.heartratesecondworkerservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class HrSensorDTO {
    @Id
    Long idHrSensor ;
    String state;
    int age;
    double cardiacFrequency;
    Date date;
    Long  idmember;

    //Long memberId;
}
