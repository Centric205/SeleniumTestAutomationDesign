package org.glow.automation.driver;


import org.openqa.selenium.WebDriver;

/**
 * DriverManger is responsible for:
 * - Holding WebDriver instances in a ThreadLocal
 * - Providing thread-safe access to WebDriver
 */
public final class DriverManager {

    // ThreadLocal ensures each test thread has its own WebDriver instance
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Private constructor to prevent instantiation
    private DriverManager() {}

    /**
     * Sets the WebDriver for the current thread
     */
    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    /**
     * Gets the WebDriver associated with the current Thread
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Removes the WebDriver from ThreadLocal to avoid memory leaks
     */
    public static void unloadDriver() {
        driver.remove();
    }
}
