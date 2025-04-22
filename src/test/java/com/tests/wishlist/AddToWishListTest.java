package com.tests.wishlist;

import com.base.BaseTest;
import com.framework.base.Product;
import com.framework.exceptions.PageLoadException;
import com.framework.pages.HomePage;
import com.framework.reporting.TestLog;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.BLOCKER;

public class AddToWishListTest extends BaseTest {

    @Test
    @Severity(BLOCKER)
    @Description("Add The Product To WishList By Index")
    public void addProductToWishListByIndex() throws PageLoadException {
        HomePage homePage = goToHomePage();
        //Using Builder Pattern to Pass Product Details
        Product product = Product.builder().productName("MacBook").
                productId("1").
                productPrice(602.00).
                build();
        homePage.addToWishListByIndex(Integer.parseInt(product.getProductId()));
        String wishListItemDetails = homePage.returnWishListItem();
        int noOfProductsInWishList = Integer.parseInt(wishListItemDetails.substring(wishListItemDetails.indexOf('(') + 1, wishListItemDetails.indexOf(')')));
        Assert.assertEquals(noOfProductsInWishList, 1, "Number of products in Cart is wrong");
        TestLog.stepInfo("No of Products in WishList is " + noOfProductsInWishList);



    }

    @Test
    @Severity(BLOCKER)
    @Description("Add The Product To WishList By Name")
    public void addProductToWishListByName() throws PageLoadException {
        HomePage homePage = goToHomePage();
        //Using Builder Pattern to Pass Product Details
        Product product = Product.builder().productName("iPhone").
                productId("2").
                productPrice(602.00).
                build();
        homePage.addToWishListByProductName(product.getProductName());
        String wishListItemDetails = homePage.returnWishListItem();
        int noOfProductsInWishList = Integer.parseInt(wishListItemDetails.substring(wishListItemDetails.indexOf('(') + 1, wishListItemDetails.indexOf(')')));
        Assert.assertEquals(noOfProductsInWishList, 1, "Number of products in Cart is wrong");
        TestLog.stepInfo("No of Products in the WishList is " + noOfProductsInWishList);

    }

    @Test
    @Severity(BLOCKER)
    @Description("Add The Multiple Products To WishList ")
    public void addMultipleProductToWishList() throws PageLoadException {
        HomePage homePage = goToHomePage();
        //Using Builder Pattern to Pass Product Details
        Product product = Product.builder().productName("iPhone").
                productId("2").
                productPrice(602.00).
                build();
        homePage.addToWishListByProductName(product.getProductName());
        homePage.addToWishListByIndex(1);
        String wishListItemDetails = homePage.returnWishListItem();
        int noOfProductsInWishList = Integer.parseInt(wishListItemDetails.substring(wishListItemDetails.indexOf('(') + 1, wishListItemDetails.indexOf(')')));
        Assert.assertEquals(noOfProductsInWishList, 2, "Number of products in Cart is wrong");
        TestLog.stepInfo("No of Products in the WishList is " + noOfProductsInWishList);

    }







}
