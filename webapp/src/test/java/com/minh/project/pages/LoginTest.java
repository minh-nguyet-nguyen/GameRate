package com.minh.project.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.minh.project.config.SeleniumWebDriverConfig;

public class LoginTest {
	private static final String BASE_URL =  "http://localhost:8080/GameRate/login";
	
    private SeleniumWebDriverConfig seleniumConfig;
    private WebDriver driver;
	
	@FindBy(id="username")
	private WebElement username;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(id="loginButton")
	private WebElement loginButton;
	
	@FindBy(id="errorMessage")
	private WebElement errorMessage;
	
	@FindBy(id="welcomeDiv")
	private WebElement welcomeDiv;
	
	@FindBy(id="welcomeMessage")
	private WebElement welcomeMessage;
	
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
		driver.navigate().to(BASE_URL);
	}
	
	@Test
	public void givenOnLoginPage_whenValidUsernameAndPassword_thenNavigateToHomePage() {
		doLogin("minh", "test");
		String expectedUrl  = BASE_URL;
		String currentURL = driver.getCurrentUrl();
		assertEquals(currentURL, expectedUrl, "Current URL is not the same as expected URL");
		
		assertTrue(welcomeDiv.isDisplayed(), "Welcome div is not displayed");
		
		String expectedMessage = "WELCOME MINH";
		String actualMessage = welcomeMessage.getText();
		assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected message");
	}
	
	@Test
	public void givenLoginPage_whenValidUsernameInvalidPassword_thenErrorOnLoginPage() {	
		doLogin("minh", "");
		String expectedUrl = BASE_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		String expectedMsg = "Please enter username and password";
		String actualMsg = errorMessage.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	}
	
	@Test
	public void givenLoginPage_whenInvalidUsernameValidPassword_thenErrorOnLoginPage() {	
		doLogin("", "test");
		String expectedUrl = BASE_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		String expectedMsg = "Please enter username and password";
		String actualMsg = errorMessage.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	}

	@Test
	public void givenLoginPage_whenValidUsernameAndNullPassword_thenErrorOnLoginPage() {	
		doLogin("minh", null);
		String expectedUrl = BASE_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		String expectedMsg = "Please enter username and password";
		String actualMsg = errorMessage.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	}
	
	@Test
	public void givenLoginPage_whenNullUsernameAndValidPassword_thenErrorOnLoginPage() {	
		doLogin(null, "test");
		String expectedUrl = BASE_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		String expectedMsg = "Please enter username and password";
		String actualMsg = errorMessage.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	}
	
	@Test
	public void givenLoginPage_whenNullUsernameAndNullPassword_thenErrorOnLoginPage() {	
		doLogin(null, null);
		String expectedUrl = BASE_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		String expectedMsg = "Please enter username and password";
		String actualMsg = errorMessage.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	}
	
	private void doLogin(String testUsername, String testPassword) {
		// enter password
		if (testUsername != null) {
			username.sendKeys(testUsername);
		}
		if (testPassword != null) {
			password.sendKeys(testPassword);
		}
		// click login button
		loginButton.click();
	}
}