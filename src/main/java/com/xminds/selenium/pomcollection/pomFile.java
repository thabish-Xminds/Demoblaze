package com.xminds.selenium.pomcollection;

import org.openqa.selenium.WebDriver;
import com.xminds.selenium.pageobjects.BlazePage;
import com.xminds.selenium.pageobjects.CompanyPage;
import com.xminds.selenium.pageobjects.ConnectorPage;
import com.xminds.selenium.pageobjects.DashboardPage;
import com.xminds.selenium.pageobjects.LoginPage;
import com.xminds.selenium.pageobjects.UsersPage;



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





