package org.sid.notificationchannelmanagerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoachDTO implements Serializable {
    Long idCoach;
    String coachFirstName;
    Long idMember;
    String memberFirstName;
    double cardiacFrequency;
}
