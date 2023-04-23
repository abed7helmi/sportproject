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
    private String name;
    private String sexe;
    private Integer age;
    private Boolean memberSubscription;
    @ManyToOne
    private Coach coach;
}
