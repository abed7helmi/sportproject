package org.sid.heartratecollectorservice.models;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class Member {

    Long idMember;
    String memberFirstName;
    /*String memberLastName;
    String memberEmail;
    String memberPhone;*/
    Boolean memberSubscription;


    private List<HrSensor> Hrs= new ArrayList<>();

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Coach coach;
}
