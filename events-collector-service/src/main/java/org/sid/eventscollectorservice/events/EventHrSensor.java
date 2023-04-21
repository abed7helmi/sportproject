package org.sid.eventscollectorservice.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventHrSensor {
    Long idHrSensor ;
    String state;
    int age;
    double cardiacFrequency;
    Date date;
    Long  idmember;

}
