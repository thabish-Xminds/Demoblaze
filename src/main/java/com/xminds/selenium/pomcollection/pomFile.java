package com.xminds.selenium.pomcollection;

import org.openqa.selenium.WebDriver;

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

// Need to create object for each page objects

	public LoginPage getLoginPage() {
		LoginPage lp = new LoginPage(driver);
		return lp;
	}

	public DashboardPage getDashboardPage() {
		DashboardPage dp = new DashboardPage(driver);
		return dp;
	}

	public CompanyPage getCompanyPage() {
		CompanyPage cp = new CompanyPage(driver);
		return cp;
	}

	public UsersPage getUsersPage() {
		UsersPage up = new UsersPage(driver);
		return up;
	}
	
	public ConnectorPage getConnectorPage() {
		ConnectorPage cp = new ConnectorPage(driver);
		return cp;
	}
}

