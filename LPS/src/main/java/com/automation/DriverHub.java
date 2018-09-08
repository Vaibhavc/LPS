package com.automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverHub {
	static WebDriver createInstance(String browserName) {
        WebDriver driver = null;
        if (browserName.toLowerCase().contains("firefox")) {
        	System.setProperty("webdriver.gecko.driver", "./src/test/resources/drivers/geckodriver.exe");
        	driver = new FirefoxDriver();
            return driver;
        }
        if (browserName.toLowerCase().contains("edge")) {
        	System.setProperty("webdriver.edge.driver", "./src/test/resources/drivers/MicrosoftWebDriver_1.exe");
			driver = new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return driver;
        }
        if (browserName.toLowerCase().contains("chrome")) {
        	System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
    		driver = new ChromeDriver();
    		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    		driver.manage().window().maximize();
            return driver;
        }
        return driver;
    }
}
