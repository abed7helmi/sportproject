package org.sid.heartrateworkerservice.models;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HrSensor {
    Long idHrSensor ;
    String state;
    double cardiacFrequency;
    Date date;
    //private Member member;
    Long memberId;
}
