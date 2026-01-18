package com.glow.automation.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glow.automation.config.ConfigReader;
import org.glow.automation.factory.WebDriverFactory;

/**
 * Cucumber Hook class
 *
 * Responsible for:
 * - Initialize WebDriver before each scenario
 * - Quit WebDriver after each scenario
 * - Log scenario lifecycle
 *
 * Hooks handle infrastructure, NOT test behaviour.
 */
public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    /**
     * Runs BEFORE each scenario.
     * Initializes WebDriver using WebDriverFactory
     */
    @Before
    public void beforeScenario(Scenario scenario) {
        logger.info("========================================");
        logger.info("STARTING SCENARIO: {}", scenario.getName());
        logger.info("SCENARIO TAGS: {}", scenario.getSourceTagNames());
        logger.info("========================================");

        // Create WebDriver using Factory pattern
        String browser = ConfigReader.getInstance().getProperty("browser");
        boolean headless = Boolean.parseBoolean(
                ConfigReader.getInstance().getProperty("headless")
        );

        // Initialize WebDriver (stored internally via DriverManager)
        WebDriverFactory.initializeDriver(browser, headless);
    }

    /**
     * Runs AFTER each scenario.
     * Quits WebDriver and cleans up ThreadLocal storage.
     */
    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.info("Scenario FAILED: {}", scenario.getName());
        }
        else {
            logger.info("Scenario PASSED: {}", scenario.getName());
        }

        // Quit WebDriver
        WebDriverFactory.quitDriver();

        logger.info("END SCENARIO: {}", scenario.getName());
        logger.info("========================================");
    }
}
