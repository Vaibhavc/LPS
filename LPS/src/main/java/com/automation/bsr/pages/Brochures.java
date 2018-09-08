package com.automation.bsr.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automation.Base;


public class Brochures extends Base {

	@FindBy(xpath = "//li[@class='shop']//a[contains(@href,'brochures')]")
	WebElement brochuresButton;

	@FindBy(xpath = "//h2[@class='main-heading fgx-brand-exlt-h2 fgx-brand-global-container-pad']")
	WebElement brochuresPageHeadingText;

	@FindBy(xpath = "//div[@class='tab-label-container']/div")
	List<WebElement> brochuresTabList;

	@FindBy(xpath = "//a[@class='btn brochure-method mail']")
	WebElement mailButtonl;
	
	@FindBy(xpath = "//span[@class='fd-golf-form-title']")
	WebElement headerGolfForm;

	public Brochures() {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(getDriver(), this);
	}
	
	public void verifyMailButton(){
		logger.debug("Checking for Mail Button");
		try {
			mailButtonl.isDisplayed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.assertTrue(false,"Mail Button is not present");
		}
	}

	public void goToBrocuresPage() {
		brochuresButton.click();
	}

}
