package com.automation.bsr.stepdef;

import com.automation.bsr.pages.Brochures;
import com.automation.bsr.pages.Homepage;
import com.automation.bsr.pages.IO;
import com.automation.bsr.pages.page;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDef {
	Homepage hp = new Homepage();
	IO io = new IO();
	Brochures br = new Brochures();

	@Given("^user is on vehicle Homepage$")
	public void verifyHomepage() {
		hp.openHomePage();
		System.out.println("Vehicle  Homepage opened ");
	}

	@When("^user clicks on Get updates Link$")
	public void clickGetUpdatesLink() throws Throwable {
		hp.openGetUpdateForm();
	}

	@Then("^user verifies Golf form$")
	public void verifyGolfForm() throws Throwable {
		hp.verifyGetUpdateForm();
	}

	@And("^user closes the golf form$")
	public void closeGolfForm() throws Throwable {
		hp.closeGolfForm();
	}

	@And("^user click on submit button$")
	public void clickOnSubmitButton() throws Throwable {
		hp.clickOnSubmitButton();
	}

	@And("^verfies the confirmation$")
	public void verifyConfirmation() throws Throwable {
		hp.verifyConfirmation();

	}

	@Then("^user enters details firstname as \"([^\"]*)\" lastname as \"([^\"]*)\" and zip as \"([^\"]*)\"$")
	public void fillBasicDetails(String fName, String lName, String zip) throws Throwable {
		hp.fillBasicDetails(fName, lName, zip);
	}

	@Then("^user enter email as \"([^\"]*)\"$")
	public void enterEmail(String email) throws Throwable {
		hp.fillEmailOnGetUpdatesForm(email);
	}

	@And("^user login to handraiser db on new tab$")
	public void accessHandRaiser() {
		hp.gotoHandRaiserAndLogin();
	}

	@Then("^user searches for the Lead$")
	public void clickSearchButton() {
		hp.searchSubmittedLead();
	}

	@Then("^user verifies \"([^\"]*)\", \"([^\"]*)\", szBrchr as \"([^\"]*)\", szQA as \"([^\"]*)\",szLanguage as \"([^\"]*)\"$")
	public void verify_lName_szBrchr_szQA_szLanguage(String lName, String zip, String szBrchr1, String szQA,
			String szLanguage) throws Throwable {
		hp.verifyFieldsOnTable(lName, zip, szBrchr1, szQA, szLanguage);
	}

	@Then("^user verifies  \"([^\"]*)\"$")
	public void verifyEmail(String email) throws Throwable {
		hp.verifyEmailOnTable(email);
	}

	@Then("^user Checks postal checkbox$")
	public void checkPostalCheckbox() throws Throwable {
		hp.selectPostalCheckBox();
	}

	@Then("^user Unchecks email checkbox$")
	public void uncheckPostalCheckbox() throws Throwable {
		hp.deSelectEmailCheckBox();
	}

	@Then("^user enters details street as \"([^\"]*)\", city as \"([^\"]*)\"$")
	public void fillAddress(String street, String city) throws Throwable {
		hp.fillAddressOnGetUpdatesForm(street, city);
	}

	@Then("^user verifies \"([^\"]*)\" and \"([^\"]*)\"$")
	public void verifyAddress(String street, String city) throws Throwable {
		hp.verifyAddressOnTable(street, city);
	}

	@Then("^user closes the tab$")
	public void closeTab() {
		hp.closeTab();
	}

	@When("^I strt$")
	public void q() {
		System.out.println("Hk");
	}

	@When("^user goes to IO Page$")
	public void user_goes_to_IO_Page() throws Throwable {
		io.goIOPage();
	}

	@Then("^user verifies the IO Page$")
	public void user_verifies_the_IO_Page() throws Throwable {
		io.verifyIOPage();
	}

	@When("^user clicks on GIP button$")
	public void user_clicks_on_GIP_button() throws Throwable {
		io.OpenIOForm();
	}

	@Then("^user verifies GIP Golf form$")
	public void user_verifies_GIP_Golf_form() throws Throwable {
		io.verifyGIPForm();
	}

	@When("^user clicks on dealer dropdown$")
	public void user_clicks_on_dealer_dropdown() throws Throwable {
		io.clickOnDealerDropDown();
	}

	@Then("^user verifies dropdown expands with (\\d+) nearest dealers as options$")
	public void user_verifies_dropdown_expands_with_nearest_dealers_as_options(int arg1) throws Throwable {
		io.verifyDealerList();
	}

	@When("^user fills the form with \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_fills_the_form_with_and(String fName, String lName, String phoneNumber, String email, String zip) {
		io.fillUserDetails(fName, lName, phoneNumber, email, zip);
	}

	@When("^user clicks on GIP submit button$")
	public void user_clicks_on_GIP_submit_button() throws Throwable {
		io.submitIOLeads();
	}

	@Then("^user verifies confirmation message$")
	public void user_verifies_confirmation_message() throws Throwable {
		io.verifyConfirmationMessage();
	}

	@Then("^user access and login to the LPS in new tab$")
	public void user_access_and_login_to_the_LPS_in_new_tab() throws Throwable {
		io.gotoLpsAndLogin();
	}

	@When("^user searches for lead submitted$")
	public void user_searches_for_lead_submitted() throws Throwable {
		io.searchForLead();
	}

	@And("^user goes to lead deatails page$")
	public void gotoLeadDetails() {
		io.getRowRecordsFromWebtable();
	}

	@Then("^user verifies the details on LPS primary and Secondary lead source$")
	public void user_verifies_the_details_on_LPS() throws Throwable {
		io.verifyLeadDetails();
	}

	@Then("^check checkbox values on ADF tab for \"([^\"]*)\" and \"([^\"]*)\"$")
	public void check_checkbox_values_on_ADF_tab_for_and(String followupOption, String financeOption) {
		io.verifyAdfTab(followupOption, financeOption);
	}

	@Given("^user is on Vehicle Homepage$")
	public void user_is_on_Vehicle_Homepage() throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@Given("^user clicks on Get a Brochures Link$")
	public void user_clicks_on_Get_a_Brochures_Link() throws Throwable {
		br.goToBrocuresPage();

	}

	@Given("^user verifies Brochure page$")
	public void user_verifies_Brochure_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@Given("^\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" Tab links display$")
	public void tab_links_display(String arg1, String arg2, String arg3) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@Given("^Brochures tab displays default$")
	public void brochures_tab_displays_default() throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@Given("^user verifies for \"([^\"]*)\" button$")
	public void user_verifies_for_button(String arg1) throws Throwable {
		br.verifyMailButton();

	}

	@When("^user clicks on mail button$")
	public void user_clicks_on_mail_button() throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@Then("^user verifies brochures Golf form$")
	public void user_verifies_the_golf_form() throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@Then("^user verifies the error fields$")
	public void verifyErrorStates() {
		hp.verifyErrorFields();
	}

	@And("^user verifies that golf is closed$")
	public void verifyGolfFormCloses() {
		hp.verifyGolfFormClosed();
	}

	page p = new page();

	@Given("^user launch the app$")
	public void veri() {
		p.veri();
	}

	@Given("^i have$")
	public void veri2() {
		p.veri2();
	}

	@Given("^we have$")
	public void veri3() {
		p.veri2();
	}
}
