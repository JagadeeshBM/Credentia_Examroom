Feature: Candidate_Validate End to End Review process Flow for Eligibility Route 6 and Schedule the exam
  @ClearDB
  Scenario: Clear Application and exam from Database
    Given : Connect to DB
    When :Run cleanup query for Applications
    And :Run Cleanup query for Exams
    Then :Application should be cleared

  @Application
  Scenario: Submit the only Application from Candidate
    Given Candidate launch Chrome Browser
    When Candidate opens URL "https://credentiauat.examroom.ai/"
    And Candidate click on GetStarted button
    And  Candidate Enters Email as "jmcandidatems@mailinator.com" and password as "Exam@123"
    And Candidate click on login button
    #Then Candidate can view Dashboard details
    #When Candidate click on Dashboard
    And Click on Start New Application
    And Click on Eligibility Route6
    And click on check box
    And click on Start button
    And Candidate Fill the Application Form_SECTION 1_CERTIFICATE INFORMATION_Certificate Number
    And Candidate Fill the Application Form_SECTION 1_CERTIFICATE INFORMATION_Date Certification was Revoked
    And Candidate Fill the Application Form_SECTION 1-CERTIFICATE INFORMATION_Please upload a copy of your criminal background check(background check must be within the last 4 weeks from date of application)
    And Candidate Fill the Application Form_SECTION 1_CERTIFICATE INFORMATION_Please upload 3 references
    And  Select ACCOMMODATIONS as No
    And  Certify REGISTRANT CERTIFICATION
    And  Click on Submit Button
    Then Candidate can view confirmation message  "Successfully Saved Response."
    #And close browser
  @STCAppApprove
  Scenario: Approve Application from State Client
    Given Launch Chrome Browser
    When STClient opens URL "https://credentiauat.examroom.ai/"
    And STClient click on GetStarted button
    And  STClient Enters Email as "jmstatems@mailinator.com" and password as "Exam@123"
    And STClient click on login button
    And STClient click on Manage Applications
    And STClient Search with candidate name
    And STClient click on Action button for candidate
    And Click on Approve Button for Approval
    Then Validate Approved success message
    Then login to candidate and validate approved status.
  @RegisterForExam
  Scenario: Candidate should be able to Register for Exam
    Given Click on Register for Exam
    When Candidate select Nurse Aide Written exam
    And Select online exam radio button
    And Select Timezone
    And select date
    And Select Range
    And Select Avilable Slots
    And Click on Add Cart
    Then Validate Added to cart Successfully Message
    When click on pay now
    And Enter the card details and click on Pay Button
    Then Validate Exam Booked Successfully message
    And Validate Status should be changed to Exam scheduled in Exam Schedule Board.
    And Click on Dashboard and take the screen shot of it