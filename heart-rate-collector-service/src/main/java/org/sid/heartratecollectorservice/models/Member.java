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
    Boolean memberSubscription;
}
