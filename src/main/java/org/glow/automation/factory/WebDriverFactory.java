package org.glow.automation.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.glow.automation.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * WebDriverFactory is responsible for:
 * - Creating browser-specific WebDriver instances
 * - Applying execution options (headless, etc.)
 * - Delegating driver storage to DriverManager
 */
public final class WebDriverFactory {

    // Private constructor to prevent object creation
    private WebDriverFactory() {}

    /**
     * Factory method to initialize WebDriver based on browser type
     */
    public static void initializeDriver(String browser, boolean headless) {

        WebDriver driver;

        switch (browser.toLowerCase()){

            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();

                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }

                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-infobars");
                chromeOptions.addArguments("--disable-notifications");

                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }

                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser
                );
        }

        // Store driver in ThreadLocal via DriverManager
        DriverManager.setDriver(driver);
    }

    /**
     * Quits the WebDriver and cleans up ThreadLocal
     */
    public static void quitDriver() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.quit();
            DriverManager.unloadDriver();
        }
    }
}
