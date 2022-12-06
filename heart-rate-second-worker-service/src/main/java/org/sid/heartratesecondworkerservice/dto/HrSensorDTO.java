package org.sid.heartratesecondworkerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class HrSensorDTO {

    Long idHrSensor ;
    String state;
    double cardiacFrequency;
    Date date;
    Long  idmember;

    //Long memberId;
}
