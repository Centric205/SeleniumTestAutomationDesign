package org.glow.automation.pages;

import org.glow.automation.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage is the parent class for all Page Objects classes.
 *
 * Responsibilities:
 * - Provide access to WebDriver and WebDriverWait
 * - Encapsulate common user actions (click, type, getText)
 * - Enforce explicit waits for stability and reliability
 *
 * This class is abstract because it should not be instantiated directly
 */
public abstract class BasePage {

    // WebDriver instance for the current thread (via ThreadLock)
    protected WebDriver driver;

    // Explicit wait instance (no implicit waits used)
    protected WebDriverWait wait;

    /**
     * Constructor initialize WebDriver and WebDriverWait
     * WebDriver is retrieved from DriveManager to ensure thread safety.
     */
    protected BasePage() {
        this.driver = DriverManager.getDriver();

        // Explicit wait timeout (can be externalized via config)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * Waits for an element to be visible and returns it.
     *
     * @param locator By locator of the element
     * @return WebElement once it is visible
     */
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for an element to be clickable and clicks it.
     *
     * @param locator By locator of the element
     */
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for an element to be visible, clears existing text, and types new text.
     *
     * @param locator By locator of the element
     * @param text    Text to be entered
     */
    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Waits for an element to be visible and returns its text.
     *
     * @param locator By locator of the element
     * @return Visible text of the element
     */
    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    /**
     * Navigates to a given URL.
     * Navigation is considered a common page responsibility,
     * not a step-definition responsibility
     *
     * @param url application URL to navigate to
     */
    protected void navigateTo(String url) {
        driver.get(url);
    }
}
