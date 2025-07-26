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






public class SignUpPageTest extends Base {
	
	 public WebDriver driver;
	 pomFile pom;
	    public static Logger log = LogManager.getLogger(Base.class.getName());

   
	  @BeforeMethod
	    public void initialize() throws IOException {
	        driver = initializeDriver();
	      
	        driver.get(CacheManager.getString(selenium_test_url_staging));
	        pom = new pomFile(driver);
	  }     

	  @Test
	    public void TS018_TS025() throws InterruptedException {
	    	
		  pom.getLoginPage().getSignUptoday().click();
		  String expectedURL= CacheManager.getString(selenium_test_staging_signup);
		    String actualURL = driver.getCurrentUrl();
		    Assert.assertEquals(actualURL, expectedURL, "SignUp page URL is correct!");
		    System.out.println("Sign in page validation is correct");
	        
		    
	
	
	  WebElement emailField = pom.getLoginPage().getUsername();
      Assert.assertTrue(emailField.isDisplayed(), "Email field is not displayed");
      Assert.assertTrue(emailField.isEnabled(), "Email field is not enabled");
      System.out.println("Email field is correct");
      
      
      WebElement signupButton = pom.getLoginPage().getSignUpButton();
      Assert.assertTrue(signupButton.isDisplayed(), "signupLink is NOT displayed");
      Assert.assertFalse(signupButton.isEnabled(), "Sign Up button is ENABLED when it should be disabled");
      System.out.println("Signup page validation is correct");
      
      
      
      WebElement returnLogin = pom.getLoginPage().getReturnToLoginLink();
      Assert.assertTrue(returnLogin.isDisplayed(), "Return To Login is not displayed");
      Assert.assertTrue(returnLogin.isEnabled(), "Return To Login not enabled");      
      System.out.println("Return page to sign  in page is correct");
      
     
      
		  pom.getLoginPage().getUsername().sendKeys(CacheManager.getString(selenium_test_staging_invaliduser));
		  WebElement signButton = pom.getLoginPage().getSignUpButton();
	      Assert.assertTrue(signButton.isEnabled(),"LoginButton is enabled");
	      System.out.println("Invalid Sign in Validation is correct");
	        
	      pom.getLoginPage().getSignUpButton().click();
	      String errorMessage = pom.getLoginPage().getInvalidEmail().getText();
	      Assert.assertTrue(errorMessage.contains("Invalid Email"), "Email is Invalid");   
	      System.out.println("In valid Email field validation In sign in page is correct");
	        
		  
	      pom.getLoginPage().getReturnToSignup().click();
	      Assert.assertEquals(actualURL, expectedURL, "SignUp page URL is correct!");
	      
	      System.out.println("Sign in page is correct");
	        
	      
	    /*  pom.getLoginPage().getUsername().sendKeys(CacheManager.getString(selenium_test_staging_validsignupuser));
		  pom.getLoginPage().getSignUpButton().click();
		  
		  String errorMessage2 = pom.getLoginPage().getEmailSent().getText();
		  Assert.assertTrue(errorMessage2.contains("Email verification sent"), "Email verification sent");  
		  Thread.sleep(2000);
		  pom.getLoginPage().getReturnToLoginButton().click();
		  String expectedURL2= CacheManager.getString(selenium_test_url_staging);
		 
		    String actualURL2 = driver.getCurrentUrl();
		    Assert.assertEquals(actualURL2, expectedURL2, "SignUp page URL is correct!");*/

	  }
	  @AfterMethod
		
		public void tearDown()
		{
			driver.close();
		}
}
