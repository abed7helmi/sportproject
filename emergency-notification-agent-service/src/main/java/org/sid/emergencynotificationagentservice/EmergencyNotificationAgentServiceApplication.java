package org.sid.emergencynotificationagentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableCaching
@EnableKafka
public class EmergencyNotificationAgentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmergencyNotificationAgentServiceApplication.class, args);
	}

}
