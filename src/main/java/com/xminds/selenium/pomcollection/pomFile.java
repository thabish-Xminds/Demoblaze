package com.xminds.selenium.pomcollection;

import org.openqa.selenium.WebDriver;
import com.xminds.selenium.pageobjects.BlazePage;




public class pomFile {

	public WebDriver driver;

	public pomFile(WebDriver rdriver) {
		this.driver = rdriver;
	}

	public BlazePage getBlazePage() {
		BlazePage bp = new BlazePage(driver);
		return bp;
	}


}





