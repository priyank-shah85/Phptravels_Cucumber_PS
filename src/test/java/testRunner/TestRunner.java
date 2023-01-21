package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
		(
			// features= {".//src/test/resources/Features/"},
			// features = { ".//src/test/resources/Features/HomePage.feature" },
			// features = { ".//src/test/resources/Features/CustomerDashboard.feature" },
			features = { ".//src/test/resources/Features/Hotels.feature" },
			// features="@target/rerun.txt", // Runs only failures
			glue = "stepDefinitions",
			plugin = 
			{
				"pretty",
				"html:reports/myreport.html",
				"json:reports/myreport.json",
				"rerun:target/rerun.txt", // Mandatory to capture failures
				"html:target/cucumber-html-report",
				"json:target/cucumber-reports/cucumber.json",
				"junit:target/cucumber-reports/cucumber.xml",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
			},
					
			dryRun = false,
			monochrome = true,
			   tags = "@temp" // Scenarios tagged with @sanity
			// tags = "@sanity and @regression" //Scenarios tagged with both @sanity and @regression
			// tags = "@sanity or @regression" //Scenarios tagged with either @sanity or @regression
			// tags = "@sanity and not @regression", //Scenarios tagged with @sanity but not tagged with @regression
)

public class TestRunner {

}