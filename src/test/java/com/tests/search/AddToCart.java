package com.tests.search;

import com.base.BaseTest;
import com.framework.exceptions.PageLoadException;
import com.framework.factory.Constant;
import com.framework.pages.*;
import com.framework.reporting.TestLog;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.openqa.selenium.devtools.v85.backgroundservice.BackgroundService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.BLOCKER;

public class AddToCart extends BaseTest {

    @Test
    @Severity(BLOCKER)
    @Description("Search Product And Add To Cart")
    private void addToWishListFromSearchPage() throws PageLoadException {

        HomePage homePage = launchHomePage();
        Login loginPage = homePage.navigateToLoginPage();
        assertPageTitle(loginPage.getTitle(driver), Constant.LOGIN_PAGE_TITLE, "Login Page");

        Account accountPage = loginPage.loginAsExistingUser(Constant.EMAIL_ADDRESS, Constant.PWD);
        homePage = accountPage.navigateToHomePage();

        Search searchPage = homePage.searchProduct("Macbook");

        Assert.assertEquals(searchPage.getNoOfProduct().size(), 3, "Product Size Mismatch");

        searchPage.addToCart(1);

        ShoppingCart shoppingCartPage=searchPage.navigateToCartPage();
        shoppingCartPage.getQuantityOfProduct(1);
        TestLog.stepInfo("Quantity of Product At Index 1 is :: " + shoppingCartPage.getQuantityOfProduct(1));



    }
}
