package org.sid.emergencynotificationagentservice.event;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class CoachConnectedEvent {

    private Long id;
    private LocalDateTime timestamp;
    private Long coachId;
    private String coachFirstName;
    private Boolean connected;
}
