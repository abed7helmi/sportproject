package org.sid.notificationchannelmanagerservice.web;

import lombok.extern.slf4j.Slf4j;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.sid.notificationchannelmanagerservice.dto.CoachDTO;
import org.sid.notificationchannelmanagerservice.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    @Autowired
    private NotificationService notificationService;
    Logger logger = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = "NotifQueue")
    private void receive(CoachDTO coachDTO) throws MqttException {

        logger.info("Message FROM BROKER NOTIFQUEUE "+coachDTO);
        notificationService.sendOrCache(coachDTO);



    }

}
