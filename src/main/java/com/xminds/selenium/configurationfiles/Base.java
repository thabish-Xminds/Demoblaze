package com.xminds.selenium.configurationfiles;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.xminds.selenium.commonfunctions.CommonFunctions;
import com.xminds.selenium.util.CacheManager;
import com.xminds.selenium.util.ConfigConstant;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base extends CommonFunctions implements ConfigConstant  {
	public WebDriver driver;
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")){
				options.addArguments("--headless");
			}		
			driver = new ChromeDriver(options);
		}
		else if (browserName.contains("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			WebDriverManager.firefoxdriver().clearDriverCache().setup();
			if(browserName.contains("headless")){
				options.addArguments("--headless");
			}
			driver = new FirefoxDriver(options);
		} 
		else if (browserName.contains("edge")) 
		{
			EdgeOptions options = new EdgeOptions();
			WebDriverManager.edgedriver().setup();
			if(browserName.contains("headless")){
				options.addArguments("--headless");
			}
			driver = new EdgeDriver(options);
		}
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		return driver;	
	}
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
}