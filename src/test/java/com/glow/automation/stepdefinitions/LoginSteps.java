package com.glow.automation.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.glow.automation.pages.pageobjects.LoginPage;
import org.glow.automation.utils.TestContext;
import org.testng.Assert;

/**
 * Step definitions for Login feature.
 *
 * NOTE:
 * - No Selenium code here
 * - Only orchestration & assertions
 * - Browser setup handled via Hooks
 */
public class LoginSteps {

    private final LoginPage loginPage;

    // Cucumber injects the same TestContext instance per scenario
    public LoginSteps(TestContext testContext) {
        this.loginPage = testContext.getLoginPage();
    }

    @Given("the user is on the login page")
    public void userIsOnLoginPage(){
        loginPage.navigateToLoginPage();
    }

    @When("the user enters username {string}")
    public void userEntersUsername(String username) {
        loginPage.enterUsername(username);
    }

    @And("the user enters password {string}")
    public void userEntersPassword(String password) {
        loginPage.enterPassword(password);
    }

    @And("the user clicks the login button")
    public void userClicksLoginButton() {
        loginPage.clickLogin();
    }

    @Then("the user should see {string}")
    public void userShouldSee(String expectedMessage) {
        loginPage.getErrorMessage();
        Assert.assertEquals(
                expectedMessage,
                loginPage.getErrorMessage(),
                "Incorrect login error message");
    }
}
