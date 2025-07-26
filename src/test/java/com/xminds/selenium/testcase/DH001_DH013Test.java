package com.xminds.selenium.testcase;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.xminds.selenium.configurationfiles.Base;

import com.xminds.selenium.configurationfiles.ExcelUtils;

import com.xminds.selenium.pomcollection.pomFile;
import com.xminds.selenium.util.CacheManager;

public class DH001_DH013Test extends Base {
	
	 public WebDriver driver;
	 pomFile pom;
	    public static Logger log = LogManager.getLogger(Base.class.getName());

   
	  @BeforeMethod
	    public void initialize() throws IOException {
	        driver = initializeDriver();
	      
	        driver.get(CacheManager.getString(selenium_test_url_staging));
	        pom = new pomFile(driver);
	  }     



		@Test (priority=1)

		public void dashboardVerifyTest() throws InterruptedException, IOException
		{
			// TODO Auto-generated method stub
			 
			 pom.getLoginPage().getUsername().click();
			 pom.getLoginPage().getUsername().sendKeys(CacheManager.getString(selenium_test_username)); 
			 pom.getLoginPage().getPassword().click();
			 pom.getLoginPage().getPassword().sendKeys(CacheManager.getString(selenium_test_password));
			 Actions actions = new Actions(driver);
			 actions.moveToElement(pom.getLoginPage().getLoginButton()).click().perform();
			 Thread.sleep(5000);
			 
			 String expectedURL= CacheManager.getString(selenium_test_stagingurl_dashboard); 
			 String actualURL = driver.getCurrentUrl();
			 Assert.assertEquals(actualURL, expectedURL, "Dashboard Page NOT is correct!");
			 System.out.println("Dashboard Link opened is correct");
		
			     WebElement dashboardLink = pom.getDashboardPage().getDashboardLink();
		        Assert.assertTrue(dashboardLink.isDisplayed(), "Dashboard field is not displayed");
		        Assert.assertTrue(dashboardLink.isEnabled(), "Dashboard field is not enabled");
		        System.out.println("Dashboard Link field  is correct");
		        
		        WebElement connectorsLink = pom.getDashboardPage().getConnectorsLink();
		        Assert.assertTrue(connectorsLink.isDisplayed(), "Connectors field is not displayed");
		        Assert.assertTrue(connectorsLink.isEnabled(), "Connectors field is not enabled");
		        System.out.println("Collectors Link field is correct");
			    
		        WebElement collectorsLink = pom.getDashboardPage().getCollectorsLink();
		        Assert.assertTrue(collectorsLink.isDisplayed(), "Collectors field is not displayed");
		        Assert.assertTrue(collectorsLink.isEnabled(), "Collectors is not enabled");
		        System.out.println("Collectors field is correct");
			    
		        WebElement datapipesLink = pom.getDashboardPage().getDatapipesLink();
		        Assert.assertTrue(datapipesLink.isDisplayed(), "Datapipes field is not displayed");
		        Assert.assertTrue(datapipesLink.isEnabled(), "Datapipes field is not enabled");
		        System.out.println("Datapipe field is correct");
			    
		        WebElement settingsButton = pom.getDashboardPage().getSettingsButton();
		        Assert.assertTrue(settingsButton.isDisplayed(), "SettingButton field is not displayed");
		        Assert.assertTrue(settingsButton.isEnabled(), "SettingButton field is not enabled");
		        System.out.println("SettingsButton field is correct");
		        
		        WebElement userMenuButton = pom.getDashboardPage().getUserMenu();
		        Assert.assertTrue(userMenuButton.isDisplayed(), "Usermenu Button field is not displayed");
		        Assert.assertTrue(userMenuButton.isEnabled(), "Usermenu Button field is not enabled");
		        System.out.println("Usermenu field is correct");
		        
		        WebElement datapipesText = pom.getDashboardPage().getDatapipesText();
		        Assert.assertTrue(datapipesText.isDisplayed(), "DatapipesText field is not displayed");
		        Assert.assertTrue(datapipesText.isEnabled(), "DatapipesText field is not enabled");
		        System.out.println("DatapipeText field is correct");
		        
		        WebElement collectorsText = pom.getDashboardPage().getCollectorsText();
		        Assert.assertTrue(collectorsText.isDisplayed(), "CollectorsText field is not displayed");
		        Assert.assertTrue(collectorsText.isEnabled(), "CollectorsText field is not enabled");
		        System.out.println("Collectors Text field is correct");
			    
		        WebElement connectorsText = pom.getDashboardPage().getConnectorsText();
		        Assert.assertTrue(connectorsText.isDisplayed(), "ConnectorsText field is not displayed");
		        Assert.assertTrue(connectorsText.isEnabled(), "ConnectorsText field is not enabled");
		        System.out.println("ConnectorsText field is correct");
		        
		        WebElement dataUsageText = pom.getDashboardPage().getDataUsage();
		        Assert.assertTrue(dataUsageText.isDisplayed(), "DataUsageText field is not displayed");
		        Assert.assertTrue(dataUsageText.isEnabled(), "DataUsageText field is not enabled");
		        System.out.println("DataUsageText field is correct");
		        
		        WebElement datavolumeText = pom.getDashboardPage().getDataVolume();
		        Assert.assertTrue(datavolumeText.isDisplayed(), "DatavolumeText field is not displayed");
		        Assert.assertTrue(datavolumeText.isEnabled(), "DatavolumeTextt field is not enabled");
		        System.out.println("DataVolumefield is correct");
		        
		        WebElement dateCalender = pom.getDashboardPage().getDateCalender();
		        Assert.assertTrue(dateCalender.isDisplayed(), "DateCalender field is not displayed");
		        Assert.assertTrue(dateCalender.isEnabled(), "DateCalender field is not enabled");
		        System.out.println("DateCalender field is correct");
		        
		        WebElement dashboardText= pom.getDashboardPage().getDashboardText();
		        Assert.assertTrue(dashboardText.isDisplayed(), "DashboardText field is not displayed");
		        Assert.assertTrue(dashboardText.isEnabled(), "DashboardText field is not enabled");
		        System.out.println("DataboardText field is correct");
		        
		            
		
			 
	    }
@AfterMethod
		
		public void tearDown()
		{
			driver.close();
		}
	  
	   
}


