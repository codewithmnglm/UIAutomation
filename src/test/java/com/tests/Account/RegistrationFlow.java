package com.tests.Account;

import com.base.BaseTest;
import com.framework.factory.Constant;
import com.framework.pages.Account;
import com.framework.pages.HomePage;
import com.framework.pages.Login;
import com.framework.pages.Register;
import com.framework.reporting.TestLog;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.BLOCKER;

public class RegistrationFlow extends BaseTest {
    HomePage homePage;

    @Test
    @Severity(BLOCKER)
    @Description("Verify If New Registrations are getting created")
    public void registerUser() {
        Register registerPage = goToRegisterUserPage();
        //Pick Data from Excel or JSON
        Account.Success myAccountSuccess = registerPage.registerNewUser("Ramesh6", "Kumar6", "ramesh0076@gmail.com",
                "7871234", "rameshUser6", "rameshUser6");
        assertPageTitle(myAccountSuccess.getTitle(driver), Constant.ACCOUNT_PAGE_SUCCESS_TITLE, "Accounts Success Page");
        myAccountSuccess.verifyAccountSuccessPageElements();
        TestLog.stepInfo("User is Successfully Registered and on Account Page");
        Account myAccount = myAccountSuccess.navigateToAccountPage();
        myAccount.verifyAccountPageElements();
        TestLog.stepInfo("My Account Page Elements Are Displayed and Enabled.");
        Account.Logout logoutPage = myAccount.logout();
        Assert.assertTrue(logoutPage.isAccountLogoutDisplayed(), "Account Log Out Field is not Displayed");
        Assert.assertTrue(logoutPage.isContinueBtnEnabled(), "Continue Button is not Enabled");
        Assert.assertTrue(logoutPage.isLogOffMsgDisplayed(), "Log Off Msg Field is not Displayed");
        Assert.assertTrue(logoutPage.isLogOutMsgDisplayed(), "Log Out Msg Field is not Displayed");
        Assert.assertEquals(logoutPage.getTitle(driver), Constant.LOGOUT_PAGE_TITLE, "Log Out Page");
        TestLog.stepInfo("User is Successfully Logged Out");
        homePage = logoutPage.navigateToHomePage();
        assertPageTitle(homePage.getTitle(driver), Constant.HOME_PAGE_TITLE, "Home Page");
        TestLog.stepInfo("User is Successfully Logged Out and on Home Page");
    }

    @Test
    @Severity(BLOCKER)
    @Description("Create Account For Exiting User")
    public void createRegistrationForExistingUser() {
        Register registerPage = goToRegisterUserPage();
        registerPage.registerNewUser("Abhishek", "Kr", "abhishek007@gmail.com",
                "228877234", "Patna246", "Patna246");
        Assert.assertTrue(registerPage.isWarningMsgPresent(), "Warning Alert Msg is not displayed");
        Login loginPage = registerPage.navigateToLoginPage();
        assertPageTitle(loginPage.getTitle(driver), Constant.LOGIN_PAGE_TITLE, "Login Page");
        TestLog.stepInfo("User is At Login Page");
        Account accountPage = loginPage.loginAsExistingUser("abhishek007@gmail.com", "Patna246");
        assertPageTitle(loginPage.getTitle(driver), Constant.ACCOUNT_PAGE_TITLE, "Account Page");
        TestLog.stepInfo("Create Registration For Existing User Test Case Passed");

    }

    @Test
    @Severity(BLOCKER)
    @Description("Create Account With Already Registered User")
    public void registerForAlreadyRegisteredEmail() {
        Register registerPage = goToRegisterUserPage();
        registerPage.registerNewUser("", "K", "abhishek007@gmail.com",
                "", "", "");
        Assert.assertTrue(registerPage.isEmailRegWarningMsgPresent(), "Email Already Registered Alert Not Present.");
        assertPageTitle(registerPage.getTitle(driver), Constant.REGISTER_PAGE_TITLE, "Register Page");

    }

    private void assertPageTitle(String actualTitle, String expectedTitle, String pageName) {
        Assert.assertEquals(actualTitle, expectedTitle, pageName + " title mismatch");
        TestLog.stepInfo("Title of the " + pageName + " is: " + actualTitle);
    }

    private Register goToRegisterUserPage() {
        homePage = new HomePage(driver);
        homePage.goTo();
        assertPageTitle(homePage.getTitle(driver), Constant.HOME_PAGE_TITLE, "Home Page");
        TestLog.stepInfo("Home Page Opened");
        Register registerPage = homePage.navigateToRegistrationPage();
        assertPageTitle(registerPage.getTitle(driver), Constant.REGISTER_PAGE_TITLE, "Register Page");
        TestLog.stepInfo("Registration Page Opened");
        return new Register(driver);
    }


}
