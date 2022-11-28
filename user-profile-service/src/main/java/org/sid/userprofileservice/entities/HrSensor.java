package org.sid.userprofileservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    double cardiacFrequency;
    Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Member member;

    //Long memberId;
}
