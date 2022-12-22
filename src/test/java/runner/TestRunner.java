package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features"},
		glue = {"stepdefs"},
		plugin = {"pretty", "com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:target/ExecutionReport.html"}
		)
public class TestRunner {

}