package com.xminds.selenium.testcase;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class UC001_UC012Test extends Base {
	
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

		public void usersPageVerifyTest() throws InterruptedException, IOException
		{
			
	    	pom.getDashboardPage().getSettingsButton().click();
	    	
	    	pom.getDashboardPage().getUsers().click();
	    	
	    	 String expectedURL= CacheManager.getString(selenium_staging_usersurl); 
			 String actualURL = driver.getCurrentUrl();
			 System.out.println(actualURL);
			 Assert.assertEquals(actualURL, expectedURL, "Users Page NOT is correct!");
			 System.out.println("Users Page opened is correct");
			
			 WebElement createUser = pom.getUsersPage().getCreateUser();
		        Assert.assertTrue(createUser.isDisplayed(), "CreateUser field is not displayed");
		        Assert.assertTrue(createUser.isEnabled(), "CreateUser field is not enabled");
		        System.out.println("CreateUser field  is correct");
		        
		        WebElement searchUsers = pom.getUsersPage().getSearchUsers();
		        Assert.assertTrue(searchUsers.isDisplayed(), "SearchUsers field is not displayed");
		        Assert.assertTrue(searchUsers.isEnabled(), "SearchUsers field is not enabled");
		        System.out.println("SearchUsers field  is correct");
		        
		        WebElement activeFilter = pom.getUsersPage().getActiveFilter();
		        Assert.assertTrue(activeFilter.isDisplayed(), "ActiveFilter field is not displayed");
		        Assert.assertTrue(activeFilter.isEnabled(), "ActiveFilter field is not enabled");
		        System.out.println("ActiveFilter field  is correct");
		        
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
		       pom.getUsersPage().getSearchUsers().sendKeys("Sandhu Admin");

		       WebElement user = wait.until(ExpectedConditions.visibilityOf(pom.getUsersPage().getTestUser()));
		       
		        String userName =  pom.getUsersPage().getTestUser().getText();
		        System.out.println(userName);
			    Assert.assertTrue(userName.contains("Sandhu Admin"), "User Name in filter is NOT valid");
			    Thread.sleep(2000);
			    
			    pom.getUsersPage().getTestUser().click();
			    Thread.sleep(3000);
			    
			   WebElement veruser = wait.until(ExpectedConditions.visibilityOf(pom.getUsersPage().getTestUser()));
			       
			   String verifyUser= pom.getUsersPage().getVerifyUser().getText();
			    System.out.println(verifyUser);
			  Assert.assertTrue(verifyUser.contains("Sandhu Admin"), "User listed is NOT same as selected");
			  
			  pom.getUsersPage().getAddFilter().click();
			  Thread.sleep(3000);
			   String verifyFilter= pom.getUsersPage().getVerifyFilter().getText();
			    System.out.println(verifyFilter);
			  Assert.assertTrue(verifyFilter.contains("Add a Filter"), "Filter listed is NOT same as selected");
			  System.out.println("Filter Opened is correct");
			  
			  driver.navigate().refresh();

			  Thread.sleep(3000);
			  
			 
				 pom.getDashboardPage().getDatapipesLink().click();
				 String expectedURLdata= CacheManager.getString(selenium_staging_datapipeurl);
				 String actualURLdata = driver.getCurrentUrl();
				 Assert.assertEquals(actualURLdata, expectedURLdata, "Datapipe Link Page NOT is correct!");
				 System.out.println("Datapipe Link opened is correct");
				 
				 pom.getDashboardPage().getCollectorsLink().click();
				 String expectedURLColle=CacheManager.getString(selenium_staging_collectorurl);
				 String actualURLColle = driver.getCurrentUrl();
				 Assert.assertEquals(actualURLColle,expectedURLColle, "Collectors Link Page NOT is correct!");
				 System.out.println("Collectors Link opened is correct");
				
				 
				 pom.getDashboardPage().getConnectorsLink().click();
				 String expectedURLConne= CacheManager.getString(selenium_staging_connectorurl);
				 String actualURLConne = driver.getCurrentUrl();
				 Assert.assertEquals(actualURLConne,expectedURLConne, "Connectors Link Page NOT is correct!");
				 System.out.println("Connectors Link opened is correct");
				 
				 pom.getDashboardPage().getDashboardLink().click();
				 
				
				 // Test different screen sizes
			        checkScreenSize(1920, 1080); // Desktop
			        checkScreenSize(1366, 768);  // Laptop
			        checkScreenSize(768, 1024);  // Tablet
			        checkScreenSize(375, 667);   // Mobile
			        
			        System.out.println("Viewing in different size screens,(1920, 1080),(1366, 768),(768, 1024),(375, 667)");
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