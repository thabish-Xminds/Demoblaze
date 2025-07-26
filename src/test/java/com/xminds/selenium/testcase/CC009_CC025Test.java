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

public class CC009_CC025Test extends Base {
	
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

		public void companyCreationTest() throws InterruptedException, IOException
		{
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
			  
	    	pom.getDashboardPage().getSettingsButton().click();
  	    	Thread.sleep(200);
  	    	pom.getDashboardPage().getCompanies().click();
  	    	
  	    	 
  	    	 System.out.println("All companies are listed");
  		        
  		        pom.getCompanyPage().getSearchCompany().sendKeys(CacheManager.getString(selenium_companyName));
  		        Thread.sleep(2000);
  		        
  		        WebElement emailBody = wait.until(ExpectedConditions.visibilityOf(pom.getCompanyPage().getSearchCompName()));
  		               
  		        
  		        pom.getCompanyPage().getSearchCompName().click();
  		        
  		        Thread.sleep(4000);
	    
             Thread.sleep(4000);
		        
		        pom.getCompanyPage().getDisableCompany().click();
		        
		        Thread.sleep(7000);
		        
		        System.out.println("Diasbled the company");
		        
		  
		     
		        pom.getCompanyPage().getCloseActiveFilter().click();
		        System.out.println("closed the active filter");
		        Thread.sleep(4000);
		    
		
		        
		     
		        pom.getCompanyPage().getCloseFilter().click();
		        System.out.println("Cleared the filter");
		        Thread.sleep(2000);
		        pom.getCompanyPage().getAddFilter().click();
		      
		        WebElement emailBody1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
		                By.xpath("//label[@id='filterBy-label']")));
		       
		        pom.getCompanyPage().getfilterBy().click();
		        
		        WebElement listcompany1 = wait.until(ExpectedConditions.visibilityOf(pom.getCompanyPage().getSelectStatus()));
		        pom.getCompanyPage().getSelectStatus().click();
		        Thread.sleep(2000);
		        pom.getCompanyPage().getFilterValue().click();
		        WebElement listcompany2 = wait.until(ExpectedConditions.visibilityOf(pom.getCompanyPage().getFilterDisabled()));
		        
		        pom.getCompanyPage().getFilterDisabled().click();
		        
		      //  pom.getCompanyPage().getApplyFilter().click();
		        Actions actions = new Actions(driver);
		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("arguments[0].click();", pom.getCompanyPage().getApplyFilter());
		       // actions.moveToElement(pom.getCompanyPage().getApplyFilter()).click().perform();
		        
		        System.out.println("Searching for disabled company");
		        Thread.sleep(2000);
		        pom.getCompanyPage().getSearchCompany().sendKeys(CacheManager.getString(selenium_companyName));
		        
		        WebElement emailBody2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
		                By.xpath(CacheManager.getString(selenium_span_companyName))));
			       
		        String companyName = driver.findElement(By.xpath(CacheManager.getString(selenium_span_companyName))).getText();
		        System.out.println(companyName);
			    Assert.assertTrue(companyName.contains("Automation"), "Company Name in filter is NOT valid");
			    Thread.sleep(2000);
			    System.out.println("Disabled company listed");
			    
			    Thread.sleep(3000);
			    pom.getCompanyPage().getReEnableCompany().click();
			    
			    System.out.println("Enabled the company again");
			    Thread.sleep(3000);
			      	
}
	    
	    @Test (priority=2)

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
	  			    
	  			  pom.getCompanyPage().getMediaTailorType().click();
	  			 //   pom.getCompanyPage().getStandardType().click();
	  			  Thread.sleep(2000);
	  			    pom.getCompanyPage().getYesButton().click();
	  			    System.out.println("Company changed to standard Type");
	  			    

	  			    pom.getCompanyPage().getCloseActiveFilter().click();
	  		        System.out.println("closed the active filter");
	  		        Thread.sleep(4000);
	  		        
	  		     
	  		        pom.getCompanyPage().getCloseFilter().click();
	  		        System.out.println("Cleared the filter");
	  		        Thread.sleep(2000);
	  		        pom.getCompanyPage().getAddFilter().click();
	  		      
	  		        WebElement emailBody1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
	  		                By.xpath("//label[@id='filterBy-label']")));
	  		       
	  		        pom.getCompanyPage().getfilterBy().click();
	  		        
	  		        WebElement listcompany1 = wait.until(ExpectedConditions.visibilityOf(pom.getCompanyPage().getSearchAccount()));
	  		        pom.getCompanyPage().getSearchAccount().click();
	  		      Thread.sleep(2000);
	  		      
	  		        pom.getCompanyPage().getFilterValue().click();
	  		        WebElement listcompany2 = wait.until(ExpectedConditions.visibilityOf(pom.getCompanyPage().getMediaTailorType()));
	  		        
	  		        pom.getCompanyPage().getMediaTailorType().click();
	  		        
	  		         Actions actions = new Actions(driver);
	  		    //   actions.moveToElement(pom.getCompanyPage().getApplyFilter()).click().perform();
	  		       js.executeScript("arguments[0].click();", pom.getCompanyPage().getApplyFilter());
	  		       
	  		        System.out.println("Searching for type company");
	  		      Thread.sleep(2000);
	  		      
	  		        pom.getCompanyPage().getSearchCompany().sendKeys(CacheManager.getString(selenium_companyName));
	  		        
	  		        WebElement emailBody2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
	  		                By.xpath(CacheManager.getString(selenium_span_companyName))));
	  			       
	  		        String companyName = driver.findElement(By.xpath(CacheManager.getString(selenium_span_companyName))).getText();
	  		        System.out.println(companyName);
	  			    Assert.assertTrue(companyName.contains("Automation"), "Company Name in filter is NOT valid");
	  			    Thread.sleep(2000);
	  			    System.out.println("AccountType Changed company listed");
	  			    
	  			  pom.getCompanyPage().getSearchCompName().click();
			        
			        Thread.sleep(4000);
			       
			        pom.getCompanyPage().getExcludeUI().click();
			        
			        pom.getCompanyPage().getYesButton().click();
			        System.out.println("Exclude Company");
			        Thread.sleep(2000);
			        
			        WebElement excludeLink = pom.getCompanyPage().getSearchCompName();
			        Assert.assertTrue(excludeLink.isDisplayed(), "Company field is displayed");
			        Assert.assertTrue(excludeLink.isEnabled(), "Company field is  enabled");
			        System.out.println("Company field is not enabled");
			        
			        Thread.sleep(4000);
				       
			        pom.getCompanyPage().getExcludeUI().click();
			        
			        pom.getCompanyPage().getYesButton().click();
			        System.out.println("Include Company");
			        Thread.sleep(2000);
			        
			        WebElement includeLink = pom.getCompanyPage().getSearchCompName();
			        Assert.assertTrue(includeLink.isDisplayed(), "Company field is Not displayed");
			        Assert.assertTrue(includeLink.isEnabled(), "Company field is Not enabled");
			        System.out.println("Company field is enabled");
			        Thread.sleep(2000);
			        
			        WebElement element1 = pom.getCompanyPage().getChangeLog();

		  		     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		  			    
			        Thread.sleep(4000);
			        pom.getCompanyPage().getChangeLog().click();
			        
			        WebElement changeHistory = pom.getCompanyPage().getChangeHistory();
			        Assert.assertTrue(changeHistory.isDisplayed(), "Company History field NOT is displayed");
			        Assert.assertTrue(changeHistory.isEnabled(), "Company History field is NOT enabled");
			        System.out.println("Company History field is enabled");
 	
	  	    	
	  }  
	    
	    @Test (priority=3)

  		public void changeAccountTest() throws InterruptedException, IOException
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
	  			  Thread.sleep(3000);
	  			    pom.getCompanyPage().getContractType().click();
	  			  Thread.sleep(2000);
	  			    pom.getCompanyPage().getYesButton().click();
	  			    System.out.println("Company changed to custom Type");
	  			  Thread.sleep(2000);
  		}
@AfterMethod
		
		public void tearDown()
		{
			driver.quit();
		}
}