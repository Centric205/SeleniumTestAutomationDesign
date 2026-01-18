package org.glow.automation.utils;

import lombok.Getter;
import lombok.Setter;
import org.glow.automation.pages.pageobjects.LoginPage;
import org.openqa.selenium.WebDriver;

/**
 * TestContext is a shared object that holds test state
 * for a single scenario execution
 */
public class TestContext {

    /**
     * Using Lombok Getter and Setter annotation reduces boilerplate
     * code of manually typing getters and setters. Instead, it generates them
     * automatically for us.
     */
    @Setter
    @Getter
    private WebDriver driver;

    private LoginPage loginPage;

    // Lazy initialization of LoginPage
    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

}
