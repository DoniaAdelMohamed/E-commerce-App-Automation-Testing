package org.example.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.P02_login;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.Color;

public class D02_loginStepDef {

    P02_login login = new P02_login();

    @Given("user go to login page")
    public void userGoToLoginPage ()
    {
        login.loginButton().click();
    }
    @When("^user login with \"(.*)\" \"(.*)\" and \"(.*)\"$")
    public void userLoginWithAnd(String validation,String email, String password) {
        login.email().sendKeys(email);
        login.password().sendKeys(password);
    }
    @And("user press on login button")
    public void userPressOnLoginButton() throws InterruptedException {
        login.pressLogin().click();
        Thread.sleep(3000);
    }

    @Then("user login to the system successfully")
    public void userLoginToTheSystemSuccessfully() {
        SoftAssert soft = new SoftAssert();

        String actualURL = "https://demo.nopcommerce.com/";
        String expectedURL = Hooks.driver.getCurrentUrl();

        //First Assertion
        soft.assertTrue(actualURL.contains(expectedURL));

        //Second Asserion
        soft.assertTrue(login.myAccount().isDisplayed());

        soft.assertAll();



    }

    @Then("user could not login to the system")
    public void userCouldNotLoginToTheSystem() {
        SoftAssert soft = new SoftAssert();

        //First Assertion
        String actualMessage = login.errorMessage().getText();
        String expectedMessage = "Login was unsuccessful.";
        soft.assertTrue(actualMessage.contains(expectedMessage));

        //Second Assertion
        String actualColor = Color.fromString(login.errorMessage().getCssValue("color")).asHex();
        String expectedColor = "#e4434b";
        soft.assertTrue(actualColor.contains(expectedColor));

        soft.assertAll();
    }
}
