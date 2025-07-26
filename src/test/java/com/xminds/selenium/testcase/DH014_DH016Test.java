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

public class DH014_DH016Test {
	
	public class DatazoomAPITest extends Base {

		 @Test
		    public void testCompanyDashboardSummaryDatapipeAPI() throws InterruptedException {
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
		        
		        String totalConnectorsdash=js.getString("data.totalConnectors");
		        System.out.println("Total Connectors:"+totalConnectorsdash);
		        
		        String activeConnectorsdash=js.getString("data.activeConnectors");
		        System.out.println("Active Connectors:"+activeConnectorsdash);
		        
		        String pausedDatapipesdash=js.getString("data.pausedDatapipes");
		        System.out.println("Paused Datapipesdash:"+pausedDatapipesdash);
		        
		        String activeDatapipesdash=js.getString("data.activeDatapipes");
		        System.out.println("Active Datapipesdash:"+activeDatapipesdash);                            
		        
		   
		        String responsedatapipe = given()
			            .header("referer", "stagingapp-latest.datazoom.io")
			            .header("accept", "application/json, text/plain, */*")
			            .header("Cookie", CacheManager.getString(selenium_dashbord_apicookie))
			            .when()
			            .get(CacheManager.getString(selenium_datapipeactive_api))      
			            .then().assertThat().statusCode(200)
			            .extract()
			            .response().asString();
		        
					System.out.println(responsedatapipe);
					JsonPath jsd=new JsonPath(responsedatapipe); 
					//for parsing Json	
					int activedatapipe = jsd.getInt("data.total");  // Correct JSON path
			        System.out.println("ActiveDatapipes: " + activedatapipe);
			        
			        int activeDatapipesInt = Integer.parseInt(activeDatapipesdash);
			        Assert.assertEquals(activeDatapipesInt, activedatapipe, "Mismatch in Active Data Pipes count!");
			        System.out.println("ActiveDatapipes count in dashboard is equal to datapipe API listed count");
			        
			        
			        String respauseddatapipe = given()
				            .header("referer", "stagingapp-latest.datazoom.io")
				            .header("accept", "application/json, text/plain, */*")
				            .header("Cookie", CacheManager.getString(selenium_dashbord_apicookie))
				            .when()
				            .get(CacheManager.getString(selenium_datapipe_deactive_api))      
				            .then().assertThat().statusCode(200)
				            .extract()
				            .response().asString();
			        
			        System.out.println(respauseddatapipe);
					JsonPath jsp=new JsonPath(respauseddatapipe); 
					//for parsing Json	
					int totaldatapipe = jsp.getInt("data.total");  // Correct JSON path
			        System.out.println("TotalDatapipes: " + totaldatapipe);

			        
			        int pauseddatapipe =  totaldatapipe- activedatapipe;  
			        System.out.println("Pauesd Datapipes: " + pauseddatapipe);
			        
			        int pausedDatapipesdashInt = Integer.parseInt(pausedDatapipesdash);
			        Assert.assertEquals(pausedDatapipesdashInt, pauseddatapipe, "Mismatch in Active Data Pipes count!");
			        System.out.println("Paused Datapipes count in dashboard is equal to datapipe API listed count");
			        
			        
			          
		        
		       
		    }}}