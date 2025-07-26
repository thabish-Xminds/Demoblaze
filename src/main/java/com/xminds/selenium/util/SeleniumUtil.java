package com.xminds.selenium.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class SeleniumUtil implements ConfigConstant{

	public static String validateStaleElementReference(WebElement element) {
		//int maxCount = 5;
		long maxCount = CacheManager.getLongValue(session_get_text_maxtry, 5);
		int count = 0;
		String text = "";
		while(StringUtils.isEmpty(text = validate(element)))
		{
			count++;
			if(count > maxCount) {
				throw new RuntimeException("Could not retrieve lable after maximum try");
			}
			System.out.println("Trying for "+count+" times...");
		}
		return text;
	}
	
	public  static String validate(WebElement element) {
		//boolean flag = true;
		String text = "";
		try {
			text = element.getText();
			//flag = false;
			Thread.sleep(3000);
		}catch (StaleElementReferenceException ee) {
			text = "";
		}catch(InterruptedException ee) {
			
		}
		return text;
		
	}
	
	public static String secureClick(WebElement element) {
		//int maxCount = 5;
		long maxCount = CacheManager.getLongValue(session_click_maxtry, 5);
		int count = 0;
		String text = "";
		while(validateSecureClick(element))
		{
			count++;
			if(count > maxCount) {
				//break;
				throw new RuntimeException("Could not complete event after maximum try");
			}
			System.out.println("Trying for "+count+" times...");
		}
		return text;
	}
	
	private static boolean validateSecureClick(WebElement element) {
		boolean flag = true;
		try {
			element.click();
			flag = false;
			Thread.sleep(3000);
		}catch(InterruptedException ee) {
			
		}catch (Exception ee) {
			System.out.println(ee.toString());
			List<String> errors =  CacheManager.getStringCollection(selenium_webelement_event_exception);
			flag =  CommonUtil.isErrorValid(errors, ee.toString());
		}
		return flag;
		
	}
	

	public static By cssSelector(String key)
	{
		return By.cssSelector(CacheManager.getString(key));
	}
	
	public static By xpath(String key)
	{
		return By.xpath(CacheManager.getString(key));
	}
}
