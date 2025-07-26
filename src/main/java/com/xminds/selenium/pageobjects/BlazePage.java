package com.xminds.selenium.pageobjects;
 
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
 
import com.xminds.selenium.commonfunctions.CommonFunctions;

import com.xminds.selenium.configurationfiles.Base;

import com.xminds.selenium.util.CacheManager;
 
 
public class BlazePage extends Base {
 
	public WebDriver driver;

	CommonFunctions cf = new CommonFunctions();
 
	

	By signup = By.id("signin2");

	By username = By.id("sign-username");

	By password = By.id("sign-password");

	By signupButton = By.xpath("//button[contains(text(),'Sign up')]");




	public BlazePage(WebDriver driver) {

		this.driver = driver;
 
	}

	public WebElement getSignupButton() {

		  return driver.findElement(signupButton); }

	public WebElement getPassword() {

		  return driver.findElement(password); }

	public WebElement getUsername() {

		  return driver.findElement(username); }

	public WebElement getSignup() {

		  return driver.findElement(signup); }
 
public BlazePage getBlazePage() {
		BlazePage bp = new BlazePage(driver);
		return bp;
	}
}
