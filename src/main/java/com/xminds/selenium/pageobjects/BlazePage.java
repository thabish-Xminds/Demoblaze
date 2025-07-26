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



	public BlazePage(WebDriver driver) {
		this.driver = driver;

	}

	public WebElement getSignup() {
		return driver.findElement(signup); }






}
