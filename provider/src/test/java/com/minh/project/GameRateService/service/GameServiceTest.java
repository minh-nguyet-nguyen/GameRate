package com.minh.project.GameRateService.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.minh.project.GameRateService.api.Game;
import com.minh.project.GameRateService.entity.GameImpl;
import com.minh.project.GameRateService.repository.GameRepository;

@ActiveProfiles("test")
@SpringBootTest(classes = { GameService.class, GameServiceImpl.class, GameRateMocks.class })
public class GameServiceTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	GameRepository gameRepository;
	
	@Autowired
	GameService gameService;
	
	@BeforeClass
	public void setup() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void givenPreSavedGame_whenSearchedUsingValidId_thenGameShouldBeFound() {
		GameImpl game = createSampleGameImpl();
		when(gameRepository.getById(anyLong())).thenReturn(game);

		Game fromService = gameService.findById(1L);
		assertEquals(GameImpl.convert(fromService), game);
		
		verify(gameRepository).getById(1L);
	}
	
	@Test
	public void givenPreSavedGame_whenSearchedUsingValidTitle_thenGameShouldBeFound() {
		GameImpl game = createSampleGameImpl();
		when(gameRepository.findByTitle(anyString())).thenReturn(game);
		
		Game fromService = gameService.findByTitle("test");
		assertEquals(GameImpl.convert(fromService), game);
	}
	
	@Test
	public void givenValidInputFields_whenSaveButtonIsClicked_thenGameShouldBeSaved() {
		GameImpl game = createSampleGameImpl();
		when(gameRepository.save(any())).thenReturn(game);
		
		Game fromService = gameService.saveGame(game);
		assertEquals(GameImpl.convert(fromService), game);

		verify(gameRepository).save(game);
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "No game provided to save")
	public void givenNullGame_whenSaveButtonIsClicked_thenErrorShouldBeThrown() {
		gameService.saveGame(null);
	}
	
	@Test
	public void givenPreSavedGame_whenGameFieldIsChangedAndSaved_thenGameShouldBeUpdated() {
		GameImpl game = createSampleGameImpl();
		GameImpl updatedGame = new GameImpl();
		updatedGame = createSampleGameImpl();
		updatedGame.setTitle(updatedGame.getTitle() + " updated");
		
		when(gameRepository.save(any())).thenReturn(game, updatedGame);
		when(gameRepository.getById(anyLong())).thenReturn(game);
		
		Game savedGame = gameService.saveGame(game);
		savedGame.setTitle(savedGame.getTitle() + " updated");
		
		Game fromService = gameService.updateGame(savedGame);
		assertNotNull(fromService);
		assertEquals(fromService.getId(), 1L);
		assertEquals(fromService.getTitle(), "test updated");
		assertEquals(fromService.getComment(), "test1");
		assertEquals(fromService.getRating(), 5);
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "No game provided to update")
	public void givenNullGame_whenAttemptingToUpdateGame_thenErrorShouldBeThrown() {
		gameService.updateGame(null);
	}
	
	@Test
	public void givenValidId_whenDeletedWithId_thenGameShouldBeDeleted() {
		doNothing().when(gameRepository).deleteById(anyLong());
		gameService.deleteGameById(1L);
		verify(gameRepository).deleteById(1L);
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "No game id provided")
	public void givenNullGame_whenAttemptingToDelete_thenErrorShouldBeThrown() {
		gameService.deleteGameById(null);
	}
	
	@Test
	public void givenPreSavedGames_whenFindAllIsCalled_thenListOfGamesIsReturned() {
		GameImpl game = createSampleGameImpl();
		List<GameImpl> gamesList = new ArrayList<GameImpl>();
		gamesList.add(game);
		when(gameRepository.save(any())).thenReturn(game);
		when(gameRepository.findAll()).thenReturn(gamesList);
		
		gameService.saveGame(game);
		List<Game> games = gameService.findAllGames();
		assertNotNull(games);
		assertEquals(games.size(), 1);
		assertEquals(games.get(0).getTitle(), "test");
	}
	
	@Test
	public void givenPreSavedGame_whenIsGameExistsCalled_thenShouldReturnTrue() {
		GameImpl game = createSampleGameImpl();
		when(gameRepository.save(any())).thenReturn(game);
		when(gameRepository.findByTitle(anyString())).thenReturn(game);
		
		Game fromService = gameService.findByTitle("test");
		assertEquals(GameImpl.convert(fromService), game);
		
		boolean doesGameExist = gameService.isGameExist(game);
		assertTrue(doesGameExist);
	}
	
	@Test
	public void givenUnsavedGame_whenIsGameExistsCalled_thenShouldReturnFalse() {
		GameImpl game = createSampleGameImpl();
		GameImpl game2 = createSampleGameImpl();
		game2.setTitle("test2");
		when(gameRepository.save(any())).thenReturn(game);
		when(gameRepository.findByTitle(anyString())).thenReturn(null);

		Game fromService = gameService.findByTitle("test2");
		assertEquals(GameImpl.convert(fromService), null);

		boolean doesGameExist = gameService.isGameExist(game2);
		assertFalse(doesGameExist);
	}
	
	public GameImpl createSampleGameImpl() {
		GameImpl game = new GameImpl();
		game.setId(1L);
		game.setTitle("test");
		game.setComment("test1");
		game.setRating(5);
		return game;
	}
	
	
}
