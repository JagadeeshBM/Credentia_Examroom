package testRunner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features ="C:\\Users\\jagadeeshm\\IdeaProjects\\Credentia_Examroom\\Features\\Candidate.feature",
        features ="C:\\Users\\jagadeeshm\\IdeaProjects\\Credentia_Examroom\\Features\\ER 2.feature",
        //features ="C:\\Users\\jagadeeshm\\IdeaProjects\\Credentia_Examroom\\Features\\Candidate.feature",
        glue = "stepDefinitionsMSER2",
        dryRun =false,
        monochrome =false,
       // plugin = {"pretty","html:test-output"},
        //plugin = {"pretty","html:target/cucumber-report/cucumber.json"},
        plugin={"html:target/cucumber-html-report","json:target/cucumber-report/cucumber.json","junit:target/cucumber-report/cucumber.xml"},
       //tags="@Application or @AppApproveTP or @AppApproveOP or @RegisterForExam"
       // tags ="@Application"
       // tags="@ClearDB"
      tags="@Application or @AppApproveOP or @RegisterForExam" //MSER2
       // tags="@ClearDB or @Application or @AppApprove or @RegisterForExam"
       // tags="@AppApprove or @RegisterForExam"
        //tags="@ClearDB"
        //tags="@RegisterForExam"
        //tags={"@AppApprove"}
        //Test
)
public class TestRunner
{
}



