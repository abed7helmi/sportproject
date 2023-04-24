package org.sid.notificationchannelmanagerservice.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.sid.notificationchannelmanagerservice.dto.CoachDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationEventService {

    private final RedisTemplate redisTemplate;
    private final KafkaTemplate kafkaTemplate;
    private String host = "tcp://broker.hivemq.com:1883";
    private MemoryPersistence memoryPersistence = new MemoryPersistence();
    private MqttClient mqttClient;
    private String clientId = "kotlin-client";


    @KafkaListener(topics = "emergency-data-collector",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void handleEmergencyMessage(CoachDTO coachDTO) throws MqttException {
        if(coachDTO!=null){
            log.info(String.format("Message sent in : Notification topic -> %s", coachDTO));
            redisTemplate.opsForValue().set("session", coachDTO.getCoachName());
            coachMqtt(coachDTO);
            kafkaTemplate.send("notification-channel", coachDTO);
        }else {
            log.info(String.format("None emergency retrieve"));
        }
    }

    public void coachMqtt(CoachDTO coachDTO) throws MqttException {
        mqttClient = new MqttClient(host, clientId, memoryPersistence);
        MqttConnectOptions options = new MqttConnectOptions();
        try {
            mqttClient.connect(options);
            log.debug("MQTTSERVICE", "Connection is success");
            mqttClient.subscribe("session", 0);
            mqttClient.setCallback(new MqttCallback() {
                public void connectionLost(Throwable throwable) {
                    // Connection to MQTT broker lost
                }
                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                    String message = new String(mqttMessage.getPayload());
                    System.out.println("Received message from mobile: " + message);
                    // Process the message received from the Android app here

                    if(redisTemplate.hasKey(message)){
                        publishMessage(coachDTO);
                    }
                }
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    // Message delivery complete
                }
            });
        } catch (MqttException e) {
            log.debug("MQTTSERVICE", "Connection is failed");
            e.printStackTrace();

        }

    }
    private void publishMessage(CoachDTO coachDTO) throws MqttException {
        String payload = "Alerte coach "+coachDTO.getCoachName()
                + " votre utilisateur "+coachDTO.getMemberFirstName()
                + " est en danger avec un frequence cardiaque de "+ coachDTO.getCardiacFrequency();
        MqttMessage message = new MqttMessage(payload.getBytes(StandardCharsets.UTF_8));
        mqttClient.publish("danger", message);
        log.info("message reçu sur le mobile du coach :", message);

    }

}
