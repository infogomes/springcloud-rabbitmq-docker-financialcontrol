Feature: I want to manager transactions

  Scenario: Save transaction without one required field
    Given: a transaction without descrition field
    When the transaction try to save
    Then receive a exception