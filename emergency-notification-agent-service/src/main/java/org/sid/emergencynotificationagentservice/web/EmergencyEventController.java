package org.sid.emergencynotificationagentservice.web;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.emergencynotificationagentservice.dto.HrSensorDTO;
import org.sid.emergencynotificationagentservice.services.EmergencyEventService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emergency")
@Slf4j
public class EmergencyEventController {

    private final EmergencyEventService emergencyEventService;

    @GetMapping("/checkEmergencyTopic")
    @KafkaListener(topics = "hr-data-collector", groupId = "groupId")
    public void checkHeartRate(HrSensorDTO heartRateData) {
        log.info(String.format("Message sent -> %s", heartRateData));
        emergencyEventService.checkHeartRateEmergency(heartRateData);
    }
}
