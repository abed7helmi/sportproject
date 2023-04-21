package org.sid.emergencynotificationagentservice.services;


import org.eclipse.paho.client.mqttv3.*;
import org.sid.emergencynotificationagentservice.dto.CoachDTO;
import org.sid.emergencynotificationagentservice.event.EventHrSensor;
import org.sid.emergencynotificationagentservice.entities.Coach;
import org.sid.emergencynotificationagentservice.entities.Member;
import org.sid.emergencynotificationagentservice.event.CoachConnectedEvent;
import org.sid.emergencynotificationagentservice.repo.CoachRepository;
import org.sid.emergencynotificationagentservice.repo.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@Service
public class CoachEventService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CoachRepository coachRepository ;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StreamBridge streamBridge;

    Logger logger = LoggerFactory.getLogger(CoachEventService.class);

    public void coachCnxEvenSourcingHandler(CoachConnectedEvent SaveCoachDTO){

        try {
            redisTemplate.opsForList().rightPush("connectedCoach",SaveCoachDTO.getCoachId());
            logger.info("Coach Ajouté dans le cache");
            this.checkNotifiInCache(SaveCoachDTO);
        }catch (Exception e){
            logger.info("Erreur Ajout Coach dans le cache");
        }

    }




    public void send(CoachDTO coachDTO) throws MqttException {

        streamBridge.send("event_notification_coach",coachDTO);
        logger.info("event envoye vers le topic event_notification_coach");

        String publisherId = UUID.randomUUID().toString();
        IMqttClient publisher = new MqttClient("tcp://broker.hivemq.com:1883",publisherId);


        if ( !publisher.isConnected()) {

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            publisher.connect(options);


            String topic = "testtopic/coach";
            String payload = "Bonjour "+coachDTO.getCoachFirstName()+" (id:"+coachDTO.getIdCoach()+", le membre d'id "+ coachDTO.getIdMember() + " est en danger";

            byte[] encodedPayload = new byte[0];
            try {
                encodedPayload = payload.getBytes("UTF-8");
                MqttMessage message = new MqttMessage(encodedPayload);
                publisher.publish(topic, message);
                logger.info("coach notified " + coachDTO.getCoachFirstName());

            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

        }


    }


    public void hrEmergencyEvenSourcingHandler(EventHrSensor eventHrSensor){

        Member m1=memberRepository.findById(eventHrSensor.getIdmember()).get();
        Coach c1=coachRepository.findById(m1.getCoach().getIdCoach()).get();
        CoachDTO coachDTO = new CoachDTO(c1.getIdCoach(), c1.getCoachFirstName(),eventHrSensor.getIdmember(),eventHrSensor.getDate(),eventHrSensor.getCardiacFrequency());

        if(this.coachConnected(c1)){
            try {
                logger.info("connected coach : "+ coachDTO.getIdCoach());
                this.send(coachDTO);

            } catch (MqttException e) {
                throw new RuntimeException(e);
            }
        }else {
            this.cacheCoach(coachDTO);
        }

    }
    public void checkNotifiInCache(CoachConnectedEvent coach){

        if(redisTemplate.hasKey(coach.getCoachId().toString())){
            CoachDTO coachDTO = (CoachDTO) redisTemplate.opsForValue().get(coach.getCoachId().toString());
            logger.info("une notif trouvée pour le coach connecté : "+ coach.getCoachId().toString());
            try {
                this.send(coachDTO);
                redisTemplate.delete(coach.getCoachId().toString());
            } catch (MqttException e) {
                throw new RuntimeException(e);
            }

        }

    }


    public void cacheCoach(CoachDTO coachDTO){
        redisTemplate.opsForValue().set(coachDTO.getIdCoach().toString(), coachDTO);
        logger.info("le coach id:"+coachDTO.getIdCoach().toString()+" n'est pas connecté , msg transféré dans le cache");
    }

    public boolean coachConnected(Coach c1){


        List<Object> list = redisTemplate.opsForList().range("connectedCoach", 0, -1);
        return list.stream().anyMatch(o -> o.equals(c1.getIdCoach().intValue()));

    }


}
