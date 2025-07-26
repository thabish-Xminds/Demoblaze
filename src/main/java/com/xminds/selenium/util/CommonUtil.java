package com.xminds.selenium.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CommonUtil {

	private static final Logger logger = LogManager.getRootLogger();
	
	public static final String configFile_store = "config.ser";
	
	public static final SimpleDateFormat DEFAULT_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
	
	/** The Constant DATE_TIME_YYYY_MM_DD_HH_MM_FORMAT. */
	public static final SimpleDateFormat DATE_TIME_YYYY_MM_DD_HH_MM_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	
	public static void storeSerializedData(String key, String value) {
		try {
			
			StoreConfigurationWorker worker = new StoreConfigurationWorker(key, value);
			WorkerThreadPool.getInstance().pushTask(worker);
						
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	public static void removeSerializedData(String key) {
		try {
			
			RemoveConfigurationWorker worker = new RemoveConfigurationWorker(key);
			WorkerThreadPool.getInstance().pushTask(worker);
						
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	
	public static String getSerializedData(String key) {
		
		try {
			Properties config = loadSerializeConfiguration(configFile_store);
			
			return config.getProperty(key);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public static Properties loadSerializeConfiguration(String configFile) {

		//check if data available in cache
		Object _tmp = CacheManager.getContextData(configFile);
		if(_tmp == null) {
			Properties prop = new Properties();
			//InputStream input = null;
			FileReader reader= null;
			try {
				File file = new File(configFile);
				// load_ISO_Configuration(file.getAbsolutePath());
				reader = new FileReader(configFile);
				logger.info("Reading configuration file: " + file.getAbsolutePath());

				// load a properties file
				prop.load(reader);
			} catch (IOException ex) {
				logger.error(ex);
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						logger.error(e);
					}
				}
			}
			CacheManager.setContextData(configFile, prop);
			return prop;
		}
		else {
			return (Properties)_tmp;
		}
		
	}	
	
	public static String formatException(Throwable e) {
		String errorMessage = "";
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintWriter out = new PrintWriter(bytes);
		e.printStackTrace(out);
		out.flush();
		out.close();
		
		byte[] message = bytes.toByteArray();
		errorMessage = new String(message);
		return errorMessage;
		
	}
	
	public static String formatTime(Date date)
	{
		if(date == null) {return "Null";}
		return DEFAULT_DATE_TIME_FORMAT.format(date);
	}
	
	/**
	 * Date time yyyy mm dd hh mm format.
	 *
	 * @param date the date
	 * @return the string
	 */
	public static String DATE_TIME_YYYY_MM_DD_HH_MM_FORMAT(Date date)
	{
		if(date == null) {return "Null";}
		return DATE_TIME_YYYY_MM_DD_HH_MM_FORMAT.format(date);
	}
	
	/**
	 * Load configuration.
	 *
	 * @param configFile the config file
	 */
	public static void loadConfiguration(String configFile) {
		
		Properties prop = loadDiskFile(configFile);
		
		for (final Entry<Object, Object> entry : prop.entrySet()) { 
			CacheManager.setContextData((String) entry.getKey(), (String) entry.getValue());
        }		

	}
	
	public static Long findFileLastModifiedTime(String configFile) {
		Long lastModified = 0l;
		try {
			File file = localFile(configFile);
			lastModified = file.lastModified();
			
		}catch(Exception e ) {
			logger.error(e);
		}
		return lastModified;
	}
	
	
	
	public static File localFile(String configFile) {
		
		File file = new File(configFile);
		
		logger.debug("Reading configuration: "+file.getAbsolutePath());
		if(!file.exists()) {
			String _conf = configFile;
			_conf = (_conf.startsWith("/")?_conf:"/"+_conf);
			try {
				//configure application file path
				String tomcatHome = System.getenv("CATALINA_HOME");///opt/tomcat
				logger.debug("CATALINA_HOME: "+tomcatHome);
				tomcatHome = (tomcatHome.endsWith("/")?tomcatHome:tomcatHome+"/")+"bin";
								
				file = new File(tomcatHome + _conf);
			} catch (Exception ee1) {
				String tomcatHome = "/opt/tomcat/bin";
								
				file = new File(tomcatHome + _conf);
			}
			//load_ISO_Configuration(file.getAbsolutePath());
			logger.debug("Reading dynamic configuration file: "+file.getAbsolutePath());
		}
		return file;
	}
	
	/**
	 * Load disk file.
	 *
	 * @param configFile the config file
	 * @return the properties
	 */
	public static Properties loadDiskFile(String configFile) {
		
		Properties prop = new Properties();
		InputStream input = null;

		try {
			File file = new File(configFile);
			logger.info("Reading configuration: "+file.getAbsolutePath());
			if(!file.exists()) {
				String _conf = configFile;
				_conf = (_conf.startsWith("/")?_conf:"/"+_conf);
				try {
					//configure application file path
					String tomcatHome = System.getenv("CATALINA_HOME");///opt/tomcat
					logger.info("CATALINA_HOME: "+tomcatHome);
					tomcatHome = (tomcatHome.endsWith("/")?tomcatHome:tomcatHome+"/")+"bin";
									
					file = new File(tomcatHome + _conf);
				} catch (Exception ee1) {
					String tomcatHome = "/opt/tomcat/bin";
									
					file = new File(tomcatHome + _conf);
				}
				
				//load_ISO_Configuration(file.getAbsolutePath());
				logger.info("Reading dynamic configuration file: "+file.getAbsolutePath());
				
			}
			
			input = new FileInputStream(file);

			// load a properties file
			prop.load(input);
			
		} catch (IOException ex) {
			logger.error(ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
		return prop;

	}

	public static boolean isErrorValid(List<String> errors, String message) {
		boolean flag = false;
		if(!CollectionUtils.isEmpty(errors)) {
			for(String rec: errors) {
				if(rec.indexOf(message) != -1) {
					//valid error
					flag = true;
					break;
				}
			}
			
		}
		return flag;
	}
 }
