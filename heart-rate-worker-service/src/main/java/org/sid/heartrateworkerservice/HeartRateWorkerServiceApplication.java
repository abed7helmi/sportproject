package org.sid.heartrateworkerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HeartRateWorkerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeartRateWorkerServiceApplication.class, args);
	}

}
