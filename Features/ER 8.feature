Feature: Candidate_Validate End to End Review process Flow for Eligibility Route 8 and Schedule the exam
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
    And Click on Eligibility Route8
    And click on check box
    And click on Start button
    And Candidate Fill the Application Form_SECTION1_UPLOAD TRANSCRIPT_Please upload a copy of your college transcript showing successful completion of the fundamentals_basic nursing skills section of a state approved LPN or RN program within the past twenty-four months
    And Candidate Fill the Application Form_SECTION 1_Please upload letter from course instructor on school letterhead
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
    And Enter the card details and click on Save Card
    Then Validate Card Saved Successfully message
    When Click on saved card radio button
    And Enter CVV
    And Click on Pay button
    Then Validate ExamBooked message
    And Validate Status should be changed to Exam scheduled in Exam Schedule Board.
    And Click on Dashboard and take the screen shot of it