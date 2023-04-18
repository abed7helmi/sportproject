package org.sid.emergencynotificationagentservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.emergencynotificationagentservice.dto.HrSensorDTO;
import org.sid.emergencynotificationagentservice.entities.Member;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class kafkaConfigProducer {

    private final KafkaTemplate<String, Member> kafkaTemplate;

    public void sendEmergencyCase(Member data) {
        Message<Member> message = MessageBuilder.withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "emergency-data-collector")
                .build();
        kafkaTemplate.send(message);
        log.info(String.format("Message sent -> %s", data));
    }
}
