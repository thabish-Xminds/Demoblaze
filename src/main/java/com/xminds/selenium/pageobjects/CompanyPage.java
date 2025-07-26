package com.xminds.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.xminds.selenium.commonfunctions.CommonFunctions;
import com.xminds.selenium.configurationfiles.Base;
import com.xminds.selenium.util.CacheManager;



public class CompanyPage extends Base {

	public WebDriver driver;
	
	CommonFunctions cf = new CommonFunctions();

	
	
	
	
	
	By createCompany = By.xpath("//button[text()='Create Company']");
	
	By searchCompany = By.xpath("//input[@placeholder='Search Companies']");
	
	By activeFilter = By.xpath("//span[text()='Active']");
	
	By testCompany = By.xpath("(//span[text()='Datazoom'])[2]");
	
	By listCompany = By.xpath("//span[text()='A1']");
	
	By verifyCompany = By.xpath("//h5[text()='Datazoom']");
	
	By companyName = By.xpath("//input[@id='companyName']");
	
	By yesButton = By.xpath("//div[@class='MuiBox-root css-ztj5gm']//button[2]");
	
	By noButton = By.xpath("//div[@class='MuiBox-root css-ztj5gm']//button[1]");
	
	By companyAssert = By.xpath("//div[@id='root']/div/div/div[5]/div[2]/div[3]/div/div");
	
	By companyType = By.xpath("//div[@id='account-Type']");
	
	By customType = By.xpath("//li[text()='Custom']");
	
	By standardType = By.xpath("//li[text()='Standard']");
	
	By contractType = By.xpath("//li[text()='Contract']");
	
	By mediaTailorType = By.xpath("//li[text()='MediaTailor Only']");
	
	By mediaTailorCustom = By.xpath("//li[text()='MediaTailor Custom']");
	
	By disableCompany = By.xpath("//button[text()='Disable Company']");
	
	By reEnableCompany = By.xpath("//button[text()='RE-ENABLE COMPANY']");
	
	By closeActiveFilter = By.xpath("//*[name()='svg'][@data-testid='CancelIcon']");
	
	By addFilter = By.xpath("//span[text()='Add Filter']");
	
	By filterBy = By.xpath("//div[@id='filterBy']");
	
	By selectStatus = By.xpath("//li[text()='Status']");
	
	By filterValue = By.xpath("//div[@id='filterValue']");
	
	By filterDisabled = By.xpath("//li[text()='Disabled']");
	
	By applyFilter = By.xpath("//button[text()='apply filter']");
	
	By searchCompName = By.xpath(CacheManager.getString(selenium_span_companyName));
	
	By closeFilter = By.xpath("//button[@aria-label='search']//*[name()='svg']");
	
	By searchAccount = By.xpath("//li[text()='Account Type']");
	
	By excludeUI = By.xpath("//input[@id='hideInUi']");
	
	By changeLog = By.xpath("//button[@value='1']//*[name()='svg']");
	
	By changeHistory = By.xpath("//p[text()='Change History']");
	
	
	
	
	
	
	
	
	
			
			
	public CompanyPage(WebDriver driver) {
		
		this.driver = driver;

	}
	
	public WebElement getContractType() {
		  return driver.findElement(contractType); }
	
	public WebElement getChangeHistory() {
		  return driver.findElement(changeHistory); }
	
	public WebElement getChangeLog() {
		  return driver.findElement(changeLog); }
	
	
	public WebElement getExcludeUI() {
		  return driver.findElement(excludeUI); }
	
	public WebElement getSearchAccount() {
		  return driver.findElement(searchAccount); }
	
	public WebElement getCloseFilter() {
		  return driver.findElement(closeFilter); }
	
	
	public WebElement getSearchCompName() {
		  return driver.findElement(searchCompName); }
	
	
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
	
	public WebElement getReEnableCompany() {
		  return driver.findElement(reEnableCompany); }
	
	public WebElement getDisableCompany() {
		  return driver.findElement(disableCompany); }
	
	public WebElement getMediaTailorCustom() {
		  return driver.findElement(mediaTailorCustom); }
	
	public WebElement getMediaTailorType() {
		  return driver.findElement(mediaTailorType); }
	
	public WebElement getStandardType() {
		  return driver.findElement(standardType); }
	
	public WebElement getCustomType() {
		  return driver.findElement(customType); }
	
	public WebElement getCompanyType() {
		  return driver.findElement(companyType); }
	
	public WebElement getCompanyAssert() {
		  return driver.findElement(companyAssert); }
	
	public WebElement getNoButton() {
		  return driver.findElement(noButton); }
	
	public WebElement getYesButton() {
		  return driver.findElement(yesButton); }
	
	public WebElement getCompanyName() {
		  return driver.findElement(companyName); }
	
	
	public WebElement getVerifyCompany() {
		  return driver.findElement(verifyCompany); }
	
	public WebElement getListCompany() {
		  return driver.findElement(listCompany); }
	
	
	public WebElement getTestCompany() {
		  return driver.findElement(testCompany); }
	
	
	public WebElement getActiveFilter() {
		  return driver.findElement(activeFilter); }
	
	public WebElement getCreateCompany() {
		  return driver.findElement(createCompany); }
	
	public WebElement getSearchCompany() {
		  return driver.findElement(searchCompany); }
	
	




	
}