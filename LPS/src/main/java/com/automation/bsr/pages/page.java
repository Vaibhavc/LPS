package com.automation.bsr.pages;

import com.automation.Base;

public class page extends Base{

	public void veri() {
		logger.info("==============================");
		logger.info("resolving Vehicle URL from argument");
		String vehicleURL = System.getProperty("vehicleName");
		logger.info("creating driver" + vehicleURL);
		Base.getDriver().get(getProperty(vehicleURL));
		System.out.println("hey vaibhav");
	}
	public  void veri2() {
		
	}
}
