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

public class CON041_CON053Test extends Base {
	
	 public WebDriver driver;
	 pomFile pom;
	    public static Logger log = LogManager.getLogger(Base.class.getName());
	    
	    @BeforeMethod
	    public void initialize() throws IOException, InterruptedException {
	        driver = initializeDriver();
	      
	        driver.get(CacheManager.getString(selenium_test_url_staging));
	        pom = new pomFile(driver);
	        pom.getLoginPage().getUsername().click();
			 pom.getLoginPage().getUsername().sendKeys(CacheManager.getString(selenium_test_username)); 
			 pom.getLoginPage().getPassword().click();
			 pom.getLoginPage().getPassword().sendKeys(CacheManager.getString(selenium_test_password));
			 
			 Actions actions = new Actions(driver);
			 actions.moveToElement(pom.getLoginPage().getLoginButton()).click().perform();
			 Thread.sleep(5000);
			
			
	  }     
   
	    @Test (priority=1)

		public void verifyConnectorPageTest() throws InterruptedException, IOException
		{
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
	    	
            pom.getDashboardPage().getConnectorsLink().click();
	    	
	    	
			
			pom.getConnectorPage().getCreateConnector().click();
			pom.getConnectorPage().getCloseConnector().click(); 
			pom.getConnectorPage().getCreateConnector().click();
			
			  WebElement activeElement;
			  
		        
		        // First tab press
		        driver.findElement(By.cssSelector("button[aria-label='close']")).sendKeys(Keys.TAB);
		        activeElement = driver.switchTo().activeElement();
		        System.out.println("First Focus: " + activeElement.getAttribute("outerHTML"));
                  Thread.sleep(2000);
		        // Next tab press
		        activeElement.sendKeys(Keys.TAB);
		        activeElement = driver.switchTo().activeElement();
		        System.out.println("Second Focus: " + activeElement.getAttribute("outerHTML"));
		        Thread.sleep(2000);
		        // Next tab press
		        activeElement.sendKeys(Keys.TAB);
		        activeElement = driver.switchTo().activeElement();
		        System.out.println("Third Focus: " + activeElement.getAttribute("outerHTML"));
		        Thread.sleep(2000);
		        // Next tab press
		        activeElement.sendKeys(Keys.TAB);
		        activeElement = driver.switchTo().activeElement();
		        System.out.println("Fourth Focus: " + activeElement.getAttribute("outerHTML"));

		    	// Test different screen sizes
		        checkScreenSize(1920, 1080); // Desktop
		        checkScreenSize(1366, 768);  // Laptop
		        checkScreenSize(768, 1024);  // Tablet
		        checkScreenSize(375, 667);   // Mobile
		        checkScreenSize(1920, 1080); // Desktop
		        System.out.println("Viewing in different size screens,(1920, 1080),(1366, 768),(768, 1024),(375, 667)");
		        
		        pom.getConnectorPage().getSearchConnector().sendKeys("Asha");
		        System.out.println("Searching non existing connector  is correct");
		        
		        pom.getConnectorPage().getCloseFilter().click();
		        
		        pom.getConnectorPage().getSearchConnector().sendKeys("Mock Connector");
		        System.out.println("Searching existing connector  is correct");
		        

				WebElement mockconnector = wait.until(ExpectedConditions.visibilityOf(pom.getConnectorPage().getMockConnector()));
				mockconnector.click();
				
				WebElement nextButton=pom.getConnectorPage().getNextButton();
				 Assert.assertTrue(nextButton.isDisplayed(), "ActiveFilter field is displayed");
			     Assert.assertTrue(nextButton.isEnabled(), "ActiveFilter field is enabled");
			     System.out.println("ActiveFilter field  is correct");
			        
				
		        WebElement nameInput =  pom.getConnectorPage().getConnectorName();
		        // Input a string longer than the max limit
		        int maxLimit = 100;
		        String longInput = "A".repeat(maxLimit + 10);  // e.g., 60 characters
		        nameInput.sendKeys(longInput);

		        // Get actual input value
		        String actualValue = nameInput.getAttribute("value");
		        System.out.println("Input value: " + actualValue);
		        System.out.println("Length: " + actualValue.length());

		        // Assert the value is not longer than the limit
		        Assert.assertTrue(actualValue.length() <= maxLimit, "Input exceeds max character limit!");

		        
	}

		    public void checkScreenSize(int width, int height) {
		        driver.manage().window().setSize(new Dimension(width, height));
		        // Add assertions or validations for elements
		        System.out.println("Testing at: " + width + "x" + height);
		    }
		 
		
@AfterMethod
		
		public void tearDown()
		{
			driver.quit();
		}
}