package com.automation.bsr.pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automation.Base;
import com.automation.Main;
import com.automation.bsr.util.Common;

public class Homepage extends Base {

	String openWindowHandle;
	static List<String> rowRecords;

	@FindBy(xpath = "//li[@class='shop']//a[contains(@href,'#$getupdates')]")
	WebElement getUpdateLink;

	@FindBy(className = "fd-golf-form-container")
	WebElement getUpdateForm;

	@FindBy(className = "fd-golf-form-title")
	WebElement getUpdateFormTitle;

	@FindBy(xpath = "//input[@id='fd-golf-ford-gu-postal-updates']")
	WebElement getPostalMailCheckbox;

	@FindBy(xpath = "//input[@id='fd-golf-ford-gu-email-updates']")
	WebElement getEMailCheckbox;

	@FindBy(name = "firstName")
	WebElement getFirstNamebox;

	@FindBy(id = "fd-golf-last-name-getupdates")
	WebElement getLastNamebox;

	@FindBy(id = "fd-golf-phone-number-request")
	WebElement getPhoneNumberbox;

	@FindBy(id = "fd-golf-street-address-getupdates")
	WebElement getStreetNamebox;

	@FindBy(id = "fd-golf-city-getupdates")
	WebElement getCityNamebox;

	@FindBy(id = "fd-golf-postal-code-getupdates")
	WebElement getZipbox;

	@FindBy(id = "fd-golf-email-address-getupdates")
	WebElement getEmailBox;

	@FindBy(id = "fd-golf-confirm-email-address-getupdates")
	WebElement getConfirmEmailBox;

	@FindBy(xpath = "//li[.='0-30 days']")
	WebElement getSurveyButton;

	@FindBy(xpath = "//Select[@aria-label='Select Vehicles']")
	WebElement getVehicleDropdown;

	@FindBy(id = "fd-golf-clone-btn")
	WebElement getAddVehicleButton;

	@FindBy(xpath = "//*[.='Submit']")
	WebElement getSubmitButton;

	@FindBy(xpath = "//button[.='Cancel']")
	WebElement getCancelButton;

	@FindBy(xpath = "//table/tbody/tr[2]/td[6]")
	WebElement getFirstNameField;

	@FindBy(xpath = "//table/tbody/tr[2]/td[5]")
	WebElement getLastNameField;

	@FindBy(xpath = "//table/tbody/tr[2]/td[10]")
	WebElement getZipField;

	@FindBy(xpath = "//table/tbody/tr[2]/td[12]")
	WebElement getEmailField;

	@FindBy(xpath = "//table/tbody/tr[2]/td[7]")
	WebElement getAddressField;

	@FindBy(xpath = "//table/tbody/tr[2]/td[8]")
	WebElement getCityField;

	@FindBy(xpath = "(//table/tbody/tr[2]/td[2])[2]")
	WebElement getSzbrchr1Field;

	@FindBy(xpath = "//table/tbody/tr[2]/td[42]")
	WebElement getSzQAField;

	@FindBy(xpath = "//table/tbody/tr[2]/td[44]")
	WebElement getSzLangField;

	@FindBy(xpath = "//input[@id='fd-golf-ford-gu-email-updates']")
	WebElement getEmailCheckBox;

	@FindBy(xpath = "//input[@id='fd-golf-ford-gu-postal-updates']")
	WebElement getPostalMailCheckBox;

	@FindBy(xpath = "//span[@class='fd-golf-check-icon']")
	List<WebElement> checkboxes;

	@FindBy(className = "fd-golf-close-btn")
	WebElement getCloseOverlayIcon;

	@FindBy(css = ".fd-golf-gu-thankyou.fd-golf-base-thankyou")
	WebElement getThankYouMessage;

	@FindBy(id = "DEALER-WSLXloginUserIdInput")
	WebElement getHrUsernameField;

	@FindBy(id = "DEALER-WSLXloginPasswordInput")
	WebElement getHrPasswordField;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement getHrLoginBtn;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement getHrContinueBtn;

	@FindBy(xpath = "//input[@name='SUBMITTED']")
	WebElement getHrSubmitBtn;

	@FindBy(className = "fd-golf-form-title")
	WebElement getGolfPageTitle;

	@FindBy(xpath = "//img[@usemap='#IPEMap']")
	WebElement popup;

	@FindBy(css = ".fd-golf-input-wrapper.fd-golf-error")
	List<WebElement> errorFields;

	public Homepage() {
		PageFactory.initElements(getDriver(), this);
	}

	public void openGetUpdateForm() {
		logger.info("openning the Get Update golf form by clickig on Get update from footer");
		waitForElementToBeClickable(getUpdateLink);
		//try {
			//getUpdateLink.click();
		//} catch (Exception e) {
			clickUsingJavaScript(getUpdateLink);
		//}
		waitForElementToBeVisible(getGolfPageTitle);
	}

	public void verifyGetUpdateForm() {
		logger.info("verifying the Get Update golf form");
		waitForElementToBeVisible(getUpdateForm);
		Assert.assertTrue(getUpdateForm.isDisplayed(), "Get Updates Form is not opened");
		waitForElementToBeSelected(getEmailCheckBox);
		Assert.assertTrue(getEmailCheckBox.isSelected());
	}

	public void clickOnSubmitButton() {
		logger.info("click on submit");
		getSubmitButton.click();

	}

	public void verifyConfirmation() {
		logger.info("veriying confirmation message");
		waitForElementToBeVisible(getThankYouMessage);
		getCloseOverlayIcon.click();
	}

	public void gotoHandRaiserAndLogin() {
		logger.info("accessing handraiser db");
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.open()");
		logger.info("------"+getDriver().getCurrentUrl());
		openWindowHandle = getDriver().getWindowHandle();
		Set<String> handles = getDriver().getWindowHandles();
		
		logger.info("accessing handraiser db----------------");
		for (String currentWindowHandle : handles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				logger.info("------2"+getDriver().getCurrentUrl());
				getDriver().switchTo().window(currentWindowHandle);
				getDriver().get(getProperty("handraiserURL"));
				logger.info("------3"+getDriver().getCurrentUrl());
				if (!isLoggedin) {
					getHrUsernameField.sendKeys(getProperty("userName.handraiser"));
					getHrPasswordField.sendKeys(getProperty("password.handraiser"));
					getHrLoginBtn.click();
					isLoggedin = true;
					waitForElementToBeVisible(getHrContinueBtn);
					getHrContinueBtn.click();
					waitForElementToBeVisible(getHrSubmitBtn);
				}
			}
		}
	}

	public void searchSubmittedLead() {
		logger.info("clicking on search button on Handraiser page");
		getHrSubmitBtn.click();
		waitForElementToBeVisible(getFirstNameField);
	}

	public void verifyFieldsOnTable(String lName, String zip, String szBrchr1, String szQA, String szLanguage) {
		rowRecords = new Common().getRowRecordsFromWebtable(getProperty("firstName"));
		logger.info("First name .........." + rowRecords.get(5));
		logger.info("Last name .........." + rowRecords.get(4));
		Assert.assertEquals(lName, rowRecords.get(4));
		logger.info("Zip .........." + rowRecords.get(9));
		Assert.assertEquals(zip, rowRecords.get(9));
		logger.info("getSzbrchr1 .........." + rowRecords.get(1));
		Assert.assertTrue(!rowRecords.get(1).equals(""));
		logger.info("getSzQA .........." + rowRecords.get(41));
		if (getProperty("brandURL").contains("lincoln") && szQA.contains("8483")) {
			Assert.assertEquals(szQA.replace("12720", "20876"), rowRecords.get(41));
		} else {
			Assert.assertEquals(szQA, rowRecords.get(41));
		}
		logger.info("getSzLangField .........." + rowRecords.get(43));
		Assert.assertEquals(szLanguage, rowRecords.get(43));
	}

	public void verifyEmailOnTable(String email) {
		logger.info("Email .........." + rowRecords.get(11));
		Assert.assertEquals(email, rowRecords.get(11));
	}

	public void verifyAddressOnTable(String street, String city) {
		logger.info("Street .........." + rowRecords.get(6));
		Assert.assertEquals(street, rowRecords.get(6));
		logger.info("City .........." + rowRecords.get(7));
		Assert.assertEquals(city, rowRecords.get(7));
	}

	public void fillBasicDetails(String fName, String lName, String zip) {
		setProperty("brandURL", getDriver().getCurrentUrl());
		waitForElementToBeVisible(getFirstNamebox);
		setProperty("firstName", fName + Common.generateRandomString());
		getFirstNamebox.sendKeys(getProperty("firstName"));
		getLastNamebox.sendKeys(lName);
		getZipbox.sendKeys(zip);
	}

	public void fillEmailOnGetUpdatesForm(String email) {
		logger.info("entering email address on golf form");
		getEmailBox.sendKeys(email);
		getConfirmEmailBox.sendKeys(email);
	}

	public void fillAddressOnGetUpdatesForm(String street, String city) {
		logger.info("entering city and street on golf form ");
		getStreetNamebox.sendKeys(street);
		getCityNamebox.sendKeys(city);
	}

	public void deSelectEmailCheckBox() {
		logger.info("checking email checkbox on golf form");
		checkboxes.get(1).click();
		Assert.assertTrue(!getEmailCheckBox.isSelected());
	}

	public void selectPostalCheckBox() {
		logger.info("checking postal mail checkbox on golf form");
		checkboxes.get(0).click();
	}

	public void closeTab() {
		logger.info("closing windows");
		if (getDriver().getWindowHandles().size() > 1) {
			getDriver().close();
			getDriver().switchTo().window(openWindowHandle);
			refresPage();
			logger.info(getDriver().getCurrentUrl());
		}
	}

	public void verifyErrorFields() {
		logger.info("verifying golf form error states");
		Assert.assertEquals(errorFields.size(), 5);
		logger.info("verifying first name field error states");
		Assert.assertEquals(getFirstNamebox.getAttribute("aria-invalid"), "true");
		logger.info("verifying last name field error states");
		Assert.assertEquals(getLastNamebox.getAttribute("aria-invalid"), "true");
		logger.info("verifying zip field error states");
		Assert.assertEquals(getZipbox.getAttribute("aria-invalid"), "true");
		logger.info("verifying email field error states");
		Assert.assertEquals(getEmailBox.getAttribute("aria-invalid"), "true");
		logger.info("verifying confirm email field error states");
		Assert.assertEquals(getConfirmEmailBox.getAttribute("aria-invalid"), "true");
	}

	public void closeGolfForm() {
		logger.info("closing golf form");
		getCancelButton.click();
	}

	public void verifyGolfFormClosed() {
		logger.info("veriying golf form is closed");
		Assert.assertTrue(!getUpdateForm.isDisplayed(), "Get Updates Form is still opened");
		//refresPage();
	}

	public void openHomePage() {
		logger.info("==============================");
		logger.info("resolving Vehicle URL from argument");
		String vehicleURL = Main.URL;
		logger.info("creating driver" + vehicleURL);
		getDriver().get(vehicleURL);
		new Common().handleHomePagePopup();
		System.setProperty("vaibhav", "myname");
	}
	
	public void refresPage(){
		getDriver().navigate().refresh();
	}
}
