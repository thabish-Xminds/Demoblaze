package com.xminds.selenium.util;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StoreConfigurationWorker extends AbstractWorkerTask {

	private static final Logger logger = LogManager.getRootLogger();
	
	private String key;
	private String value;
	
	private StoreConfigurationWorker() {
		super("StoreConfigurationWorker - "+CommonUtil.formatTime(new Date()));
	}

	public StoreConfigurationWorker(String key, String value) {
		this();
		this.key = key;
		this.value = value;
	}

	
	@Override
	public void executeTask() {
		try {
			Properties config = CommonUtil.loadSerializeConfiguration(CommonUtil.configFile_store);
			
			PrintWriter out = new PrintWriter(new File(CommonUtil.configFile_store));
			config.put(key, value);
			config.store(out, "Configuration properties");
			out.flush();
			out.close();
			CacheManager.setContextData(CommonUtil.configFile_store, config);
			logger.info("New value is serialized for ["+key+"]");
		} catch (Exception e) {
			logger.error(e);
		}
		
		
	}

}
