Feature: Alert Limit

  In order to see 5 alerts
  As an administrator of the website
  As a user of the system
  I should be able to upload more than 5 alerts

  Scenario: Alert Limit

    Given I am an administrator of the website and I upload more than 5 alerts
    Given I am a user of marketalertum

    When I view a list of alerts

    Then I should see 5 alerts