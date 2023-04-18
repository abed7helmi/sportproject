package org.sid.notificationchannelmanagerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveCoachDTO {
    private Long idCoach;
    private String email;
    private String coachFirstName;
    // Add getters and setters
}
