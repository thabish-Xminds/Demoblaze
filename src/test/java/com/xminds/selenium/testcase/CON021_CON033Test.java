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

public class CON021_CON033Test extends Base {
	
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
			 String expectedURL= CacheManager.getString(selenium_staging_createconnectorurl); 
			 String actualURL = driver.getCurrentUrl();
			 System.out.println(actualURL);
			 Assert.assertEquals(actualURL, expectedURL, "Connector Page NOT is correct!");
			 System.out.println("Create Connector Page opened is correct");
			 

		        WebElement selectDest = pom.getConnectorPage().getSelectDestination();
		        Assert.assertTrue(selectDest.isDisplayed(), "SelectDestination field is not displayed");
		        Assert.assertTrue(selectDest.isEnabled(), "SelectDestination field is not enabled");
		        System.out.println("SelectDestination field  is correct");
		        
		        WebElement nameConfiguration = pom.getConnectorPage().getNameConfiguration();
		        Assert.assertTrue(nameConfiguration.isDisplayed(), "NameConfiguration field is not displayed");
		        Assert.assertTrue(nameConfiguration.isEnabled(), "NameConfiguration field is not enabled");
		        System.out.println("NameConfiguration field  is correct");
		        
		        WebElement accessControl = pom.getConnectorPage().getAccessControl();
		        Assert.assertTrue(accessControl.isDisplayed(), "AccessControl field is not displayed");
		        Assert.assertTrue(accessControl.isEnabled(), "AccessControl field is not enabled");
		        System.out.println("AccessControl field  is correct");
		           
		        WebElement deployConnect = pom.getConnectorPage().getDeployConn();
		        Assert.assertTrue(deployConnect.isDisplayed(), "DeployConnector field is not displayed");
		        Assert.assertTrue(deployConnect.isEnabled(), "DeployConnector field is not enabled");
		        System.out.println("DeployConnector field  is correct");
		        
		        String titlePage=driver.getTitle();
		        System.out.println(titlePage);
		        String expectedTitle= CacheManager.getString(selenium_staging_connectortitle); 
		        Assert.assertEquals(titlePage, expectedTitle, "Connector Title Page NOT is correct!");
				 System.out.println(" Connector Page title opened is correct");
		        
		        pom.getConnectorPage().getSearchConnector().sendKeys("Mock Connector");
		        System.out.println("Searching existing connector  is correct");
		        WebElement connector = wait.until(ExpectedConditions.visibilityOf(pom.getConnectorPage().getMockConnector()));
			       
		        String connectorName =  pom.getConnectorPage().getMockConnector().getText();
		        System.out.println(connectorName);
			    Assert.assertTrue(connectorName.contains("Mock Connector"), "Connector Name in filter is NOT valid");
			    Thread.sleep(2000);
			    pom.getConnectorPage().getSelectSearch().click();
			    
			    pom.getConnectorPage().getAddFilter().click();
			    

		        WebElement emailBody1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
		                By.xpath("//label[@id='filterBy-label']")));
		        
		       pom.getConnectorPage().getFilterBy().click();
		       
		       WebElement listcompany1 = wait.until(ExpectedConditions.visibilityOf(pom.getConnectorPage().getSelectCatagory()));
		       pom.getConnectorPage().getSelectCatagory().click();
		       Thread.sleep(2000);
		       
		       pom.getConnectorPage().getFilterValue().click();
		       
		       WebElement logAnalysis = pom.getConnectorPage().getSelectLogAnalysis();
		        Assert.assertTrue(logAnalysis.isDisplayed(), "LogAnalysis field is not displayed");
		        Assert.assertTrue(logAnalysis.isEnabled(), "LogAnalysis field is not enabled");
		        System.out.println("LogAnalysis field  is correct");
		           
		        
			       WebElement selectAnalytics = pom.getConnectorPage().getSelectAnalytics();
			        Assert.assertTrue(selectAnalytics.isDisplayed(), "selectAnalytics field is not displayed");
			        Assert.assertTrue(selectAnalytics.isEnabled(), "selectAnalytics field is not enabled");
			        System.out.println("selectAnalytics field  is correct");
			           
		        
			        WebElement selectAPM = pom.getConnectorPage().getSelectAPM();
			        Assert.assertTrue(selectAPM.isDisplayed(), "selectAPM field is not displayed");
			        Assert.assertTrue(selectAPM.isEnabled(), "selectAPM field is not enabled");
			        System.out.println("selectAPM field  is correct");
			           
			        WebElement selectDataWareHouse = pom.getConnectorPage().getSelectDataWarehouse();
			        Assert.assertTrue(selectDataWareHouse.isDisplayed(), "selectDataWareHouse field is not displayed");
			        Assert.assertTrue(selectDataWareHouse.isEnabled(), "selectDataWareHouse field is not enabled");
			        System.out.println("selectDataWareHouse field  is correct");
				 
			        
			        WebElement selectOther = pom.getConnectorPage().getSelectOther();
			        Assert.assertTrue(selectOther.isDisplayed(), "selectOther field is not displayed");
			        Assert.assertTrue(selectOther.isEnabled(), "selectOther field is not enabled");
			        System.out.println("selectOther field  is correct");
			 
			    	driver.navigate().refresh();
				 
		}		 
	   
@AfterMethod
		
		public void tearDown()
		{
			driver.quit();
		}
}