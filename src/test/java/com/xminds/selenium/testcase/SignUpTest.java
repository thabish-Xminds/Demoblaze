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

public class SignUpTest extends Base {

	public WebDriver driver;
	pomFile pom;

	@BeforeMethod
	public void initialize() throws IOException, InterruptedException {
		driver = initializeDriver();
		pom = new pomFile(driver);
	}
	@Test (priority=1)

	public void verifyDemoBlazePageTest() throws InterruptedException, IOException
	
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
		driver.get("https://www.demoblaze.com/");
		String title = driver.getTitle();
		System.out.println("The title of the page is " + title);
		WebElement signUpLink = pom.getBlazePage().getSignup();
		signUpLink.click();
		WebElement usernameField = pom.getBlazePage().getUsername();
		WebElement passwordField = driver.findElement(By.id("sign-password"));
		Assert.assertTrue(usernameField.isEnabled(), "Username field is not enabled");
		Assert.assertTrue(passwordField.isEnabled(), "Password field is not enabled");
		usernameField.sendKeys("thabish123");
		passwordField.sendKeys("Jaise");
		WebElement signUpButton = driver.findElement(By.xpath("//button[contains(text(),'Sign up')]"));
		signUpButton.click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert text: " + alert.getText());
		alert.accept();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();

	}
}



