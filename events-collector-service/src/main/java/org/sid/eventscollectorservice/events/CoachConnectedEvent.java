package org.sid.eventscollectorservice.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class CoachConnectedEvent {

    private Long id;
    private LocalDateTime timestamp;
    private Long coachId;
    private String coachFirstName;
    private Boolean connected;
}
