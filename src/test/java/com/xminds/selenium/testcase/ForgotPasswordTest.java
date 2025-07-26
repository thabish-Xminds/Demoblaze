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






public class ForgotPasswordTest extends Base {
	
	 public WebDriver driver;
	 pomFile pom;
	    public static Logger log = LogManager.getLogger(Base.class.getName());

   
	  @BeforeMethod
	    public void initialize() throws IOException {
	        driver = initializeDriver();
	      
	        driver.get(CacheManager.getString(selenium_test_url_staging));
	        pom = new pomFile(driver);
	  }     

	  @Test(priority=1)
	    public void TS026_TS029() throws InterruptedException {
		  
		  pom.getLoginPage().getForgotPass().click();
		  String expectedURL= CacheManager.getString(selenium_test_staging_forgotUrl);
		    String actualURL = driver.getCurrentUrl();
		    Assert.assertEquals(actualURL, expectedURL, "ForgotPass page URL is correct!");
		    System.out.println("Forgot password link field is correct");
	        
		    
		    WebElement emailField = pom.getLoginPage().getUsername();
		      Assert.assertTrue(emailField.isDisplayed(), "Email field is not displayed");
		      Assert.assertTrue(emailField.isEnabled(), "Email field is not enabled");
		      System.out.println("Email field in forgot password is correct");
		        
		      
		      WebElement resetInst = pom.getLoginPage().getResetInstructions();
		      Assert.assertTrue(resetInst.isDisplayed(), "ResetLink is NOT displayed");
		      Assert.assertFalse(resetInst.isEnabled(), "Reset button is ENABLED when it should be disabled");
		      System.out.println("Reset link in forgot password page is correct");
		        
		      WebElement returnLogin = pom.getLoginPage().getReturnToLoginLink();
		      Assert.assertTrue(returnLogin.isDisplayed(), "Return To Login is not displayed");
		      Assert.assertTrue(returnLogin.isEnabled(), "Return To Login not enabled");
		      System.out.println("Return link in forgot password field is correct");
		        
		     
		      
	  }
	  @Test(priority=2)
	    public void TS030_TS031() throws InterruptedException {
		  
		      pom.getLoginPage().getForgotPass().click();
		
		      pom.getLoginPage().getUsername().sendKeys(CacheManager.getString(selenium_test_staging_invaliduser));
			  WebElement resetIn = pom.getLoginPage().getResetInstructions();
		      Assert.assertTrue(resetIn.isEnabled(),"ResetButton is enabled");
		      System.out.println("Reset button in forgot password field is correct");
		        
		        
		      
		      pom.getLoginPage().getResetInstructions().click();
		      String errorMessage = pom.getLoginPage().getInvalidPassword().getText();
		      Assert.assertTrue(errorMessage.contains("Invalid"), "Password is NOT Invalid");   
		      System.out.println("Invalid Password in forgot password field is correct");
		        
			  
		      String returnLogin = pom.getLoginPage().getReturnToLoginButton().getText();
		      Assert.assertTrue(returnLogin.contains("Return to login"), "Return to Login Button is NOT valid");
		      
		      String resetPassAgain = pom.getLoginPage().getResetPassAgain().getText();
		      Assert.assertTrue(resetPassAgain.contains("Reset password again"), "Reset password again is NOT valid"); 
		      
		      pom.getLoginPage().getResetPassAgain().click();
		      pom.getLoginPage().getUsername().sendKeys(CacheManager.getString(selenium_test_staging_invaliduser));
		      pom.getLoginPage().getResetInstructions().click();
		      pom.getLoginPage().getReturnToLoginButton().click();
		      String expectedURL2= CacheManager.getString(selenium_test_url_staging);
		      String actualURL2 = driver.getCurrentUrl();
			  Assert.assertEquals(actualURL2, expectedURL2, "Moved to Login Page");
			  System.out.println("Login in  forgot password field is correct");
		        
	  }
	  
	  @Test(priority=3)
	    public void TS032() throws InterruptedException {
		  pom.getLoginPage().getForgotPass().click();
			
		    
	      pom.getLoginPage().getUsername().sendKeys(CacheManager.getString(selenium_test_username));
	      pom.getLoginPage().getResetInstructions().click();
	   // String recMessage= pom.getLoginPage().getRecoveryLinkText().getText();
	    //  Assert.assertTrue(recMessage.contains("Recovery link sent"), "Recovery link sent NOT valid");
	      String retnLogin= pom.getLoginPage().getReturnToLoginButton().getText();
	      Assert.assertTrue(retnLogin.contains("Return to login"), "Return to login is NOT valid"); 
	      System.out.println("Return link in forgot password field is correct");
	        
	      String resetPassAgain = pom.getLoginPage().getResetPassAgain().getText();
	      Assert.assertTrue(resetPassAgain.contains("Reset password again"), "Reset password again is NOT valid");
	      System.out.println("Reset password in forgot password field is correct");
	        
	      
	      
	  }
	  @AfterMethod
		
		public void tearDown()
		{
			driver.close();
		}
		  
}
