package com.xminds.selenium.testcase;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class CC001_CC008Test extends Base {
	
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

		public void companyPageVerifyTest() throws InterruptedException, IOException
		{
			
	    	pom.getDashboardPage().getSettingsButton().click();
	    	
	    	pom.getDashboardPage().getCompanies().click();
	    	
	    	 String expectedURL= CacheManager.getString(selenium_staging_companyurl); 
			 String actualURL = driver.getCurrentUrl();
			 System.out.println(actualURL);
			 Assert.assertEquals(actualURL, expectedURL, "Company Page NOT is correct!");
			 System.out.println("Company Page opened is correct");
			
			 WebElement createCompany = pom.getCompanyPage().getCreateCompany();
		        Assert.assertTrue(createCompany.isDisplayed(), "CreateCompanyfield is not displayed");
		        Assert.assertTrue(createCompany.isEnabled(), "CreateCompany field is not enabled");
		        System.out.println("CreateCompany field  is correct");
		        
		        WebElement searchCompany = pom.getCompanyPage().getSearchCompany();
		        Assert.assertTrue(searchCompany.isDisplayed(), "SearchCompany field is not displayed");
		        Assert.assertTrue(searchCompany.isEnabled(), "SearchCompany field is not enabled");
		        System.out.println("SearchCompany field  is correct");
		        
		        WebElement activeFilter = pom.getCompanyPage().getActiveFilter();
		        Assert.assertTrue(activeFilter.isDisplayed(), "ActiveFilter field is not displayed");
		        Assert.assertTrue(activeFilter.isEnabled(), "ActiveFilter field is not enabled");
		        System.out.println("ActiveFilter field  is correct");
		        
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
		      //  WebElement listcompany = wait.until(ExpectedConditions.visibilityOf(pom.getCompanyPage().getListCompany()));
		      //  System.out.println("All companies are listed");
		        
		        pom.getCompanyPage().getSearchCompany().sendKeys("datazoom");

		       WebElement company = wait.until(ExpectedConditions.visibilityOf(pom.getCompanyPage().getTestCompany()));
		       
		        String companyName =  pom.getCompanyPage().getTestCompany().getText();
		        System.out.println(companyName);
			    Assert.assertTrue(companyName.contains("Datazoom"), "Company Name in filter is NOT valid");
			    Thread.sleep(2000);
			    
			    pom.getCompanyPage().getTestCompany().click();
			    Thread.sleep(3000);
			    
			   WebElement vercompany = wait.until(ExpectedConditions.visibilityOf(pom.getCompanyPage().getVerifyCompany()));
			       
			   String verifyCompany= pom.getCompanyPage().getVerifyCompany().getText();
			    System.out.println(verifyCompany);
			  Assert.assertTrue(verifyCompany.contains("Datazoom"), "Company listed is NOT same as selected");
			  
			  

				 pom.getDashboardPage().getDatapipesLink().click();
				 String expectedURLdata= CacheManager.getString(selenium_staging_datapipeurl);
				 String actualURLdata = driver.getCurrentUrl();
				 Assert.assertEquals(actualURLdata, expectedURLdata, "Datapipe Link Page NOT is correct!");
				 System.out.println("Datapipe Link opened is correct");
				 Thread.sleep(2000);
				 
				 pom.getDashboardPage().getCollectorsLink().click();
				 String expectedURLColle=CacheManager.getString(selenium_staging_collectorurl);
				 String actualURLColle = driver.getCurrentUrl();
				 Assert.assertEquals(actualURLColle,expectedURLColle, "Collectors Link Page NOT is correct!");
				 System.out.println("Collectors Link opened is correct");
				 Thread.sleep(2000);
				
				 
				 pom.getDashboardPage().getConnectorsLink().click();
				 String expectedURLConne= CacheManager.getString(selenium_staging_connectorurl);
				 String actualURLConne = driver.getCurrentUrl();
				 Assert.assertEquals(actualURLConne,expectedURLConne, "Connectors Link Page NOT is correct!");
				 System.out.println("Connectors Link opened is correct");
				 Thread.sleep(2000);
				 
				 pom.getDashboardPage().getDashboardLink().click();
			 
}
@AfterMethod
		
		public void tearDown()
		{
			driver.quit();
		}
}