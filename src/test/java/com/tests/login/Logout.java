package com.tests.login;

import com.base.BaseTest;
import com.framework.exceptions.PageLoadException;
import com.framework.factory.Constant;
import com.framework.pages.Account;
import com.framework.pages.HomePage;
import com.framework.pages.Login;
import com.framework.reporting.TestLog;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class Logout extends BaseTest {

    private Login loginPage;
    private Account accountPage;
    @Test
    @Description("User Logs Out Successfully")
    @Severity(SeverityLevel.BLOCKER)
    private void existingUserLogOut() throws PageLoadException {
        HomePage homePage = launchHomePage();
        loginPage = homePage.navigateToLoginPage();
        assertPageTitle(loginPage.getTitle(driver), Constant.LOGIN_PAGE_TITLE, "Login Page");
        accountPage = loginPage.loginAsExistingUser(Constant.EMAIL_ADDRESS, Constant.PWD);
        TestLog.stepInfo("Title of the Account Page is: " + accountPage.getTitle(driver));
        TestLog.stepInfo("✅ User is Successfully Logged In");
        Account.Logout logoutPage=accountPage.logout();
        verifyLogoutPageElements(logoutPage);
        TestLog.stepInfo("✅ User is Successfully Logged Out");

    }


}
