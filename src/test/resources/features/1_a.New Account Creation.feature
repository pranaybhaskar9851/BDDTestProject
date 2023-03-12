Feature: Create a new account on LUMA shopping site
As a new user
I want to create an account in LUMA shopping site
So that I can buy product using my registered account

  @UI
  Scenario: Verify that the user is able to access "Create an Account" page
    Given user launches the LUMA shopping site
    When user is on the LUMA shopping site homepage
    Then user should see the "Create an Account" button on the homepage
    When user clicks on "Create an Account" button
    Then user should be redirected to "Create New Customer Account" page

  @UI
  Scenario: Verify the fileds present on Account Creation page
    Given user launches the LUMA shopping site homepage
    When user clicks on "Create an Account" button
    Then user should see the following fields "<Field Name>"
      | Filed Name       |
      | First Name       |
      | Last Name        |
      | Email            |
      | Password         |
      | Confirm Password |
    And user should see "Create an Account" button

  @UI @RequiredFileds
  Scenario: Verify the required fields on the Account Creation page
    Given user launches the LUMA shopping site homepage
    When user clicks on "Create an Account" button
    And user fills in the form with missing required fields
    And user clicks on the 'Create an Account' button
    Then user should see error messages "This is a required field." indicating the missing fields.

  @UI @EmailFormat
  Scenario Outline: User tries to create an account with invalid email address
    Given user launches the LUMA shopping site homepage
    When user clicks on "Create an Account" button
    And user fills in the form with an invalid email address "<EmailAddress>"
    And user clicks on the 'Create an Account' button
    Then user should see error message "Please enter a valid email address (Ex: johndoe@domain.com)." indicating invalid email address

    Examples: 
      | EmailAddress        |
      | pranaybhaskar       |
      | pranaybhaskar@      |
      | pranaybhaskar@gmail |
      | pranaybhaskar.com   |

  @UI @PasswordPolicy
  Scenario Outline: User tries to create an account with password which does not meet the site password policy requirements
    Given user launches the LUMA shopping site homepage
    When user clicks on "Create an Account" button
    And user fills in the form with an invalid password "<Password>" which does not meet the password policy requirements
    And user clicks on the 'Create an Account' button
    Then user should see error message "<Error Message>" indicating password policy mismatch

    Examples: 
      | Password    | Description        | Error Message                                                                                                                           |
      | abc         | lessthan 8 chars   | Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.                      |
      | abc123      | lessthan 8 chars   | Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.                      |
      | abc@123     | lessthan 8 chars   | Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.                      |
      | abcdefgh    | lessthan 3 classes | Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters. |
      | abcdefgh123 | lessthan 3 classes | Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters. |
      | abcdefgh@   | lessthan 3 classes | Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters. |

  @UI
  Scenario: Creating a new account with mismatched passwords
    Given user launches the LUMA shopping site homepage
    When user clicks on "Create an Account" button
    And user enters valid "first name", "last name", "email", "password" and "mismatched confirm password"
    And user clicks on the "Create an Account" button
    Then user should see an error message "Please enter the same value again." indicating password mismatch

  @PositiveCase @Valid @AccountCreation
  Scenario Outline: Creating a new account with valid details
    Given user launches the LUMA shopping site homepage
    When user clicks on "Create an Account" button
    And user enters valid "<first name>", "<last name>", "<email>", "<password>" and "<confirm password>"
    And user clicks on the 'Create an Account' button
    Then user should see a success message "Thank you for registering with Main Website Store."
    And user should be navigated to 'My Account' page
    And user should see all his Account details

    Examples: 
      | first name | last name | email            | password | confirm password |
      | pranay     | pagadala  | pranay@gmail.com | ******** | ********         |

  @NegativeCase
  Scenario Outline: Creating a new account with an existing email address
    Given user launches the LUMA shopping site homepage
    When user clicks on "Create an Account" button
    And user enters valid "<first name>", "<last name>", "<email>", "<password>" and "<confirm password>"
    And user clicks on the 'Create an Account' button
    Then user should see an error message "<Error Message>" indicating the email address is already registered

    Examples: 
      | first name | last name | email            | password | confirm password | Error Message                                                                                                                                                |
      | pranay     | pagadala  | pranay@gmail.com | ******** | ********         | There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account. |

  @SignIn @PositiveCase
  Scenario: Verify that the user is able Sign in to LUMA site with the registed credentials
    Given user launches the LUMA shopping site
    When user clicks on "Sign In" button
    Then user should be redirected to the "Customer Login" page
    When user enters registered "Email" and "Password"
    And user clicks on the 'Sign In' button
    And user should be navigated to 'My Account' page

  @SignIn @InvalidCredentials @NegativeCase
  Scenario Outline: User tries to login with invalid credentials
    Given user launches the LUMA shopping site
    And user clicks on "Sign In" button
    When user enters invalid credentails for "Email" or "Password"
    And user clicks on the 'Sign In' button
    Then user should see an error message "<Error Message>" indicating incorrect credentials

    Examples: 
      | email            | password         | Error Message                                                                                               |
      | Invalid Email    | ********         | The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later. |
      | pranay@gmail.com | Invalid Password | The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later. |
      
      
      
