package com.xminds.selenium.util;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author sumeshr
 *
 */
public class CacheManager {
	

	public static void setData(String key, String data) {

		CacheCore.getInstance().setData(key, data);
	}

	public static  String getString(String key) {
		return CacheCore.getInstance().getString(key);
	}
	
	public static  String getString(String key, String defaultValue) {
		return CacheCore.getInstance().getString(key,defaultValue);
	}
	
	public static Object remove(String key) {
		return CacheCore.getInstance().remove(key);
		
	}
	
	public static void setContextData(String key, Object data) {
		CacheCore.getInstance().setContextData(key, data);
	}
	
	public static List<String> getStringCollection(String key) {
		return CacheCore.getInstance().getStringCollection(key);
	}
	
	public static long getLongValue(String key) {
		return CacheCore.getInstance().getLongValue(key);
	}
	
	public static long getLongValue(String key, long defaultValue) {
		return CacheCore.getInstance().getLongValue(key, defaultValue);
	}
	
	/**
	 * Gets the context data.
	 *
	 * @param key the key
	 * @return the context data
	 */
	public static Object getContextData(String key) {
		return CacheCore.getInstance().getContextData(key);
	}
}
