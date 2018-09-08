package com.automation;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * 
 * @author vaibhav.choudhary This listener class will get executed before any
 *         test gets started
 */
public class BSRListner implements ITestListener {

	public void onFinish(ITestContext arg0) {

	}

	public void onStart(ITestContext arg0) {
	/*	String vehicleName = arg0.getCurrentXmlTest().getParameter("vehicleName");
		System.out.println(System.getProperty("vehicleName"));
		if (System.getProperty("vehicleName") == null)
			System.setProperty("vehicleName", vehicleName);*/
/*		WebDriver driver = DriverHub.createInstance("chrome");
		Base.setWebDriver(driver);*/
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestStart(ITestResult arg0) {
	}

	public void onTestSuccess(ITestResult arg0) {

	}

}
