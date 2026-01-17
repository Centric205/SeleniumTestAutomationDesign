package org.glow.automation.base;

import org.glow.automation.config.ConfigReader;
import org.glow.automation.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * BaseTest is the parent class for all TestNG test classes.
 *
 * Responsibilities:
 * 1. Initialize WebDriver before each test method
 * 2. Clean up WebDriver after each test method
 * 3. Provide access to configuration values
 *
 * Using @BeforeMethod and @AfterMethod ensures:
 * - Each test runs in Isolation
 * - Tests are safe to run in parallel
 */
public abstract class BaseTest {

    /**
     * ConfigReader is used to read framework configuration
     * (browser, headless mode, environment, etc.)
     */
    protected ConfigReader configReader;

    /**
     * This method runs BEFORE every @Test method.
     * Initializes the WebDriver using WebDriverFactory.
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp(){

        // Load configuration (singleton instance)
        configReader = ConfigReader.getInstance();

        // Read browser configuration
        String browser = configReader.getProperty("browser");
        boolean headless = configReader.getBooleanProperty("headless");

        // Initialize WebDriver using Factory Pattern
        // WebDriver instance is stored in ThreadLocal for parallel execution
        WebDriverFactory.initializeDriver(browser, headless);
    }

    /**
     * This method runs AFTER every @Test method.
     * It quits the WebDriver and cleans up ThreadLocal storage.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        // Quit browser and remove driver from ThreadLocal
        WebDriverFactory.quitDriver();
    }
}
