package com.xminds.selenium.util;

public interface ConfigConstant {

	/** The Constant cache_timeout_minutes. */
	public static final String cache_timeout_minutes = "cache.timeout.minutes";

	/** The Constant cache_level2_timeout_minutes. */
	public static final String cache_level2_timeout_minutes = "session.level2.timeout.minutes";
	public static final String selenium_webelement_event_exception = "selenium_webelement_event_exception";
	public static final String session_get_text_maxtry = "session.get.text.maxtry";
	public static final String session_click_maxtry = "session.click.maxtry";
	public static final String selenium_testdata_file_location = "selenium.testdata.file.location";
	
	/**LoginPage ***/
	public static final String selenium_browser_name = "selenium.browser.name";
	public static final String selenium_test_url_staging = "selenium.test.url.staging";
	public static final String selenium_test_username = "selenium.test.username";
	public static final String selenium_test_password = "selenium.test.password";
	public static final String selenium_test_staging_signup = "selenium.test.staging.signup";
	public static final String selenium_test_staging_validsignupuser = "selenium.test.staging.validsignupuser";
	public static final String selenium_test_staging_invaliduser = "selenium.test.staging.invaliduser";
	public static final String selenium_test_staging_forgotUrl = "selenium.test.staging.forgotUrl";
	public static final String selenium_test_stagingurl_dashboard = "selenium.test.stagingurl.dashboard";
	
	
	/**Dashboard  **/
	public static final String selenium_staging_baseuri= "selenium.staging.baseuri";
	public static final String selenium_dashbord_apicookie= "selenium.dashbord.apicookie";
	public static final String selenium_company_idapi= "selenium.company.idapi";
	public static final String selenium_datapipeactive_api= "selenium.datapipeactive.api";
	public static final String selenium_datapipe_deactive_api= "selenium.datapipe.deactive.api";
	public static final String selenium_companyid= "selenium.companyid";
	public static final String selenium_companyid_api= "selenium.companyid_api";

	
	/**Company **/
	public static final String selenium_staging_companyurl= "selenium.staging.companyurl";
	public static final String selenium_staging_datapipeurl= "selenium.staging.datapipeurl";
	public static final String selenium_staging_collectorurl= "selenium.staging.collectorurl";
	public static final String selenium_staging_connectorurl= "selenium.staging.connectorurl";
	public static final String selenium_companyName= "selenium.companyName";
	public static final String selenium_span_companyName= "selenium.span.companyName";
	
	/**Users **/
	public static final String selenium_staging_usersurl= "selenium.staging.usersurl";
	public static final String selenium_test_companyName= "selenium.test.companyName";
	public static final String selenium_companyadmin_userName= "selenium.companyadmin.userName";
	public static final String selenium_billingadmin_userName= "selenium.billingadmin.userName";
	public static final String selenium_customeradmin_userName= "selenium.customeradmin.userName";
	public static final String selenium_companyuser_userName= "selenium.companyuser.userName";
	
	
	/**Connector **/
	
	public static final String selenium_staging_connectortitle= "selenium.staging.connectortitle";
	public static final String selenium_staging_createconnectorurl= "selenium.staging.createconnectorurl";
	
}