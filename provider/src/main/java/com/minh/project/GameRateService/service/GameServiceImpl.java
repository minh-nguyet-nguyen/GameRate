package com.minh.project.GameRateService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.ImmutableList;

import com.minh.project.GameRateService.api.Game;
import com.minh.project.GameRateService.entity.GameImpl;
import com.minh.project.GameRateService.repository.GameRepository;

@Service
@Transactional
public class GameServiceImpl implements GameService {
	@Autowired
	GameRepository gameRepo;

	@Override
	public Game findById(Long id) {
		return gameRepo.getById(id);
	}

	@Override
	public Game findByTitle(String title) {
		return gameRepo.findByTitle(title);
	}

	@Override
	public Game saveGame(Game game) {
		if (game == null) {
			throw new RuntimeException("No game provided to save");
		}
		return gameRepo.save(GameImpl.convert(game));
	}

	@Override
	public Game updateGame(Game game) {
		if (game == null) {
			throw new RuntimeException("No game provided to update");
		}
		GameImpl updatedGame = GameImpl.convert(game);
		return gameRepo.save(updatedGame);
	}

	@Override
	public void deleteGameById(Long id) {
		if (id == null) {
			throw new RuntimeException("No game id provided");
		}
		gameRepo.deleteById(id);
	}

	@Override
	public List<Game> findAllGames() {
		return ImmutableList.copyOf(gameRepo.findAll());
	}

	@Override
	public boolean isGameExist(Game game) {
		Game foundGame = gameRepo.findByTitle(game.getTitle());
		if (foundGame == null) {
			return false;
		}
		return true;
	}

}
