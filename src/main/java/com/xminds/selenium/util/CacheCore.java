package com.xminds.selenium.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;


import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class CacheCore.
 */
public class CacheCore {

	/** The logger. */
	private static final Logger logger = LogManager.getRootLogger();

	/** The Constant DEFAULT_SESSION_EXPIRY. */
	private static final long DEFAULT_SESSION_EXPIRY = 14 * 60 * 1000;// 14 minutes

	/** The Constant DEFAULT_SESSION_LEVEL2_EXPIRY. */
	private static final long DEFAULT_SESSION_LEVEL2_EXPIRY = 60 * 60 * 1000;// 60 minutes

	/** The cache. */
	private ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<String, Object>();

	/** The level 2 cache. */
	private ConcurrentHashMap<String, Object> level2Cache = new ConcurrentHashMap<String, Object>();

	/** The system cache. */
	private ConcurrentHashMap<String, Object> systemCache = new ConcurrentHashMap<String, Object>();

	/** The cache metadata. */
	private Map<String, Date> cacheMetadata = new ConcurrentHashMap<String, Date>();

	private static final String FILE_ACCESS_PREFIX_ = "FILE_ACCESS_PREFIX_";
	
	/** The Constant DEAFULT_CONFIGURATON_FILES. */
	private static final String DEAFULT_CONFIGURATON_FILES[] = {
			"selenium_config.properties", "locator_config.properties"};

	/** The Constant MAX_TRY. */
	private static final int MAX_TRY = 3;

	/** The try count. */
	private static int try_count = 0;
	
	/** The Constant me. */
	private static final CacheCore me = new CacheCore();
	
	/**
	 * Gets the single instance of CacheCore.
	 *
	 * @return single instance of CacheCore
	 */
	public static CacheCore getInstance() {
		return me;
	}
	
	/**
	 * Instantiates a new cache manager.
	 */
	private CacheCore() {
		logger.info("Initialize monitoring...");
		new MemoryMonitor().start();
	}

	/**
	 * Sets the data.
	 *
	 * @param key
	 *            the key
	 * @param data
	 *            the data
	 */
	public void setData(String key, String data) {
		cache.put(key, data);
		level2Cache.remove(key);
		this.registerObject(key);
		//serialize
		CommonUtil.storeSerializedData(key, data);
	}

	/**
	 * Sets the level 2 data.
	 *
	 * @param key the key
	 * @param data the data
	 */
	public void setLevel2Data(String key, Object data) {
		level2Cache.put(key, data);
		cache.remove(key);
		this.registerObject(key);
	}
	
	/**
	 * Sets the context data.
	 *
	 * @param key the key
	 * @param data the data
	 */
	public void setContextData(String key, Object data) {
		systemCache.put(key, data);
	}
	
	
	/**
	 * Gets the context data.
	 *
	 * @param key the key
	 * @return the context data
	 */
	public Object getContextData(String key) {
		return systemCache.get(key);
	}
	
	
	/**
	 * Gets the object.
	 *
	 * @param key
	 *            the key
	 * @return the object
	 */
	private Object getObject(String key) {
		if(!StringUtils.isEmpty(key)) {
			key = key.replaceAll(" ", "");
		}
		Object _tmp = cache.get(key);
		
		this.updateObjectTime(key);
		
		//If object is null, check level2 cache
		if (_tmp == null) {
			_tmp = level2Cache.get(key);
		}

		//If object is null, check system context
		if (_tmp == null) {
			_tmp = getContextData(key);
		}
		//if context data is null, try reloading configuration files
		if (_tmp == null) {
			loadCacheConfigFiles();
			_tmp = getContextData(key);
		}
		return _tmp;
	}

	/**
	 * Load cache config files.
	 */
	private void loadCacheConfigFiles() {
		if (systemCache.isEmpty() || try_count <= MAX_TRY) {
			synchronized (DEAFULT_CONFIGURATON_FILES) {
				for (String file : DEAFULT_CONFIGURATON_FILES) {
					CommonUtil.loadConfiguration(file);
					//update meta data with 
					Long lastModified = CommonUtil.findFileLastModifiedTime(file);
					Date then = new Date(lastModified);
					this.cacheMetadata.put(FILE_ACCESS_PREFIX_+file, then);
					logger.info(file+" modified --> "+CommonUtil.DATE_TIME_YYYY_MM_DD_HH_MM_FORMAT(then));
				}
			}
			try_count++;
		}

	}
	
	
	public void validateAllFileAccess() {
		synchronized (DEAFULT_CONFIGURATON_FILES) {
			for (String file : DEAFULT_CONFIGURATON_FILES) {
				validateFileAccess(file);
			}
		}
	}

	public void validateFileAccess(String file) {
		try {
			Long lastModified = CommonUtil.findFileLastModifiedTime(file);
			Date then = this.cacheMetadata.get(FILE_ACCESS_PREFIX_+file);
			if(then == null) {
				loadCacheConfigFiles();
				then = this.cacheMetadata.get(FILE_ACCESS_PREFIX_+file);
			}
			long diff =  lastModified - then.getTime();
			if(diff > 0) {
				//recent chanes happend.
				Date dtLastModified = new Date(lastModified);
				logger.info(file+" modified --> "+CommonUtil.DATE_TIME_YYYY_MM_DD_HH_MM_FORMAT(dtLastModified)+", reloading....");
				CommonUtil.loadConfiguration(file);
				//update meta data
				this.cacheMetadata.put(FILE_ACCESS_PREFIX_+file, dtLastModified);
			}
		}catch(Exception e) {
			logger.error(e);
		}

	}
	
	
	/**
	 * Gets the string.
	 *
	 * @param key
	 *            the key
	 * @return the string
	 */
	public String getString(String key) {
		Object obj = getObject(key);
		String val = String.valueOf((obj ==null? "": obj));
		if(StringUtils.isEmpty(val)) {
			//check serialized data
			val = CommonUtil.getSerializedData(key);
		}
		return val;
	}
	
	public String getString(String key, String defaultValue) {
		String val = getString(key);
		
		return (StringUtils.isEmpty(val)?defaultValue:val);
	}
	
	
	/**
	 * Gets the all cache keys.
	 *
	 * @param pattern the pattern
	 * @return the all cache keys
	 */
	private String[] getAllCacheKeys(String pattern) {
		List<String> keyList = new ArrayList<String>();
		Enumeration<String> keys = this.cache.keys();
		while(keys.hasMoreElements()){
			String _tmp = keys.nextElement();
			if(_tmp.startsWith(pattern)) {
				keyList.add(_tmp);
			}
		}
		return keyList.toArray(new String[0]);
	}
	
	/**
	 * Gets the all cache keys.
	 *
	 * @return the all cache keys
	 */
	public List<String> getAllCacheKeys() {
		List<String> keyList = new ArrayList<String>();
		Enumeration<String> keys = this.cache.keys();
		while(keys.hasMoreElements()){
			keyList.add(keys.nextElement());
		}
		return keyList;
	}
	
	/**
	 * Gets the all level 2 cache keys.
	 *
	 * @return the all level 2 cache keys
	 */
	public List<String> getAllLevel2CacheKeys() {
		List<String> keyList = new ArrayList<String>();
		Enumeration<String> keys = this.level2Cache.keys();
		while(keys.hasMoreElements()){
			keyList.add(keys.nextElement());
		}
		return keyList;
	}
	
	
	
	/**
	 * Removes the cache with pattern.
	 *
	 * @param pattern the pattern
	 * @return the string[]
	 */
	public String[] removeCacheWithPattern(String pattern) {
		String[] keys = this.getAllCacheKeys(pattern);
		for(String key: keys) {
			this.remove(key);
		}
		return keys;
	}
	
	

	/**
	 * Removes the.
	 *
	 * @param key the key
	 * @return the object
	 */
	public Object remove(String key) {
		this.cacheMetadata.remove(key);
		logger.info("Removing object from cache  ==> "+key);
		Object _tmp = this.cache.remove(key);
		Object _tmp2 = this.level2Cache.remove(key);
		return (_tmp2 == null? _tmp:_tmp2);		
	}
	
	/**
	 * Gets the string values.
	 *
	 * @param key
	 *            the key
	 * @return the string values
	 */
	public String[] getStringValues(String key) {
		String record = getString(key);
		List<String> valueArr = getStringCollection(key);
		return (String[]) valueArr.toArray(new String[0]);
	}

	/**
	 * Gets the string collection.
	 *
	 * @param key the key
	 * @return the string collection
	 */
	public List<String> getStringCollection(String key) {
		String record = getString(key);
		List<String> valueArr = new ArrayList<String>();
		if(record == null || "null".equals(record)) {
			return valueArr;
		}
		StringTokenizer tok = new StringTokenizer(record, ",;");
		while (tok.hasMoreTokens()) {
			valueArr.add(tok.nextToken().trim());
		}
		return valueArr;
	}
	

	/**
	 * Gets the numberic value.
	 *
	 * @param key
	 *            the key
	 * @return the numberic value
	 */
	public double getNumbericValue(String key) {
		try {
			return Double.parseDouble(getString(key));
		} catch (Exception e) {
			//logger.error(e);
			return -1;
		}

	}
	
	public long getLongValue(String key) {
		try {
			return Long.parseLong(getString(key));
		} catch (Exception e) {
			//logger.error(e);
			return -1;
		}
	}
	
	public long getLongValue(String key, long defaultValue) {
		long value = getLongValue(key);
		if(value < 0) {
			value = defaultValue;
		}
		
		return value;
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	/*
	public static void main(String[] args) {
		String key = " Guest User ";
		key = key.replaceAll(" ", "");
		System.out.println("key="+key);
		
	}
	*/
	
	/**
	 * The Class MemoryMonitor.
	 */
	class MemoryMonitor extends Thread {

		/**
		 * Instantiates a new memory monitor.
		 */
		MemoryMonitor() {
			super.setDaemon(true);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			try {
				logger.debug("Monitoring user memory...");
				long sessionExpiry = DEFAULT_SESSION_EXPIRY;
				long sessionLevel2Expiry = DEFAULT_SESSION_LEVEL2_EXPIRY;
				while (true) {

					try {
						long _tmp = Long.parseLong(getString(ConfigConstant.cache_timeout_minutes, "60"));
						sessionExpiry = (_tmp * 60 * 1000);
						logger.debug("Cache timeout from config file is: "
								+ _tmp);
					} catch (Exception e) {
						sessionExpiry = DEFAULT_SESSION_EXPIRY;
						logger.error(
								"ERROR: While loading cache expiry configuration",
								e);
					}
					
					try {
						long _tmp = Long.parseLong(getString(ConfigConstant.cache_level2_timeout_minutes, "120"));
						sessionLevel2Expiry = (_tmp * 60 * 1000);
						logger.debug("Cache level2 timeout from config file is: "
								+ _tmp);
					} catch (Exception e) {
						sessionLevel2Expiry = DEFAULT_SESSION_LEVEL2_EXPIRY;
						logger.error(
								"ERROR: While loading cache level2 expiry configuration",
								e);
					}
					// now
					Date now = new Date();
					// verify each user for expiry
					// validUsers.put(token, userDetails);
					Iterator<String> iter2 = level2Cache.keySet().iterator();
					List<String> _toks2 = new ArrayList<String>();
					while (iter2.hasNext()) {
						_toks2.add(iter2.next());
					}

					for (String tok : _toks2) {
						// userMetadata
						Date then = cacheMetadata.get(tok);
						// logger.debug("then : " + then );

						if (then != null) {
							long diff = now.getTime() - then.getTime();
							// logger.debug("Duration : " +
							// CommonUtil.roundDouble(diff/(1000*60)));
							if (diff > sessionLevel2Expiry) {
								synchronized(level2Cache) {
									logger.info("Removing level2 object: " + tok);
									level2Cache.remove(tok);
									cacheMetadata.remove(tok);
									logger.info("Total objects in level2 cache: "
											+ level2Cache.size());
								}
							}

						}
					}
					
					Iterator<String> iter = cache.keySet().iterator();
					List<String> _toks = new ArrayList<String>();
					while (iter.hasNext()) {
						_toks.add(iter.next());
					}

					for (String tok : _toks) {
						// userMetadata
						Date then = cacheMetadata.get(tok);
						// logger.debug("then : " + then );

						if (then != null) {
							long diff = now.getTime() - then.getTime();
							// logger.debug("Duration : " +
							// CommonUtil.roundDouble(diff/(1000*60)));
							if (diff > sessionExpiry) {
								synchronized(cache) {
									logger.info("Removing object: " + tok);
									cache.remove(tok);
									cacheMetadata.remove(tok);
									//remove from serialized 
									CommonUtil.removeSerializedData(tok);
									logger.info("Total objects in cache: "
											+ cache.size());
								}
							}

						}
					}
					//check if any configuration file is modified then reload.
					validateAllFileAccess();
					try {
						Thread.sleep(5 * 60 * 1000);// wait for 5 minute
					} catch (InterruptedException ie) {
					}
				}

			} catch (Throwable e) {
				logger.error("DAEMON ERROR: ", e);
			}

		}

	}
	
	
	/**
	 * Register object.
	 *
	 * @param token the token
	 */
	public void registerObject(String token) {
		this.cacheMetadata.put(token, new Date());
	}
	
	
	/**
	 * Update object time.
	 *
	 * @param token
	 *            the token
	 */
	public void updateObjectTime(String token) {
		if (this.cacheMetadata.containsKey(token)
				&& this.cacheMetadata.get(token) != null) {
			this.cacheMetadata.put(token, new Date());
		}
	}
	
}
