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

public class SearchTest extends BaseTest {


    @Test
    @Severity(BLOCKER)
    @Description("Search Product")
    private void searchProduct() throws PageLoadException {

        HomePage homePage = launchHomePage();
        Login loginPage = homePage.navigateToLoginPage();
        assertPageTitle(loginPage.getTitle(driver), Constant.LOGIN_PAGE_TITLE, "Login Page");

        Account accountPage = loginPage.loginAsExistingUser(Constant.EMAIL_ADDRESS, Constant.PWD);
        homePage = accountPage.navigateToHomePage();

        Search searchPage = homePage.searchProduct("Macbook");

        Assert.assertEquals(searchPage.getNoOfProduct().size(),1,"Product Size Mismatch");

        TestLog.stepInfo("Total No Of Products In Search Appearances is :::" + searchPage.getNoOfProduct().size());

    }

    @Test
    @Severity(BLOCKER)
    @Description("Search Unavailable Product")
    private void searchUnavailableProduct() throws PageLoadException {

        HomePage homePage = launchHomePage();
        Login loginPage = homePage.navigateToLoginPage();
        assertPageTitle(loginPage.getTitle(driver), Constant.LOGIN_PAGE_TITLE, "Login Page");

        Account accountPage = loginPage.loginAsExistingUser(Constant.EMAIL_ADDRESS, Constant.PWD);
        homePage = accountPage.navigateToHomePage();

        Search searchPage = homePage.searchProduct("Suzuki");

        Assert.assertTrue(searchPage.isNoProductFound());

        Assert.assertEquals(searchPage.getNoOfProduct().size(),0,"Product Size Mismatch");

        TestLog.stepInfo("Total No Of Products In Search Appearances is :::" + searchPage.getNoOfProduct().size());

    }

    @Test
    @Severity(BLOCKER)
    @Description("Search Product in List View ")
    private void searchProductInListView() throws PageLoadException {

        HomePage homePage = launchHomePage();
        Login loginPage = homePage.navigateToLoginPage();
        assertPageTitle(loginPage.getTitle(driver), Constant.LOGIN_PAGE_TITLE, "Login Page");

        Account accountPage = loginPage.loginAsExistingUser(Constant.EMAIL_ADDRESS, Constant.PWD);
        homePage = accountPage.navigateToHomePage();

        Search searchPage = homePage.searchProduct("Macbook");

        searchPage.getListView();

        Assert.assertEquals(searchPage.getNoOfProduct().size(),3,"Product Size Mismatch");

        TestLog.stepInfo("Total No Of Products In Search Appearances is :::" + searchPage.getNoOfProduct().size());

    }

    @Test
    @Severity(BLOCKER)
    @Description("Search Product in Grid View ")
    private void searchProductInGridView() throws PageLoadException {

        HomePage homePage = launchHomePage();
        Login loginPage = homePage.navigateToLoginPage();
        assertPageTitle(loginPage.getTitle(driver), Constant.LOGIN_PAGE_TITLE, "Login Page");

        Account accountPage = loginPage.loginAsExistingUser(Constant.EMAIL_ADDRESS, Constant.PWD);
        homePage = accountPage.navigateToHomePage();

        Search searchPage = homePage.searchProduct("Macbook");

        searchPage.getGridView();

        Assert.assertEquals(searchPage.getNoOfProduct().size(),3,"Product Size Mismatch");

        TestLog.stepInfo("Total No Of Products In Search Appearances is :::" + searchPage.getNoOfProduct().size());

    }




}
