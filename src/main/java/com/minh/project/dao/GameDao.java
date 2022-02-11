package com.minh.project.dao;

import java.util.List;

import com.minh.project.model.Game;

public interface GameDao {
	
	Game findById(Long id);
	
	Game findByTitle(String title);
	
	void saveGame(Game game);
	
	void updateGame(Game game);
	
	void deleteGameById(Long id);

	List<Game> findAllGames();
	
	public boolean isGameExist(Game game);	
}