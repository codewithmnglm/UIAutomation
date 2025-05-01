package com.tests.search;

import com.base.BaseTest;
import com.framework.base.CommonMethods;
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

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.SeverityLevel.BLOCKER;

public class FilterSearchTest extends BaseTest {


    @Test
    @Severity(BLOCKER)
    @Description("Search Product And Sort By Price In Ascending Order")
    private void searchProductInAscendingOrder() throws PageLoadException {

        HomePage homePage = launchHomePage();
        Login loginPage = homePage.navigateToLoginPage();
        assertPageTitle(loginPage.getTitle(driver), Constant.LOGIN_PAGE_TITLE, "Login Page");

        Account accountPage = loginPage.loginAsExistingUser(Constant.EMAIL_ADDRESS, Constant.PWD);
        homePage = accountPage.navigateToHomePage();

        Search searchPage = homePage.searchProduct("Macbook");
        searchPage.sortProductByPriceLowToHigh();

        Assert.assertEquals(searchPage.getNoOfProduct().size(), 3, "Product Size Mismatch");

        List<Double> listOfSearchedProducts = new java.util.ArrayList<>(List.of());

        for (int i = 1; i <= searchPage.getNoOfProduct().size(); i++) {

            String price = priceExtractor(searchPage.getProductPrice(i), "\\$\\d{1,3}(,\\d{3})*\\.\\d{2}");
            double finalPrice = Double.parseDouble(CommonMethods.removeCurrencySymbols(price));
            listOfSearchedProducts.add(finalPrice);
        }
        Assert.assertTrue(CommonMethods.isSorted(listOfSearchedProducts, true), "Price Of Products is not in ascending order");
        TestLog.stepInfo("Product List Is Sorted in Ascending Order Based on Price.");

    }

    @Test
    @Severity(BLOCKER)
    @Description("Search Product And Sort By Price In Descending Order")
    private void searchProductInDescendingOrder() throws PageLoadException {

        HomePage homePage = launchHomePage();
        Login loginPage = homePage.navigateToLoginPage();
        assertPageTitle(loginPage.getTitle(driver), Constant.LOGIN_PAGE_TITLE, "Login Page");

        Account accountPage = loginPage.loginAsExistingUser(Constant.EMAIL_ADDRESS, Constant.PWD);
        homePage = accountPage.navigateToHomePage();

        Search searchPage = homePage.searchProduct("Macbook");
        searchPage.getListView();
        searchPage.sortProductByPriceHighToLow();

        Assert.assertEquals(searchPage.getNoOfProduct().size(), 3, "Product Size Mismatch");

        List<Double> listOfSearchedProducts = new ArrayList<>(List.of());

        for (int i = 1; i <= searchPage.getNoOfProduct().size(); i++) {

            String price = priceExtractor(searchPage.getProductPrice(i), "\\$\\d{1,3}(,\\d{3})*\\.\\d{2}");
            double finalPrice = Double.parseDouble(CommonMethods.removeCurrencySymbols(price));
            listOfSearchedProducts.add(finalPrice);
        }
        Assert.assertTrue(CommonMethods.isSorted(listOfSearchedProducts, false), "Price Of Products is not in descending order");
        TestLog.stepInfo("Product List Is Sorted in Descending Order Based on Price.");


    }

}
