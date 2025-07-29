package com.xminds.selenium.testcase;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import com.xminds.selenium.configurationfiles.Base;
import com.xminds.selenium.pomcollection.pomFile;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class AddToCartTest extends Base {

	public WebDriver driver;
	pomFile pom;
	public static Logger log = LogManager.getLogger(Base.class.getName());

	@BeforeMethod
	public void initialize() throws IOException, InterruptedException {
		driver = initializeDriver();
		pom = new pomFile(driver);
	}
	
	@Test(priority = 1)
	public void testUntitledTestCase() throws Exception {
		driver.get("https://www.demoblaze.com/");
		driver.findElement(By.id("itemc")).click();
		driver.findElement(By.linkText("Samsung galaxy s6")).click();
		driver.get("https://www.demoblaze.com/prod.html?idp_=1");
		driver.findElement(By.linkText("Add to cart")).click();
		//assertEquals(closeAlertAndGetItsText(), "Product added.");
		driver.findElement(By.id("cartur")).click();
		driver.get("https://www.demoblaze.com/cart.html");
		driver.findElement(By.xpath("//div[@id='page-wrapper']/div/div[2]/button")).click();
		driver.findElement(By.id("name")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("tabish");
		driver.findElement(By.id("country")).click();
		driver.findElement(By.id("country")).clear();
		driver.findElement(By.id("country")).sendKeys("India");
		driver.findElement(By.id("city")).click();
		driver.findElement(By.id("city")).clear();
		driver.findElement(By.id("city")).sendKeys("tvm");
		driver.findElement(By.xpath("//div[@id='orderModal']/div/div/div[2]/form/div[4]")).click();
		driver.findElement(By.id("card")).click();
		driver.findElement(By.id("card")).clear();
		driver.findElement(By.id("card")).sendKeys("333333333");
		driver.findElement(By.id("month")).click();
		driver.findElement(By.id("month")).clear();
		driver.findElement(By.id("month")).sendKeys("July");
		driver.findElement(By.id("year")).click();
		driver.findElement(By.id("year")).clear();
		driver.findElement(By.id("year")).sendKeys("2025");
		driver.findElement(By.xpath("//div[@id='orderModal']/div/div/div[3]/button[2]")).click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]")).click();
		driver.get("https://www.demoblaze.com/index.html");
	}
}
