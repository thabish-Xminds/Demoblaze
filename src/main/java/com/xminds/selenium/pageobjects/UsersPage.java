package com.xminds.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.xminds.selenium.commonfunctions.CommonFunctions;
import com.xminds.selenium.configurationfiles.Base;
import com.xminds.selenium.util.CacheManager;



public class UsersPage extends Base {

	public WebDriver driver;
	
	CommonFunctions cf = new CommonFunctions();

	
	
	
	
	
	By createUser = By.xpath("//button[text()='CREATE USER']");
	
	By searchUsers = By.xpath("//input[@placeholder='Search Users']");

	By activeFilter = By.xpath("//span[text()='Active']");
	
	By testUser = By.xpath("//span[text()='Sandhu Admin']");
	
	By verifyUser = By.xpath("//p[text()='Sandhu Admin']");
	
	By verifyFilter = By.xpath("//p[text()='Add a Filter']");
	
	By yesButton = By.xpath("//div[@class='MuiBox-root css-ztj5gm']//button[2]");
	
	By noButton = By.xpath("//div[@class='MuiBox-root css-ztj5gm']//button[1]");
	
	By closeActiveFilter = By.xpath("//*[name()='svg'][@data-testid='CancelIcon']");
	
	By addFilter = By.xpath("//span[text()='Add Filter']");
	
	By selectCompany = By.xpath("//div[text()='Datazoom']");
	
	By testCompany = By.xpath(CacheManager.getString(selenium_test_companyName));
	
	By selectFullName = By.xpath("//input[@name='name']");
	
	By selectRole= By.xpath("//div[@id='roleName']");
	
	By selectEmail= By.xpath("//input[@name='email']");
	
	By userRole= By.xpath("//li[text()='User']");
	
	By billAdminRole= By.xpath("//li[text()='Billing Admin']");
	
	By customerAdminRole= By.xpath("//li[text()='Customer Admin']");
	
	By superadminRole= By.xpath("//li[text()='Super Admin']");
	
	By adminRole= By.xpath("//li[text()='Admin']");
	
	By dashCompany= By.xpath("//div[@class='MuiBox-root css-11oqizh']//*[name()='svg']");
	
	By searchCompany= By.xpath("//input[@placeholder='Search Companies']");
	
	By invitedUser= By.xpath("//span[text()='AutomationCompanyAdmin']");
	
	By filterBy = By.xpath("//div[@id='filterBy']");
	
	By selectStatus = By.xpath("//li[text()='Status']");
	
	By filterValue = By.xpath("//div[@id='filterValue']");
	
	By filterDisabled = By.xpath("//li[text()='Disabled']");
	
	By applyFilter = By.xpath("//button[text()='apply filter']");
	
	By closeFilter = By.xpath("//button[@aria-label='search']//*[name()='svg']");
	
	By statusInvited = By.xpath("//i[text()='Invited']");
	
	By filterRole = By.xpath("//li[text()='Role']");
	
	By filterCompany= By.xpath("//li[text()='Company']");
	
	By filterInvited = By.xpath("//li[text()='Invited']");
	
	
	

			
	public UsersPage(WebDriver driver) {
		
		this.driver = driver;

	}
	public WebElement getFilterInvited() {
		  return driver.findElement(filterInvited); }
	
	public WebElement getFilterCompany() {
		  return driver.findElement(filterCompany); }
	
	public WebElement getFilterRole() {
		  return driver.findElement(filterRole); }
	
	public WebElement getStatusInvited() {
		  return driver.findElement(statusInvited); }
	
	
	public WebElement getInvitedUser() {
		  return driver.findElement(invitedUser); }
	
	
	public WebElement getSearchCompany() {
		  return driver.findElement(searchCompany); }
	
	
	public WebElement getDashCompany() {
		  return driver.findElement(dashCompany); }
	
	public WebElement getSuperadminRole() {
		  return driver.findElement(superadminRole); }
	
	
	public WebElement getBillAdminRole() {
		  return driver.findElement(billAdminRole); }
	
	public WebElement getAdminRole() {
		  return driver.findElement(adminRole); }
	
	public WebElement getCustomerAdminRole() {
		  return driver.findElement(customerAdminRole); }
	
	
	public WebElement getUserRole() {
		  return driver.findElement(userRole); }
	
	public WebElement getSelectFullName() {
		  return driver.findElement(selectFullName); }
	
	
	public WebElement getSelectRole() {
		  return driver.findElement(selectRole); }
	
	public WebElement getSelectEmail() {
		  return driver.findElement(selectEmail); }
	
	public WebElement getTestCompany() {
		  return driver.findElement(testCompany); }
	
	
	public WebElement getSelectCompany() {
		  return driver.findElement(selectCompany); }
	
	
	public WebElement getVerifyFilter() {
		  return driver.findElement(verifyFilter); }
	
	
	public WebElement getVerifyUser() {
		  return driver.findElement(verifyUser); }
	
	public WebElement getTestUser() {
		  return driver.findElement(testUser); }
	
	public WebElement getSearchUsers() {
		  return driver.findElement(searchUsers); }
	
	
	public WebElement getCreateUser() {
		  return driver.findElement(createUser); }
	
	
	public WebElement getCloseFilter() {
		  return driver.findElement(closeFilter); }
	
	
	public WebElement getCloseActiveFilter() {
		  return driver.findElement(closeActiveFilter); }
	
	
	public WebElement getApplyFilter() {
		  return driver.findElement(applyFilter); }
	
	public WebElement getFilterDisabled() {
		  return driver.findElement(filterDisabled); }
	
	public WebElement getFilterValue() {
		  return driver.findElement(filterValue); }
	
	
	public WebElement getSelectStatus() {
		  return driver.findElement(selectStatus); }
	
	public WebElement getfilterBy() {
		  return driver.findElement(filterBy); }
	
	
	public WebElement getAddFilter() {
		  return driver.findElement(addFilter); }
	
	public WebElement getNoButton() {
		  return driver.findElement(noButton); }
	
	public WebElement getYesButton() {
		  return driver.findElement(yesButton); }
	
	public WebElement getActiveFilter() {
		  return driver.findElement(activeFilter); }
	
	
	




	
}