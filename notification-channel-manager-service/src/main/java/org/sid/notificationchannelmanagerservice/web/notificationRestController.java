package org.sid.notificationchannelmanagerservice.web;



import org.sid.notificationchannelmanagerservice.dto.SaveCoachDTO;
import org.sid.notificationchannelmanagerservice.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class notificationRestController {

    @Autowired
    NotificationService notificationService;

    Logger logger = LoggerFactory.getLogger(notificationRestController.class);
    @PostMapping(value ="/subscribeCoach",consumes="application/json")
    public void subscribeCoach(@RequestBody SaveCoachDTO coach) {
        logger.info("Ressive from gateway , COACH inscrit : " + coach );
        notificationService.subscribeCoachCache(coach);


    }

    @PostMapping(value ="/unsubscribeCoach",consumes="application/json")
    public void unsubscribeCoach(@RequestBody SaveCoachDTO coach) {
        logger.info("Ressive from gateway , COACH a quitté la salle : " + coach );
        notificationService.UnsubscribeCoachCache(coach);


    }


}
