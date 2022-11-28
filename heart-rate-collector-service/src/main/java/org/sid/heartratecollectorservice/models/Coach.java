package org.sid.heartratecollectorservice.models;

import java.util.ArrayList;
import java.util.List;

public class Coach {

    Long idCoach;
    String coachFirstName;
    /*String coachLastName;
    String coachEmail;
    String coachPhone;*/


    //@JsonIgnore
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Member> members= new ArrayList<>();
}
