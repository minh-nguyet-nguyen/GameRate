package com.minh.project.service;

import java.util.List;

import com.minh.project.model.Game;

public interface GameService {
	
	Game findById(long id);
	
	Game findByTitle(String title);
	
	void saveGame(Game user);
	
	void updateGame(Game user);
	
	void deleteGameById(long id);

	List<Game> findAllGames(); 
	
	void deleteAllGames();
	
	public boolean isGameExist(Game user);	
}