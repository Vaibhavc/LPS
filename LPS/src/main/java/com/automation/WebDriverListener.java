package com.automation;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class WebDriverListener implements IInvokedMethodListener {

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
/*		if (method.isTestMethod()) {
			String browserName = method.getTestMethod().getXmlTest().getParameters().get("browser");
			System.out.println(browserName);
			WebDriver driver = DriverHub.createInstance(browserName);
			Base.setWebDriver(driver);
		}*/
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			WebDriver driver = Base.getDriver();
			if (driver != null) {
				driver.quit();
			}
		}

	}

}