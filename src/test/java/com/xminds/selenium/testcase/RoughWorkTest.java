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

public class RoughWorkTest extends Base {
	
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

  		public void companydifferentAccountTest() throws InterruptedException, IOException
  		{
    	 JavascriptExecutor js = (JavascriptExecutor) driver;
  	    	pom.getDashboardPage().getSettingsButton().click();
  	    	Thread.sleep(200);
  	    	pom.getDashboardPage().getCompanies().click();
  	    	
  	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
  		   //     WebElement listcompany = wait.until(ExpectedConditions.visibilityOf(pom.getCompanyPage().getListCompany()));
  		     
  	    	 
  	    	 System.out.println("All companies are listed");
  		        
  		        pom.getCompanyPage().getSearchCompany().sendKeys(CacheManager.getString(selenium_companyName));
  		        Thread.sleep(2000);
  		        
  		        WebElement emailBody = wait.until(ExpectedConditions.visibilityOf(pom.getCompanyPage().getSearchCompName()));
  		               
  		        
  		        pom.getCompanyPage().getSearchCompName().click();
  		        
  		        Thread.sleep(4000);
  		        
  		        WebElement element = pom.getCompanyPage().getCompanyType();

  		     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
  			    
  			    pom.getCompanyPage().getCompanyType().click();
  			    
  			    pom.getCompanyPage().getCustomType().click();
  			  Thread.sleep(2000);
  			    pom.getCompanyPage().getYesButton().click();
  			    System.out.println("Company changed to standard Type");
	  }  

	   
@AfterMethod
		
		public void tearDown()
		{
			driver.quit();
		}
}