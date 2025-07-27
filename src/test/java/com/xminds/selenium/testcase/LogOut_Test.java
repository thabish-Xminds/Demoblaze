package com.xminds.selenium.testcase;


import java.io.IOException;

import java.time.Duration;

import java.util.ArrayList;

import java.util.List;

import java.util.Set;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import org.openqa.selenium.Alert;

import org.openqa.selenium.By;

import org.openqa.selenium.Dimension;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.WindowType;

import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

import com.xminds.selenium.configurationfiles.Base;

import com.xminds.selenium.configurationfiles.ExcelUtils;

import com.xminds.selenium.pomcollection.pomFile;

import com.xminds.selenium.util.CacheManager;

public class LogOut_Test extends Base {

	public WebDriver driver;

	pomFile pom;

	public static Logger log = LogManager.getLogger(Base.class.getName());

	@BeforeMethod

	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		
		pom = new pomFile(driver);

		// driver.get("https://www.demoblaze.com/");

		Thread.sleep(5000);


	}

	@Test
	public void testLogoutFunctionality() throws InterruptedException {
		// Click Login link
		WebElement loginLink = driver.findElement(By.id("login2"));
		loginLink.click();
		// Verify login form elements are enabled
		WebElement usernameLabel = driver.findElement(By.xpath("//label[@for='log-name']"));
		WebElement passwordLabel = driver.findElement(By.xpath("//label[@for='log-pass']"));
		Assert.assertTrue(usernameLabel.isEnabled(), "Username label is not enabled");
		Assert.assertTrue(passwordLabel.isEnabled(), "Password label is not enabled");
		Thread.sleep(200); // Small pause for demo purposes
		// Enter valid credentials
		WebElement usernameField = driver.findElement(By.id("loginusername"));
		WebElement passwordField = driver.findElement(By.id("loginpassword"));
		usernameField.sendKeys("thabish123");
		Thread.sleep(200);
		passwordField.sendKeys("Jaise");
		Thread.sleep(200);
		// Click Login button
		WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]"));
		loginButton.click();
		// Wait for logout button to be clickable
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
		WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout2")));
		// Perform logout
		logoutButton.click();
		System.out.println("Logout Successful!");
		// Verify logout was successful by checking login button reappears
		wait.until(ExpectedConditions.elementToBeClickable(By.id("login2")));
	}
	@AfterMethod

	public void tearDown()

	{

		driver.quit();

	}

}



 