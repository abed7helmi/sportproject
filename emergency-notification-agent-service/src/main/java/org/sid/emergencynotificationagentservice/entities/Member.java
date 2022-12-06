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
    Long idMember;
    String memberFirstName;
    String sexe;
    int age;
    /*String memberLastName;
    String memberEmail;
    String memberPhone;*/
    Boolean memberSubscription;
    /*@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<HrSensor> Hrs= new ArrayList<>();*/
    @ManyToOne()
    private Coach coach;
}
