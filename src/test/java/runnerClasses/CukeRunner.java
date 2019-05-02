package runnerClasses;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/html/ExtentReport.html" },
// plugin = {"pretty"},
//    plugin = {
//        "html:target/cucumberHtmlReport",
//        "json:target/cucumber-report.json"
//    }, // Plugin to generate HTML report and json report
		features = { "src/test/resources/Features" }, glue = { "gluecode" }, tags = { "@API_Test , @UI_Test" })

public class CukeRunner {
	@AfterClass
	public static void setup() {
		Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
		// Reporter.setSystemInfo("Test User", System.getProperty("user.name"));
		Reporter.setSystemInfo("User Name", "AJ");
		Reporter.setSystemInfo("Application Name", "Test App ");
		Reporter.setSystemInfo("Operating System Type", System.getProperty("os.name").toString());
		Reporter.setSystemInfo("Environment", "Production");
		Reporter.setTestRunnerOutput("Test Execution Cucumber Report");
	}
}