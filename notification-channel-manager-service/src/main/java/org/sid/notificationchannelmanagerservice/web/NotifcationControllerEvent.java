package org.sid.notificationchannelmanagerservice.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.notificationchannelmanagerservice.services.NotificationEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class NotifcationControllerEvent {

    private final NotificationEventService notificationEventService;


    @PostMapping("/coachName")
    public ResponseEntity<String> checkingSession(@RequestBody String name){
        notificationEventService.coachConnected(name);
        return ResponseEntity.ok("Coach exist and can receive notification");
    }
}
