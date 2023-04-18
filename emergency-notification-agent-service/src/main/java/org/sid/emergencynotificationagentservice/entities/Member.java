package org.sid.emergencynotificationagentservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMember;
    private String memberFirstName;
    private String sexe;
    private int age;
    private Boolean memberSubscription;
    @ManyToOne()
    private Coach coach;
}
