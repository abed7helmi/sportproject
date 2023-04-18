package org.sid.heartratecollectorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HeartRateCollectorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeartRateCollectorServiceApplication.class, args);
	}

}
