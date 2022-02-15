package com.minh.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minh.project.dao.GameDao;
import com.minh.project.model.Game;

@Service
@Transactional
public class GameServiceImpl implements GameService{
	
	@Autowired
	private GameDao gameDao;

	public List<Game> findAllGames() {
		return gameDao.findAllGames();
	}
	
	public Game findById(Long id) {
		return gameDao.findById(id);
	}
	
	public Game findByTitle(String title) {
		return gameDao.findByTitle(title);
	}
	
	public void saveGame(Game game) {
		gameDao.saveGame(game);
	}

	public void updateGame(Game game) {
		gameDao.updateGame(game);
	}

	public void deleteGameById(Long id) {
		gameDao.deleteGameById(id);
	}

	public boolean isGameExist(Game game) {
		return gameDao.isGameExist(game);
	}
}