package org.sid.emergencynotificationagentservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.emergencynotificationagentservice.dto.HrSensorDTO;
import org.sid.emergencynotificationagentservice.entities.Member;
import org.sid.emergencynotificationagentservice.repo.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HeartRateService {

    private final MemberRepository memberRepository;

    private kafkaConfigProducer kafkaConfigProducer;

    public boolean checkSubscrib (Member m){

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
        Member member= memberRepository.findById(hrSensorDTO.getIdmember())
                .orElseThrow(() -> new RuntimeException( "this id " + hrSensorDTO. getIdmember() + "doesn't exist"));
        if(checkSubscrib(member)){
            log.info("USER "+ member.getMemberFirstName()+" SUBSCRIBED");
            if(checkEmergency(member,hrSensorDTO)){
                kafkaConfigProducer.sendEmergencyCase(member);
                log.warn("CONTACT EMERGENCY SERVICE FOR USER  "+ member);
                hrSensorDTO.setState("NOT GOOD");
            }
        }else {
            log.warn("USER "+ member.getMemberFirstName()+" NOT SUBSCRIBED");
        }

    }


}
