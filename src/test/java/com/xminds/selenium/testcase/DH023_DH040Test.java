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

public class DH023_DH040Test extends Base {
	
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

		public void privacyLinkTest() throws InterruptedException, IOException
		{
			// TODO Auto-generated method stub
			 
			
			
		        
		        pom.getDashboardPage().getPrivacyText().click();
		        
		        Set<String> windowHandles = driver.getWindowHandles();
		        List<String> windowList = new ArrayList<>(windowHandles);

		      
		        driver.switchTo().window(windowList.get(1));

		        Thread.sleep(3000);
		        String expectedUrl = "https://www.datazoom.io/privacy-policy";
		        String actualUrl = driver.getCurrentUrl();

		        Assert.assertEquals(actualUrl, expectedUrl, "URL does not match");
		        System.out.println("Privacy Link opened is correct");
		       
		        driver.close();
		        driver.switchTo().window(windowList.get(0));
		        Thread.sleep(3000);

		}
		@Test (priority=2)

		public void termsLinkTestYear() throws InterruptedException, IOException
		{
			// TODO Auto-generated method stub
			 
		        pom.getDashboardPage().getTermsText().click();
		        Set<String> windowHandles1 = driver.getWindowHandles();
		       List<String> windowList1 = new ArrayList<>(windowHandles1);
		        driver.switchTo().window(windowList1.get(1));
		        Thread.sleep(3000);
		        String expectedUrl2 = "https://www.datazoom.io/terms-and-conditions/";
		        String actualUrl2 = driver.getCurrentUrl();
		        Assert.assertEquals(actualUrl2, expectedUrl2, "URL does not match");
		        System.out.println("Terms Link opened is correct");
		        driver.close();
		        driver.switchTo().window(windowList1.get(0));
		        Thread.sleep(3000);

		        
		        
		        String yearDatazoom = pom.getDashboardPage().getYearDatazoom().getText();
		        System.out.println(yearDatazoom);
			      Assert.assertTrue(yearDatazoom.contains("© 2025 Datazoom. All Rights Reserved."), "Year in Dashboard is NOT valid");
		}   
		        
		
			      @Test (priority=3)

					public void datacollconneLinkTest() throws InterruptedException, IOException
					{
						// TODO Auto-generated method stub
						 
						

						 pom.getDashboardPage().getDatapipesLink().click();
						 String expectedURLdata= "https://stagingapp-latest.datazoom.io/data-pipe-list";
						 String actualURLdata = driver.getCurrentUrl();
						 Assert.assertEquals(actualURLdata, expectedURLdata, "Datapipe Link Page NOT is correct!");
						 System.out.println("Datapipe Link opened is correct");
						 
						 pom.getDashboardPage().getCollectorsLink().click();
						 String expectedURLColle= "https://stagingapp-latest.datazoom.io/collector-list";
						 String actualURLColle = driver.getCurrentUrl();
						 Assert.assertEquals(actualURLColle,expectedURLColle, "Collectors Link Page NOT is correct!");
						 System.out.println("Collectors Link opened is correct");
						
						 
						 pom.getDashboardPage().getConnectorsLink().click();
						 String expectedURLConne= "https://stagingapp-latest.datazoom.io/connector-list";
						 String actualURLConne = driver.getCurrentUrl();
						 Assert.assertEquals(actualURLConne,expectedURLConne, "Connectors Link Page NOT is correct!");
						 System.out.println("Connectors Link opened is correct");
						 
						 pom.getDashboardPage().getDashboardLink().click();
						
				    }        
		        
		        
			      @Test (priority=4)

					public void settingsbuttonTest() throws InterruptedException, IOException
					{
						// TODO Auto-generated method stub
						 
						

						 
				       pom.getDashboardPage().getSettingsButton().click();
				        WebElement usersLink = pom.getDashboardPage().getUsers();
				        Assert.assertTrue(usersLink.isDisplayed(), "UsersLink field is not displayed");
				        Assert.assertTrue(usersLink.isEnabled(), "UsersLink field is not enabled");
				        System.out.println("UsersLink field is correct");
				        
				     
				        WebElement projectsLink = pom.getDashboardPage().getProjects();
				        Assert.assertTrue(projectsLink.isDisplayed(), "ProjectsLink field is not displayed");
				        Assert.assertTrue(projectsLink.isEnabled(), "ProjectsLink field is not enabled");
				        System.out.println("ProjectsLink field is correct");
				        
				        WebElement billingLink = pom.getDashboardPage().getBilling();
				        Assert.assertTrue(billingLink.isDisplayed(), "BillingLink field is not displayed");
				        Assert.assertTrue(billingLink.isEnabled(), "BillingLink field is not enabled");
				        System.out.println("BillingLink field is correct");
				        
				        WebElement companiesLink = pom.getDashboardPage().getCompanies();
				        Assert.assertTrue(companiesLink.isDisplayed(), "CompaniesLink field is not displayed");
				        Assert.assertTrue(companiesLink.isEnabled(), "CompaniesLink field is not enabled");
				        System.out.println("CompaniesLink field is correct");
				        
				        WebElement ratecardLink = pom.getDashboardPage().getRateCards();
				        Assert.assertTrue(ratecardLink.isDisplayed(), "RateCardsLink field is not displayed");
				        Assert.assertTrue(ratecardLink.isEnabled(), "RateCardsLink field is not enabled");
				        System.out.println("RateCardsLink field is correct");
				        
				        WebElement datapointManLink = pom.getDashboardPage().getDataPointManagement();
				        Assert.assertTrue(datapointManLink.isDisplayed(), "DataPointManagement Link field is not displayed");
				        Assert.assertTrue(datapointManLink.isEnabled(), "DataPointManagement Link field is not enabled");
				        System.out.println("DataPointManagement Link field is correct");
				        
				        WebElement collectorManLink = pom.getDashboardPage().getCollectorManagement();
				        Assert.assertTrue(collectorManLink.isDisplayed(), "CollectorManagement Link field is not displayed");
				        Assert.assertTrue(collectorManLink.isEnabled(), "CollectorManagement Link field is not enabled");
				        System.out.println("CollectorManagement Link field is correct");
				        
		       
				
					}
			      @Test (priority=5)

					public void userMenuLinkTest() throws InterruptedException, IOException
					{
						// TODO Auto-generated method stub
						 
						

					
				        pom.getDashboardPage().getUserMenu().click();
				        WebElement changePasswordLink = pom.getDashboardPage().getChangePassword();
				        Assert.assertTrue(changePasswordLink.isDisplayed(), "ChangePassword Link field is not displayed");
				        Assert.assertTrue(changePasswordLink.isEnabled(), "ChangePassword Link field is not enabled");
				        System.out.println("ChangePassword Link field is correct");
				        
				        WebElement helpSupportLink = pom.getDashboardPage().getHelpSupport();
				        Assert.assertTrue(helpSupportLink.isDisplayed(), "HelpSupport Link field is not displayed");
				        Assert.assertTrue(helpSupportLink.isEnabled(), "HelpSupport Link field is not enabled");
				        System.out.println("HelpSupport Link field is correct");
				        
				        WebElement logoutLink = pom.getDashboardPage().getLogout();
				        Assert.assertTrue(logoutLink.isDisplayed(), "Logout Link field is not displayed");
				        Assert.assertTrue(logoutLink.isEnabled(), "Logout Link field is not enabled");
				        System.out.println("Logout Link field is correct");
				        
				    }


@AfterMethod
		
		public void tearDown()
		{
			driver.quit();
		}
}