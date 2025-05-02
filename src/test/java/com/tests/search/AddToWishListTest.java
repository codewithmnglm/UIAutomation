package com.tests.search;

import com.base.BaseTest;
import com.framework.exceptions.PageLoadException;
import com.framework.factory.Constant;
import com.framework.pages.Account;
import com.framework.pages.HomePage;
import com.framework.pages.Login;
import com.framework.pages.Search;
import com.framework.reporting.TestLog;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.BLOCKER;

public class AddToWishListTest extends BaseTest {



    @Test
    @Severity(BLOCKER)
    @Description("Search Product And Add To WishList")
    private void addToWishListFromSearchPage() throws PageLoadException {

        HomePage homePage = launchHomePage();
        Login loginPage = homePage.navigateToLoginPage();
        assertPageTitle(loginPage.getTitle(driver), Constant.LOGIN_PAGE_TITLE, "Login Page");

        Account accountPage = loginPage.loginAsExistingUser(Constant.EMAIL_ADDRESS, Constant.PWD);
        homePage = accountPage.navigateToHomePage();

        Search searchPage = homePage.searchProduct("Macbook");

        Assert.assertEquals(searchPage.getNoOfProduct().size(), 3, "Product Size Mismatch");

        searchPage.addToWishList(1);

        Account.WishList wishListPage=searchPage.navigateToWishListPage();
        assertPageTitle(wishListPage.getTitle(driver), Constant.WISHLIST_PAGE_TITLE, "Wishlist Page");
        TestLog.stepInfo("User is navigated to Wishlist Page");
        TestLog.stepInfo("No of Products in WishList is " + wishListPage.getWishList().size());
        Assert.assertEquals(wishListPage.getWishList().size(),1);


    }
}
