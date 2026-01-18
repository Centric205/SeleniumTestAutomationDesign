package com.glow.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * Cucumber TestNG Runner
 *
 * Responsibilities:
 * - Discovers and executes all feature files
 * - Uses TestNG as the execution engine
 * - Enables parallel scenario execution
 * - Integrates automatically with Hooks and WebDriverFactory
 * - Generates default Cucumber reports
 */
@CucumberOptions(
        features = "src/test/resources/features",       // All feature files
        glue = {
                "com.glow.automation.stepdefinitions",  // Step Definitions
                "com.glow.automation.hooks"             // Hooks (Before/After)
        },
        plugin = {
                "pretty",                               // Console output
                "html:target/cucumber-reports.html",    // HTML report
                "json:target/cucumber-reports.json"     // JSON report
        },
        monochrome = true                               // Cleaner console logs
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

    /**
     * Enables parallel execution of scenarios using TestNG.
     *
     * IMPORTANT:
     * - ThreadLocal WebDriver ensures thread safety
     * - Each scenario gets its own browser instance
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
