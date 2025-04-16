package com.tests.Login;

import com.base.BaseTest;
import com.framework.exceptions.PageLoadException;
import com.framework.factory.Constant;
import com.framework.pages.Account;
import com.framework.pages.HomePage;
import com.framework.pages.Login;
import com.framework.reporting.TestLog;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.qameta.allure.SeverityLevel.BLOCKER;

public class LoginUser extends BaseTest {

    private Login loginPage;
    private Account accountPage;

    @Test
    @Severity(BLOCKER)
    @Description("Verify UI Page is Running")
    public void openHomePage() throws PageLoadException {
        HomePage homePage = launchHomePage();
        assertPageTitle(homePage.getTitle(driver), Constant.HOME_PAGE_TITLE, "Home Page");
        TestLog.testPass("Verify UI Page is Running Test Case Passed");
    }

    @Test
    @Severity(BLOCKER)
    @Description("Verify User Is Able to Login")
    public void verifyLoginUser() throws PageLoadException {
        HomePage homePage = launchHomePage();
        loginPage = homePage.navigateToLoginPage();
        assertPageTitle(loginPage.getTitle(driver), Constant.LOGIN_PAGE_TITLE, "Login Page");

        accountPage = loginPage.loginAsExistingUser(Constant.EMAIL_ADDRESS, Constant.PWD);
        TestLog.stepInfo("Title of the Account Page is: " + accountPage.getTitle(driver));

        TestLog.testPass("Verify Login User Test Case Passed");
    }

    @Test
    @Severity(BLOCKER)
    @Description("Invalid User should not be able to login")
    public void verifyInvalidLoginUser() throws PageLoadException {
        HomePage homePage = launchHomePage();
        loginPage = homePage.navigateToLoginPage();
        assertPageTitle(loginPage.getTitle(driver), Constant.LOGIN_PAGE_TITLE, "Login Page");

        accountPage = loginPage.loginAsExistingUser("test123", "test123");
        Assert.assertEquals(loginPage.invalidUser(), Constant.INVALID_USER_WARNING, "Invalid user warning not shown");

        TestLog.stepInfo("Invalid login verified, warning displayed as expected.");
        TestLog.testPass("Verify Invalid Login Test Case Passed");
    }

    @Test
    @Severity(BLOCKER)
    @Description("Validate Home Page Objects")
    public void verifyHomePageElementsArePresent() throws PageLoadException  {
        HomePage homePage = launchHomePage();
        assertPageTitle(homePage.getTitle(driver), Constant.HOME_PAGE_TITLE, "Home Page");
        verifyElement(homePage.isSearchIconEnabled(), "Search Icon");
        verifyElement(homePage.isPageLogoDisplayed(), "Page Logo");
        verifyElement(homePage.isSearchBarEnabled(), "Search Bar");
        verifyElement(homePage.isWishListEnabled(), "Wish List");
        verifyElement(homePage.isMyAccountEnabled(), "My Account List");
        TestLog.testPass("Home Page UI element validation passed.");
    }



    private HomePage launchHomePage() throws PageLoadException {
        try {
            HomePage homePage = new HomePage(driver);
            homePage.goTo();
            TestLog.stepInfo("✅ Home Page Opened Successfully");
            return homePage;
        } catch (Exception e) {
            String errorMsg = "❌ Failed to load Home Page: " + e.getMessage();
            TestLog.stepInfo(errorMsg);
            throw new PageLoadException(errorMsg, e);
        }
    }

    private void assertPageTitle(String actualTitle, String expectedTitle, String pageName) {
        Assert.assertEquals(actualTitle, expectedTitle, pageName + " title mismatch");
        TestLog.stepInfo("Title of the " + pageName + " is: " + actualTitle);
    }

    private void verifyElement(boolean condition, String elementName) {
        Assert.assertTrue(condition, elementName + " is not present/enabled");
        TestLog.stepInfo(elementName + " is Present and Enabled/Displayed");
    }
}
