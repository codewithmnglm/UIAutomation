package com.tests.account;

import com.base.BaseTest;
import com.framework.exceptions.PageLoadException;
import com.framework.factory.Constant;
import com.framework.pages.Account;
import com.framework.pages.HomePage;
import com.framework.pages.Login;
import com.framework.pages.Register;
import com.framework.reporting.TestLog;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.BLOCKER;

public class RegistrationFlowTest extends BaseTest {
    HomePage homePage;

    @Test(groups = {"regression"})
    @Severity(BLOCKER)
    @Description("Verify If New Registrations are getting created")
    public void registerUser() throws PageLoadException {
        Register registerPage = goToRegisterUserPage();
        //Pick Data from Excel or JSON
        Account.Success myAccountSuccess = registerPage.registerNewUser("Suresh123", "Kumar123", "Suresh5123@gmail.com",
                "000765", "SureshUser123", "SureshUser123");
        assertPageTitle(myAccountSuccess.getTitle(driver), Constant.ACCOUNT_PAGE_SUCCESS_TITLE, "Accounts Success Page");
        myAccountSuccess.verifyAccountSuccessPageElements();
        TestLog.stepInfo("✅User is Successfully Registered and on Account Page");
        Account myAccount = myAccountSuccess.navigateToAccountPage();
        myAccount.verifyAccountPageElements();
        TestLog.stepInfo("My Account Page Elements Are Displayed and Enabled.");
        Account.Logout logoutPage = myAccount.logout();
        verifyLogoutPageElements(logoutPage);
        TestLog.stepInfo("✅User is Successfully Logged Out");
        homePage = logoutPage.navigateToHomePage();
        assertPageTitle(homePage.getTitle(driver), Constant.HOME_PAGE_TITLE, "Home Page");
        TestLog.stepInfo("✅ User is Successfully Logged Out and on Home Page");
    }

    @SneakyThrows
    @Test(groups = {"regression"})
    @Severity(BLOCKER)
    @Description("Create Account For Exiting User")
    public void createRegistrationForExistingUser() {
        Register registerPage = goToRegisterUserPage();
        registerPage.registerNewUser("Abhishek", "Kr", "abhishek007@gmail.com",
                "228877234", "Patna246", "Patna246");
        Assert.assertTrue(registerPage.isWarningMsgPresent(), "Warning Alert Msg is not displayed");
        Login loginPage = registerPage.navigateToLoginPage();
        assertPageTitle(loginPage.getTitle(driver), Constant.LOGIN_PAGE_TITLE, "Login Page");
        TestLog.stepInfo("✅User is At Login Page");
        Account accountPage = loginPage.loginAsExistingUser("abhishek007@gmail.com", "Patna246");
        assertPageTitle(loginPage.getTitle(driver), Constant.ACCOUNT_PAGE_TITLE, "Account Page");
        TestLog.stepInfo("✅ Create Registration For Existing User Test Case Passed");

    }

    @SneakyThrows
    @Test(groups = {"regression"})
    @Severity(BLOCKER)
    @Description("Create Account With Already Registered User")
    public void registerForAlreadyRegisteredEmail() {
        Register registerPage = goToRegisterUserPage();
        registerPage.registerNewUser("", "K", "abhishek007@gmail.com",
                "", "", "");
        Assert.assertTrue(registerPage.isEmailRegWarningMsgPresent(), "Email Already Registered Alert Not Present.");
        assertPageTitle(registerPage.getTitle(driver), Constant.REGISTER_PAGE_TITLE, "Register Page");

    }



}
