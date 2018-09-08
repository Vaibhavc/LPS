package com.automation.bsr.runner;

import com.automation.Base;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends Base{
	
	@Before
	public void beforeScenario(Scenario scenario) {
		logger.info("------------------------------");
		logger.info("starting - " + scenario.getName());
		logger.info("------------------------------");
	}

	@After
	public void afterScenario(Scenario scenario) {
		logger.info("------------------------------");
		logger.info("is scenarion failed - " + scenario.isFailed());
		logger.info("------------------------------");
	}

}
