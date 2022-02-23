package com.minh.project.service;

import org.springframework.http.ResponseEntity;

import com.minh.project.model.GameView;

public interface GameService {
	
	ResponseEntity<GameView> findById(Long id);
	
	ResponseEntity<GameView> findByTitle(String title);
	
	ResponseEntity<GameView> saveGame(GameView game);
	
	ResponseEntity<GameView> updateGame(GameView game);
	
	void deleteGameById(Long id);

	ResponseEntity<GameView[]> findAllGames();
}