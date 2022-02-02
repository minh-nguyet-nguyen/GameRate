package com.minh.project.controller;
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.minh.project.model.Game;
import com.minh.project.service.GameService;
 
@RestController
@RequestMapping(value = "/game/")
public class GameRestController {
	
	Logger logger = LoggerFactory.getLogger(GameRestController.class);
	
    @Autowired
    private GameService gameService;
 
    
    //-------------------Retrieve All Games--------------------------------------------------------
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAllGames() {
        List<Game> games = gameService.findAllGames();
        if(games.isEmpty()){
            return new ResponseEntity<List<Game>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
    }
 
 
    
    //-------------------Retrieve Single Game--------------------------------------------------------
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGame(@PathVariable("id") long id) {
        logger.debug("Fetching Game with id " + id);
        Game game = gameService.findById(id);
        if (game == null) {
        	logger.debug("Game with id " + id + " not found");
            return new ResponseEntity<>("No Game With ID: " + id + " Found", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Game--------------------------------------------------------
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createGame(@RequestBody Game game) {
        logger.debug("Creating Game " + game.getTitle());
 
        if (gameService.isGameExist(game)) {
            logger.debug("A Game with title " + game.getTitle() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        gameService.saveGame(game);
        return new ResponseEntity<Game>(game, HttpStatus.CREATED);
    }
 
    
     
    //------------------- Update a Game --------------------------------------------------------
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateGame(@PathVariable("id") Long id, @RequestBody Game game) {
        logger.debug("Updating Game " + id);
         
        Game currentGame = gameService.findById(id);
         
        if (currentGame==null) {
            logger.debug("Game with id " + id + " not found");
            return new ResponseEntity<>("No Game With ID: " + id + " Found", HttpStatus.NO_CONTENT);
        }
 
        currentGame.setTitle(game.getTitle());
        currentGame.setComment(game.getComment());
        currentGame.setRating(game.getRating());
         
        gameService.updateGame(currentGame);
        return new ResponseEntity<Game>(currentGame, HttpStatus.OK);
    }
 
    
    
    //------------------- Delete a Game --------------------------------------------------------
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGame(@PathVariable("id") long id) {
        logger.debug("Fetching & Deleting Game with id " + id);
 
        Game game = gameService.findById(id);
        if (game == null) {
            logger.debug("Unable to delete. Game with id " + id + " not found");
            return new ResponseEntity<>("No Game With ID: " + id + " Found", HttpStatus.NO_CONTENT);
        }
 
        gameService.deleteGameById(id);
        return new ResponseEntity<Game>(HttpStatus.NO_CONTENT);
    }
 
     
    
    //------------------- Delete All Games --------------------------------------------------------
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllGames() {
        logger.debug("Deleting All Games");
 
        gameService.deleteAllGames();
        return new ResponseEntity<Game>(HttpStatus.NO_CONTENT);
    }
 
}