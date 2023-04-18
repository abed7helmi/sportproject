package org.sid.notificationchannelmanagerservice.services;


import org.sid.notificationchannelmanagerservice.dto.CoachDTO;
import org.sid.notificationchannelmanagerservice.dto.SaveCoachDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {



    Logger logger = LoggerFactory.getLogger(NotificationService.class);

    /*public void sendOrCache(CoachDTO coachDTO){
        if(this.coachConnected(coachDTO)){
            try {
                logger.info("connected coach : "+ coachDTO.getCoachFirstName());
                this.send(coachDTO);

            } catch (MqttException e) {
                throw new RuntimeException(e);
            }
        }else {
            this.cacheCoach(coachDTO);
        }

    }
    // TODO : on peut pas stocker plusieurs notifs pour le meme coach , si une nouvelle notif arrive elle ecrase celle qui existe deja : à ameliorer
    public void cacheCoach(CoachDTO coachDTO){
        redisTemplate.opsForValue().set(coachDTO.getIdCoach().toString(), coachDTO);
        logger.info("le coach id:"+coachDTO.getIdCoach().toString()+" name : "+coachDTO.getCoachFirstName().toString()+" n'est pas connecté , msg transféré dans le cache");
    }


    public void subscribeCoachCache(SaveCoachDTO SaveCoachDTO){

        try {
            redisTemplate.opsForList().rightPush("connectedCoach",SaveCoachDTO.getIdCoach());
            logger.info("Coach Ajouté dans le cache");
            this.checkNotifiInCache(SaveCoachDTO);
        }catch (Exception e){
            logger.info("Erreur Ajout Coach dans le cache");
        }

    }


    public void checkNotifiInCache(SaveCoachDTO SaveCoachDTO){

        if(redisTemplate.hasKey(SaveCoachDTO.getIdCoach().toString())){
            CoachDTO coachDTO = (CoachDTO) redisTemplate.opsForValue().get(SaveCoachDTO.getIdCoach().toString());
            logger.info("une notif trouvée pour le coach connecté : "+ SaveCoachDTO.getIdCoach());
            try {
                this.send(coachDTO);
                redisTemplate.delete(SaveCoachDTO.getIdCoach().toString());
            } catch (MqttException e) {
                throw new RuntimeException(e);
            }

        }

    }


    public void UnsubscribeCoachCache(SaveCoachDTO deleteCoachDTO){

        try {
            redisTemplate.opsForList().remove("connectedCoach", 0, deleteCoachDTO.getIdCoach());
            logger.info("Coach supprimé du cache");
        }catch (Exception e){
            logger.info("Erreur sup du cache");
        }

    }


    public void send(CoachDTO coachDTO) throws MqttException {


        String publisherId = UUID.randomUUID().toString();
        IMqttClient publisher = new MqttClient("tcp://broker.hivemq.com:1883",publisherId);


        if ( !publisher.isConnected()) {

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            publisher.connect(options);


            String topic = "testtopic/coach";
            String payload = "Bonjour "+coachDTO.getCoachFirstName()+" (id:"+coachDTO.getIdCoach()+", le membre "+ coachDTO.getMemberFirstName() + " est en danger";

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


    public boolean coachConnected(CoachDTO coachDTO){


        List<Object> list = redisTemplate.opsForList().range("connectedCoach", 0, -1);
        return list.stream().anyMatch(o -> o.equals(coachDTO.getIdCoach().intValue()));

    }*/



}
