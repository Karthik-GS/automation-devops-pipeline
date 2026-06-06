Feature: Internet Herokuapp Login Functionality

  Scenario: Verify successful login with valid credentials
    Given User is on the login page
    When User enters valid username "tomsmith" and password "SuperSecretPassword!"
    And User clicks on the login button
    Then User should be redirected to the secure dashboard page