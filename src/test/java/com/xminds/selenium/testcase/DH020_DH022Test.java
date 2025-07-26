package com.xminds.selenium.testcase;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.xminds.selenium.configurationfiles.Base;

import com.xminds.selenium.util.CacheManager;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DH020_DH022Test {
	
	public class DatazoomAPITest extends Base {

		 @Test
		    public void testCompanyDashboardSummaryConnecterAPI() throws InterruptedException {
		        // Set Base URI
			 RestAssured.baseURI = CacheManager.getString(selenium_staging_baseuri);
			    

		        // Send GET Request
		       String response = given()
		            .header("referer", "stagingapp-latest.datazoom.io")
		            .header("accept", "application/json, text/plain, */*")
		            .header("Cookie", CacheManager.getString(selenium_dashbord_apicookie))
		            .when()
		            .get(CacheManager.getString(selenium_company_idapi))      
		            .then().assertThat().statusCode(200)
		            .extract()
		            .response().asString();


				System.out.println(response);
				JsonPath js=new JsonPath(response); //for parsing Json	
		        System.out.println("Raw Response Body:");
		        
		        
		        String totalConnectorsdash=js.getString("data.totalConnectors");
		        System.out.println("Total Connectorsdash:"+totalConnectorsdash);
		        
		        String activeConnectorsdash=js.getString("data.activeConnectors");
		        System.out.println("Active Connectorsdash:"+activeConnectorsdash);
		        
		        int totalConnectors = Integer.parseInt(totalConnectorsdash);
		        int activeConnectors = Integer.parseInt(activeConnectorsdash);

		        int disabledConnectorDash = totalConnectors - activeConnectors;
		        System.out.println("disabledConnectorin Dashboad: " + disabledConnectorDash);
		        
		        
		        Response responseConnector = given().log().all()
			            .header("Content-Type", "application/json")
			            .header("Accept", "application/json, text/plain, */*")
			            .header("Cookie", CacheManager.getString(selenium_dashbord_apicookie))
			            .header("referer", "https://stagingapp-latest.datazoom.io/")
			            .body("{\"query\":\"\",\"company_id\":\"a573aecc-03c6-472a-b2b9-4704ec9f3a92\",\"field\":\"lastModified\",\"order\":0,\"page\":0,\"size\":100,\"filters\":\"{\\\"connector\\\":[],\\\"category\\\":[],\\\"status\\\":[false],\\\"project\\\":[]}\"}")
			            .when().post("/admin-backend/v1/connectorCustomerMap/list")
			            .then().assertThat().statusCode(200)
			            .extract().response();
		        System.out.println("Response: " + responseConnector);
		        
		        JSONObject jsonResponse = new JSONObject(responseConnector.asString());

		        // Extract total count
		        int activeConnectorpage = jsonResponse.getJSONObject("data").getInt("total");

		        // Print total count
		        System.out.println("Total count: " + activeConnectorpage);
		        
		    
		        int activeConnectorsdashInt = Integer.parseInt(activeConnectorsdash);
		        Assert.assertEquals(activeConnectorsdashInt, activeConnectorpage, "Mismatch in Active Connectors count!");
		        System.out.println("Active Connectors count in dashboard is equal to Connectors API listed count");
		        
		        
		          
		        
		        Response resDisabledConnector = given().log().all()
			            .header("Content-Type", "application/json")
			            .header("Accept", "application/json, text/plain, */*")
			            .header("Cookie", CacheManager.getString(selenium_dashbord_apicookie))
			            .header("referer", "https://stagingapp-latest.datazoom.io/")
			            .body("{\"query\":\"\",\"company_id\":\"a573aecc-03c6-472a-b2b9-4704ec9f3a92\",\"field\":\"lastModified\",\"order\":0,\"page\":0,\"size\":100,\"filters\":\"{\\\"connector\\\":[],\\\"category\\\":[],\\\"status\\\":[true],\\\"project\\\":[]}\"}")
			            .when().post("/admin-backend/v1/connectorCustomerMap/list")
			            .then().assertThat().statusCode(200)
			            .extract().response();
		        System.out.println("Response: " + resDisabledConnector);
		        
		        JSONObject jsonResponseDisConn = new JSONObject(resDisabledConnector.asString());

		        // Extract total count
		        int disabledConnectorpage = jsonResponseDisConn.getJSONObject("data").getInt("total");

		        // Print total count
		        System.out.println("Total count: " + disabledConnectorpage);
		        
		    
		        
		        Assert.assertEquals(disabledConnectorDash, disabledConnectorpage, "Mismatch in disabled Connectors count!");
		        System.out.println("Diasabled Connectors count in dashboard is equal to Connectors API listed count");
		        
		        
		        
			       
			        
		    }}}