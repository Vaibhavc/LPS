package com.automation;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class Main {
	public static String URL;
	public static String browser;
	public static String username;
	public static String password;

	/*
	 * @SuppressWarnings("rawtypes") public static void main(String[] args) {
	 * URL="https://www.ford.com/cars/focus"; //URL =args[0];
	 * System.out.println("Started!"); TestNG testng = new TestNG();
	 * List<String> suites = Lists.newArrayList();
	 * suites.add("./config/testng.xml"); testng.setTestSuites(suites);;
	 * testng.run(); }
	 */
	public static void main(String[] args) {
		browser =args[1];
		URL = args[3];
		username =args[5].trim();
		password = args[7].trim();
		
		WebDriver driver = DriverHub.createInstance(browser);
		Base.setWebDriver(driver);
		//URL="https://www.ford.com/cars/focus";
			//URL =args[0];
		System.out.println("Started!");
		XmlSuite suite = new XmlSuite();
		suite.setName("TmpSuite"); 
		XmlTest test = new XmlTest(suite);
		test.setName("TmpTest");
		@SuppressWarnings("rawtypes")
		List<Class> listnerClasses = new ArrayList<Class>();
		listnerClasses.add(com.automation.BSRListner.class);
	    listnerClasses.add(com.automation.WebDriverListener.class); 
		test.addParameter("browser", "chrome");
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass("com.automation.bsr.runner.Runner"));
		test.setXmlClasses(classes) ;
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.setListenerClasses(listnerClasses);
		tng.run();
		tng.getReporters();
		}

}
