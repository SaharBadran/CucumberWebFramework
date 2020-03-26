Feature: Create Incident
  AS A User
  I WANT TO Create Incident using API request
  
  Scenario: Perform Get request and verify that response return the valid Authorization value
    Given User is calling the Get Request and set Headers
    And User print the response on Console
    Then response should return the valid Authorization value
    
  Scenario: Perform Post request and verify that response return the valid Attachment ID
    Given User is calling the Post Attachment Request and set Headers
    Then response should return the valid Attachment ID
 
 Scenario: Perform Post request and verify that response return the note status
    Given User is calling the Post Note Request and set Headers
    Then response should return the note status