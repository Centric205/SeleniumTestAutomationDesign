# Feature file for login functionality
# This file contains scenarios for successful and failed login attempts
# Written in clear business-readable language for stakeholders


Feature: Login Functionality
  # High-level description of the feature
  As a registered user
  I want to log in to the web application
  So that I can access my account securely


  # -------------------------------
  # Scenario 1: Successful Login
  # -------------------------------
  Scenario Outline: User logs in successfully with valid credentials
    Given the user is on the login page
    When the user enters username "<username>"
    And the user enters password "<password>"
    And the user clicks the login button
    Then the user should see "<expectedMessage>"


  # -------------------------------
  # Test Data: Examples Table
  # -------------------------------
  Examples:
    # Successful login
    | username          | password          | expectedMessage     |
    | theo@gmail.com    | Test@2026         | Welcome Theo        |

    # Invalid login: Wrong Password
    | theo@gmail.com   | wrongPassword     | Invalid username or password |

    # Invalid login: Wrong username
    | invalid.user@gmail.com | Test@2026    | Invalid username or password |

    # Invalid login: both wrong
    | invalid.user@gmail.com | wrongPassword | Invalid username or password |