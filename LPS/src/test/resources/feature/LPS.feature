@GIP
Feature: Lead Testing - GIP 
  I want to test GIP leads used in Project

  Background: Setting Vehicle Homepage
    Given user is on vehicle Homepage

  @CustomerFollowup
  Scenario Outline: verify GIP form with email from HomePage
    When user goes to IO Page
    Then user verifies the IO Page
    When user clicks on GIP button
    Then user verifies GIP Golf form
    When user clicks on dealer dropdown
    #Then user verifies dropdown expands with 5 nearest dealers as options
    When user fills the form with "<firstname>","<lastName>","<phoneNumber>","<email>" and "<zip>"
    And user clicks on GIP submit button
    Then user verifies confirmation message
    And user access and login to the LPS in new tab
    When user searches for lead submitted
    And user goes to lead deatails page
    Then user verifies the details on LPS primary and Secondary lead source
   
    Examples: 
      | firstname | lastName | phoneNumber | email         | zip   |
      | fName     | lName    |  1234567890 | test@test.com | 48127 |

