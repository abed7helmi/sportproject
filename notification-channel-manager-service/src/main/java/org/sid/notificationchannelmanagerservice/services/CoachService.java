package org.sid.notificationchannelmanagerservice.services;

import org.sid.notificationchannelmanagerservice.dto.SaveCoachDTO;
import org.sid.notificationchannelmanagerservice.entities.Coach;
import org.sid.notificationchannelmanagerservice.event.CoachConnectedEvent;
import org.sid.notificationchannelmanagerservice.event.CoachConnectedEventRepository;
import org.sid.notificationchannelmanagerservice.repo.CoachRestClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CoachService {


    @Autowired
    private CoachRestClientService coachRestClientService;

    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    private CoachConnectedEventRepository coachConnectedEventRepository ;
    Logger logger = LoggerFactory.getLogger(NotificationService.class);


    public void subscribeCoachCommandHandler(SaveCoachDTO coachDTO){
        Coach coach = coachRestClientService.coachById(coachDTO.getIdCoach());
        if (coach != null) {
            this.subscribeCoachEvenSourcingHandler(coach,coachDTO.getIdCoach(),true);
        }

    }

    public void UnsubscribeCoachCommandHandler(SaveCoachDTO coachDTO){
        Coach coach = coachRestClientService.coachById(coachDTO.getIdCoach());
        if (coach != null) {
            this.subscribeCoachEvenSourcingHandler(coach,coachDTO.getIdCoach(),false);
        }

    }



    public void subscribeCoachEvenSourcingHandler(Coach coach,Long coachId,boolean sub){
        CoachConnectedEvent coachConnectedEvent=new CoachConnectedEvent(null, LocalDateTime.now(),coachId,coach.getCoachFirstName(),sub);
        coachConnectedEventRepository.save(coachConnectedEvent);
        streamBridge.send("event_coach_cnx",coachConnectedEvent);
        logger.info("envoyé vers le topic event_coach_cnx " + coachConnectedEvent);


    }

}
