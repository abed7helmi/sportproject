package org.sid.heartratecollectorservice.services;

import lombok.extern.slf4j.Slf4j;
import org.sid.heartratecollectorservice.dto.HrSensorDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public record HearRateServiceEventProducer(KafkaTemplate<String, HrSensorDTO> kafkaTemplate) {
    public void sendHeartRateMessage(HrSensorDTO data) {
        Message<HrSensorDTO> message = MessageBuilder.withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "hr-data-collector")
                .build();
        kafkaTemplate.send(message);
        log.info(String.format("Event heart sent -> %s", data));
    }

}
