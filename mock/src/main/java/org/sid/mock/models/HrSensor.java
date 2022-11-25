package org.sid.mock.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HrSensor {
    Long idHrSensor ;
    String state;
    double cardiacFrequency;
    Date date;
    //private Member member;
    Long memberId;
}
