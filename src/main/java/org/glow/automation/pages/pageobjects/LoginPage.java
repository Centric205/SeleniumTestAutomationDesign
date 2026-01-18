package org.glow.automation.pages.pageobjects;

import org.glow.automation.config.ConfigReader;
import org.glow.automation.pages.BasePage;
import org.openqa.selenium.By;

/**
 * LoginPage extends BasePage to resuse:
 * - WebDriver from DriverManager
 * - Explicit wait utilities
 * - Common actions (click, type, getText)
 */
public class LoginPage extends BasePage {

        /* =========================
       Locators
       ========================= */
    private final By usernameInput = By.id("f");
    private final By passwordInput = By.id("p");
    private final By loginButton = By.id("btnLogin");
    private final By errorMessage = By.id("error");

    /**
     * Constructor calls BasePage constructor to initialize driver & wait.
     */
    public LoginPage(){
        super();
    }

        /* =========================
       Page Actions using BasePage methods
       ========================= */
    public void navigateToLoginPage() {
        navigateTo(ConfigReader.getInstance().getProperty("base.url"));
    }

    public LoginPage enterUsername(String username) {
        type(usernameInput, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    public void clickLogin() {
        click(loginButton);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
