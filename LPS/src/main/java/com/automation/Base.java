package com.automation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	Boolean flag;
	protected static Boolean isLoggedin = false;
	public static Logger logger = Logger.getLogger(Base.class);
	public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	static Properties prop = null;

	// To Load property File
	static {
		String log4jConfigFile = "src/test/resources/properties/log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		logger.info("loading properties");
		InputStream input = null;
		try {
			prop = new Properties();
			input = new FileInputStream("./src/test/resources/properties/config.properties");

			// load a properties file
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Base() {
		PageFactory.initElements(getDriver(), this);
	}
	/*
	 * static WebDriver createInstance(String browserName) { WebDriver driver =
	 * null; if (browserName.toLowerCase().contains("firefox")) {
	 * System.setProperty("webdriver.gecko.driver",
	 * "./src/test/resources/drivers/geckodriver.exe"); driver = new
	 * FirefoxDriver(); return driver; } if
	 * (browserName.toLowerCase().contains("edge")) {
	 * System.setProperty("webdriver.edge.driver",
	 * "./src/test/resources/drivers/MicrosoftWebDriver_1.exe"); driver = new
	 * EdgeDriver(); return driver; } if
	 * (browserName.toLowerCase().contains("chrome")) {
	 * System.setProperty("webdriver.chrome.driver",
	 * "./src/test/resources/drivers/chromedriver.exe"); driver = new
	 * ChromeDriver(); return driver; } return driver; }
	 */

	public static WebDriver getDriver() {
		return webDriver.get();
	}

	static void setWebDriver(WebDriver driver) {
		webDriver.set(driver);
	}

	public String getProperty(String key) {
		logger.info("getting properties of : " + key);
		if (!key.isEmpty()) {
			logger.info(prop.getProperty(key) + " is the value for " + key);
			return prop.getProperty(key);
		} else {
			logger.error("please provide valid key");
			return key;
		}
	}

	public void setProperty(String key, String Value) {
		logger.info("setting property with key as " + key + "and its value as " + Value);
		prop.setProperty(key, Value);
	}

	public void waitForElementToBeVisible(WebElement e) {
		logger.info("waiting for element to be visible");
		new WebDriverWait(getDriver(), 20).until(ExpectedConditions.visibilityOf(e));
	}

	public void waitForElementWithAttribute(WebElement e, String s, String v) {
		logger.info("waiting for element with attribute" + s);
		new WebDriverWait(getDriver(), 30).until(ExpectedConditions.attributeToBe(e, s, v));
	}

	public void waitForElementToBeSelected(WebElement e) {
		logger.info("waiting for element to be selected");
		new WebDriverWait(getDriver(), 30).until(ExpectedConditions.elementToBeSelected(e));
	}

	public void waitForElementToBeClickable(WebElement e) {
		logger.info("waiting for element to be clickable");
		new WebDriverWait(getDriver(), 20).until(ExpectedConditions.elementToBeClickable(e));
	}

	public void waitForTitleToContain(String title) {
		logger.info("waiting for title to contain " + title);
		new WebDriverWait(getDriver(), 50).until(ExpectedConditions.titleContains(title));
	}

	public void scrollToElement(WebElement e) {
		logger.info("scrolling to element");
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView()", e);
	}

	public void waitForLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(getDriver(), 50);
		wait.until(pageLoadCondition);
	}

	public void clickUsingJavaScript(WebElement e) {
		logger.info("clicking on element using javascript");
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click();", e);
	}

	public void waitForPresenceOfElement(By by) {
		logger.info("waiting for element using BY");
		new WebDriverWait(getDriver(), 50).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	}

}
