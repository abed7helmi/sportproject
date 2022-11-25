package org.sid.mock.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    Long idMember;
    String memberFirstName;
    String memberLastName;
    String memberEmail;
    String memberPhone;
    Boolean memberSubscription;
    private List<HrSensor> Hrs= new ArrayList<>();
}
