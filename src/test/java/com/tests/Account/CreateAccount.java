package com.tests.Account;

import com.base.BaseTest;
import com.framework.exceptions.PageLoadException;
import com.framework.factory.Constant;
import com.framework.pages.Account;
import com.framework.pages.HomePage;
import com.framework.pages.Register;
import com.framework.reporting.TestLog;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.BLOCKER;

public class CreateAccount extends BaseTest {

    @Test
    @Severity(BLOCKER)
    @Description("Verify If New Registrations are getting created")
    public void createAccount(){
        HomePage homePage= new HomePage(driver);
        homePage.goTo();
        assertPageTitle(homePage.getTitle(driver), Constant.HOME_PAGE_TITLE, "Home Page");
        TestLog.stepInfo("Home Page Opened");
        Register registerPage=homePage.navigateToRegistrationPage();
        assertPageTitle(registerPage.getTitle(driver), Constant.REGISTER_PAGE_TITLE, "Register Page");
        TestLog.stepInfo("Registration Page Opened");
        Account myAccount = registerPage.registerNewUser("abc","def","abc@gmail.com",
                "007","raw","raw");
        assertPageTitle(registerPage.getTitle(driver), Constant.ACCOUNT_PAGE_TITLE, "Accounts Page");
        myAccount.verifyAccountPageElements();
        TestLog.stepInfo("My Account Page Element Are Displayed and Enabled.");
        


    }
    private void assertPageTitle(String actualTitle, String expectedTitle, String pageName) {
        Assert.assertEquals(actualTitle, expectedTitle, pageName + " title mismatch");
        TestLog.stepInfo("Title of the " + pageName + " is: " + actualTitle);
    }



}
