Feature: Candidate_Validate End to End Review process Flow for Eligibility Route 5 and Schedule the exam
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
    And Click on Eligibility Route5
    And click on check box
    And click on Start button
    When Candidate Fill the Application Form_SECTION 1-LAPSED CERTIFICATE INFORMATION_Certificate Number
    And Candidate Fill the Application Form_SECTION 1-LAPSED CERTIFICATE INFORMATION_Lapsed State
    And Candidate Fill the Application Form_SECTION 1-LAPSED CERTIFICATE INFORMATION_Lapsed Date
    And  Select ACCOMMODATIONS as No
    And  Certify REGISTRANT CERTIFICATION
    And  Click on Submit Button
    Then Candidate can view confirmation message  "Successfully Saved Response."
    #And close browser
  @AppApprove
  Scenario: Approve Application from Operation staff Credentia
    Given Launch Chrome Browser
    When OP opens URL "https://credentiauat.examroom.ai/"
    And OP click on GetStarted button
    And  OP Enters Email as "testuser05@examroom.ai" and password as "Credentia$$15"
    And OP click on login button
    And OP click on Manage Applications
    And OP Search with candidate name
    And OP click on Action button for candidate
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
    And Enter the card details and click on Save Card
    Then Validate Card Saved Successfully message
    When Click on saved card radio button
    And Enter CVV
    And Click on Pay button
    Then Validate ExamBooked message
    And Validate Status should be changed to Exam scheduled in Exam Schedule Board.
    And Click on Dashboard and take the screen shot of it