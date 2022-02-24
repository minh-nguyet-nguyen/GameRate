package com.minh.project.GameRateService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.minh.project.GameRateService.repository")
@EntityScan("com.minh.project.GameRateService.entity")
public class GameRateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameRateServiceApplication.class, args);
	}
}
