package org.example.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.P03_homePage;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class D05_searchStepDef {
    P03_homePage search = new P03_homePage();

    @When("user click on search field")
    public void clickOnSearch ()
    {
         search.searchField().click();
    }

    @And("user search with {string}")
    public void userSearchWith(String product) {
        search.searchField().sendKeys(product);
    }

    @And("user click on search button")
    public void userClickOnSearchButton() {
        search.searchButton().click();
    }

    @Then("user Could find {string} relative results")
    public void userCouldFindRelativeResults(String expectedProduct) {

        SoftAssert soft = new SoftAssert();

        //First Assertion

         String expectedURL = " https://demo.nopcommerce.com/search?q="+expectedProduct;
         String actualURL = search.getCurrentURL();
         soft.assertTrue(actualURL.contains(expectedProduct));

        //Second Assertion
        String actualProduct = "";
        List<WebElement> actualproducts = search.relativeResults();

        for (int i = 0 ; i< actualproducts.size();i++)
        {
            actualProduct = actualproducts.get(i).getText().toLowerCase();
            soft.assertTrue(actualProduct.contains(expectedProduct));
        }

        // Assert all
        soft.assertAll();
    }

    @Then("user could find {string} inside product detail page")
    public void userCouldFindInsideProductDetailPage(String expectedSku) {


        SoftAssert soft = new SoftAssert();

        //First Assertion
        soft.assertEquals(search.getCuurentProduct().size(),1);

        //Second Assertion
        search.productClick().click();
        soft.assertTrue(search.getActualSKU().get(0).getText().contains(expectedSku));

        soft.assertAll();
    }
}
