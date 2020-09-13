package com.rental.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

//import com.rental.car.PerDayRentPrice;
//import com.rental.car.PerDayRentPriceWithDiscount;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Scenario3 {
public void getRequest_Returns_LowestPerDayRentWithPriceAndWithPriceAfterDiscount() {
		
		//String sHostName ="http://localhost:8088";
		//String URI = "/getcars";
		//String URL = sHostName+URI;
		//RestAssured.baseURI= URL;
		
		Response response = RestAssured.given().contentType("application/json").get();
		
		//System.out.println("Question 2:");
		//System.out.println(response.asString());
		//System.out.println(response.getStatusCode());
		//String responseBody = response.getBody().asString();
		//List<String> limake = response.jsonPath().getList("Car.make");
		//List<String> limodel = response.jsonPath().getList("Car.model");
		//List<String> livin = response.jsonPath().getList("Car.vin");
		
		List<Float> liPerDayRent = response.jsonPath().getList("Car.perdayrent");
		System.out.println("Printing per day rental of all cars Discount and Price: "+liPerDayRent);
		System.out.println();
		//System.out.println("Printing make, model, vin no and per day rental of all cars: "+limake+" "+limodel+ " "+livin+" "+ liPerDayRent );
		ArrayList<PerDayRentPrice> alPerDayRentPrice = new ArrayList<PerDayRentPrice>();
		ArrayList<PerDayRentPriceWithDiscount> alPerDayRentPriceWithDiscount = new ArrayList<PerDayRentPriceWithDiscount>();
		//int setIndex=0;
		for(int i=0; i<liPerDayRent.size();i++) {
			
			String vinumber = response.jsonPath().getString("Car["+i+"].vin");
			Float perDayRentPrice = response.jsonPath().getFloat("Car["+i+"].perdayrent.Price");
			Float perDayRentDiscount = response.jsonPath().getFloat("Car["+i+"].perdayrent.Discount");
			Float perDayRentPriceAndDiscount = (perDayRentPrice-(perDayRentPrice*perDayRentDiscount/100));
			alPerDayRentPrice.add(new PerDayRentPrice(vinumber, perDayRentPrice));
			alPerDayRentPriceWithDiscount.add(new PerDayRentPriceWithDiscount(vinumber, perDayRentPriceAndDiscount));
						
		}
		Collections.sort(alPerDayRentPrice);
		
		System.out.println("Printing lowest per day rental of all cars with sorted price: ");		
		Iterator<PerDayRentPrice> itPrice = alPerDayRentPrice.iterator();
		while(itPrice.hasNext()) {
			PerDayRentPrice obj = (PerDayRentPrice) itPrice.next();
			System.out.println("Vin: "+obj.sVin+" and "+"Price sorted: "+obj.fPrice);
		}
		System.out.println();
		
		
		Collections.sort(alPerDayRentPriceWithDiscount);
		
		System.out.println("Printing lowest per day rental of all cars with sorted discount price: ");		
		Iterator<PerDayRentPriceWithDiscount> itDiscount = alPerDayRentPriceWithDiscount.iterator();
		while(itDiscount.hasNext()) {
			PerDayRentPriceWithDiscount obj = (PerDayRentPriceWithDiscount) itDiscount.next();
			System.out.println("Vin: "+obj.sVin+" and "+" Discount Price sorted: "+obj.fPriceAfterDiscount);
		}	
		System.out.println();

		//asserThat(Float.valueOf((float)130.0), comparesEqualTo(alPerDayRentPrice.get(0).fPrice));
		
		//System.out.println("Question 2 completed");
		//System.out.println("-----------------------------------------------------");
		
	}

}
