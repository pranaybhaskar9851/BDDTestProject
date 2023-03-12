package Runner;
import io.cucumber.java.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.runner.RunWith;
import cucumber.api.*;
import io.cucumber.java.*;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "StepDefinitions",
//        format = {
//                "pretty",
//                "html:target/cucumber-reports/cucumber-pretty",
//                "json:target/cucumber-reports/CucumberTestReport.json",
//                "junit:target/cucumber-reports/CucumberTestReport.xml"
//        }
        		plugin = {"html:target/cucumber-html-report", "json:target/cucumber-json-report.json" }
)
public class TestRunner {
	
}