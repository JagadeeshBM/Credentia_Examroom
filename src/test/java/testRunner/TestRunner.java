package testRunner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features ="C:\\Users\\jagadeeshm\\IdeaProjects\\Credentia_Examroom\\Features\\Candidate.feature",
        glue = "stepDefinitions",
        dryRun =false,
        monochrome =false,
       // plugin = {"pretty","html:test-output"},
        plugin = {"pretty","html:target/cucumber-report/cucumber.json"},
        tags="@Application or @AppApprove or @RegisterForExam"
        //tags="@Application or @AppApprove"
        //tags="@RegisterForExam"
        //tags="@ClearDB or @Application or @AppApprove or @RegisterForExam"
        //tags={"@AppApprove"}
)
public class TestRunner
{
}



