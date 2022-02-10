package com.minh.project.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.minh.project.model.Game;

@Service("gameService")
public class GameServiceImpl implements GameService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Game> games;
	
	static {
		games = populateDummyGames();
	}

	public List<Game> findAllGames() {
		return games;
	}
	
	public Game findById(Long id) {
		for(Game game : games){
			if(game.getId() == id){
				return game;
			}
		}
		return null;
	}
	
	public Game findByTitle(String title) {
		for(Game game : games){
			if(game.getTitle().equalsIgnoreCase(title)){
				return game;
			}
		}
		return null;
	}
	
	public void saveGame(Game game) {
		game.setId(counter.incrementAndGet());
		games.add(game);
	}

	public void updateGame(Game game) {
		int index = games.indexOf(game);
		games.set(index, game);
	}

	public void deleteGameById(Long id) {
		for (Iterator<Game> iterator = games.iterator(); iterator.hasNext(); ) {
		    Game game = iterator.next();
		    if (game.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isGameExist(Game game) {
		return findByTitle(game.getTitle())!=null;
	}
	
	public void deleteAllGames(){
		games.clear();
	}

	private static List<Game> populateDummyGames(){
		List<Game> games = new ArrayList<Game>();
		games.add(new Game(counter.incrementAndGet(), "Hollow Knight", "great music, fun combat, intricate lore", 5));
		games.add(new Game(counter.incrementAndGet(), "Gris", "good atmosphere", 3));
		games.add(new Game(counter.incrementAndGet(), "RiME", "touching story", 4));
		return games;
	}

}