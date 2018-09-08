@Brochures
Feature: Lead Testing - Get a Brochure
  I want to test Brochure leads used in Project

  Background: 
    Given user is on Vehicle Homepage
    And user clicks on Get a Brochures Link
    And user verifies Brochure page
    And "Brochures","Guides","Manual" Tab links display
    And Brochures tab displays default
    And user verifies for "mail" button

  @BrochuresEmail
  Scenario Outline: verify Get A brochure lead
    When user clicks on mail button
    Then user verifies brochures Golf form
    And user enters details firstname as "<firstName>" lastname as "<lastName>" and zip as "<zip>"
    And user enter email as "<email>"
    And user click on submit button
    And user login to handraiser db on new tab
    And user searches for the Lead
    Then user verifies "<lastName>", "<zip>", szBrchr as "NoBlank", szQA as "0799A",szLanguage as "en"
    Then user verifies  "<email>"
    Then user closes the tab

    Examples: 
      | firstName | lastName | zip    | email         |
      | Ftest     | LTest    |  48127 | test@test.com |


  @BrochuresPostalMail
  Scenario Outline: verify Get updates form with mail from HomePage
    When user clicks on Get updates Link
    Then user verifies Golf form
    Then user Checks postal checkbox
    Then user Unchecks email checkbox
    And user enters details firstname as "<firstName>" lastname as "<lastName>" and zip as "<zip>"
    And user enters details street as "<street>", city as "<city>"
    And user click on submit button
    And user login to handraiser db on new tab
    And user searches for the Lead
    Then user verifies "<lastName>", "<zip>", szBrchr as "", szQA as "0848312720",szLanguage as "en"
    Then user verifies "<street>" and "<city>"
    Then user closes the tab

    Examples: 
      | firstName | lastName | zip   | street      | city     |
      | Ftest     | LTest    | 48127 | TestAddress | TestCity |

  @BrochuresEmailPostalMail
  Scenario Outline: verify Get updates form with mail and email from HomePage
    When user clicks on Get updates Link
    Then user verifies Golf form
    Then user Checks postal checkbox
    And user enters details firstname as "<firstName>" lastname as "<lastName>" and zip as "<zip>"
    And user enters details street as "<street>", city as "<city>"
    And user enter email as "<email>"
    And user click on submit button
    And user login to handraiser db on new tab
    And user searches for the Lead
    Then user verifies "<lastName>", "<zip>", szBrchr as "", szQA as "0848312720|0799A",szLanguage as "en"
    Then user verifies "<street>" and "<city>"
    Then user verifies  "<email>"
    Then user closes the tab

    Examples: 
      | firstName | lastName | zip   | street      | city     | email         |
      | Ftest     | LTest    | 48127 | TestAddress | TestCity | test@test.com |
    