package com.rental.resources;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

//import org.testng.annotations.BeforeTest;

//import com.rental.car.TestBase;

public class StubMappings extends TestBase {
	//@BeforeTest
	public void getMappingsRequestAndResponse() {
		wireMockServer.stubFor(
              get(urlEqualTo("/getcars"))
              .withHeader("Content-Type", equalTo("application/json; charset=UTF-8"))
              .willReturn(
            		  aResponse()
            		  .withStatus(200)
            		  .withHeader("Content-Type", "application/json; charset=UTF-8")
            		  .withBodyFile("RentalCarData.json")
            		  ) );
		
	}

}