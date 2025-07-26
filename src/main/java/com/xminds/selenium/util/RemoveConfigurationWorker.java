package com.xminds.selenium.util;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RemoveConfigurationWorker extends AbstractWorkerTask {

	private static final Logger logger = LogManager.getRootLogger();
	
	private String key;
	
	private RemoveConfigurationWorker() {
		super("StoreConfigurationWorker - "+CommonUtil.formatTime(new Date()));
	}

	public RemoveConfigurationWorker(String key) {
		this();
		this.key = key;
	}

	
	@Override
	public void executeTask() {
		try {
			Properties config = CommonUtil.loadSerializeConfiguration(CommonUtil.configFile_store);
			
			PrintWriter out = new PrintWriter(new File(CommonUtil.configFile_store));
			config.remove(key);
			config.store(out, "Configuration properties");
			out.flush();
			out.close();
			CacheManager.setContextData(CommonUtil.configFile_store, config);
			logger.info("Key ["+key+"] removed from serilazed storage");
		} catch (Exception e) {
			logger.error(e);
		}
		
		
	}

}
