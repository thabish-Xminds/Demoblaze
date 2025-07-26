package com.xminds.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.xminds.selenium.commonfunctions.CommonFunctions;
import com.xminds.selenium.configurationfiles.Base;
import com.xminds.selenium.util.CacheManager;



public class ConnectorPage extends Base {

	public WebDriver driver;
	
	CommonFunctions cf = new CommonFunctions();

	
	
	
	
	
	By searchConnector = By.xpath("//input[@placeholder='Search Connectors']");
	
	By createConnector = By.xpath("//button[text()='create connector']");
	
	By activeFilter = By.xpath("//span[text()='Active']");
	
	By addFilter = By.xpath("//span[text()='Add Filter']");
	
	By noResults = By.xpath("//p[text()='No results found']");
	
	By sortIcon = By.xpath("(//*[name()='svg'][@data-testid='SortIcon'])");
	
	By downIcon = By.xpath("(//*[name()='svg'][@data-testid='ArrowDownwardIcon'])");
	
	By dashboardLink = By.xpath("//a[text()='DASHBOARD']");
	
	By datapipesLink = By.xpath("//a[text()='DATA PIPES']");
	
	By connectorsLink = By.xpath("//a[text()='CONNECTORS']");
	
	By selectSearch = By.xpath("//button[@aria-label='search']");
	
	By testConnector = By.xpath("//th[text()='Mock Connector']");
	
	By verifyFilter = By.xpath("//p[text()='Add a Filter']");
	
	By lastModified = By.xpath("//p[text()='Last Modified']");
	
	By usageAmount= By.xpath("//p[text()='Usage Amount']");
	
	By connectorList= By.xpath("//p[text()='Connector']");
	
	By configName= By.xpath("//p[text()='Configuration Name']");
	
	By  selectDestination= By.xpath("//span[text()='Select Destination']");
	
	By  nameConfiguration= By.xpath("//span[text()='Name Configuration']");
	
	By  accessControl= By.xpath("//span[text()='Access Control']");
	
	By  deployConn= By.xpath("//span[text()='Deploy Connector']");
	
	By  mockConnector= By.xpath("//p[text()='Mock Connector']");
	
	By filterBy = By.xpath("//div[@id='filterBy']");
	
	By selectCatagory = By.xpath("//li[text()='Category']");
	
	By filterValue = By.xpath("//div[@id='filterValue']");
	
	By selectAnalytics = By.xpath("//li[text()='Analytics']");
	
	By selectAPM = By.xpath("//li[text()='APM']");
	
	By selectDataWarehouse = By.xpath("//li[text()='Data Warehouse']");
	
	By selectLogAnalysis = By.xpath("//li[text()='Log Analysis']");
	
	By selectOther = By.xpath("//li[text()='Other']");
	
	By connectorName = By.xpath("//input[@name='Connector Name']");
	
	By nextButton = By.xpath("//button[text()='Next']");
	
	By customTag = By.xpath("//input[@name='Custom_Tag']");
	
	By createConnButton = By.xpath("//button[text()='Create Connector']");
	
	By closeConnector = By.xpath("//button[@aria-label='close']");
	
	By closeFilter = By.xpath("(//*[name()='svg'][@data-testid='CloseIcon'])[2]");
	
	
			
	public ConnectorPage(WebDriver driver) {
		
		this.driver = driver;

	}
	public WebElement getCloseFilter() {
		  return driver.findElement(closeFilter); }
	
	public WebElement getCloseConnector() {
		  return driver.findElement(closeConnector); }
	
	public WebElement getCreateConnButton() {
		  return driver.findElement(createConnButton); }
	
	public WebElement getCustomTag() {
		  return driver.findElement(customTag); }
	
	public WebElement getNextButton() {
		  return driver.findElement(nextButton); }
	
	public WebElement getConnectorName() {
		  return driver.findElement(connectorName); }
	
	
	public WebElement getSelectOther() {
		  return driver.findElement(selectOther); }
	
	public WebElement getFilterValue() {
		  return driver.findElement(filterValue); }
	
	public WebElement getSelectLogAnalysis() {
		  return driver.findElement(selectLogAnalysis); }
	
	public WebElement getSelectDataWarehouse() {
		  return driver.findElement(selectDataWarehouse); }
	
	public WebElement getSelectAPM() {
		  return driver.findElement(selectAPM); }
	
	public WebElement getSelectAnalytics() {
		  return driver.findElement(selectAnalytics); }
	
	public WebElement getSelectCatagory() {
		  return driver.findElement(selectCatagory); }
	
	
	public WebElement getFilterBy() {
		  return driver.findElement(filterBy); }
	
	public WebElement getMockConnector() {
		  return driver.findElement(mockConnector); }
	
	public WebElement getDeployConn() {
		  return driver.findElement(deployConn); }
	
	public WebElement getConfigName() {
		  return driver.findElement(configName); }
	
	public WebElement getAccessControl() {
		  return driver.findElement(accessControl); }
	
	public WebElement getNameConfiguration() {
		  return driver.findElement(nameConfiguration); }
	
	public WebElement getSelectDestination() {
		  return driver.findElement(selectDestination); }
	
	
	public WebElement getConnectorList() {
		  return driver.findElement(connectorList); }
	
	public WebElement getUsageAmount() {
		  return driver.findElement(usageAmount); }
	
	public WebElement getLastModified() {
		  return driver.findElement(lastModified); }
	
	
	public WebElement getVerifyFilter() {
		  return driver.findElement(verifyFilter); }
	
	
	public WebElement getTestConnector() {
		  return driver.findElement(testConnector); }
	
	public WebElement getSelectSearch() {
		  return driver.findElement(selectSearch); }
	
	public WebElement getConnectorsLink() {
		  return driver.findElement(connectorsLink); }
	
	public WebElement getDatapipesLink() {
		  return driver.findElement(datapipesLink); }
	
	public WebElement getDashboardLink() {
		  return driver.findElement(dashboardLink); }
	
	
	public WebElement getDownIcon() {
		  return driver.findElement(downIcon); }
	
	
	public WebElement getSortIcon() {
		  return driver.findElement(sortIcon); }
	
	public WebElement getNoResults() {
		  return driver.findElement(noResults); }
	
	public WebElement getSearchConnector() {
		  return driver.findElement(searchConnector); }
	
	public WebElement getCreateConnector() {
		  return driver.findElement(createConnector); }
	
	public WebElement getActiveFilter() {
		  return driver.findElement(activeFilter); }
	
	public WebElement getAddFilter() {
		  return driver.findElement(addFilter); }
	
	
	
	




	
}