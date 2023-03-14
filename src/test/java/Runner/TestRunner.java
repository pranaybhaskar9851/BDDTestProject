package Runner;
import io.cucumber.java.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import io.cucumber.java.*;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
 

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Place an Order_AutomatedFlow.feature",
        glue = "StepDefinitions",
        tags = "@Automated",
        dryRun = false,

         plugin = {"html:target/cucumber-html-report.html", "json:target/cucumber-json-report.json" 
        		
        		}
)
public class TestRunner {
	
}