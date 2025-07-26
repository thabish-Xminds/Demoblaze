package pages;

import base.BasePage;
import org.openqa.selenium.By;

public class ProductPage extends BasePage {
	// Locators
	private final By addToCartButton = By.xpath("//a[contains(text(), 'Add to cart')]");
	private final By productName = By.cssSelector(".name");
	private final By productPrice = By.cssSelector(".price-container");

	public String addToCart() {
		clickElement(addToCartButton);
		return handleAlert();
	}

	public String getProductName() {
		return getText(productName);
	}

	public String getProductPrice() {
		return getText(productPrice);
	}
}

// src/main/java/pages/CartPage.java
package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {
	// Locators
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

// src/main/java/pages/CheckoutPage.java
package pages;

import base.BasePage;
import org.openqa.selenium.By;
import utils.TestDataGenerator;

public class CheckoutPage extends BasePage {
	// Locators
	private final By nameInput = By.id("name");
	private final By countryInput = By.id("country");
	private final By cityInput = By.id("city");
	private final By cardInput = By.id("card");
	private final By monthInput = By.id("month");
	private final By yearInput = By.id("year");
	private final By purchaseButton = By.xpath("//button[text()='Purchase']");
	private final By successMessage = By.cssSelector(".sweet-alert h2");
	private final By orderDetails = By.cssSelector(".sweet-alert p");
	private final By okButton = By.cssSelector(".sweet-alert .confirm");

	public void fillCheckoutForm(TestDataGenerator.CheckoutData checkoutData) {
		sendKeys(nameInput, checkoutData.getName());
		sendKeys(countryInput, checkoutData.getCountry());
		sendKeys(cityInput, checkoutData.getCity());
		sendKeys(cardInput, checkoutData.getCard());
		sendKeys(monthInput, checkoutData.getMonth());
		sendKeys(yearInput, checkoutData.getYear());
	}

	public void completePurchase() {
		clickElement(purchaseButton);
	}

	public String getSuccessMessage() {
		return getText(successMessage);
	}

	public String getOrderDetails() {
		return getText(orderDetails);
	}

	public void closeSuccessDialog() {
		clickElement(okButton);
	}
}

// src/main/java/base/BaseTest.java
package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.ConfigReader;
import utils.DriverFactory;

public class BaseTest {

	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser) {
		String browserName = (browser != null) ? browser : ConfigReader.getBrowser();
		DriverFactory.setDriver(browserName);
	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}

// src/main/java/listeners/TestListener.java
package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		takeScreenshot(result.getMethod().getMethodName());
	}

	private void takeScreenshot(String testName) {
		try {
			TakesScreenshot screenshot = (TakesScreenshot) DriverFactory.getDriver();
			File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String fileName = testName + "_" + timestamp + ".png";
			File destFile = new File("screenshots/" + fileName);

			FileUtils.copyFile(sourceFile, destFile);
			System.out.println("Screenshot saved: " + destFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}




