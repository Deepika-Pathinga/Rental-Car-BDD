package com.cucumber.stepdefinition;

import org.testng.Assert;

import com.rental.resources.Scenario2;
import com.rental.resources.Scenario3;
import com.rental.resources.Scenario4;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CarRentalStepDef  {
	
	String sHostName ="http://localhost:8088";
	String URI = "/getcars";
	String URL = sHostName+URI;
	Response response;
	//String responseBody;
	

	@Given("^I mock the URL$")
	public void i_mock_the_URL() throws Throwable {		
		RestAssured.baseURI= URL;
	}	
	
	@When("^I perform get request$")
	public void i_perform_get_request() throws Throwable {	    
		response = RestAssured.given().contentType("application/json").get();	    
	}
	
	@Then("^I get response and check status code$")
	public void i_get_response_and_check_status_code() throws Throwable {
		System.out.println("Response: "+response.asString());
		System.out.println("Status Code: "+response.getStatusCode());
	}

	@Then("^I assert if the response body contains tesla$")
	public void i_assert_if_the_response_body_contains_tesla() throws Throwable {
	   
		String responseBody = response.getBody().asString();
		Assert.assertTrue(responseBody.contains("Tesla"));
		System.out.println("Response body contains Tesla");
	    
	}
		
	@Then("^scenario (\\d+) is completed$")
	public void scenario_is_completed(int arg1) throws Throwable {
		System.out.println("Test case "+ arg1+ " is completed");
	}
	
	@Then("^I put car make in a list and I get blue tesla with notes$")
	public void i_put_car_make_in_a_list_and_I_get_blue_tesla_with_notes() throws Throwable {
		
		Scenario2 s2 = new Scenario2();
		s2.getRequest_Returns_TeslaCarswithBlueColorandNotes();
	}
	
	@Then("^I get lowest per day rent with price and with price after discount$")
	public void i_get_lowest_per_day_rent_with_price_and_with_price_after_discount() throws Throwable {
	    Scenario3 s3 = new Scenario3();
	    s3.getRequest_Returns_LowestPerDayRentWithPriceAndWithPriceAfterDiscount();
	}
	
	@Then("^I get highest revenue for last year$")
	public void i_get_highest_revenue_for_last_year() throws Throwable {
	    Scenario4 s4 = new Scenario4();
	    s4.getRequest_Returns_HightestRevenueForLastYear();
	}

}
