package org.sid.heartratesecondworkerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HeartRateSecondWorkerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeartRateSecondWorkerServiceApplication.class, args);
	}

}
