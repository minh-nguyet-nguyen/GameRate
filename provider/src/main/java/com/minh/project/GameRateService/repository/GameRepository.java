package com.minh.project.GameRateService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minh.project.GameRateService.entity.GameImpl;

public interface GameRepository extends JpaRepository<GameImpl, Long> {
	public GameImpl findByTitle(String title);
}
