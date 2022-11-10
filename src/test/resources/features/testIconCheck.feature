Feature: Icon Check

  In order to see an alert with its icon
  As an administrator and user of marketalertum
  I should be able to upload an alert and view a list of alerts

  Scenario: Icon Check

    Given I am an administrator of the website and I upload an alert of type 6
    Given I am a user of marketalertum

    When I view a list of alerts

    Then I should see 1 alerts
    And the icon displayed should be "icon-electronics.png"