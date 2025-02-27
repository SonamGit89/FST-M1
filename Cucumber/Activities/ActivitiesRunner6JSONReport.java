package TestRunner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Features",
        glue = {"StepDefinitions"},
        tags = "@SmokeTest",
        plugin = {"json: json-report.json"},
        monochrome = true

)

public class ActivitiesRunner6JSONReport {
    //empty
}
