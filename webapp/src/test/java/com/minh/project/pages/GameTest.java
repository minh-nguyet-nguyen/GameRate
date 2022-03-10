package com.minh.project.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.minh.project.config.SeleniumWebDriverConfig;

public class GameTest {
	private static final String BASE_URL =  "http://localhost:8080/GameRate/";
	
    private SeleniumWebDriverConfig seleniumConfig;
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(id="gameForm")
	private WebElement gameForm;
	
	@FindBy(id="title")
	private WebElement title;
	
	@FindBy(id="comment")
	private WebElement comment;
	
	@FindBy(id="rating")
	private WebElement rating;
	
	@FindBy(id="submitButton")
	private WebElement submitButton;
	
    @FindBy(id="list")
   	private WebElement list;
    
    @FindBy(id="gameId")
  	private WebElement gameId;
    
    @FindBy(id="gameTitle")
	private WebElement gameTitle;
	
	@FindBy(id="gameComment")
	private WebElement gameComment;
	
	@FindBy(id="gameRating")
	private WebElement gameRating;
	
	@FindBy(id="resetButton")
	private WebElement resetButton;
	
	@FindBy(id="editButton")
	private WebElement editButton;
	
	@FindBy(id="deleteButton")
	private WebElement deleteButton;
	
	@BeforeSuite
	public void setup() {
		seleniumConfig = new SeleniumWebDriverConfig();
		driver = seleniumConfig.getDriver();
		PageFactory.initElements(driver, this); //required for selenium to find webelements
	}
	
	@AfterSuite
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	@BeforeTest
	public void beforeEach() {
		driver.navigate().to(BASE_URL + "game");
		wait = new WebDriverWait(driver, Duration.ofSeconds(500));
	}
	
	@Test
	public void givenGamePage_whenAllInputFieldsValid_thenAddGameToList() {
		enterFormData("Hollow Knight", "good music", 5);
		assertTrue(isElementEnabled("submitButton", submitButton));
		submit();
		String expectedUrl = BASE_URL + "game";
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Current URL is not the same as expected URL");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("list")));
		assertTrue(list.isDisplayed(), "Saved game does not appear on list");
		assertTrue(gameTitle.getText().equals("Hollow Knight"), "Saved game does not have correct title");
		assertFalse(isElementEnabled("submitButton", submitButton));
		deleteButton.click();
	}
	
	@Test
	public void givenGamePage_whenEmptyTitle_thenAddButtonIsDisabled() {
		clear();
		enterFormData("", "good music", 5);
		assertFalse(isElementEnabled("submitButton", submitButton));
	}
	
	@Test
	public void givenGamePage_whenEmptyRating_thenAddButtonIsDisabled() {
		clear();
		enterFormData("Hollow Knight", "good music", null);
		assertFalse(isElementEnabled("submitButton", submitButton));
	}
	
	@Test
	public void givenPreAddedGame_whenTitleIsChanged_thenGameIsUpdated() {
		clear();
		enterFormData("Hollow Knight", "good music", 5);
		assertTrue(isElementEnabled("submitButton", submitButton));
		submit();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("editButton")));

		editButton.click();
		clear();
		enterFormData("Shovel Knight", "good music", 5);
		assertTrue(isElementEnabled("submitButton", submitButton));
		submit();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gameTitle")));
		assertTrue(gameTitle.getText().equals("Shovel Knight"), "Saved game does not have updated title");
		deleteButton.click();
	}
	
	@Test
	public void givenPreAddedGame_whenCommentIsChanged_thenGameIsUpdated() {
		clear();
		enterFormData("Hollow Knight", "good music", 5);
		assertTrue(isElementEnabled("submitButton", submitButton));
		submit();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("list")));
		
		editButton.click();
		clear();
		enterFormData("Shovel Knight", "fun graphics", 5);
		submit();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gameComment")));
		assertTrue(gameComment.getText().equals("fun graphics"), "Saved game does not have updated comment");
		deleteButton.click();
	}
	
	@Test
	public void givenPreAddedGame_whenRatingIsChanged_thenGameIsUpdated() {
		clear();
		enterFormData("Hollow Knight", "good music", 5);
		assertTrue(isElementEnabled("submitButton", submitButton));
		submit();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("list")));
		
		editButton.click();
		clear();
		enterFormData("Shovel Knight", "fun graphics", 3);
		submit();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gameRating")));
		assertTrue(gameRating.getText().equals("3"), "Saved game does not have updated rating");
		deleteButton.click();
	}
	
	public void enterFormData(String testTitle, String testComment, Integer testRating) {
		if (gameForm.isDisplayed()) {
			if (testTitle != null) {
				title.sendKeys(testTitle);
			}
			if (testComment != null) {
				comment.sendKeys(testComment);
			}
			if (testRating != null) {
				rating.sendKeys(testRating.toString());
			}
		}
	}
	
	public void submit() {
		submitButton.click();
	}
	
	public void clear() {
		title.clear();
		comment.clear();
		rating.clear();
	}
	
	private boolean isElementEnabled(String elementId, WebElement webElement) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementId)));
        return webElement.isEnabled();
	} 
}