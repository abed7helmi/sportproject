package org.sid.notificationchannelmanagerservice.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.notificationchannelmanagerservice.dto.CoachDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationEventService {

    private final RedisTemplate redisTemplate;
    private final KafkaTemplate kafkaTemplate;

    @KafkaListener(topics = "emergency-data-collector",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void handleEmergencyMessage(CoachDTO coachDTO) {
        if(coachDTO!=null){
            kafkaTemplate.send("notification-channel", coachDTO);
            redisTemplate.opsForValue().set(String.valueOf("session"), coachDTO.getCoachName());
        }else {
            log.info(String.format("None emergency retrieve"));
        }
    }

    public boolean coachConnected(String coachName){
        String key = "session";
        coachName = (String) redisTemplate.opsForValue().get(key);
        if (coachName != null){
            return true;
        }
        return false;
    }



}
