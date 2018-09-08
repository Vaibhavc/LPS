package com.automation.bsr.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


/**
 * Please notice that cucumber.examples.java.testNG.stepDefinitions.BeforeAfterHooks class
 * is in the same package as the steps definitions.
 * It has two methods that are executed before or after scenario.
 * I'm using it to delete cookies and take a screenshot if scenario fails.
 */
@CucumberOptions(
        features = "./src/test/resources/feature",
        glue = {"com.automation.bsr.stepdef"},
        dryRun=false,
        plugin = { "json:target/cucumber-report/chrome/cucumber.json"},
        strict = true,
        tags="@GIP")
public class Runner2 extends AbstractTestNGCucumberTests {
	
}
