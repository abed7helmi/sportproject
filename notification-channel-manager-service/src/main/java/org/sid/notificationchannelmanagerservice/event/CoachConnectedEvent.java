package org.sid.notificationchannelmanagerservice.event;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@ToString
public class CoachConnectedEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime timestamp;
    private Long coachId;
    private String coachFirstName;
    private Boolean connected;
}
