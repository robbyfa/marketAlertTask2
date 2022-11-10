Feature: Invalid Login

  In order to see the login screen again
  As a user of the system
  I should be able to enter invalid credentials

  Scenario: Valid Login
    Given I am a user of marketalertum
    When I login using invalid credentials
    Then I should see the login screen again
