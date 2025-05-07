package com.tests.cart;

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

import java.util.List;

import static io.qameta.allure.SeverityLevel.BLOCKER;

public class AddToCartTest extends BaseTest {

    @Test(groups = {"smoke"})
    @Severity(BLOCKER)
    @Description("Add The Product By Index")
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

    @Test(groups={"smoke"})
    @Severity(BLOCKER)
    @Description("Add The Product By Name")
    public void addProductToCartByName() throws PageLoadException {
        setUpDriver();
        HomePage homePage = goToHomePage();
        //Using Builder Pattern to Pass Product Details
        Product product = Product.builder().productName("MacBook").
                productId("1").
                productPrice(602.00).
                build();
        homePage.addToCartByProductName(product.getProductName());
        String cartItemsDetails = homePage.returnCartItem();
        int noOfProductsInCart = Integer.parseInt(cartItemsDetails.split(" ")[0]);
        Assert.assertEquals(noOfProductsInCart, 1, "Number of products in Cart is wrong");
        TestLog.stepInfo("No of Products in Cart is " + noOfProductsInCart);
        String priceOfItemsInCart = homePage.returnCartItem().split("-")[1].replace(Constant.CURRENCY_DOLLAR,"");
        Assert.assertEquals(Double.parseDouble(priceOfItemsInCart), product.getProductPrice(), "Total price of items in Cart is wrong");
        TestLog.stepInfo("Price Of Products in Cart is " + priceOfItemsInCart);


    }

    @Test
    @Severity(BLOCKER)
    @Description("Add Multiple Products")
    public void addMultipleProducts() {
        HomePage homePage;
        try {
            homePage = goToHomePage();
        } catch (PageLoadException e) {
            throw new RuntimeException(e);
        }
        //Using Builder Pattern to Pass Product Details


        List<Product> productList = List.of(
                Product.builder().productName("MacBook").
                        productId("1").
                        productPrice(602.00).
                        build(),
                Product.builder().productName("iPhone").
                        productId("2").
                        productPrice(123.20).
                        build()
        );
        //Any no of products can be added in similar way.

        for (Product product : productList) {
            homePage.addToCartByIndex(Integer.parseInt(product.getProductId()));
            TestLog.stepInfo("First Product added is :: " + product.getProductName());

        }
        // Validate cart item count
        String cartItemsDetails = homePage.returnCartItem();
        double actualItemCount = Double.parseDouble(cartItemsDetails.split(" ")[0]);
        Assert.assertEquals(actualItemCount, productList.size(), "Number of products in Cart is wrong");
        TestLog.stepInfo("Number of Products in Cart: " + actualItemCount);

        String actualPriceText = homePage.returnCartItem().split("-")[1].replace(Constant.CURRENCY_DOLLAR, "").trim();
        double actualPrice = Double.parseDouble(actualPriceText);

        double expectedTotal = productList.stream().mapToDouble(Product::getProductPrice).sum();
        Assert.assertEquals(actualPrice, expectedTotal, "Total price of items in Cart is wrong");
        TestLog.stepInfo("Expected total price: $" + expectedTotal);


    }


}
