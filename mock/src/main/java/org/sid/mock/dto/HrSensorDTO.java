package org.sid.mock.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.mock.models.Member;

import javax.persistence.*;
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
    Long  idMember;

}
