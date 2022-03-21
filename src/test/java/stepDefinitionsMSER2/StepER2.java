package stepDefinitionsMSER2;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepER2 {
    WebDriver driver;
    public static WebElement element;

    public StepER2() {
    }

    public void main(String[] args) {
    }

    @Given(": Connect to DB")
    public void connect_to_DB() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ExamRoomV2UAT", "ExamRoomV2UatUser", "S0oprS3cur1t3M@n");
        Statement stmt = con.createStatement();
        String query = "GO\ndeclare @emailId varchar(100) ='jmcandidatems@mailinator.com';\ndeclare @persontenantroleId as int;\ndeclare @PID as int;\n\nselect @persontenantroleId = ptr.Id, @PID = p.Id\nfrom dbo.person p\ninner join dbo.PersonTenantRole ptr\non p.Id = ptr.PersonId\nand p.EmailId = @emailId\n\nselect @persontenantroleId = ptr.Id\nfrom dbo.person p\ninner join dbo.PersonTenantRole ptr\non p.Id = ptr.PersonId\nand p.EmailId = @emailId\nand ptr.PersonRoleId = 1\nwhere emailid = @emailId;\n\ndelete PFNF\nfrom dbo.PersonFormNoteFile PFNF\ninner join dbo.PersonFormNote PFN\non PFNF.PersonFormNOteId = PFN.Id\ninner join dbo.PersonForm PF\non PF.Id = PFN.PersonFormId\nand PF.PersonTenantRoleId = @persontenantroleId\n\ndelete PFN\nfrom dbo.PersonFormNote PFN\ninner join dbo.PersonForm PF\non PF.Id = PFN.PersonFormId\nand PF.PersonTenantRoleId = @persontenantroleId\n\ndelete sl from dbo.PersonForm pf\ninner join dbo.PersonFormStatusLog sl\non pf.id = sl.PersonFormId\nand pf.persontenantroleid = @persontenantroleId\n\ndelete pfr from dbo.PersonForm pf\ninner join dbo.PersonFormReview pfr\non pf.id = pfr.PersonFormId\nand pf.persontenantroleid = @persontenantroleId\n\ndelete pf from dbo.PersonForm pf\nwhere  pf.persontenantroleid = @persontenantroleId\n\n\ndelete pf from dbo.PersonEligibilityRoute pf\nwhere  pf.persontenantroleid = @persontenantroleId\n\nUPDATE PERSON SET DATADETAIL = JSON_MODIFY(DATADETAIL, '$.TrainingInstituteId', 0) WHERE ID = @PID\ndelete from Voucher where assigntopersonid= @PID\n\t\nupdate voucher set assigntopersonid=null\nwhere id in (select id from voucher where assigntopersonid in (select id from person where emailid='jmcandidatems@mailinator.com '))\n";
        stmt.executeQuery(query);
        System.out.println("Application is cleared successfully");
    }

    @When(":Run cleanup query for Applications")
    public void run_cleanup_query_for_Applications() {
    }

    @When(":Run Cleanup query for Exams")
    public void run_Cleanup_query_for_Exams() {
    }

    @Then(":Application should be cleared")
    public void application_should_be_cleared() {
    }

    @Given("Candidate launch Chrome Browser")
    public void candidate_launch_Chrome_Browser() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C://Drivers//chromedriver_win32//chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().deleteAllCookies();
        this.driver.manage().timeouts().implicitlyWait(50L, TimeUnit.SECONDS);
    }

    @When("Candidate opens URL {string}")
    public void candidate_opens_URL(String string) {
        this.driver.get("https://credentiauat.examroom.ai/");
    }

    @When("Candidate click on GetStarted button")
    public void candidate_click_on_GetStarted_button() {
        this.driver.findElement(By.xpath("/html/body/main/div/div/div[2]/div/div/a")).click();
    }

    @When("Candidate Enters Email as {string} and password as {string}")
    public void candidate_Enters_Email_as_and_password_as(String string, String string2) {
        this.driver.findElement(By.xpath("//*[@id=\"mat-input-0\"]")).sendKeys(new CharSequence[]{"jmcandidatems@mailinator.com"});
        this.driver.findElement(By.xpath("//*[@id=\"mat-input-1\"]")).sendKeys(new CharSequence[]{"Exam@123"});
    }

    @When("Candidate click on login button")
    public void candidate_click_on_login_button() throws InterruptedException {
        this.driver.findElement(By.xpath("/html/body/exai-root/exai-login/div/div/form/button")).click();
        Thread.sleep(10000L);
    }

    @When("Click on Start New Application")
    public void click_on_Start_New_Application() throws IOException {
        WebDriverWait wait = new WebDriverWait(this.driver, 500L);
        WebElement element = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/exai-root/exai-custom-layout/exai-layout/div/mat-sidenav-container/mat-sidenav-content/main/exai-dashboard/div/div/div/div/div[4]/button")));
        element.click();
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File src = (File)ts.getScreenshotAs(OutputType.FILE);
        File trg = new File(".\\screenshots\\homepage_CR.png");
        FileUtils.copyFile(src, trg);
    }

    @And("Click on Eligibility Route{int}")
    public void clickOnEligibilityRoute(int arg0) throws IOException {
        this.driver.findElement(By.xpath("//body//exai-root//exai-layout//button[2]")).click();
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File src = (File)ts.getScreenshotAs(OutputType.FILE);
        File trg = new File(".\\screenshots\\ER_Validation.png");
        FileUtils.copyFile(src, trg);
    }

    @When("click on check box")
    public void click_on_check_box() {
        this.driver.findElement(By.xpath("//*[@id=\"mat-checkbox-1\"]/label/span[1]")).click();
    }

    @When("click on Start button")
    public void click_on_Start_button() throws InterruptedException {
        this.driver.findElement(By.xpath("/html/body/exai-root/exai-custom-layout/exai-layout/div/mat-sidenav-container/mat-sidenav-content/main/exai-application/div/div[2]/div[2]/div/div[2]/button")).click();
        Thread.sleep(1200L);
    }

    @When("Candidate Fill the Application Form_SECTION{int} UPLOAD CERTIFICATE")
    public void candidateFillTheApplicationForm_SECTIONUPLOADCERTIFICATE(int arg0) throws InterruptedException, IOException {
        WebElement uploaddocument = this.driver.findElement(By.xpath("//button[@class='mat-focus-indicator btn-2 text-xs mat-button mat-button-base ng-star-inserted']"));
        uploaddocument.click();
        Thread.sleep(1000L);
        Runtime.getRuntime().exec("C:\\Autoitfile\\fileupload.exe D:\\Document\\Noodles-MSLocations_Address.pdf");
        Thread.sleep(1000L);
    }

    @When("Select ACCOMMODATIONS as No")
    public void select_ACCOMMODATIONS_as_No() throws InterruptedException {
        this.driver.findElement(By.xpath("//span[@class='mat-expansion-indicator ng-tns-c133-16 ng-trigger ng-trigger-indicatorRotate ng-star-inserted']")).click();
        Thread.sleep(800L);
        this.driver.findElement(By.xpath("//label[@for='mat-radio-11-input']//span[@class='mat-radio-inner-circle']")).click();
    }

    @When("Certify REGISTRANT CERTIFICATION")
    public void certify_REGISTRANT_CERTIFICATION() throws InterruptedException {
        this.driver.findElement(By.xpath("//span[@class='mat-expansion-indicator ng-tns-c133-18 ng-trigger ng-trigger-indicatorRotate ng-star-inserted']")).click();
        Thread.sleep(1500L);
        this.driver.findElement(By.xpath("//span[@class='mat-checkbox-inner-container']")).click();
        Thread.sleep(10000L);
    }

    @When("Click on Submit Button")
    public void click_on_Submit_Button() throws InterruptedException {
        this.driver.findElement(By.xpath("//body//exai-root//button[4]")).click();
        Thread.sleep(800L);
        this.driver.findElement(By.xpath("//button[@class='mat-focus-indicator btn-1 mat-button mat-button-base mat-primary']")).click();
        Thread.sleep(16000L);
    }

    @Then("Candidate can view confirmation message  {string}")
    public void candidate_can_view_confirmation_message(String string) throws InterruptedException, IOException {
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File src = (File)ts.getScreenshotAs(OutputType.FILE);
        File trg = new File(".\\screenshots\\AppSubmitted_CR.png");
        FileUtils.copyFile(src, trg);
    }

    @Given("Launch Chrome Browser")
    public void launch_Chrome_Browser() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C://Drivers//chromedriver_win32//chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        Thread.sleep(2000L);
    }

    @When("OP opens URL {string}")
    public void op_opens_url(String string) {
        this.driver.get("https://credentiauat.examroom.ai/");
    }

    @When("OP click on GetStarted button")
    public void op_click_on_get_started_button() {
        this.driver.findElement(By.xpath("/html/body/main/div/div/div[2]/div/div/a")).click();
    }

    @When("OP Enters Email as {string} and password as {string}")
    public void op_enters_email_as_and_password_as(String string, String string2) {
        this.driver.findElement(By.xpath("//*[@id=\"mat-input-0\"]")).sendKeys(new CharSequence[]{"testuser05@examroom.ai"});
        this.driver.findElement(By.xpath("//*[@id=\"mat-input-1\"]")).sendKeys(new CharSequence[]{"Credentia$$15"});
    }

    @When("OP click on login button")
    public void op_click_on_login_button() throws InterruptedException {
        this.driver.findElement(By.xpath("/html/body/exai-root/exai-login/div/div/form/button")).click();
        Thread.sleep(50500L);
    }

    @When("OP click on Manage Applications")
    public void op_click_on_manage_applications() throws InterruptedException {
        this.driver.findElement(By.xpath("//span[@class='item-label ng-tns-c298-7']")).click();
        Thread.sleep(20000L);
    }

    @When("OP Search with candidate name")
    public void op_search_with_candidate_name() throws InterruptedException {
        this.driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(new CharSequence[]{"Appa B Mulimani"});
        Thread.sleep(1000L);
    }

    @When("OP click on Action button for candidate")
    public void op_click_on_action_button_for_candidate() throws InterruptedException {
        this.driver.findElement(By.xpath("//tbody/tr[1]/td[10]/button[1]/span[1]/mat-icon[1]")).click();
        Thread.sleep(10000L);
    }

    @When("Click on Approve Button for Approval")
    public void click_on_approve_button_for_approval() throws InterruptedException {
        this.driver.findElement(By.xpath("//button[@class='mat-focus-indicator btn-1 t-xs mr-2 mat-button mat-button-base ng-star-inserted']")).click();
        this.driver.findElement(By.xpath("//textarea[@id='mat-input-1']")).sendKeys(new CharSequence[]{"Approved By Jaga opperationstaff"});
        this.driver.findElement(By.xpath("//button[@class='mat-focus-indicator btn-1 mat-button mat-button-base mat-primary']")).click();
        Thread.sleep(12000L);
    }

    @Then("Validate Approved success message")
    public void validate_approved_success_message() {
    }

    @Then("login to candidate and validate approved status.")
    public void login_to_candidate_and_validate_approved_status() throws InterruptedException, IOException {
        this.driver.get("https://credentiauat.examroom.ai/");
        this.driver.findElement(By.xpath("/html/body/main/div/div/div[2]/div/div/a")).click();
        this.driver.findElement(By.xpath("//*[@id=\"mat-input-0\"]")).sendKeys(new CharSequence[]{"jmcandidatems@mailinator.com"});
        this.driver.findElement(By.xpath("//*[@id=\"mat-input-1\"]")).sendKeys(new CharSequence[]{"Exam@123"});
        this.driver.findElement(By.xpath("/html/body/exai-root/exai-login/div/div/form/button")).click();
        Thread.sleep(30000L);
        String actual_status = this.driver.findElement(By.xpath("//span[@class='t-xs ml-2 -mt-3']")).getText();
        String expected_status = "Approved";
        Assert.assertEquals(actual_status, expected_status);
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File src = (File)ts.getScreenshotAs(OutputType.FILE);
        File trg = new File(".\\screenshots\\Application Approved by TP.png");
        FileUtils.copyFile(src, trg);
    }

    @Given("Click on Register for Exam")
    public void click_on_Register_for_Exam() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C://Drivers//chromedriver_win32//chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.get("https://credentiauat.examroom.ai/");
        this.driver.findElement(By.xpath("/html/body/main/div/div/div[2]/div/div/a")).click();
        this.driver.findElement(By.xpath("//*[@id=\"mat-input-0\"]")).sendKeys(new CharSequence[]{"jmcandidatems@mailinator.com"});
        this.driver.findElement(By.xpath("//*[@id=\"mat-input-1\"]")).sendKeys(new CharSequence[]{"Exam@123"});
        this.driver.findElement(By.xpath("/html/body/exai-root/exai-login/div/div/form/button")).click();
        Thread.sleep(30000L);
        WebElement register = this.driver.findElement(By.xpath("//button[@class='mat-focus-indicator mat-button mat-button-base mat-primary add-new text-xs']"));
        register.click();
        Thread.sleep(10000L);
    }

    @When("Candidate select Nurse Aide Written exam")
    public void candidate_select_Nurse_Aide_Written_exam() {
        this.driver.findElement(By.xpath("//div[@class='card shadow-none cardBorder']//div[1]//button[1]")).click();
    }

    @When("Select online exam radio button")
    public void select_online_exam_radio_button() {
        this.driver.findElement(By.xpath("//label[@for='mat-radio-8-input']//span[@class='mat-radio-inner-circle']")).click();
    }

    @When("Select Timezone")
    public void select_Timezone() {
        this.driver.findElement(By.xpath("//div[@class='mat-select-arrow ng-tns-c128-12']")).click();
        this.driver.findElement(By.xpath("//span[normalize-space()='(UTC-05:00) Eastern Time (US & Canada)']")).click();
        this.driver.findElement(By.xpath("//button[@aria-label='Open calendar']")).click();
    }

    @When("select date")
    public void select_date() throws InterruptedException {
        while(true) {
            String text = this.driver.findElement(By.xpath("//span[@id='mat-calendar-button-0']")).getText();
            String month = "MAY 2022";
            if (text.equals(month)) {
                this.driver.findElement(By.xpath("//div[normalize-space()='7']")).click();
                Thread.sleep(10000L);
                return;
            }

            this.driver.findElement(By.xpath("//button[@aria-label='Next month']")).click();
        }
    }

    @When("Select Range")
    public void select_Range() throws InterruptedException {
        this.driver.findElement(By.xpath("//button[@class='mat-focus-indicator mat-stroked-button mat-button-base mat-light px-4 ml-3 mb-3 pt-3 pb-3 state slots2 buttom6 active ng-star-inserted']")).click();
        Thread.sleep(800L);
    }

    @When("Select Avilable Slots")
    public void select_Avilable_Slots() {
        this.driver.findElement(By.xpath("/html/body/exai-root/exai-custom-layout/exai-layout/div/mat-sidenav-container/mat-sidenav-content/main/exai-register-for-exam/div/div[2]/div[2]/div[1]/div[1]/div[6]/div/button[2]")).click();
    }

    @When("Click on Add Cart")
    public void click_on_Add_Cart() throws InterruptedException {
        this.driver.findElement(By.xpath("//button[@class='mat-focus-indicator buuton1 mr-4 mat-raised-button mat-button-base ng-star-inserted']")).click();
        Thread.sleep(8000L);
    }

    @Then("Validate Added to cart Successfully Message")
    public void validate_Added_to_cart_Successfully_Message() throws InterruptedException, IOException {
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File src = (File)ts.getScreenshotAs(OutputType.FILE);
        File trg = new File(".\\screenshots\\Exam_Added_toCart.png");
        FileUtils.copyFile(src, trg);
        this.driver.findElement(By.xpath("//mat-icon[normalize-space()='close']")).click();
        Thread.sleep(1000L);
    }

    @When("click on pay now")
    public void click_on_pay_now() throws InterruptedException {
        this.driver.findElement(By.xpath("//button[@class='mat-focus-indicator mr-5 buuton2 height mat-raised-button mat-button-base ng-star-inserted']")).click();
        Thread.sleep(12000L);
    }

    @When("Enter the card details and click on Save Card")
    public void enter_the_card_details_and_click_on_Save_Card() throws IOException, InterruptedException {
        this.driver.findElement(By.xpath("//*[@id=\"cardnumber\"]")).sendKeys(new CharSequence[]{"4111 1111 1111 1111"});
        this.driver.findElement(By.xpath("//input[@id='first_last_name']")).sendKeys(new CharSequence[]{"Jaga M"});
        this.driver.findElement(By.xpath("//input[@id='expirationdate']")).sendKeys(new CharSequence[]{"2028-12"});
        this.driver.findElement(By.xpath("//input[@id='securitycode']")).sendKeys(new CharSequence[]{"999"});
        this.driver.findElement(By.xpath("//button[@class='mat-focus-indicator buuton2 text-xs mat-raised-button mat-button-base']")).click();
        Thread.sleep(18000L);
    }

    @Then("Validate Card Saved Successfully message")
    public void validate_Card_Saved_Successfully_message() throws IOException {
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File src = (File)ts.getScreenshotAs(OutputType.FILE);
        File trg = new File(".\\screenshots\\Save_Card.png");
        FileUtils.copyFile(src, trg);
    }

    @When("Click on saved card radio button")
    public void click_on_saved_card_radio_button() throws InterruptedException {
        Thread.sleep(18000L);
        this.driver.findElement(By.xpath("//*[@id=\"0\"]/label/span[1]/span[2]")).click();
    }

    @When("Enter CVV")
    public void enter_CVV() throws InterruptedException {
        Thread.sleep(1000L);
        this.driver.findElement(By.xpath("//input[@id='mat-input-7']")).sendKeys(new CharSequence[]{"999"});
    }

    @When("Click on Pay button")
    public void click_on_Pay_button() throws InterruptedException {
        this.driver.findElement(By.xpath("//button[@class='mat-focus-indicator btn-1 t-xs ml-4 mb-2 mat-button mat-button-base ng-star-inserted']")).click();
        Thread.sleep(30000L);
    }

    @Then("Validate ExamBooked message")
    public void validate_exam_booked_message() throws InterruptedException {
        String actual_status = this.driver.findElement(By.xpath("//p[normalize-space()='Exams booked Sucessfully']")).getText();
        String expected_status = "Exams booked Sucessfully";
        Assert.assertEquals(actual_status, expected_status);
        Thread.sleep(1000L);
    }

    @Then("Validate Status should be changed to Exam scheduled in Exam Schedule Board.")
    public void validate_Status_shoud_be_changed_to_Exam_scheduled_in_Exam_Schedule_Board() throws IOException, InterruptedException {
        String actual_status = this.driver.findElement(By.xpath("//span[@class='t-xs ml-2 active2 ml-2']")).getText();
        String expected_status = "Exam Scheduled";
        Assert.assertEquals(actual_status, expected_status);
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File src = (File)ts.getScreenshotAs(OutputType.FILE);
        File trg = new File(".\\screenshots\\ExamScheduled.png");
        FileUtils.copyFile(src, trg);
        Thread.sleep(1000L);
    }

    @Then("Click on Dashboard and take the screen shot of it")
    public void click_on_Dashboard_and_take_the_screen_shot_of_it() throws IOException, InterruptedException {
        this.driver.findElement(By.xpath("//span[@class='item-label ng-tns-c170-4']")).click();
        Thread.sleep(10000L);
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File src = (File)ts.getScreenshotAs(OutputType.FILE);
        File trg = new File(".\\screenshots\\Exam_Dashboard.png");
        FileUtils.copyFile(src, trg);
    }
}

