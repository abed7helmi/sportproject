package org.sid.heartratecollectorservice.models;

import lombok.*;

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
