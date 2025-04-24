package com.tests.cart;

import com.base.BaseTest;
import com.framework.base.CommonMethods;
import com.framework.base.Product;
import com.framework.exceptions.PageLoadException;
import com.framework.factory.Constant;
import com.framework.pages.HomePage;
import com.framework.pages.ShoppingCart;
import com.framework.reporting.TestLog;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.BLOCKER;

public class ModifyCartTest extends BaseTest {

    @Test
    @Severity(BLOCKER)
    @Description("Modify Product Quantity In Cart")
    public void modifyCartProductByIndex() throws PageLoadException {
        HomePage homePage = goToHomePage();
        //Using Builder Pattern to Pass Product Details
        Product product = Product.builder().productName("MacBook").
                productId("1").
                productPrice(602.00).
                build();
        homePage.addToCartByIndex(Integer.parseInt(product.getProductId()));
        ShoppingCart shoppingCartPage = homePage.navigateToShoppingCartPage();
        assertPageTitle(shoppingCartPage.getTitle(driver), Constant.SHOPPING_CART_PAGE_TITLE, "Shopping Cart Page");
        shoppingCartPage.getQuantityOfProduct(1);
        TestLog.stepInfo("Quantity of Product At Index 1 is :: " + shoppingCartPage.getQuantityOfProduct(1));
        String updatedProductCount = "3";
        shoppingCartPage.updateQuantityOfProduct(1, updatedProductCount);
        shoppingCartPage.refreshProductQuantity(1);
        TestLog.stepInfo("Quantity of Product At Index 1 is :: " + shoppingCartPage.getQuantityOfProduct(1));
        Assert.assertEquals(shoppingCartPage.getQuantityOfProduct(1), Integer.parseInt(updatedProductCount), "Quantity of Product Mismatch");
        Assert.assertTrue(shoppingCartPage.modifyShoppingCartMessage(), "Cart Modification Failed");


    }

    @Test
    @Severity(BLOCKER)
    @Description("Calculate && Validate Product Price In Cart")
    public void validatePriceInTheCart() throws PageLoadException {
        HomePage homePage = goToHomePage();
        //Using Builder Pattern to Pass Product Details
        Product product = Product.builder().productName("MacBook").
                productId("1").
                productPrice(602.00).
                build();
        homePage.addToCartByIndex(Integer.parseInt(product.getProductId()));
        ShoppingCart shoppingCartPage = homePage.navigateToShoppingCartPage();
        assertPageTitle(shoppingCartPage.getTitle(driver), Constant.SHOPPING_CART_PAGE_TITLE, "Shopping Cart Page");
        shoppingCartPage.getQuantityOfProduct(1);
        TestLog.stepInfo("✅Quantity of Product At Index 1 is :: " + shoppingCartPage.getQuantityOfProduct(1));
        TestLog.stepInfo("✅Price Of the product At Index 1 is :: " + shoppingCartPage.getProductPrice(1));
        TestLog.stepInfo("✅Total price of Product " + shoppingCartPage.getTotalProductPrice(1));
        int quantityOfProduct = shoppingCartPage.getQuantityOfProduct(1);
        double productPrice = Double.parseDouble(CommonMethods.removeCurrencySymbols(shoppingCartPage.getTotalProductPrice(1)));
        Assert.assertEquals(productPrice * quantityOfProduct, Double.parseDouble(CommonMethods.removeCurrencySymbols(shoppingCartPage.getTotalProductPrice(1))));
        Assert.assertEquals(productPrice * quantityOfProduct, Double.parseDouble(CommonMethods.removeCurrencySymbols(shoppingCartPage.finalPriceOfCart())));
        TestLog.stepInfo("✅Final Cart Price is Successfully calculated and is " + shoppingCartPage.finalPriceOfCart());

    }

    @Test
    @Severity(BLOCKER)
    @Description("Calculate && Validate Product Price In Cart")
    public void modifyCartAndValidatePrices() throws PageLoadException {
        HomePage homePage = goToHomePage();
        //Using Builder Pattern to Pass Product Details
        Product product = Product.builder().productName("MacBook").
                productId("1").
                productPrice(602.00).
                build();
        homePage.addToCartByIndex(Integer.parseInt(product.getProductId()));
        ShoppingCart shoppingCartPage = homePage.navigateToShoppingCartPage();
        assertPageTitle(shoppingCartPage.getTitle(driver), Constant.SHOPPING_CART_PAGE_TITLE, "Shopping Cart Page");
        shoppingCartPage.getQuantityOfProduct(1);
        TestLog.stepInfo("✅Quantity of Product At Index 1 is :: " + shoppingCartPage.getQuantityOfProduct(1));
        TestLog.stepInfo("✅Price Of the product At Index 1 is :: " + shoppingCartPage.getProductPrice(1));
        TestLog.stepInfo("✅Total price of Product " + shoppingCartPage.getTotalProductPrice(1));
        int quantityOfProduct = shoppingCartPage.getQuantityOfProduct(1);
        double productPrice = Double.parseDouble(shoppingCartPage.getTotalProductPrice(1).replace("$", ""));
        double finalPrice = Double.parseDouble(CommonMethods.removeCurrencySymbols(shoppingCartPage.finalPriceOfCart()));
        Assert.assertEquals(productPrice * quantityOfProduct, finalPrice, "Product Price Mismatch");
        TestLog.stepInfo("✅Final Cart Price is Successfully calculated and is " + shoppingCartPage.finalPriceOfCart());
        String updatedProductCount = "3";
        shoppingCartPage.updateQuantityOfProduct(1, updatedProductCount);
        shoppingCartPage.refreshProductQuantity(1);
        shoppingCartPage.getQuantityOfProduct(1);
        TestLog.stepInfo("✅Quantity of Product At Index 1 is :: " + shoppingCartPage.getQuantityOfProduct(1));
        TestLog.stepInfo("✅Price Of the product At Index 1 is :: " + shoppingCartPage.getProductPrice(1));
        TestLog.stepInfo("✅Total price of Product " + shoppingCartPage.getTotalProductPrice(1));
        quantityOfProduct = shoppingCartPage.getQuantityOfProduct(1);
        productPrice = Double.parseDouble(CommonMethods.removeCurrencySymbols(shoppingCartPage.getProductPrice(1)));
        finalPrice = Double.parseDouble(CommonMethods.removeCurrencySymbols(shoppingCartPage.finalPriceOfCart()));
        Assert.assertEquals(productPrice * quantityOfProduct, finalPrice, "Product Price Mismatch");
        TestLog.stepInfo("✅Final Cart Price is Successfully calculated and is " + shoppingCartPage.finalPriceOfCart());

    }


}
