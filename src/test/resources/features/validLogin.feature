Feature: Valid Login

  In order to view my alerts
  As a user of the system
  I should be able to login

  Scenario: Valid Login
    Given I am a user of marketalertum
    When I login using valid credentials
    Then I should see my alerts
