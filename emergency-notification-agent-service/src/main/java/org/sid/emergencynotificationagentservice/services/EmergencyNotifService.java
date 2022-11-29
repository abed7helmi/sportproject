package org.sid.emergencynotificationagentservice.services;

import org.sid.emergencynotificationagentservice.dto.CoachDTO;
import org.sid.emergencynotificationagentservice.dto.HrSensorDTO;
import org.sid.emergencynotificationagentservice.entities.Coach;
import org.sid.emergencynotificationagentservice.entities.Member;
import org.sid.emergencynotificationagentservice.repo.CoachRepository;
import org.sid.emergencynotificationagentservice.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmergencyNotifService {

    @Autowired
    CoachRepository coachRepository;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    Producer producer;

    public void notifVerif (HrSensorDTO hrSensorDTO){

        Member m1=memberRepository.findById(hrSensorDTO.getIdmember()).get();
        Coach c1=coachRepository.findById(m1.getCoach().getIdCoach()).get();
        CoachDTO coachDTO= new CoachDTO(c1.getIdCoach(), c1.getCoachFirstName(), m1.getIdMember(), m1.getMemberFirstName());
        producer.send(coachDTO);
        System.out.print(c1.getIdCoach());

    }


}
