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

public class CON001_CON020Test extends Base {
	
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
	    	
	    	 String expectedURL= CacheManager.getString(selenium_staging_connectorurl); 
			 String actualURL = driver.getCurrentUrl();
			 System.out.println(actualURL);
			 Assert.assertEquals(actualURL, expectedURL, "Connector Page NOT is correct!");
			 System.out.println("Connector Page opened is correct");
			
			 WebElement createConnector =pom.getConnectorPage().getCreateConnector();
		        Assert.assertTrue(createConnector.isDisplayed(), "CreateConnector is not displayed");
		        Assert.assertTrue(createConnector.isEnabled(), "CreateConnector field is not enabled");
		        System.out.println("CreateConnector field  is correct");
		        
		        WebElement searchConnector=pom.getConnectorPage().getSearchConnector();
		        pom.getConnectorPage().getSearchConnector().click();
		        Assert.assertTrue(searchConnector.isDisplayed(), "Search Connector is not displayed");
		        Assert.assertTrue(searchConnector.isEnabled(), "Search Connector field is not enabled");
		        System.out.println("Search Connector Field is enable");
		        
		        pom.getConnectorPage().getSearchConnector().sendKeys("Mock Connector");
		        System.out.println("Searching existing connector  is correct");
		        WebElement connector = wait.until(ExpectedConditions.visibilityOf(pom.getConnectorPage().getTestConnector()));
			       
		        String connectorName =  pom.getConnectorPage().getTestConnector().getText();
		        System.out.println(connectorName);
			    Assert.assertTrue(connectorName.contains("Mock Connector"), "Connector Name in filter is NOT valid");
			    Thread.sleep(2000);
		        
		        pom.getConnectorPage().getSelectSearch().click();
		        System.out.println("Clearing Search  is correct");
		        WebElement dashConne = wait.until(ExpectedConditions.visibilityOf(pom.getConnectorPage().getSearchConnector()));
		        pom.getConnectorPage().getSearchConnector().sendKeys("Asha");
		        System.out.println("Searching Non-existing connector  is correct");
		        
		        pom.getConnectorPage().getSelectSearch().click();
		        
		        WebElement activeFilter = pom.getConnectorPage().getActiveFilter();
		        Assert.assertTrue(activeFilter.isDisplayed(), "ActiveFilter field is not displayed");
		        Assert.assertTrue(activeFilter.isEnabled(), "ActiveFilter field is not enabled");
		        System.out.println("ActiveFilter field  is correct");
		        
		        
		        WebElement addFilter = pom.getConnectorPage().getAddFilter();
		        Assert.assertTrue(addFilter.isDisplayed(), "AddFilterActiveFilter field is not displayed");
		        Assert.assertTrue(addFilter.isEnabled(), "AddFilter field is not enabled");
		        System.out.println("AddFilter field  is correct");
		        
		        pom.getConnectorPage().getAddFilter().click();
				  Thread.sleep(3000);
				   String verifyFilter=  pom.getConnectorPage().getVerifyFilter().getText();
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
				 
				 pom.getDashboardPage().getDashboardLink().click();
				 
				 String expectedURLdash=CacheManager.getString(selenium_test_stagingurl_dashboard);
				 String actualURLdash = driver.getCurrentUrl();
				 Assert.assertEquals(expectedURLdash,actualURLdash, "Dashboard Link Page NOT is correct!");
				 System.out.println("Dashboard Link opened is correct");
		
				 pom.getDashboardPage().getConnectorsLink().click();
				 
				
				 WebElement sortIcon =pom.getConnectorPage().getSortIcon();
				 Assert.assertTrue(sortIcon.isDisplayed(), "SortIcon field is not displayed");
			     Assert.assertTrue(sortIcon.isEnabled(), "SortIcon field is not enabled");
			     pom.getConnectorPage().getSortIcon().click();
				 System.out.println("Sort Icon  opened is correct");
				 
				 WebElement lastModified =pom.getConnectorPage().getLastModified();
				 Assert.assertTrue(lastModified.isDisplayed(), "LastModified field is not displayed");
				 Assert.assertTrue(lastModified.isEnabled(), "LastModified field is not enabled");
			  	 System.out.println("LastModified opened is correct"); 
					
			  	 WebElement usageAmount =pom.getConnectorPage().getUsageAmount();
				 Assert.assertTrue(usageAmount.isDisplayed(), "UsageAmount field is not displayed");
				 Assert.assertTrue(usageAmount.isEnabled(), "UsageAmount field is not enabled");
			  	 System.out.println("UsageAmount opened is correct"); 
				 
			  	 WebElement connectorList =pom.getConnectorPage().getConnectorList();
				 Assert.assertTrue(connectorList.isDisplayed(), "ConnectorList field is not displayed");
				 Assert.assertTrue(connectorList.isEnabled(), "ConnectorList field is not enabled");
			  	 System.out.println("ConnectorListopened is correct"); 
			  	 
			  	 WebElement configurationName =pom.getConnectorPage().getConfigName();
				 Assert.assertTrue(configurationName.isDisplayed(), "ConfigurationName field is not displayed");
				 Assert.assertTrue(configurationName.isEnabled(), "ConfigurationName field is not enabled");
			  	 System.out.println("ConfigurationName is correct");
			  	 
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