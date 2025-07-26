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

public class DH017_DH019Test {
	
	public class DatazoomAPITest extends Base {

		 @Test
		    public void testCompanyDashboardSummaryCollectorAPI() throws InterruptedException {
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
		        
		        String totalCollectorsdash=js.getString("data.totalCollectors");
		        System.out.println("Total Collectors:"+totalCollectorsdash);
		        
		        String activeCollectorsdash=js.getString("data.activeCollectors");
		        System.out.println("Active Collectors:"+activeCollectorsdash);
		       

		        String resactivecollector = given()
			            .header("referer", "stagingapp-latest.datazoom.io")
			            .header("accept", "application/json, text/plain, */*")
			            .header("Cookie", CacheManager.getString(selenium_dashbord_apicookie))
			            .queryParam("page", "0")
		                .queryParam("size", "100")
		                .queryParam("filters", "{\"platform\":[],\"mediaplayer\":[],\"status\":[false],\"project\":[]}")
		                .queryParam("company_id", CacheManager.getString(selenium_companyid))
		                .when()
		                .get("/admin-backend/v2.1/collector_config/list?page=1&size=100")
			            .then().assertThat().statusCode(200)
			            .extract()
			            .response().asString();
		        
					System.out.println(resactivecollector);
					JsonPath jac=new JsonPath(resactivecollector); 
					//for parsing Json	
					int activecollectorpage = jac.getInt("data.total");  // Correct JSON path
			        System.out.println("ActiveCollectorPage: " + activecollectorpage);
			        
			        
			       int activeCollectorsdashInt = Integer.parseInt(activeCollectorsdash);
			       Assert.assertEquals(activeCollectorsdashInt, activecollectorpage, "Mismatch in Active Collectors count!");
			        System.out.println("Active Collectors count in dashboard is equal to Collector API listed count");   
			        
			        
			        

			        String responsecollector = given()
				            .header("referer", "stagingapp-latest.datazoom.io")
				            .header("accept", "application/json, text/plain, */*")
				            .header("Cookie", CacheManager.getString(selenium_dashbord_apicookie))
				            .queryParam("page", "0")
			                .queryParam("size", "100")
			                .queryParam("company_id", CacheManager.getString(selenium_companyid))
			                .queryParam("filters", "{\"platform\":[],\"mediaplayer\":[],\"status\":[true],\"project\":[]}")
			                .when()
			                .get("/admin-backend/v2.1/collector_config/list")
				            .then().assertThat().statusCode(200)
				            .extract()
				            .response().asString();
			        
						System.out.println(responsecollector);
						JsonPath jdc=new JsonPath(responsecollector); 
						//for parsing Json	
						int disabledcollector = jdc.getInt("data.total");  // Correct JSON path
				        System.out.println("DisabledCollector: " + disabledcollector);
				        
				        int totalCollectorPage =  activecollectorpage + disabledcollector;  
				        System.out.println("TotalCollectorPage: " + totalCollectorPage);
				        
				        int totalCollectorsdashInt = Integer.parseInt(totalCollectorsdash);
				        Assert.assertEquals(totalCollectorsdashInt, totalCollectorPage, "Mismatch in Toatal Collector count!");
				        System.out.println("Total Collector count in dashboard is equal to Collector API listed count");
				        
			        
		        
		        
		   
		        

		        
		        
		       
		    }}}