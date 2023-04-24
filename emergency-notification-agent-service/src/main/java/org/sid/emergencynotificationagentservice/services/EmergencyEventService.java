package org.sid.emergencynotificationagentservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.emergencynotificationagentservice.dto.CoachDTO;
import org.sid.emergencynotificationagentservice.dto.HrSensorDTO;
import org.sid.emergencynotificationagentservice.entities.Member;
import org.sid.emergencynotificationagentservice.repo.MemberRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmergencyEventService {


    private final MemberRepository memberRepository;


    private final RedisTemplate redisTemplate;
    private final KafkaTemplate kafkaTemplate;



    public boolean checkMemberSubscribe(Member m){
        if (m.getMemberSubscription()){
            return true;
        }else {
            return false;
        }

    }

    public boolean checkEmergency (Member member,HrSensorDTO hrSensorDTO){
        // La méthode la plus fiable que nous vous conseillons : FC max = 207 – 0,7 x âge
        Double cardiacMax = 207 - 0.7*member.getAge();
        return hrSensorDTO.getCardiacFrequency()>cardiacMax ? true : false;

    }

    public void checkHeartRateEmergency (HrSensorDTO hrSensorDTO){
        Member member= memberRepository.findById(hrSensorDTO.getIdMember())
                .orElseThrow(() -> new RuntimeException( "this id " + hrSensorDTO. getIdMember() + "doesn't exist"));
        CoachDTO coachDTO = new CoachDTO(member.getCoach().getId(),member.getCoach().getNom(), member.getIdMember(), member.getName(), hrSensorDTO.getCardiacFrequency(), member.getAge());
        log.info(String.format("Coach Info -> %s", coachDTO));
        if(checkMemberSubscribe(member)){
            log.info("USER "+ member.getName()+" IS SUBSCRIBED");
            if(checkEmergency(member,hrSensorDTO)){
                kafkaTemplate.send("emergency-data-collector", coachDTO);
                log.warn(String.format("Message sent : in Emergency Topic -> %s", coachDTO));
                hrSensorDTO.setState("NOT GOOD");
                redisTemplate.opsForValue().set(String.valueOf(member.getIdMember()), coachDTO);
                log.warn(String.format("Message sent -> %s", member.getIdMember()));
            }
        }else {
            log.warn("USER "+ member.getName()+" NOT SUBSCRIBED");
        }

    }


}
