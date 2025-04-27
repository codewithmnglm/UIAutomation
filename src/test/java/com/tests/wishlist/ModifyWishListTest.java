package com.tests.wishlist;

import com.base.BaseTest;
import com.framework.exceptions.PageLoadException;
import com.framework.factory.Constant;
import com.framework.pages.Account;
import com.framework.pages.HomePage;
import com.framework.pages.ShoppingCart;
import com.framework.reporting.TestLog;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.BLOCKER;

public class ModifyWishListTest extends BaseTest {


    @Test
    @Severity(BLOCKER)
    @Description("Move Product from WishList To Shopping Cart")
    public void moveProductFromWishlistToCart() throws PageLoadException {

        HomePage homePage = launchHomePage();
        homePage = homePage.navigateToLoginPage().loginAsExistingUser(Constant.EMAIL_ADDRESS, Constant.PWD).navigateToHomePage();
        homePage.addToWishListByIndex(1);
        Account.WishList wishlistPage = homePage.navigateToWishListPage();
        assertPageTitle(wishlistPage.getTitle(driver), Constant.WISHLIST_PAGE_TITLE, "Wishlist Page");
        TestLog.stepInfo("User is navigated to Wishlist Page");
        TestLog.stepInfo("No of Products in WishList is " + wishlistPage.getWishList().size());
        wishlistPage.addToShoppingCart(1);
        TestLog.stepInfo("No of Products in WishList is " + wishlistPage.getWishList().size());
        ShoppingCart shoppingCartBtn= wishlistPage.navigateToCartPage();
        assertPageTitle(driver.getTitle(), Constant.SHOPPING_CART_PAGE_TITLE, "Shopping Cart");
        Assert.assertEquals(shoppingCartBtn.getShoppingCartItems().size(), 1);
        TestLog.stepInfo("Size of the shopping cart is " + shoppingCartBtn.getShoppingCartItems().size());

    }

    @Test
    @Severity(BLOCKER)
    @Description("Move Product from WishList To Shopping Cart")
    public void moveMultipleProductFromWishlistToCart() throws PageLoadException {

        HomePage homePage = launchHomePage();
        homePage = homePage.navigateToLoginPage().loginAsExistingUser(Constant.EMAIL_ADDRESS, Constant.PWD).navigateToHomePage();
        homePage.addToWishListByIndex(1);
        homePage.addToWishListByIndex(2);
        Account.WishList wishlistPage = homePage.navigateToWishListPage();
        assertPageTitle(wishlistPage.getTitle(driver), Constant.WISHLIST_PAGE_TITLE, "Wishlist Page");
        TestLog.stepInfo("User is navigated to Wishlist Page");
        TestLog.stepInfo("No of Products in WishList is " + wishlistPage.getWishList().size());
        wishlistPage.addToShoppingCart(2);
        wishlistPage.addToShoppingCart(1);
        TestLog.stepInfo("No of Products in WishList is " + wishlistPage.getWishList().size());
        ShoppingCart shoppingCartBtn= wishlistPage.navigateToCartPage();
        assertPageTitle(driver.getTitle(), Constant.SHOPPING_CART_PAGE_TITLE, "Shopping Cart");
        Assert.assertEquals(shoppingCartBtn.getShoppingCartItems().size(), 2);
        TestLog.stepInfo("Size of the shopping cart is " + shoppingCartBtn.getShoppingCartItems().size());

    }



}
