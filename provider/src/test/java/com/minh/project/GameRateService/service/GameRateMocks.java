package com.minh.project.GameRateService.service;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.minh.project.GameRateService.repository.GameRepository;

@TestConfiguration
public class GameRateMocks {
	@Bean
	@Primary
	public GameRepository themeRepository() {
		return Mockito.mock(GameRepository.class);
	}
}
