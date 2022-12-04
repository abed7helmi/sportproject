package org.sid.notificationchannelmanagerservice.services;

import org.eclipse.paho.client.mqttv3.*;
import org.sid.notificationchannelmanagerservice.dto.CoachDTO;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Service
public class NotificationService {




    public void send(CoachDTO coachDTO) throws MqttException {

        String publisherId = UUID.randomUUID().toString();
        IMqttClient publisher = new MqttClient("tcp://broker.hivemq.com:1883",publisherId);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        publisher.connect(options);


        String topic = "testtopic/coach";
        String payload = "Bonjour "+coachDTO.getCoachFirstName()+" (id:"+coachDTO.getIdCoach()+", le membre "+ coachDTO.getMemberFirstName() + " est en danger";

        byte[] encodedPayload = new byte[0];
        try {
            encodedPayload = payload.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            publisher.publish(topic, message);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
