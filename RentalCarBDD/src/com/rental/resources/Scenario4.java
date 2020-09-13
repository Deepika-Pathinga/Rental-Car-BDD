package com.rental.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

//import com.rental.car.CarRevenue;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Scenario4 {
	public void getRequest_Returns_HightestRevenueForLastYear() {
		//String sHostName ="http://localhost:8088";
		//String URI = "/getcars";
		//String URL = sHostName+URI;
	//	RestAssured.baseURI= URL;
		
		Response response = RestAssured.given().contentType("application/json").get();
		
		System.out.println("Question 3:");
		ArrayList<CarRevenue> alCarRevenue = new ArrayList<>();
		List<Float> licarmetrics = response.jsonPath().get("Car.metrics");
		System.out.println(licarmetrics);
		for(int i=0;i<licarmetrics.size();i++) {
			String vinumber = response.jsonPath().getString("Car["+i+"].vin");
			Float perDayRentPrice = response.jsonPath().getFloat("Car["+i+"].perdayrent.Price");
			Float perDayRentDiscount = response.jsonPath().getFloat("Car["+i+"].perdayrent.Discount");
			Float perDayRentPriceAndDiscount = (perDayRentPrice-(perDayRentPrice*perDayRentDiscount/100));
			
			Float yoyMaintenanceCost = response.jsonPath().getFloat("Car["+i+"].metrics.yoymaintenancecost");
			Float depreciation = response.jsonPath().getFloat("Car["+i+"].metrics.depreciation");
			int yeartodate = response.jsonPath().getInt("Car["+i+"].metrics.rentalcount.yeartodate");
			
			Float carRevenue =((yeartodate*perDayRentPriceAndDiscount)-(yoyMaintenanceCost-depreciation));
			alCarRevenue.add(new CarRevenue(vinumber, carRevenue));
			
			Collections.sort(alCarRevenue,Collections.reverseOrder());
			System.out.println("Highest revenue car in the last year: ");
			
			Iterator<CarRevenue> itRevenue = alCarRevenue.iterator();
			while(itRevenue.hasNext()) {
				CarRevenue obj = (CarRevenue) itRevenue.next();
				System.out.println("Vin: "+obj.sVin+" and "+" Highest Car Revenue sorted: "+obj.fCarRevenue);
			}
					
		}
		System.out.println("Question 3 completed");
		System.out.println("-----------------------------------------------------");			
	}
	

}
