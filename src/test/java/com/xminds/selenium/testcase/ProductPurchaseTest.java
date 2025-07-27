package com.xminds.selenium.testcase;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import pages.*;
import utils.ConfigReader;
import utils.Constants;
import utils.TestDataGenerator;

public class ProductPurchaseTest extends BaseTest {

    @Test(description = "Complete product purchase flow on Demoblaze", priority = 1)
    public void testCompleteProductPurchaseFlow() {
        // Generate test data
        TestDataGenerator.UserData userData = null;
        TestDataGenerator.CheckoutData checkoutData = null;
        String productName = null;
        
        try {
            userData = new TestDataGenerator.UserData();
            checkoutData = new TestDataGenerator.CheckoutData();
            productName = ConfigReader.getProperty("product.name");
            
            // Validate required data
            Assert.assertNotNull(userData, "Failed to generate user data");
            Assert.assertNotNull(checkoutData, "Failed to generate checkout data");
            Assert.assertNotNull(productName, "Product name not found in configuration");
            Assert.assertFalse(productName.trim().isEmpty(), "Product name cannot be empty");

        } catch (Exception e) {
            Assert.fail("Failed to initialize test data: " + e.getMessage());
        }

        // Initialize page objects
        HomePage homePage = new HomePage();
        AuthPage authPage = new AuthPage();
        ProductPage productPage = new ProductPage();
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();

        try {
            // Step 1: Launch browser and open demoblaze
            homePage.openHomePage();
            Assert.assertTrue(homePage.isHomePageLoaded(), "Home page failed to load");

            // Step 2: Register a new user
            authPage.clickSignUp();
            String signupMessage = authPage.signUp(userData.getUsername(), userData.getPassword());
            Assert.assertNotNull(signupMessage, "Signup message is null");
            Assert.assertTrue(signupMessage.contains(Constants.SIGNUP_SUCCESS_MESSAGE),
                    "Sign up was not successful. Expected: " + Constants.SIGNUP_SUCCESS_MESSAGE + 
                    ", Actual: " + signupMessage);

            // Step 3: Login with registered credentials
            authPage.clickLogin(); 
            authPage.login(userData.getUsername(), userData.getPassword());

            // Verify login success
            Assert.assertTrue(authPage.isLoggedIn(), "User login failed");
            String welcomeText = authPage.getWelcomeText();
            Assert.assertNotNull(welcomeText, "Welcome text is null");
            Assert.assertTrue(welcomeText.contains(userData.getUsername()),
                    "Welcome message doesn't contain username. Expected to contain: " + 
                    userData.getUsername() + ", Actual: " + welcomeText);

            // Step 4: Select a product from home page
            Assert.assertTrue(homePage.isProductAvailable(productName), 
                    "Product '" + productName + "' is not available on home page");
            homePage.selectProductByName(productName);

            // Step 5: Add product to cart and verify confirmation
            String confirmationMessage = productPage.addToCart();
            Assert.assertNotNull(confirmationMessage, "Confirmation message is null");
            Assert.assertTrue(confirmationMessage.contains(Constants.PRODUCT_ADDED_MESSAGE),
                    "Product was not added to cart. Expected: " + Constants.PRODUCT_ADDED_MESSAGE + 
                    ", Actual: " + confirmationMessage);

            // Step 6: Navigate to cart and verify product is displayed
            homePage.goToCart();
            Assert.assertTrue(cartPage.isProductInCart(productName),
                    "Product '" + productName + "' is not present in cart");
            int cartItemCount = cartPage.getCartItemCount();
            Assert.assertTrue(cartItemCount > 0, "Cart is empty. Item count: " + cartItemCount);

            // Step 7: Proceed to checkout and fill payment details
            cartPage.placeOrder();
            checkoutPage.fillCheckoutForm(checkoutData);

            // Step 8: Place order and verify success message
            checkoutPage.completePurchase();
            String successMessage = checkoutPage.getSuccessMessage();
            Assert.assertNotNull(successMessage, "Success message is null");
            Assert.assertTrue(successMessage.contains(Constants.PURCHASE_SUCCESS_MESSAGE),
                    "Purchase was not successful. Expected: " + Constants.PURCHASE_SUCCESS_MESSAGE + 
                    ", Actual: " + successMessage);

            // Get order details for verification
            String orderDetails = checkoutPage.getOrderDetails();
            Assert.assertNotNull(orderDetails, "Order details are null");
            Assert.assertTrue(orderDetails.contains(checkoutData.getName()),
                    "Order details don't contain customer name. Expected to contain: " + 
                    checkoutData.getName() + ", Actual: " + orderDetails);

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
            
        } catch (Exception e) {
            System.err.println("❌ Test failed with exception: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test execution failed: " + e.getMessage());
        }
    }

    @Test(description = "Verify user can add product to cart", 
          dataProvider = "productData", 
          priority = 2,
          dependsOnMethods = {"testCompleteProductPurchaseFlow"})
    public void testAddProductToCart(String productName) {
        TestDataGenerator.UserData userData = null;
        
        try {
            userData = new TestDataGenerator.UserData();
            Assert.assertNotNull(userData, "Failed to generate user data");
            Assert.assertNotNull(productName, "Product name is null");
            Assert.assertFalse(productName.trim().isEmpty(), "Product name cannot be empty");
        } catch (Exception e) {
            Assert.fail("Failed to initialize test data: " + e.getMessage());
        }

        HomePage homePage = new HomePage();
        AuthPage authPage = new AuthPage();
        ProductPage productPage = new ProductPage();
        CartPage cartPage = new CartPage();

        try {
            // Setup: Login
            homePage.openHomePage();
            Assert.assertTrue(homePage.isHomePageLoaded(), "Home page failed to load");
            
            authPage.clickSignUp();
            String signupMessage = authPage.signUp(userData.getUsername(), userData.getPassword());
            Assert.assertTrue(signupMessage.contains(Constants.SIGNUP_SUCCESS_MESSAGE),
                    "Sign up failed for user: " + userData.getUsername());
            
            authPage.clickLogin();
            authPage.login(userData.getUsername(), userData.getPassword());
            Assert.assertTrue(authPage.isLoggedIn(), "Login failed for user: " + userData.getUsername());

            // Test: Add product to cart
            Assert.assertTrue(homePage.isProductAvailable(productName), 
                    "Product '" + productName + "' is not available");
            homePage.selectProductByName(productName);
            
            String confirmation = productPage.addToCart();
            Assert.assertNotNull(confirmation, "Add to cart confirmation is null");
            Assert.assertTrue(confirmation.contains(Constants.PRODUCT_ADDED_MESSAGE),
                    "Failed to add product to cart. Product: " + productName);

            // Verify in cart
            homePage.goToCart();
            Assert.assertTrue(cartPage.isProductInCart(productName),
                    "Product '" + productName + "' not found in cart");
            Assert.assertTrue(cartPage.getCartItemCount() > 0, 
                    "Cart is empty after adding product: " + productName);

        } catch (Exception e) {
            System.err.println("❌ Test failed for product: " + productName + 
                             " with exception: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test execution failed for product '" + productName + "': " + e.getMessage());
        } finally {
            // Cleanup - ensure logout even if test fails
            try {
                if (authPage.isLoggedIn()) {
                    authPage.logout();
                }
            } catch (Exception e) {
                System.err.println("Failed to logout during cleanup: " + e.getMessage());
            }
        }
    }

    @DataProvider(name = "productData")
    public Object[][] getProductData() {
        return new Object[][] {
                {"Samsung galaxy s6"},
                {"Nokia lumia 1520"}, 
                {"Nexus 6"}
        };
    }
}