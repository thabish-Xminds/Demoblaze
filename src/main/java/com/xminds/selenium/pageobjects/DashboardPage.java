package com.xminds.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.xminds.selenium.commonfunctions.CommonFunctions;



public class DashboardPage {

	public WebDriver driver;
	
	CommonFunctions cf = new CommonFunctions();

	
	
	By dashboardLink = By.xpath("//a[text()='DASHBOARD']");
	
	By datapipesLink = By.xpath("//a[text()='DATA PIPES']");
	
	By collectorsLink = By.xpath("//a[text()='COLLECTORS']");
	
	By connectorsLink = By.xpath("//a[text()='CONNECTORS']");
	
	By settingsButton = By.xpath("//span[@name='adminmenu']//*[name()='svg']");
	
	By userMenu = By.xpath("//span[@name='usermenu']//*[name()='svg']");
	
	By datapipesText = By.xpath("//div[text()='Data Pipes']");
	
	By collectorsText = By.xpath("//div[text()='Collectors']");
	
	By connectorsText = By.xpath("//div[text()='Connectors']");
	
	By dataUsage = By.xpath("//label[text()='Data Usage']");
	
	By dataVolume = By.xpath("//span[text()='Data Volume']");
	
	By dateCalender = By.xpath("//span[@class='fa fa-calendar']");
	
	By newUserAccount = By.xpath("//p[text()='New users have requested access to your account.']");
	
	By reviewTRequests = By.xpath("//span[text()='Review Requests Now']");
	
	By dismissLink= By.xpath("//span[text()='Dismiss']");
	
	By dashboardText= By.xpath("//h3[text()='Dashboard']");
	
	By privacyText= By.xpath("//a[text()='Privacy']");
	
	By termsText= By.xpath("//a[text()='Terms']");
	
	By dashUnderText= By.xpath("//div[@class='MuiBox-root css-gmuwbf']");
	
	By contactUs= By.xpath("//a[@class='preFade fadeIn'][normalize-space()='Contact Us']");
	
	By yearDatazoom= By.xpath("//div[@class='MuiBox-root css-gmuwbf']");
	
	By users= By.xpath("//div[contains(text(),'Users')]");
	
	By projects= By.xpath("//div[contains(text(),'Projects')]");
	
	By Billing= By.xpath("//div[contains(text(),'Billing & Usage')]");
	
	By companies= By.xpath("//div[contains(text(),'Companies')]");
	
	By rateCards= By.xpath("//div[contains(text(),'Rate Cards')]");
	
	By dataPointManagement= By.xpath("//div[contains(text(),'Data Point Management')]");
	
	By collectorManagement= By.xpath("//div[contains(text(),'Collector Management')]");
	
	By changePassword= By.xpath("//div[contains(text(),'Change Password')]");
	
	By helpSupport= By.xpath("//div[contains(text(),'Help & Support')]");
	
	By logout= By.xpath("//div[contains(text(),'Logout')]");
	

	
	
			
			
	public DashboardPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	

		this.driver = driver;

	}
	public WebElement getLogout() {
		  return driver.findElement(logout); }
	
	public WebElement getHelpSupport() {
		  return driver.findElement(helpSupport); }
	
	public WebElement getChangePassword() {
		  return driver.findElement(changePassword); }
	
	public WebElement getCollectorManagement() {
		  return driver.findElement(collectorManagement); }
	
	public WebElement getDataPointManagement() {
		  return driver.findElement(dataPointManagement); }
	
	public WebElement getRateCards() {
		  return driver.findElement(rateCards); }
	
	public WebElement getCompanies() {
		  return driver.findElement(companies); }
	
	public WebElement getBilling() {
		  return driver.findElement(Billing); }
	
	public WebElement getProjects() {
		  return driver.findElement(projects); }
	
	public WebElement getUsers() {
		  return driver.findElement(users); }
	
	public WebElement getYearDatazoom() {
		  return driver.findElement(yearDatazoom); }
	
	
	public WebElement getContactUs() {
		  return driver.findElement(contactUs); }
	
	public WebElement getTermsText() {
		  return driver.findElement(termsText); }
	
	
	public WebElement getPrivacyText() {
		  return driver.findElement(privacyText); }
	
	public WebElement getDashboardText() {
		  return driver.findElement(dashboardText); }
	
	public WebElement getDismissLink() {
		  return driver.findElement(dismissLink); }
	
	public WebElement getReviewTRequests() {
		  return driver.findElement(reviewTRequests); }
	
	
	public WebElement getNewUserAccount() {
		  return driver.findElement(newUserAccount); }
	
	public WebElement getDateCalender() {
		  return driver.findElement(dateCalender); }
	
	public WebElement getDataUsage() {
		  return driver.findElement(dataUsage); }
	
	public WebElement getDataVolume() {
		  return driver.findElement(dataVolume); }
	
	public WebElement getConnectorsText() {
		  return driver.findElement(connectorsText); }
	
	public WebElement getCollectorsText() {
		  return driver.findElement(collectorsText); }
	
	public WebElement getUserMenu() {
		  return driver.findElement(userMenu); }
	
	public WebElement getDatapipesText() {
		  return driver.findElement(datapipesText); }
	
	public WebElement getSettingsButton() {
		  return driver.findElement(settingsButton); }
	
	public WebElement getConnectorsLink() {
		  return driver.findElement(connectorsLink); }
	
	public WebElement getCollectorsLink() {
		  return driver.findElement(collectorsLink); }
	
	public WebElement getDatapipesLink() {
		  return driver.findElement(datapipesLink); }
	
	public WebElement getDashboardLink() {
		  return driver.findElement(dashboardLink); }
}
