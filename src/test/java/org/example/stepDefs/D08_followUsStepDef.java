package org.example.stepDefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.P03_homePage;
import org.testng.Assert;

public class D08_followUsStepDef {
    P03_homePage followUs = new P03_homePage();

    @When("user opens {string} link")
    public void openLink(String linkName) throws InterruptedException {
      followUs.getFollowUsLink(linkName).click();
      Thread.sleep(2000);
    }

    @Then("{string} is opened in new tab")
    public void isOpenedInNewTab(String followUsLink) {
        // switch between tabs
        followUs.switchLink();

        //get current URL
        String actualURL = followUs.getCurrentURL();
        String expectedURL = followUsLink;

        //Assert
        Assert.assertTrue(actualURL.contains(expectedURL));


    }
}
