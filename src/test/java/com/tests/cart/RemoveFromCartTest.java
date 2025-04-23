package com.tests.cart;

import com.base.BaseTest;
import com.framework.base.Product;
import com.framework.exceptions.PageLoadException;
import com.framework.factory.Constant;
import com.framework.pages.HomePage;
import com.framework.pages.ShoppingCart;
import com.framework.reporting.TestLog;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static io.qameta.allure.SeverityLevel.BLOCKER;

public class RemoveFromCartTest extends BaseTest {


    @Test
    @Severity(BLOCKER)
    @Description("Add The Product By Index")
    public void removeProductFromCartByIndex() throws PageLoadException {
        HomePage homePage = goToHomePage();
        //Using Builder Pattern to Pass Product Details
        Product product = Product.builder().productName("MacBook").
                productId("1").
                productPrice(602.00).
                build();
        homePage.addToCartByIndex(Integer.parseInt(product.getProductId()));

        homePage.addToCartByIndex(2);

        ShoppingCart shoppingCartPage = homePage.navigateToShoppingCartPage();

        assertPageTitle(shoppingCartPage.getTitle(driver), Constant.SHOPPING_CART_PAGE_TITLE, "Shopping Cart Page");


        List<WebElement> reversedCart = shoppingCartPage.getShoppingCartItems();
        Collections.reverse(reversedCart);

        IntStream.range(0, reversedCart.size())
                .forEach(i -> {
                    int index = reversedCart.size() - i;

                    TestLog.stepInfo("Product Name is :: " + shoppingCartPage.getProductName(index));
                    TestLog.stepInfo("Product Model is :: " + shoppingCartPage.getProductModel(index));
                    TestLog.stepInfo("Product Price is :: " + shoppingCartPage.getProductPrice(index));
                    TestLog.stepInfo("Total Product Price is :: " + shoppingCartPage.getTotalProductPrice(index));
                    TestLog.stepInfo("Quantity of Product is :: " + shoppingCartPage.getQuantityOfProduct(index));

                    shoppingCartPage.removeFromCartByIndex(index);
                });

        Assert.assertTrue(shoppingCartPage.emptyShoppingCartMessage(), "Shopping Cart Empty Msg Not Displayed");


    }


}
