package com.uiautomation.framework.tests.Login;

import com.uiautomation.framework.Constant;
import com.uiautomation.framework.pages.Account;
import com.uiautomation.framework.pages.HomePage;
import com.uiautomation.framework.pages.Login;
import com.uiautomation.framework.pages.Register;

import com.uiautomation.framework.reporting.TestLog;
import com.uiautomation.framework.tests.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.qameta.allure.SeverityLevel.BLOCKER;

public class LoginUser extends BaseTest {

    private HomePage homePage;
    private Register registerPage;
    private Login loginPage;
    private Account accountPage;

    @Severity(BLOCKER)
    @Description("Verify UI Page is Running")
    @Test
    public void openHomePage() throws InterruptedException {

        homePage = new HomePage(driver);
        homePage.goTo();
        TestLog.stepInfo("Home Page Opened ");
        Assert.assertEquals(homePage.getTitle(driver), Constant.HOME_PAGE_TITLE);
        TestLog.stepInfo("Title of the Page is ::" + homePage.getTitle(driver));
        TestLog.testPass("Verify UI Page is Running Test Case Passed");


    }

    @Severity(BLOCKER)
    @Description("Verify User Is Able to Login")
    @Test
    public void verifyLoginUser() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        homePage.goTo();
        TestLog.stepInfo("Home Page Opened ");
        Assert.assertEquals(homePage.getTitle(driver), Constant.HOME_PAGE_TITLE);
        TestLog.stepInfo("Title of the Home Page is ::" + homePage.getTitle(driver));
        loginPage = homePage.navigateToLoginPage();
        TestLog.stepInfo("Login Page Opened ");
        Assert.assertEquals(loginPage.getTitle(driver), Constant.LOGIN_PAGE_TITLE);
        TestLog.stepInfo("Title of the Login Page is ::" + loginPage.getTitle(driver));
        accountPage = loginPage.loginAsExistingUser(Constant.EMAIL_ADDRESS, Constant.PWD);
        TestLog.stepInfo("Title of the Account Page is ::" + accountPage.getTitle(driver));
        TestLog.testPass("Verify Login User Test Case Passed");


    }

    @Severity(BLOCKER)
    @Description("Inavlid User should not be able to login")
    @Test
    public void verifyInvalidLoginUser() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        homePage.goTo();
        TestLog.stepInfo("Home Page Opened ");
        Assert.assertEquals(homePage.getTitle(driver), Constant.HOME_PAGE_TITLE);
        TestLog.stepInfo("Title of the Home Page is ::" + homePage.getTitle(driver));
        loginPage = homePage.navigateToLoginPage();
        TestLog.stepInfo("Login Page Opened ");
        Assert.assertEquals(loginPage.getTitle(driver), Constant.LOGIN_PAGE_TITLE);
        TestLog.stepInfo("Title of the Login Page is ::" + loginPage.getTitle(driver));
        accountPage = loginPage.loginAsExistingUser("test123", "test123");
        Assert.assertEquals(loginPage.invalidUser(), Constant.INVALID_USER_WARNING);
        TestLog.stepInfo("Title of the Account Page is ::" + accountPage.getTitle(driver));
        TestLog.testPass("Verify Login User Test Case Passed");


    }


}
