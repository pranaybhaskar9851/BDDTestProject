Feature: As a user, I want to create credentials

  @ValidCase @SomeTags
  Scenario: Successful login using valid account
    Given Login form in login page
    When Submit email using "pranaybhaskar@gmail.com" and password using "pranaybhaskar@gmail.com1"
    And user clicks on Menu item "Men"
    And user clicks on category "Tops"
    And user clicks on category "Jackets"

  #@InvalidCase @SomeTags
  #Scenario Outline: Failed login using invalid account
    #Given Login form in login page
    #When Submit email using "<email>" and password using "<password>"
    #Then Login failed with displaying error message
    #Examples:
      #| email             | password |
      #| qwert             | 123456   |
#
      #|                   | 1234qw   |
      #| akutest@email.com |          |
	  