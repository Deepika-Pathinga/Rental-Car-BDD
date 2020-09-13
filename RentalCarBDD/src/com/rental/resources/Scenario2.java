package com.rental.resources;

import java.util.List;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Scenario2 {
public void getRequest_Returns_TeslaCarswithBlueColorandNotes() {
		
		//String sHostName ="http://localhost:8088";
		//String URI = "/getcars";
		//String URL = sHostName+URI;
		//RestAssured.baseURI= URL;
		
		String make = "Tesla";
		String color ="Blue";
		Response response = RestAssured.given().contentType("application/json").get();
		//System.out.println(response.asString());
		//System.out.println("Test case 2 started: Status Code "+response.getStatusCode());
		
		//System.out.println("Response Body: "+responseBody);
		List<String> limake = response.jsonPath().getList("Car.make");
		//System.out.println(limake);
		//System.out.println("Question 1:");
		System.out.println("Printing the blue tesla car notes:");
		
		int setIndex=0;
		int carnumber =0;
		for(int i=0; i<limake.size();i++) {
			if (limake.get(i).equalsIgnoreCase(make)) {
				setIndex=i;
				String carColor = response.jsonPath().getString("Car["+setIndex+"].metadata.Color");
				if(carColor.equalsIgnoreCase(color)) {
					//System.out.println("Printing the car make which is only "+limake.get(i));
					//System.out.println("Printing only "+make+" of color "+ carColor);
					//assertThat(carColor, containsString("Blue"));
					junit.framework.Assert.assertTrue(carColor.contains("Blue"));
					
					String carNotes = response.jsonPath().getString("Car["+setIndex+"].metadata.Notes");
					carnumber++;
					System.out.println(carnumber+" "+carNotes);
					
				}
			}
		}
		String responseBody = response.getBody().asString();
		org.junit.Assert.assertTrue(responseBody.contains("Tesla"));
		junit.framework.Assert.assertTrue(limake.contains("Tesla"));
		//assertThat(limake, hasItem("Tesla"));
		//System.out.println("Question 1 completed");
		//System.out.println("-----------------------------------------------------");
		
	}

}
