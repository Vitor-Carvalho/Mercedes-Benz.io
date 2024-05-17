Feature: Explore available vehicles and order online

  Scenario: Validate the negative path of enquiring the highest price at Mercedes-Benz
    Given I am on the search page
    And I have chosen my location
      | State           | Postal Code | Purpose |
      | New South Wales | 2007        | Private |
    When I filter for Pre-Owned cars with colour 'BRILLANTBLUE metallic'
    And I enquire about the most expensive one with invalid data
    Then I get a proper error indicating the reason