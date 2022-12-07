package org.sid.notificationchannelmanagerservice.services;

import org.eclipse.paho.client.mqttv3.*;
import org.sid.notificationchannelmanagerservice.dto.CoachDTO;
import org.sid.notificationchannelmanagerservice.web.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class NotificationService {

    @Autowired
    private RedisTemplate redisTemplate;

    Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private static final String KEY ="COACH";

    //private boolean clientConnected = true;


    public void sendOrCache(CoachDTO coachDTO){
        if(this.coachConnected(coachDTO)){
            try {
                this.send(coachDTO);
                logger.info("coach notified");
            } catch (MqttException e) {
                throw new RuntimeException(e);
            }
        }else {
            this.cacheCoach(coachDTO);
        }

    }

    public void cacheCoach(CoachDTO coachDTO){
        redisTemplate.opsForHash().put(KEY,coachDTO.getIdMember().toString(),coachDTO);

        //List<CoachDTO> coachDTOList=redisTemplate.opsForHash().values(KEY);

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
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

        }


    }

    // TODO : cette fonction doit savoir si le coach est connecté (inscript au TOPIC du mqtt BROKER) ou pas
    public boolean coachConnected(CoachDTO coachDTO){
        return true;
        //return false;
    }


    // TODO : cette fonction doit s'excuter automatiquement lorsque un nouveau coach se s'inscrit au TOPIC
    // TODO : elle doit verifier si il y a des messages qui appartiennent au coach dans le cache et les envoyés.
    public boolean conectCoach(CoachDTO coachDTO){
        return true;
    }


}
