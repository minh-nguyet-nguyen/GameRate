package com.minh.project.GameRateService.service;

import java.util.List;

import com.minh.project.GameRateService.api.Game;

public interface GameService {
	
	Game findById(Long id);
	
	Game findByTitle(String title);
	
	Game saveGame(Game game);
	
	Game updateGame(Game game);
	
	void deleteGameById(Long id);

	List<Game> findAllGames();
	
	boolean isGameExist(Game game);	
}
