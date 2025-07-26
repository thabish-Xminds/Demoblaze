
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

public class ProductPageTest extends Base {

	public WebDriver driver;

	pomFile pom;

	public static Logger log = LogManager.getLogger(Base.class.getName());

	@BeforeMethod

	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();

		// driver.get("https://www.demoblaze.com/");

		Thread.sleep(5000);


	}

	@Test (priority=1)

	public void verifyConnectorPageTest() throws InterruptedException, IOException

	{

		private final By cartItems = By.cssSelector("#tbodyid tr");
	    private final By placeOrderButton = By.xpath("//button[text()='Place Order']");
	    private final By productNamesInCart = By.cssSelector("#tbodyid tr td:nth-child(2)");
	    private final By deleteLinks = By.cssSelector("#tbodyid tr td:nth-child(4) a");
	    
	    public List<WebElement> getCartItems() {
	        return driver.findElements(cartItems);
	    }
	    
	    public boolean isProductInCart(String productName) {
	        List<WebElement> productElements = driver.findElements(productNamesInCart);
	        return productElements.stream()
	                .anyMatch(element -> element.getText().contains(productName));
	    }
	    
	    public void placeOrder() {
	        clickElement(placeOrderButton);
	    }
	    
	    public int getCartItemCount() {
	        return getCartItems().size();
	    }
	}

	@AfterMethod

	public void tearDown()

	{

		driver.quit();

	}

}



 


