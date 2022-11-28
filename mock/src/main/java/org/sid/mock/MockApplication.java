package org.sid.mock;

import org.sid.mock.models.Coach;
import org.sid.mock.models.Member;
import org.sid.mock.repositories.CoachRepository;
import org.sid.mock.repositories.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MockApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockApplication.class, args);
	}

	/*@Bean
	CommandLineRunner start(CoachRepository coachRepository, MemberRepository memberRepository) {
		return args -> {
			coachRepository.saveAll(List.of(
					Coach.builder().idCoach(null).coachFirstName("Helmi").members(null).build()
					//Member.builder().memberFirstName("Ahmed").memberSubscription(true).build()
					//Customer.builder().name("IMane").email("imane@gmail.com").build()
			));
			coachRepository.findAll().forEach(System.out::println);
		};



	}*/

}
