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

public class UC017_UC040Test extends Base {
	
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

		public void NewusersPageTest() throws InterruptedException, IOException
		{
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(140));
	    	
	    	 JavascriptExecutor js = (JavascriptExecutor) driver;
	   
			pom.getUsersPage().getDashCompany().click();
			
			WebElement dashComp = wait.until(ExpectedConditions.visibilityOf(pom.getUsersPage().getSearchCompany()));
			
			pom.getUsersPage().getSearchCompany().click();
			
			pom.getUsersPage().getSearchCompany().sendKeys(CacheManager.getString(selenium_companyName));
			
			 WebElement emailBody = wait.until(ExpectedConditions.visibilityOfElementLocated(
					 By.xpath(CacheManager.getString(selenium_span_companyName))));
			
			//Thread.sleep(1000);
			
			driver.findElement(By.xpath(CacheManager.getString(selenium_span_companyName))).click();
			Thread.sleep(2000);
			
			driver.navigate().refresh();
			
			 System.out.println("Opened new company users");
			
			pom.getDashboardPage().getSettingsButton().click();
			
			pom.getDashboardPage().getUsers().click();
			
			pom.getUsersPage().getCreateUser().click();
	    	
			  WebElement fullnameField= pom.getUsersPage().getSelectFullName();
		        Assert.assertTrue(fullnameField.isDisplayed(), "Fullname Field is not displayed");
		        Assert.assertTrue(fullnameField.isEnabled(), "Fullname Field is not enabled");
		        System.out.println("fullname Field  is correct");
		        
		        WebElement emailField= pom.getUsersPage().getSelectEmail();
		        Assert.assertTrue(emailField.isDisplayed(), "Email Field is not displayed");
		        Assert.assertTrue(emailField.isEnabled(), "Email Field is not enabled");
		        System.out.println("Email Field  is correct");
		        
		        WebElement roleField= pom.getUsersPage().getSelectRole();
		        Assert.assertTrue(roleField.isDisplayed(), "RoleField is not displayed");
		        Assert.assertTrue(roleField.isEnabled(), "RoleField is not enabled");
		        System.out.println("Role Field  is correct");
		        
		        pom.getUsersPage().getSelectRole().click();
		        Thread.sleep(2000);
		        WebElement customerAdminField= pom.getUsersPage().getCustomerAdminRole();
		        Assert.assertTrue(customerAdminField.isDisplayed(), "CustomerAdmin Field is not displayed");
		        Assert.assertTrue(customerAdminField.isEnabled(), "CustomerAdmin Field is not enabled");
		        System.out.println("customerAdmin Field  is correct");
			
		        WebElement adminField= pom.getUsersPage().getAdminRole();
		        Assert.assertTrue(adminField.isDisplayed(), "Admin Field is not displayed");
		        Assert.assertTrue(adminField.isEnabled(), "Admin Field is not enabled");
		        System.out.println("Admin Field  is correct");
		        
		        WebElement billAdminField= pom.getUsersPage().getBillAdminRole();
		        Assert.assertTrue(billAdminField.isDisplayed(), "BillAdmin Field is not displayed");
		        Assert.assertTrue(billAdminField.isEnabled(), "BillAdmin Field is not enabled");
		        System.out.println("BillAdmin Field is correct");
		        
		        WebElement userField= pom.getUsersPage().getUserRole();
		        Assert.assertTrue(userField.isDisplayed(), "User Field is not displayed");
		        Assert.assertTrue(userField.isEnabled(), "UserField is not enabled");
		        System.out.println("UserField is correct");
		        
		        WebElement searchUsersField= pom.getUsersPage().getSearchUsers();
		        Assert.assertTrue(searchUsersField.isDisplayed(), "Search Users Field is not displayed");
		        Assert.assertTrue(searchUsersField.isEnabled(), "Search Users Field is not enabled");
		        System.out.println("Search Users Field is correct");
		        
		        
		        WebElement activeFilter= pom.getUsersPage().getActiveFilter();
		        Assert.assertTrue(activeFilter.isDisplayed(), "Active Filter Field is not displayed");
		        Assert.assertTrue(activeFilter.isEnabled(), "Active Filter Field is not enabled");
		        System.out.println("Active Filter is correct");
		        driver.navigate().refresh();

				  Thread.sleep(3000);
				  
		      
		        WebElement closeFilter = wait.until(ExpectedConditions.visibilityOf(pom.getUsersPage().getCloseActiveFilter()));
		        pom.getUsersPage().getCloseActiveFilter().click();
		        System.out.println("Closing the Active Filter is correct");
		        
		        Thread.sleep(1000);
		        pom.getUsersPage().getInvitedUser().click();
		        
		        WebElement statusInvited= pom.getUsersPage().getStatusInvited();
		        Assert.assertTrue(statusInvited.isDisplayed(), "StatusInvited Field is not displayed");
		        Assert.assertTrue(statusInvited.isEnabled(), "StatusInvited Field is not enabled");
		        System.out.println("StatusInvited Field is correct");
		        
		        pom.getUsersPage().getAddFilter().click();
		        
		        WebElement emailBody1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
  		                By.xpath("//label[@id='filterBy-label']")));
		      
		        pom.getUsersPage().getfilterBy().click();

				  Thread.sleep(3000);
				  
  		        		        
		      //  pom.getUsersPage().getfilterBy().click();
		        
		        WebElement statusSelect= pom.getUsersPage().getSelectStatus();
		        Assert.assertTrue(statusSelect.isDisplayed(), "Status filter Field is not displayed");
		        Assert.assertTrue(statusSelect.isEnabled(), "Status filter Field is not enabled");
		        System.out.println("Status filter Field is correct");
		        
		        WebElement statusRole= pom.getUsersPage().getFilterRole();
		        Assert.assertTrue(statusRole.isDisplayed(), "Status Role Field is not displayed");
		        Assert.assertTrue(statusRole.isEnabled(), "Status Role Field is not enabled");
		        System.out.println("Status Role filter Field is correct");
		        
		        WebElement statusCompany= pom.getUsersPage().getFilterCompany();
		        Assert.assertTrue(statusCompany.isDisplayed(), "StatusCompany Field is not displayed");
		        Assert.assertTrue(statusCompany.isEnabled(), "StatusCompany Field is not enabled");
		        System.out.println("StatusCompany filter Field is correct");
		        
		        Thread.sleep(3000);
		        pom.getUsersPage().getSelectStatus().click();
		        
		        pom.getUsersPage().getFilterValue().click();
  		        WebElement listcompany2 = wait.until(ExpectedConditions.visibilityOf(pom.getUsersPage().getFilterInvited()));
  		        
  		      pom.getUsersPage().getFilterInvited().click();
  		        
  		    Thread.sleep(3000);
   		       js.executeScript("arguments[0].click();",  pom.getUsersPage().getApplyFilter());
  		       
  		        System.out.println("Searching for Invited Users");
  		      Thread.sleep(2000);
  		      

		        WebElement closeFilter1 = wait.until(ExpectedConditions.visibilityOf(pom.getUsersPage().getCloseActiveFilter()));
		        pom.getUsersPage().getCloseActiveFilter().click();
		        System.out.println("Closing the Filter is correct");
		        
                 pom.getUsersPage().getAddFilter().click();
		        
		        WebElement emailBody2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
  		                By.xpath("//label[@id='filterBy-label']")));
		      
		        pom.getUsersPage().getfilterBy().click();

				  Thread.sleep(3000);
				  pom.getUsersPage().getFilterRole().click();
				  
				  pom.getUsersPage().getFilterValue().click();
	  		        WebElement listcompany3 = wait.until(ExpectedConditions.visibilityOf(pom.getUsersPage().getAdminRole()));
	  		        
	  		      pom.getUsersPage().getAdminRole().click();
	  		      
	  		    Thread.sleep(3000);
	   		       js.executeScript("arguments[0].click();",  pom.getUsersPage().getApplyFilter());
	  		       
	  		        System.out.println("Searching for role Users");
	  		      Thread.sleep(2000);
	  		      
	  		   	  		        
		}

@AfterMethod
		
		public void tearDown()
		{
			driver.quit();
		}
}