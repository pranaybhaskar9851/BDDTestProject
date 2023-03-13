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
//        format = {
//                "pretty",
//                "html:target/cucumber-reports/cucumber-pretty",
//                "json:target/cucumber-reports/CucumberTestReport.json",
//                "junit:target/cucumber-reports/CucumberTestReport.xml"
//        }
        		plugin = {"html:target/cucumber-html-report.html", "json:target/cucumber-json-report.json" 
        		//plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"
        		//plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        		}
)
public class TestRunner {
	
}