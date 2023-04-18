package org.sid.heartratecollectorservice.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.heartratecollectorservice.dto.HrSensorDTO;
import org.sid.heartratecollectorservice.services.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class KafkaCollectorController {

    private final KafkaProducer kafkaProducer;


    @PostMapping("/hr")
    public ResponseEntity<String> publishHeartRate(@RequestBody HrSensorDTO hrSensorDTO){
        kafkaProducer.sendHeartRateMessage(hrSensorDTO);
        return ResponseEntity.ok("Message heart Rate well publish to topic");
    }
}
