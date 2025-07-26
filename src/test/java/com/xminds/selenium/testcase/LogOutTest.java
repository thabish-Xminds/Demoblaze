@Test
public void testLogoutFunctionality() throws InterruptedException {
	// Click Login link
	WebElement loginLink = driver.findElement(By.id("login2"));
	loginLink.click();
	// Verify login form elements are enabled
	WebElement usernameLabel = driver.findElement(By.xpath("//label[@for='log-name']"));
	WebElement passwordLabel = driver.findElement(By.xpath("//label[@for='log-pass']"));
	Assert.assertTrue(usernameLabel.isEnabled(), "Username label is not enabled");
	Assert.assertTrue(passwordLabel.isEnabled(), "Password label is not enabled");
	Thread.sleep(200); // Small pause for demo purposes
	// Enter valid credentials
	WebElement usernameField = driver.findElement(By.id("loginusername"));
	WebElement passwordField = driver.findElement(By.id("loginpassword"));
	usernameField.sendKeys("thabish123");
	Thread.sleep(200);
	passwordField.sendKeys("Jaise");
	Thread.sleep(200);
	// Click Login button
	WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]"));
	loginButton.click();
	// Wait for logout button to be clickable
	WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout2")));
	// Perform logout
	logoutButton.click();
	System.out.println("Logout Successful!");
	// Verify logout was successful by checking login button reappears
	wait.until(ExpectedConditions.elementToBeClickable(By.id("login2")));
}




