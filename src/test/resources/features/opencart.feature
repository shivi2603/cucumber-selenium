Feature: Login functionality of opencart e-commerce website
  As a user
  I want to be able to login to the application
  So that I can access the account features

  Background:
    Given I am on the opencart login page

  Scenario: successful login to the opencart
    Given I entered valid username and password
    When I click on the login button
    Then I should be logged in successfully


  Scenario Outline: Unsuccessful login with invalid usernames and password
    Given I entered invalid "<username>" and "<password>"
    When  I click on the login button
    Then I should get error message indicating "<errormessage>"

    Examples:

      | username         | password | errormessage                                         |  |
      | invalid@mail.com | invalid  | Warning: No match for E-mail Address and/or Password |  |

