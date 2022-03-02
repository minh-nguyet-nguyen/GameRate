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

import com.minh.project.manager.GameManager;
import com.minh.project.model.GameView;
 
@RestController
@RequestMapping(value = "/game/")
public class GameRestController {
	
	Logger logger = LoggerFactory.getLogger(GameRestController.class);
	
    @Autowired
    private GameManager gameManager;
 
    
    //-------------------Retrieve All Games--------------------------------------------------------
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAllGames() {
        return gameManager.findAllGames();
    }
 
 
    
    //-------------------Retrieve Single Game--------------------------------------------------------
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGame(@PathVariable("id") Long id) {
        logger.debug("Fetching Game with id " + id);
        
        return gameManager.findById(id);
    }
 
     
    //-------------------Create a Game--------------------------------------------------------
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createGame(@RequestBody GameView game) {
        logger.debug("Creating Game " + game.getTitle());
 
        return gameManager.saveGame(game);
    }
 
     
    //------------------- Update a Game --------------------------------------------------------
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateGame(@PathVariable("id") Long id, @RequestBody GameView game) {
        logger.debug("Updating Game " + id);
         
        return gameManager.updateGame(game);
    }
 
    
    //------------------- Delete a Game --------------------------------------------------------
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGame(@PathVariable("id") Long id) {
        logger.debug("Fetching & Deleting Game with id " + id);
 
        gameManager.deleteGameById(id);
        return new ResponseEntity<GameView>(HttpStatus.NO_CONTENT);
    }
 
}