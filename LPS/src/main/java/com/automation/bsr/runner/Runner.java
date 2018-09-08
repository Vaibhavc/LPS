package com.automation.bsr.runner;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.Base;
import com.automation.Main;
import com.automation.bsr.util.Common;

public class Runner extends Base {

	String openWindowHandle;
	By getPostalCodeLink = By.id("updatePostalCodeLink");
	static boolean isCommercialVehicle;
	static boolean isFordVehicle;

	@FindBy(className = "fd-golf-form-container")
	WebElement getUpdateForm;
	@FindBy(css = ".dropdown-font.fgx-brand-global-container-pad-left")
	WebElement getSecondaryMenuDrpdwn;
	@FindBy(xpath = "//span[@class='nav-secondary_menu2_top nav-secondary_top nav_top']//span[contains(.,'Pricing & Incentives')] ")
	WebElement getIOLink;
	@FindBy(className = "postal-code-wrapper")
	WebElement getPostalCodeOverlay;
	@FindBy(id = "offers-by-model-tab")
	WebElement getOffersByModelTab;
	@FindBy(id = "txt_zipCode")
	WebElement getZipcodeInput;
	@FindBy(xpath = "//span[.='Update']")
	WebElement getZipcodeUpdateBtn;
	@FindBy(xpath = "//div[contains(@class,'loader ng-animate')]")
	WebElement getLoadingIcon;
	@FindBy(xpath = "//div[@class='io-card-container io-nameplate']//a[.='Get an Internet Price']")
	List<WebElement> getGIPButtonList;
	@FindBy(id = "featured-offers-tab-content")
	WebElement featureTabContent;
	@FindBy(xpath = "//h2[.='Featured Offers']")
	WebElement getfeatureTab;
	@FindBy(xpath = "//h2[.='Pricing & Incentives']")
	WebElement getPricingTab;
	@FindBy(xpath = "(//div[@class='io-title'])/sup[.='7']")
	WebElement getLeaseOfferDisclaimer;
	@FindBy(xpath = "(//div[@class='io-title'])/sup[.='6']")
	WebElement getRetailOfferDisclaimer;
	@FindBy(xpath = "(//div[@class='io-card ng-scope']//i)[1]")
	WebElement getPurchaseiBall;
	@FindBy(xpath = "//*[@class='fgx-tooltip']")
	WebElement getDisclaimerPopup;
	@FindBy(xpath = "//*[@class='fgx-tooltip']//div[2]")
	WebElement getDisclaimerPopupTitle;
	@FindBy(xpath = "//*[@class='fgx-tooltip']//div[1]")
	WebElement getDisclaimerPopupCloseIcon;
	@FindBy(id = "cashOverlay0")
	WebElement getCashOverlay;
	@FindBy(xpath = "//*[@id='cashOverlay0']//strong")
	WebElement getCashOverlayTitle;
	@FindBy(xpath = "//*[@id='cashOverlay0']//i")
	WebElement getCashOverlayCloseIcon;
	@FindBy(xpath = "//*[@class='h1_css fd-golf-form-title']")
	WebElement getGolfFormTitle;
	@FindBy(name = "firstName")
	WebElement getFirstNamebox;
	@FindBy(name = "lastName")
	WebElement getLastNamebox;
	@FindBy(name = "postalCode")
	WebElement getZipcodebox;
	@FindBy(name = "phoneNumber")
	WebElement getPhoneNumberbox;
	@FindBy(name = "emailAddress")
	WebElement getEmailAddressbox;
	@FindBy(xpath = "//button[.='Get an Internet Price']")
	WebElement getSubmitBtn;
	@FindBy(css = ".fd-golf-dealer-list.fd-golf-select-container")
	WebElement getDealerDropdown;
	@FindBy(xpath = "//*[@class='fd-golf-dealer-list-options']//li")
	List<WebElement> getDealerList;
	@FindBy(xpath = "//*[@class='fd-golf-privacy fd-golf-privacy-cta-mbl']/a")
	WebElement getPrivacyLink;
	@FindBy(id = "DEALER-WSLXloginUserIdInput")
	WebElement getHrUsernameField;
	@FindBy(id = "DEALER-WSLXloginPasswordInput")
	WebElement getHrPasswordField;
	@FindBy(xpath = "//input[@value='Login']")
	WebElement getHrLoginBtn;
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement getHrContinueBtn;
	@FindBy(id = "DoSearch")
	WebElement getLpsSearchBtn;
	@FindBy(id = "s2id_autogen6")
	WebElement getFirstNameInput;
	@FindBy(xpath = "//table[@class='leadsearch table table-striped']//tbody/tr[1]")
	WebElement getFirstTableRow;
	@FindBy(xpath = "//div[@class='fd-golf-intro-txt']/div")
	WebElement getConfirmationMessageTitle;
	@FindBy(xpath = "//td[.='Primary Lead Source']/following-sibling::td")
	WebElement primaryLeadSource;
	@FindBy(xpath = "//td[.='Secondary Lead Source']/following-sibling::td")
	WebElement secondaryLeadSource;
	@FindBy(xpath = "//a[.='Final ADF']")
	WebElement finalAdfTab;
	@FindBy(xpath = "//img[@usemap='#IPEMap']")
	WebElement popup;

	public Runner() {
		PageFactory.initElements(getDriver(), this);
	}

	@BeforeTest
	public void stepup() {
		logger.info("==============================");
		logger.info("opening vehicle hompage");
		String vehicleURL = Main.URL;
		getDriver().get(vehicleURL);
		new Common().handleHomePagePopup();
		navigateToIncentivesPage();
		handleZipCodeOverlay();
		goToPriceIncentivesTab();
	}

	@Test
	public void verifyIOTabs() {
		verifyTrue(getPricingTab.isDisplayed(), "pricing tab is displayed", "pricing tab is not  displayed");
		verifyTrue(getfeatureTab.isDisplayed(), "features tab is displayed", "features tab is not  displayed");
	}

	@Test(dependsOnMethods = { "verifyIOTabs" })
	public void verifyGIPGolfForm() {
		openGIP();
		waitForElementToBeVisible(getGolfFormTitle);
		assertEquals(getGolfFormTitle.getAttribute("id"), "getAnInternetPrice");
		isFordVehicle = getDriver().getCurrentUrl().contains("ford");
		setProperty("brandURL", getDriver().getCurrentUrl());
	}

	@Test(dependsOnMethods = { "verifyGIPGolfForm" })
	public void verifyLeadSubmisson() {
		fillGolfForm();
		assertEquals(getConfirmationMessageTitle.getText(), "Request Received");

	}

	@Test(dependsOnMethods = { "verifyLeadSubmisson" })
	public void verifyLeadDetails() {
		gotoLpsAndLogin();
		searchForLead();
		getRowRecordsFromWebtable();
		verifyLeadDetails();
		String primaryLeadText = isFordVehicle ? "Ford.com" : "BC US Lincoln FastQuote";
		Assert.assertTrue(primaryLeadSource.getText().contains(primaryLeadText));
		if (!isCommercialVehicle)
			Assert.assertTrue(secondaryLeadSource.getText().replace(" ", "").contains("NGBS(NGBS)"));
		else
			Assert.assertTrue(secondaryLeadSource.getText().replace(" ", "").contains("NGBS(CommercialNGBS)"));
	}

	
	
	public void getRowRecordsFromWebtable() {
		// To locate table.
		WebElement mytable = getDriver().findElement(By.xpath("//table[@class='leadsearch table table-striped']"));
		// To locate rows of table.
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		// To calculate no of rows In table.
		int rows_count = rows_table.size();
		// Loop will execute till the last row of table.
		for (int row = 1; row < rows_count; row++) {
			// To locate columns(cells) of that specific row.
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			// To calculate no of columns(cells) In that specific row.
			int columns_count = Columns_row.size();
			// Loop will execute till the last cell of that specific row.
			for (int column = 0; column < columns_count; column++) {
				// To retrieve text from that specific cell.
				String celtext = Columns_row.get(column).getText();
				if (celtext.contains(getProperty("firstName"))) {
					System.out.println("Row is...." + row);
					List<String> rowRecords = new ArrayList<String>();
					for (WebElement e : Columns_row) {
						rowRecords.add(e.getText());
					}
					getDriver().findElement(By.xpath(String.format("//a[.='%s']", rowRecords.get(0)))).click();
				}
			}
		}
	}

	public void gotoLpsAndLogin() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.open()");
		openWindowHandle = getDriver().getWindowHandle();
		Set<String> handles = getDriver().getWindowHandles();
		for (String currentWindowHandle : handles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				getDriver().switchTo().window(currentWindowHandle);
				getDriver().get(getProperty("lpsURL"));
				if (!isLoggedin) {
					waitForElementToBeVisible(getHrUsernameField);
					/*
					 * getHrUsernameField.sendKeys(getProperty(
					 * "userName.handraiser"));
					 * getHrPasswordField.sendKeys(getProperty(
					 * "password.handraiser"));
					 */
					getHrUsernameField.sendKeys(Main.username);
					getHrPasswordField.sendKeys(Main.password);
					getHrLoginBtn.click();
					isLoggedin = true;
					waitForElementToBeVisible(getHrContinueBtn);
					getHrContinueBtn.click();
					waitForElementToBeVisible(getLpsSearchBtn);
				}
			}
		}
	}

	public void searchForLead() {
		// setProperty("firstName", "fNameBaNUb");
		waitForElementToBeVisible(getFirstNameInput);
		getFirstNameInput.clear();
		getFirstNameInput.sendKeys(getProperty("firstName"));
		// getFirstNameInput.sendKeys("fNameDbXKf");
		getDriver()
				.findElement(By.xpath(
						String.format("//*[@class='select2-result-label']/span[.='%s']", getProperty("firstName"))))
				.click();

		getLpsSearchBtn.click();
		waitForElementToBeVisible(getFirstTableRow);
	}

	public void verifyTrue(boolean condition, String successMsg, String failureMsg) {
		if (condition) {
			logger.info("passed:- " + successMsg);
			Assert.assertTrue(condition);
		} else
			Assert.assertTrue(condition, failureMsg);

	}

	public void navigateToIncentivesPage() {
		getSecondaryMenuDrpdwn.click();
		waitForElementToBeClickable(getIOLink);
		getIOLink.click();
		waitForTitleToContain("Pricing");
	}

	public void fillGolfForm() {
		waitForElementToBeVisible(getFirstNamebox);
		setProperty("firstName", "test" + Common.generateRandomString());
		System.out.println(getProperty("firstName"));
		getFirstNamebox.sendKeys(getProperty("firstName"));
		getLastNamebox.sendKeys("info");
		getPhoneNumberbox.sendKeys("0123456789");
		getEmailAddressbox.sendKeys("test@test.com");
		System.out.println(getZipcodebox.getText());
		isCommercialVehicle = getDriver().getCurrentUrl().contains("commercial");
		getSubmitBtn.click();
		waitForElementToBeVisible(getConfirmationMessageTitle);
	}

	public void handleZipCodeOverlay() {
		if (getPostalCodeOverlay.isDisplayed()) {
			logger.info("handling zip code overlay");
			getZipcodeInput.clear();
			getZipcodeInput.sendKeys("48127");
			getZipcodeUpdateBtn.click();
		}
	}

	public void goToPriceIncentivesTab() {
		waitForElementToBeVisible(featureTabContent);
		waitForElementToBeClickable(getOffersByModelTab);
		getOffersByModelTab.click();
	}

	public void openGIP() {
		logger.info("listed model count -" + getGIPButtonList.size());
		WebElement firstGIPBtn = getGIPButtonList.get(0);
		waitForElementToBeVisible(firstGIPBtn);
		clickUsingJavaScript(firstGIPBtn);
	}
}
