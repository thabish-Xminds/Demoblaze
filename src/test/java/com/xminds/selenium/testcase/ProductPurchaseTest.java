package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigReader;
import utils.Constants;
import utils.TestDataGenerator;

public class ProductPurchaseTest extends BaseTest {

	@Test(description = "Complete product purchase flow on Demoblaze")
	public void testCompleteProductPurchaseFlow() {
		// Generate test data
		TestDataGenerator.UserData userData = new TestDataGenerator.UserData();
		TestDataGenerator.CheckoutData checkoutData = new TestDataGenerator.CheckoutData();
		String productName = ConfigReader.getProperty("product.name");

		// Initialize page objects
		HomePage homePage = new HomePage();
		AuthPage authPage = new AuthPage();
		ProductPage productPage = new ProductPage();
		CartPage cartPage = new CartPage();
		CheckoutPage checkoutPage = new CheckoutPage();

		// Step 1: Launch browser and open demoblaze
		homePage.openHomePage();
		Assert.assertTrue(homePage.isHomePageLoaded(), "Home page failed to load");

		// Step 2: Register a new user
		authPage.clickSignUp();
		String signupMessage = authPage.signUp(userData.getUsername(), userData.getPassword());
		Assert.assertTrue(signupMessage.contains(Constants.SIGNUP_SUCCESS_MESSAGE),
				"Sign up was not successful. Message: " + signupMessage);

		// Step 3: Login with registered credentials
		authPage.clickLogin();
		authPage.login(userData.getUsername(), userData.getPassword());

		// Verify login success
		Assert.assertTrue(authPage.isLoggedIn(), "User login failed");
		String welcomeText = authPage.getWelcomeText();
		Assert.assertTrue(welcomeText.contains(userData.getUsername()),
				"Welcome message doesn't contain username");

		// Step 4: Select a product from home page
		homePage.selectProductByName(productName);

		// Step 5: Add product to cart and verify confirmation
		String confirmationMessage = productPage.addToCart();
		Assert.assertTrue(confirmationMessage.contains(Constants.PRODUCT_ADDED_MESSAGE),
				"Product was not added to cart. Message: " + confirmationMessage);

		// Step 6: Navigate to cart and verify product is displayed
		homePage.goToCart();
		Assert.assertTrue(cartPage.isProductInCart(productName),
				"Product is not present in cart");
		Assert.assertTrue(cartPage.getCartItemCount() > 0, "Cart is empty");

		// Step 7: Proceed to checkout and fill payment details
		cartPage.placeOrder();
		checkoutPage.fillCheckoutForm(checkoutData);

		// Step 8: Place order and verify success message
		checkoutPage.completePurchase();
		String successMessage = checkoutPage.getSuccessMessage();
		Assert.assertTrue(successMessage.contains(Constants.PURCHASE_SUCCESS_MESSAGE),
				"Purchase was not successful. Message: " + successMessage);

		// Get order details for verification
		String orderDetails = checkoutPage.getOrderDetails();
		Assert.assertTrue(orderDetails.contains(checkoutData.getName()),
				"Order details don't contain customer name");

		// Close success dialog
		checkoutPage.closeSuccessDialog();

		// Step 9: Logout
		authPage.logout();
		Assert.assertFalse(authPage.isLoggedIn(), "User logout failed");

		// Print test results
		System.out.println("✅ Test completed successfully!");
		System.out.println("User created: " + userData.getUsername());
		System.out.println("Product purchased: " + productName);
		System.out.println("Order details: " + orderDetails);
	}

	@Test(description = "Verify user can add product to cart", dataProvider = "productData")
	public void testAddProductToCart(String productName) {
		TestDataGenerator.UserData userData = new TestDataGenerator.UserData();

		HomePage homePage = new HomePage();
		AuthPage authPage = new AuthPage();
		ProductPage productPage = new ProductPage();
		CartPage cartPage = new CartPage();

		// Setup: Login
		homePage.openHomePage();
		authPage.clickSignUp();
		authPage.signUp(userData.getUsername(), userData.getPassword());
		authPage.clickLogin();
		authPage.login(userData.getUsername(), userData.getPassword());

		// Test: Add product to cart
		homePage.selectProductByName(productName);
		String confirmation = productPage.addToCart();
		Assert.assertTrue(confirmation.contains(Constants.PRODUCT_ADDED_MESSAGE));

		// Verify in cart
		homePage.goToCart();
		Assert.assertTrue(cartPage.isProductInCart(productName));

		// Cleanup
		authPage.logout();
	}

	@org.testng.annotations.DataProvider(name = "productData")
	public Object[][] getProductData() {
		return new Object[][] {
				{"Samsung galaxy s6"},
				{"Nokia lumia 1520"},
				{"Nexus 6"}
		};
	}
}

// src/test/resources/testng.xml
/*
<?xml version="1.0" encoding="UTF-8"?>
<suite name="DemoblazeSuite" verbose="1">
    <parameter name="browser" value="chrome"/>

    <listeners>
        <listener class-name="listeners.TestListener"/>
    </listeners>

    <test name="ProductPurchaseTest">
        <classes>
            <class name="tests.ProductPurchaseTest"/>
        </classes>
    </test>
</suite>
*/