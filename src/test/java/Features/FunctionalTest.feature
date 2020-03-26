Feature: Create Incident
  AS A User
  I WANT TO Create Incident
  SO THAT I am able to submit request

  Scenario Outline: Submit a Success Feedback with comment and valid attachments
    Given the User is on "https://wtt-api-test.skytap-tss.vodafone.com/TA123456789014?token=z%2Fa3Z3kzK0qtzpURo%2FOyQdw1I6nrOazfLfX77fkz6SU%3D"
    When the user select request option number "<optionNumber>"
    And he add "<comment>"
    And he uploads the Incident file "Service.xlsx"
    And he submit the request
    Then he should be navigated to success page with message "Thank you for your message!"
    Examples:
    |comment       |optionNumber|
    |First request |     1      |
    |Second request|     2      |