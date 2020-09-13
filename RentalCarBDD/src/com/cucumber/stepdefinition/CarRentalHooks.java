package com.cucumber.stepdefinition;

import java.io.IOException;

//import com.cucumber.rentcar.StubMappings;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CarRentalHooks extends com.rental.resources.StubMappings {
	
	@Before
	public void setup() throws IOException {
		//propertyExtentSetup();
		try {
			setUpWireMockServer();
			getMappingsRequestAndResponse();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	@After
	public void tearDown() {
		tearDownServer();	
		
	}
	
	

}
