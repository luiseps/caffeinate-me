Feature: Order a coffee

  In order to save time when I pick up my morning coffee
  As a coffee lover
  I want to be able to order my coffee in advance

  Scenario: Buyer orders a coffee as a registered user
    Given Cathy has a Caffeinate-Me account
    When she orders a large cappuccino
    Then Barry should receive the order


