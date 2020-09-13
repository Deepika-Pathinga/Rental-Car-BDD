package com.rentalcar.runner;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@RunWith(Cucumber.class)

@CucumberOptions(features= "src/com/rentalcar/feature",
glue={"com.cucumber.stepdefinition"}, 
dryRun = false, //if it is true does not execute code just lists if there is any missing mappings for step definition
strict = true, //executes code and then lists if there is any missing mappings for step definition
format = { "pretty", 
		"pretty:target/cucumber-reports/cucumber-pretty.txt",
		//"html:test_output",
		"html:target/cucumber-reports/cucumber-html",
		"junit:target/cucumber-reports/cucumber-junit/cucumber-junit.xml",
		"json:target/cucumber.json",
		//"json:target/cucumber-reports/CucumberTestReport.json",
		//"rerun:target/cucumber-reports/rerun.txt"
		//"json:target/cucumber-reports/cucumber-json/cucumber-json.json"
		//"junit:junit_report.cucumber.xml", 
		//"json:test_output/json.cucumber.json",		
		}, //reports
//plugin = "json:target/cucumber-reports/CucumberTestReport.json",
//plugin = "json:target/cucumber-reports/cucumber-json/cucumber-json.json",
monochrome = true ) //code readability in output console 


public class CarRentalRunnerTest {
	private TestNGCucumberRunner testNGCucumberRunner;
	 
    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
 
    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }
 
    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }
 
    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }

}
