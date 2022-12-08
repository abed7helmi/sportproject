package org.sid.userprofileservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CoachProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCoach;
    private String coachName;
    private Integer userAssigned;
    private Boolean coachSession;
}
