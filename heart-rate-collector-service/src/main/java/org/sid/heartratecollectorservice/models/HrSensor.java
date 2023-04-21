package org.sid.heartratecollectorservice.models;

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
@Entity
public class HrSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idHrSensor ;
    String state;
    int age;
    double cardiacFrequency;
    Date date;
    Long  idmember;
}
