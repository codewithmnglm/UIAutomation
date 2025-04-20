package com.tests.cart;

import com.base.BaseTest;
import com.beust.ah.A;
import com.framework.base.CommonMethods;
import com.framework.exceptions.PageLoadException;
import com.framework.factory.Constant;
import com.framework.pages.Account;
import com.framework.pages.HomePage;
import com.framework.reporting.TestLog;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.BLOCKER;

public class AddToCart extends BaseTest {

    @Test
    @Severity(BLOCKER)
    @Description("Verify UI Page is Running")
    public void addProductToCart() throws PageLoadException {
        HomePage homePage = launchHomePage();
        assertPageTitle(homePage.getTitle(driver), Constant.HOME_PAGE_TITLE, "Home Page");
        TestLog.testPass("Verify UI Page is Running Test Case Passed");
        int productIndex=returnProductIndex("MacBook");
        homePage.addToCartByIndex(productIndex);

    }

}
