package org.sid.notificationchannelmanagerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NotificationChannelManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationChannelManagerServiceApplication.class, args);
	}

}
