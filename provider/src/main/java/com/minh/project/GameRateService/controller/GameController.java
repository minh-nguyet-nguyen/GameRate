package com.minh.project.GameRateService.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.minh.project.GameRateService.model.GameView;
import com.minh.project.GameRateService.service.GameService;
 
@RestController
@RequestMapping(value = "/game/")
public class GameController {
	
    @Autowired
    private GameService gameService;
 
    
    //-------------------Retrieve All Games--------------------------------------------------------
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAllGames() {
    	List<GameView> gamesList = 
				gameService.findAllGames()
				.stream().map(GameView :: convert)
				.collect(Collectors.toList());
        if(gamesList.isEmpty()){
            return new ResponseEntity<List<GameView>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<GameView>>(gamesList, HttpStatus.OK);
    }
 
 
    
    //-------------------Retrieve Single Game--------------------------------------------------------
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGame(@PathVariable("id") Long id) {
        GameView game = GameView.convert(gameService.findById(id));
        if (game == null) {
            return new ResponseEntity<>("No Game With ID: " + id + " Found", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
      
     
    //-------------------Create a Game--------------------------------------------------------
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createGame(@RequestBody GameView game) {
        if (gameService.isGameExist(game)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        gameService.saveGame(game);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
 
    
     
    //------------------- Update a Game --------------------------------------------------------
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateGame(@PathVariable("id") Long id, @RequestBody GameView game) {
    	gameService.updateGame(game);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
    
    //------------------- Delete a Game --------------------------------------------------------
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGame(@PathVariable("id") Long id) {
    	gameService.deleteGameById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 
}
