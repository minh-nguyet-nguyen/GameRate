package com.minh.project.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.minh.project.model.GameView;

@Service
public class GameManagerImpl implements GameManager{
	
	Logger logger = LoggerFactory.getLogger(GameManagerImpl.class);
	private final String RESOURCE_URI = "/game/";
	
	@Value("${service.url}")
	private String serviceBaseUrl; 
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<GameView> findById(Long id) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		ResponseEntity<GameView> responseEntity = restTemplate.getForEntity(requestUri+"/{id}", GameView.class, Long.toString(id));
		return responseEntity;
	}

	@Override
	public ResponseEntity<GameView> findByTitle(String title) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		ResponseEntity<GameView> responseEntity = restTemplate.getForEntity(requestUri+"?title={title}", GameView.class, title);
		return responseEntity;
	}

	@Override
	public ResponseEntity<GameView> saveGame(GameView game) {
		if (game == null) {
			throw new RuntimeException("No game to save");
		}
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		
		return restTemplate.postForEntity(requestUri, game, GameView.class);
	}

	@Override
	public ResponseEntity<GameView> updateGame(GameView game) {
		if (game == null || game.getId() == null) {
			throw new RuntimeException("No game to update");
		}
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		
		return restTemplate.exchange(requestUri + "/{id}",
				HttpMethod.PUT,
				new HttpEntity<>(game),
				GameView.class,
				Long.toString(game.getId()));
	}

	@Override
	public void deleteGameById(Long id) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		restTemplate.delete(requestUri + "/{id}", Long.toString(id));
	}

	@Override
	public ResponseEntity<GameView[]> findAllGames() {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		ResponseEntity<GameView[]> responseEntity = restTemplate.getForEntity(requestUri, GameView[].class);
		return responseEntity;
	}
}