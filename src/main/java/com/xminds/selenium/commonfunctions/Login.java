package com.xminds.selenium.commonfunctions;

import org.openqa.selenium.WebDriver;

import com.xminds.selenium.pomcollection.pomFile;


public class Login extends CommonFunctions{
	 private WebDriver driver;

	    public Login(WebDriver driver) {
	          this.driver = driver;
	    }

public void login(String UName, String PWord)
    {
        pomFile pom=new pomFile (driver);
        
        sendKeys(pom.getLoginPage().getUsername(), UName);
        sendKeys(pom.getLoginPage().getPassword(), PWord);
        click(pom.getLoginPage().getLoginButton());
        
    }
}

//Call to the script file like this
/*
 * Login lp=new Login(); lp.login(call from
 * configconstant(variablenameforusername),
 * prop.getProperty(variablenameforpassword));
 */