package org.example.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.P02_login;
import org.testng.Assert;

public class D03_resetStepDef {

    P02_login reset = new P02_login();
@When("user select forgot password")
    public void userSelectForgotPassword ()
{
  reset.forgetPassword().click();
}

    @And("^user enter the email \"(.*)\"$")
    public void userEnterTheEmail(String email) {
    reset.emailAddress().sendKeys(email);
    }

    @And("user click on recover button")
    public void userClickOnRecoverButton() {
     reset.recoverButton().click();
    }

    @Then("email sent message is displayed")
    public void emailSentMessageIsDisplayed() {

    String expectedMessage = "Email with instructions has been sent to you.";
    String actualMessage = reset.resetMessage().getText();

    Assert.assertTrue(actualMessage.contains(expectedMessage));

    }
}
