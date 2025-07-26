package com.xminds.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.xminds.selenium.commonfunctions.CommonFunctions;



public class LoginPage {

	public WebDriver driver;
	
	CommonFunctions cf = new CommonFunctions();

	
	
	By username = By.id("email");

	By password = By.id("password");

	By loginButton = By.xpath("//button[text()='Log In']");
	
	By eyeButton = By.xpath("//span[@id='basic-addon2']");
	
	By logoMessage = By.cssSelector("div[class='login-txt'] p");
	
	By signUptoday = By.xpath("//a[text()='Sign up today']");
	
	By forgotPass = By.xpath("//a[text()='Forgot Password']");

	By errorMessage= By.xpath("//p[@class='text-danger text-center']");
	
	By signUpButton = By.xpath("//button[text()='Sign Up']");
	
	By returnToLoginLink = By.xpath("//a[text()='Return to login']");
	
	By invalidEmail = By.xpath("//p[text()='Invalid Email']");
	
	By returnToSignup = By.xpath("//button[text()='Return to sign up']");
	
	By emailSent = By.xpath("//p[text()='Email verification sent']");
	
	By returnToLoginButton = By.xpath("//button[text()='Return to login']");
	
	By resetInstructions = By.xpath("//button[text()='Send Reset Instructions']");
	
	By invalidPassword = By.xpath("//h2[text()='Invalid']");
	
	By resetPassAgain = By.xpath("//a[text()='Reset password again']");
	
	By recoveryLinkText = By.xpath("//h2[text()='Recovery link sent']");
	
	By userMenu = By.xpath("//span[@name='usermenu']//*[name()='svg']");
	
	By logoutButton = By.xpath("//a[@href='/logout']//li[@role='menuitem']");
	
	
	
			
			
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		;

		this.driver = driver;

	}
	public WebElement getLogoutButton() {
		  return driver.findElement(logoutButton); }
	
	public WebElement getUserMenu() {
		  return driver.findElement(userMenu); }
	
	public WebElement getRecoveryLinkText() {
		  return driver.findElement(recoveryLinkText); }
	
	public WebElement getResetPassAgain() {
		  return driver.findElement(resetPassAgain); }
	
	public WebElement getInvalidPassword() {
		  return driver.findElement(invalidPassword); }
	
	public WebElement getResetInstructions() {
		  return driver.findElement(resetInstructions); }
	
	public WebElement getReturnToLoginButton() {
		  return driver.findElement(returnToLoginButton); }
	
	public WebElement getEmailSent() {
		  return driver.findElement(emailSent); }
	
	
	public WebElement getReturnToSignup() {
		  return driver.findElement(returnToSignup); }
	
	public WebElement getInvalidEmail() {
		  return driver.findElement(invalidEmail); }
		
	public WebElement getReturnToLoginLink() {
		  return driver.findElement(returnToLoginLink); }
		
	
	public WebElement getSignUpButton() {
		  return driver.findElement(signUpButton); }
		
	 public WebElement getEyeButton() {
		  return driver.findElement(eyeButton); }
		 
	
	 public WebElement getForgotPass() {
		  return driver.findElement(forgotPass); }
		 
	 public WebElement getSignUptoday() {
		  return driver.findElement(signUptoday); }
		 

	  public WebElement getLogoMessage() {
	  return driver.findElement(logoMessage); }
	 

	  public WebElement getErrorMessage() {
	  return driver.findElement(errorMessage); }
	 

	  public WebElement getUsername() { //return driver.findElement(user_name);
	  return driver.findElement(username); }
	 

	public WebElement getPassword() {
		return driver.findElement(password);
	}

	public WebElement getLoginButton() {
		return driver.findElement(loginButton);
	}

	
public void goTo() {
	driver.get("https://stagingapp-latest.datazoom.io/");
}





	
}