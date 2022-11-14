package org.example.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.P03_homePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class D09_WishlistStepDef {

    P03_homePage wishList = new P03_homePage();

    @When("user click on wishlist button")
    public void addProductToWishlist()
    {
       wishList.clickOnWishButton().get(2).click();
    }


    @Then("success message is displayed with background color is green")
    public void successMessageIsDisplayedWithBackgroundColorIsGreen() {
        SoftAssert soft = new SoftAssert();

        String actualMessage= wishList.wishlistSuccessMessage().getText();
        String expectedMessage = "The product has been added to your wishlist";

        //first assertion
        soft.assertTrue(actualMessage.contains(expectedMessage));

        //second assertion
        String actualColor = wishList.wishListSuccessMessageColor().getCssValue("background-color");
        String expectedColor = "rgba(75, 176, 122, 1)";

        soft.assertTrue(actualColor.contains(expectedColor));
        
        soft.assertAll();
    }

    @And("click on Wishlist Tab on the top of the page")
    public void clickOnWishlistTabOnTheTopOfThePage() {
        WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(20));
        Boolean element =
                wait.until(ExpectedConditions.invisibilityOf(wishList.returnWishlistSuccessMessage()));

        if(element==true)
        {
            wishList.wishlistButton().click();
            Assert.assertNotEquals(wishList.wishlistQuantity().getAttribute("value") , 0);
        }
    }

    @Then("product is added to wish list with quantity one")
    public void productIsAddedToWishListWithQuantityOne() {
    }
}
