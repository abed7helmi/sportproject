package org.sid.heartratesecondworkerservice.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Member {
    Long idMember;
    String memberFirstName;
    String sexe;
    int age;
    /*String memberLastName;
    String memberEmail;
    String memberPhone;*/
    Boolean memberSubscription;
}
