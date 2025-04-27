package com.tests.wishlist;

import com.base.BaseTest;
import com.framework.base.Product;
import com.framework.exceptions.PageLoadException;
import com.framework.factory.Constant;
import com.framework.pages.Account;
import com.framework.pages.HomePage;
import com.framework.reporting.TestLog;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.BLOCKER;

public class RemoveFromWishListTest extends BaseTest {


    @Test
    @Severity(BLOCKER)
    @Description("Remove From WishList By Index")
    public void removeProductFromWishListByIndex() throws PageLoadException {

        HomePage homePage = launchHomePage();
        homePage = homePage.navigateToLoginPage().loginAsExistingUser(Constant.EMAIL_ADDRESS, Constant.PWD).navigateToHomePage();
        Product product = Product.builder().productName("MacBook").
                productId("1").
                productPrice(602.00).
                build();
        homePage.addToWishListByIndex(Integer.parseInt(product.getProductId()));
        String wishListItemDetails = homePage.returnWishListItem();
        int noOfProductsInWishList = Integer.parseInt(wishListItemDetails.substring(wishListItemDetails.indexOf('(') + 1, wishListItemDetails.indexOf(')')));
        Assert.assertEquals(noOfProductsInWishList, 1, "Number of products in Cart is wrong");
        TestLog.stepInfo("No of Products in WishList is " + noOfProductsInWishList);

        Account.WishList wishlistPage = homePage.navigateToWishListPage();
        assertPageTitle(wishlistPage.getTitle(driver), Constant.WISHLIST_PAGE_TITLE, "Wishlist Page");
        TestLog.stepInfo("User is navigated to Wishlist Page");
        String productName = wishlistPage.getProductName(1);
        TestLog.stepInfo("Product Name is " + productName);
        TestLog.stepInfo("Product Model is " + wishlistPage.getProductModel(1));
        TestLog.stepInfo("Product Unit Price is " + wishlistPage.getProductUnitPrice(1));
        TestLog.stepInfo("No of Products in WishList is " + wishlistPage.getWishList().size());
        TestLog.stepInfo("Product is Available " + wishlistPage.isAvailable(1));
        wishlistPage.removeFromWishList(1);
        Assert.assertTrue(wishlistPage.verifyEmptyWishList(), "Wishlist is not empty");
        TestLog.stepInfo("Wish list is empty.....");

    }

    @Test
    @Severity(BLOCKER)
    @Description("Add Multiple Products and Remove Single Product From WishList By Index")
    public void removeSingleProductFromWishListByIndex() throws PageLoadException {

        HomePage homePage = launchHomePage();
        homePage = homePage.navigateToLoginPage().loginAsExistingUser(Constant.EMAIL_ADDRESS, Constant.PWD).navigateToHomePage();
        homePage.addToWishListByIndex(1);
        homePage.addToWishListByIndex(2);
        Account.WishList wishlistPage = homePage.navigateToWishListPage();
        assertPageTitle(wishlistPage.getTitle(driver), Constant.WISHLIST_PAGE_TITLE, "Wishlist Page");
        TestLog.stepInfo("User is navigated to Wishlist Page");
        TestLog.stepInfo("No of Products in WishList is " + wishlistPage.getWishList().size());
        wishlistPage.removeFromWishList(1);
        TestLog.stepInfo("No of Products in WishList is " + wishlistPage.getWishList().size());
        TestLog.stepInfo("Wish list is not empty.....");

    }
    @Test
    @Severity(BLOCKER)
    @Description("Remove All Product From WishList By Index")
    public void removeAllProductsFromWishListByIndex() throws PageLoadException {

        HomePage homePage = launchHomePage();
        homePage = homePage.navigateToLoginPage().loginAsExistingUser(Constant.EMAIL_ADDRESS, Constant.PWD).navigateToHomePage();
        homePage.addToWishListByIndex(1);
        homePage.addToWishListByIndex(2);
        Account.WishList wishlistPage = homePage.navigateToWishListPage();
        assertPageTitle(wishlistPage.getTitle(driver), Constant.WISHLIST_PAGE_TITLE, "Wishlist Page");
        TestLog.stepInfo("User is navigated to Wishlist Page");
        TestLog.stepInfo("No of Products in WishList is " + wishlistPage.getWishList().size());
        TestLog.stepInfo("Product At Index 1 is Available" + wishlistPage.isAvailable(1));
        wishlistPage.removeFromWishList(1);
        TestLog.stepInfo("No of Products in WishList is " + wishlistPage.getWishList().size());
        wishlistPage.removeFromWishList(1);
        Assert.assertTrue(wishlistPage.verifyEmptyWishList(), "Wishlist is not empty");
        TestLog.stepInfo("Wish list is empty.....");


    }


}
