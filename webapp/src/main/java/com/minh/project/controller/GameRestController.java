package com.minh.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.minh.project.model.GameView;
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
        return gameService.findAllGames();
    }
 
 
    
    //-------------------Retrieve Single Game--------------------------------------------------------
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGame(@PathVariable("id") Long id) {
        logger.debug("Fetching Game with id " + id);
        
        return gameService.findById(id);
    }
 
     
    //-------------------Create a Game--------------------------------------------------------
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createGame(@RequestBody GameView game) {
        logger.debug("Creating Game " + game.getTitle());
 
        return gameService.saveGame(game);
    }
 
     
    //------------------- Update a Game --------------------------------------------------------
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateGame(@PathVariable("id") Long id, @RequestBody GameView game) {
        logger.debug("Updating Game " + id);
         
        return gameService.updateGame(game);
    }
 
    
    //------------------- Delete a Game --------------------------------------------------------
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGame(@PathVariable("id") Long id) {
        logger.debug("Fetching & Deleting Game with id " + id);
 
        gameService.deleteGameById(id);
        return new ResponseEntity<GameView>(HttpStatus.NO_CONTENT);
    }
 
}