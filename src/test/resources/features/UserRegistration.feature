Feature: User Registration

  Scenario Outline: Creating user account with different inputs
    Given I am on the "<Browser>" User Registration Page
    * I have entered date of birth "<DateOfBirth>"
    * I have entered the name "<FirstName>" and the surname "<LastName>"
    * I have entered email address "<EmailAddress>" and confirmed "<ConfirmedEmail>"
    * I have entered a password "<Password>"
    * I have confirmed the password "<ConfirmedPassword>"
    * I have <TermsConditionStatus> the Terms and Conditions
    * I have accepted age requirements
    * I have accepted Code of Ethics and Conduct
    When I have clicked the button join and confirm
    Then my user account is <Outcome>

    Examples:
      | Browser | DateOfBirth | FirstName | LastName  | EmailAddress    | ConfirmedEmail  | Password  | ConfirmedPassword | TermsConditionStatus  | Outcome                               |
      | Chrome  | 27/10/1990  | Astrid    | Kling     | test43@mail.com | test43@mail.com | Password  | Password          | accepted              |  successfully created                 |
      | Edge    | 02/01/1959  | Bodil     |           | bodil@mail.com  | bodil@mail.com  | Password  | Password          | accepted              |  missing last name input              |
      | Chrome  | 07/07/1988  | Clara     | Kim       | stina@mail.com  | stina@mail.com  | Password  | Password1         | rejected              |  lacking correct password confirmation|
      | Firefox | 20/04/1933  | Ester     | Klang     | ester@mail.com  | ester@mail.com  | Password  | Password          | rejected              |  lacking accepted terms and conditions|


  #Scenario: Creating user account with correct input
    #Given I am on the User Registration Page
    #* I have entered date of birth "27/10/1990"
    #* I have entered the name "Clara" and the surname "Kim"
    #* I have entered email address "test1@mail.com" and confirmed "test1@mail.com"
    #* I have entered a password "Password"
    #* I have confirmed the password "Password"
    #* I have "accepted" the Terms and Conditions
    #* I have "accepted" age requirements and acceptance of CoC
    #When I have clicked the button join and confirm
    #Then my user account is "successfully created"


  #Scenario: Creating user account without surname
    #Given I am on the User Registration Page
    #* I have entered date of birth "27/10/1990"
    #* I have entered the name "Clara" and the surname ""
    #* I have entered email address "clara@mail.com" and confirmed "clara@mail.com"
    #* I have entered a password "Password"
    #* I have confirmed the password "Password"
    #* I have "accepted" the Terms and Conditions
    #* I have "accepted" age requirements and acceptance of CoC
    #When I have clicked the button join and confirm
    #Then my user account is not successfully created and error message is presented


  #Scenario: Creating user account with incorrect password confirmation
    #Given I am on the User Registration Page
    #* I have entered date of birth "27/10/1990"
    #* I have entered the name "Clara" and the surname "Kim"
    #* I have entered email address "clara@mail.com" and confirmed "clara@mail.com"
    #* I have entered a password "Password"
    #* I have confirmed the password "Password1"
    #* I have "accepted" the Terms and Conditions
    #* I have "accepted" age requirements and acceptance of CoC
    #When I have clicked the button join and confirm
    #Then my user account is not successfully created and error message is presented


  #Scenario: Creating user account without acceptance of terms and conditions
    #Given I am on the User Registration Page
    #* I have entered date of birth "27/10/1990"
    #* I have entered the name "Clara" and the surname "Kim"
    #* I have entered email address "clara@mail.com" and confirmed "clara@mail.com"
    #* I have entered a password "Password"
    #* I have confirmed the password "Password"
    #* I have "not accepted" the Terms and Conditions
    #* I have "accepted" age requirements and acceptance of CoC
    #When I have clicked the button join and confirm
    #Then my user account is not successfully created and error message is presented


