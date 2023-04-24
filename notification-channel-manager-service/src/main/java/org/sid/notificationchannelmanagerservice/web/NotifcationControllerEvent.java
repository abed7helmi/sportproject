package org.sid.notificationchannelmanagerservice.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.sid.notificationchannelmanagerservice.dto.CoachDTO;
import org.sid.notificationchannelmanagerservice.services.NotificationEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class NotifcationControllerEvent {

    private final NotificationEventService notificationEventService;

    @KafkaListener(topics = "notification-channel",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void mqttConnection(CoachDTO coachDTO) throws MqttException {
        notificationEventService.coachMqtt(coachDTO);
        log.info(" Handling message received in Notification -topic");
    }


}
