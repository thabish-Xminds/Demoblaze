package com.xminds.selenium.commonfunctions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.NoSuchElementException;

public class CommonFunctions {
	public static WebDriver driver;

	public static void close() {

		driver.close();
	}

	public static void getUrl(String url) {

		driver.get(url);
	}

	public static void navigateTo(String url) {
		driver.navigate().to(url);
	}

	public static void back() {
		driver.navigate().back();
	}

	public static void forward() {
		driver.navigate().forward();
	}

	public static void refresh() {
		driver.navigate().refresh();
	}

	public static void quit() {
		driver.quit();
	}

	public static void sendKeys(WebElement element, String value) {
		element.sendKeys(value);
	}

	public static void click(WebElement element) {
		element.click();
	}

	public static void clear(WebElement element) {
		element.click();
	}

	public static String getText(WebElement element) {
		return element.getText();
	}

	public static void isDisplayed(WebElement element) {
		System.out.println(element.isDisplayed());
	}

	public static void isEnabaled(WebElement element) {
		System.out.println(element.isEnabled());
	}

	// Alert

	public static void alertHandle(String options) {
		Alert alert = driver.switchTo().alert();
		if (options.equalsIgnoreCase("ok")) {
			alert.accept();
		} else if (options.equalsIgnoreCase("cancel")) {
			alert.dismiss();
		}

		else if (options.equalsIgnoreCase("getText")) {
			System.out.println(alert.getText());
		}
	}

//Single Drop down
	public static void singleDropdown(WebElement element, String options, String input) {
		Select s = new Select(element);

		if (options.equalsIgnoreCase("value"))
			s.selectByValue(input);
		else if (options.equalsIgnoreCase("text"))
			s.selectByVisibleText(input);
		else if (options.equalsIgnoreCase("Index")) {
			int parseInt = Integer.parseInt(input);
			System.out.println(parseInt);
		}

	}

// Single Drop down-End

	public static void draganddrop(WebElement src, WebElement target) {
		Actions act = new Actions(driver);
		act.dragAndDrop(src, target).build().perform();
	}

	// Robot

	public static void robot(String options) throws AWTException {
		Robot r = new Robot();

		if (options.equalsIgnoreCase("down")) {
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
		} else if (options.equalsIgnoreCase("Up")) {
			r.keyPress(KeyEvent.VK_UP);
			r.keyRelease(KeyEvent.VK_UP);
		} else if (options.equalsIgnoreCase("Enter")) {
			r.keyPress(KeyEvent.VK_ENTER);
		}

	}

	public static void getAttribute(WebElement element) {

		String attribute = element.getAttribute("Value");
		System.out.println(attribute);
	}

	public static void getAttributeName(WebElement element) {
		String a = element.getAttribute("name");
		System.out.println(a);
	}

	public static void defaultContent() {
		driver.switchTo().defaultContent();
	}

	public static void Wait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public static void frame(int i) {
		driver.switchTo().frame(i);
	}

	public static void defaultFrame() {
		driver.switchTo().defaultContent();

	}

	public static void maximize() {
		driver.manage().window().maximize();
	}

	// Alert accept and dismiss

	public void handleAlertAccept() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void handleAlertDismiss() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	// radio button
	public void selectRadioButton(By by) {
		WebElement radioButton = driver.findElement(by);
		radioButton.click();
	}

	// Handle dropdown

	public void selectOptionFromDropdownByVisibleText(By by, String optionText) {
		WebElement dropdownElement = driver.findElement(by);
		Select select = new Select(dropdownElement);
		select.selectByVisibleText(optionText);
	}

	public void selectOptionFromDropdownByValue(By by, String optionValue) {
		WebElement dropdownElement = driver.findElement(by);
		Select select = new Select(dropdownElement);
		select.selectByValue(optionValue);
	}

	public void selectOptionFromDropdownByIndex(By by, int index) {
		WebElement dropdownElement = driver.findElement(by);
		Select select = new Select(dropdownElement);
		select.selectByIndex(index);
	}

	// List handling
	public List<String> getOptionsFromDropdown(By by) {
		WebElement dropdownElement = driver.findElement(by);
		Select select = new Select(dropdownElement);
		List<WebElement> options = select.getOptions();
		List<String> optionTexts = new ArrayList<>();
		for (WebElement option : options) {
			optionTexts.add(option.getText());
		}
		return optionTexts;
	}

	public static String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public static List<WebElement> findElements(WebDriver driver, By by) {
		return driver.findElements(by);
	}

	/*
	 * WebDriver driver = // initialize WebDriver List<WebElement> elements =
	 * ElementFunctions.findElements(driver, By.id("element_id"));
	 */
	public static WebElement findElement(WebDriver driver, By by) {
		try {
			return driver.findElement(by);
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	// WebElement element = CommonFunctions.findElement(driver, By.id("element_id"));

	// Mouse hover
	public void performMouseHover(WebDriver driver, By by) {
		Actions actions = new Actions(driver);
		WebElement mouseHover = driver.findElement(by);
		actions.moveToElement(mouseHover);
		actions.perform();
	}

	// Drag and drop
	public void performDragAndDrop(WebDriver driver, By sourceLocator, By destinationLocator) {
		WebElement sourceElement = driver.findElement(sourceLocator);
		WebElement destinationElement = driver.findElement(destinationLocator);
		Actions actions = new Actions(driver);
		actions.dragAndDrop(sourceElement, destinationElement).build().perform();
	}
	
	//Date selection from calendar
	public void selectDateFromCalendar(WebDriver driver, String date, By calendarLocator, By dateLocator) {
	    WebElement calendar = driver.findElement(calendarLocator);
	    List<WebElement> dates = calendar.findElements(dateLocator);
	    for (WebElement currentDate : dates) {
	      if (currentDate.getText().equals(date)) {
	        currentDate.click();
	        break;
	      }
	    }
	  }
	
	//Date input direct
	 public void inputDate(WebDriver driver, String date, By dateLocator) {
		    WebElement dateField = driver.findElement(dateLocator);
		    dateField.clear();
		    dateField.sendKeys(date);
		  }

	 public void fileUpload(String filePath) throws AWTException, InterruptedException {
			Robot rb = new Robot();
			Thread.sleep(2000);
			// copying File path to Clipboard
			StringSelection str = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
			// press Contol+V for pasting
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);

			// release Contol+V for pasting
			rb.keyRelease(KeyEvent.VK_CONTROL);
			rb.keyRelease(KeyEvent.VK_V);

			// for pressing and releasing Enter
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);
		}
	 
	 public void languageTranslation(WebDriver driver) throws InterruptedException {
		 Actions ac = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath("//span[@class='color_bkl clr3']"));
			ac.moveToElement(mainMenu).build().perform();Thread.sleep(5000);
			WebElement sub = driver.findElement(By.cssSelector("div[class='cdk-overlay-container'] li:nth-child(1)"));
			ac.moveToElement(sub).click().build().perform();Thread.sleep(5000);
	 }

	 
	 
	 
	 
	 
}