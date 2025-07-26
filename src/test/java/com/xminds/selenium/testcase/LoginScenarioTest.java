package com.xminds.selenium.testcase;






import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.xminds.selenium.configurationfiles.Base;

import com.xminds.selenium.configurationfiles.ExcelUtils;

import com.xminds.selenium.pomcollection.pomFile;
import com.xminds.selenium.util.CacheManager;






public class LoginScenarioTest extends Base {
	
	 public WebDriver driver;
	 pomFile pom;
	    public static Logger log = LogManager.getLogger(Base.class.getName());

   
	  @BeforeMethod
	    public void initialize() throws IOException {
	        driver = initializeDriver();
	      
	        driver.get(CacheManager.getString(selenium_test_url_staging));
	        pom = new pomFile(driver);
	  }     

	  @Test(priority = 1)
	    public void TS001_TS008() throws InterruptedException {
	    	
		
		  String expectedURL= CacheManager.getString(selenium_test_url_staging);
		 //  String expectedURL = "https://stagingapp-latest.datazoom.io/login";
		    String actualURL = driver.getCurrentUrl();
		    Assert.assertEquals(actualURL, expectedURL, "Login page URL is correct!");
			
	    	String logoMessage=pom.getLoginPage().getLogoMessage().getText();
	    	Assert.assertTrue(logoMessage.contains("The Real-time Data-as-a-Service Platform Capture - Process - Deliver"), "Logo Displayed");
	    	 System.out.println("Login pAGE opened is correct");
	    	
	    	WebElement emailField = pom.getLoginPage().getUsername();
	        Assert.assertTrue(emailField.isDisplayed(), "Email field is not displayed");
	        Assert.assertTrue(emailField.isEnabled(), "Email field is not enabled");
	        System.out.println("Email field is correct");
	        
	        WebElement passwordField = pom.getLoginPage().getPassword();
	        Assert.assertTrue(passwordField.isDisplayed(), "Password field is not displayed");
	        Assert.assertTrue(passwordField.isEnabled(), "Password field is not enabled");
	        System.out.println("password field is correct");
	        
	    	
	        
	        WebElement loginButton = pom.getLoginPage().getLoginButton();
	        Assert.assertTrue(loginButton.isDisplayed(), "LoginButton is not displayed");
	        Assert.assertFalse(loginButton.isEnabled(),"LoginButton is not enabled");
	        System.out.println("Login button field is correct");
	        
	        
	        WebElement signupLink = pom.getLoginPage().getSignUptoday();
	        Assert.assertTrue(signupLink.isDisplayed(), "signupLink is not displayed");
	        Assert.assertTrue(signupLink.isEnabled(), "signupLink is not enabled");
	        System.out.println("Signup field is correct");
	        
	        
	        WebElement forgotPass = pom.getLoginPage().getForgotPass();
	        Assert.assertTrue(forgotPass.isDisplayed(), "forgotPass is not displayed");
	        Assert.assertTrue(forgotPass.isEnabled(), "forgotPass is not enabled"); 
	        System.out.println("Forgot Password field is correct");
	        
	          
	    }
	
	  @Test(priority = 2)
	    public void TS009_TS013() throws InterruptedException {
		  
		  pom.getLoginPage().getUsername().sendKeys(CacheManager.getString(selenium_test_staging_invaliduser));
		  pom.getLoginPage().getPassword().sendKeys("password");
		  WebElement eyeButton = pom.getLoginPage().getEyeButton();
	       Assert.assertTrue(eyeButton.isEnabled(),"EyeButton is disabled");
	       System.out.println("Eye button field is correct");
	        
	       
		  WebElement loginButton = pom.getLoginPage().getLoginButton();
	        Assert.assertTrue(loginButton.isEnabled(),"LoginButton is enabled");
	        
		   pom.getLoginPage().getLoginButton().click();
	        Thread.sleep(2000);
	        String errorMessage = pom.getLoginPage().getErrorMessage().getText();
	        Assert.assertTrue(errorMessage.contains("Incorrect username or password"), "Error message is not displayed!");  
	        System.out.println("Invalid users field validation is correct");
	        
	  }
		  

	  @Test(priority = 3)
	    public void TS14_TS018() throws InterruptedException {
		  
		  WebElement loginButton = pom.getLoginPage().getLoginButton();
	        Assert.assertTrue(loginButton.isDisplayed(), "LoginButton is not displayed");
	        System.out.println("Without data valaidation is correct");
	        
	        
	        pom.getLoginPage().getUsername().sendKeys("");
	        Assert.assertTrue(loginButton.isDisplayed(), "LoginButton is not displayed");
	        System.out.println("no data user validation is correct");
	        
	        pom.getLoginPage().getPassword().sendKeys("");
	        Assert.assertTrue(loginButton.isDisplayed(), "LoginButton is not displayed");
	        System.out.println("No password validation is correct");
	        
	        
	          
		  
	  }     

	  @Test(priority = 4)
	    public void TS33_TS035() throws InterruptedException {
		  
			 pom.getLoginPage().getUsername().click();
			 pom.getLoginPage().getUsername().sendKeys(CacheManager.getString(selenium_test_username)); 
			 pom.getLoginPage().getPassword().click();
			 pom.getLoginPage().getPassword().sendKeys(CacheManager.getString(selenium_test_password));
			 pom.getLoginPage().getLoginButton().click();
			 Thread.sleep(5000);
			 String expectedURL= CacheManager.getString(selenium_test_stagingurl_dashboard); 
			 String actualURL = driver.getCurrentUrl();
			 Assert.assertEquals(actualURL, expectedURL, "Dashboard Page NOT is correct!");
			 pom.getLoginPage().getUserMenu().click();
			 pom.getLoginPage().getLogoutButton().click();
			 System.out.println("valid user & password is correct");
		        
	  }
	  @AfterMethod
		
		public void tearDown()
		{
			driver.close();
		}
	   
}
