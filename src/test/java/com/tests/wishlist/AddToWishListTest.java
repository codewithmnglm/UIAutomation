package com.tests.wishlist;

import com.base.BaseTest;
import com.framework.base.Product;
import com.framework.exceptions.PageLoadException;
import com.framework.factory.Constant;
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
    @Description("Add The Product To WishList")
    public void addProductToCartByIndex() throws PageLoadException {
        HomePage homePage = goToHomePage();
        //Using Builder Pattern to Pass Product Details
        Product product = Product.builder().productName("MacBook").
                productId("1").
                productPrice(602.00).
                build();
        homePage.addToCartByIndex(Integer.parseInt(product.getProductId()));
        String cartItemsDetails = homePage.returnCartItem();
        int noOfProductsInCart = Integer.parseInt(cartItemsDetails.split(" ")[0]);
        Assert.assertEquals(noOfProductsInCart, 1, "Number of products in Cart is wrong");
        TestLog.stepInfo("No of Products in Cart is " + noOfProductsInCart);
        String priceOfItemsInCart = homePage.returnCartItem().split("-")[1].replace(Constant.CURRENCY_DOLLAR,"");
        Assert.assertEquals(Double.parseDouble(priceOfItemsInCart), product.getProductPrice(), "Total price of items in Cart is wrong");
        TestLog.stepInfo("Price Of Products in Cart is " + priceOfItemsInCart);


    }




}
