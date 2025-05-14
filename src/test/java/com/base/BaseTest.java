package com.base;

import com.framework.exceptions.PageLoadException;
import com.framework.factory.Constant;
import com.framework.pages.Account;
import com.framework.pages.HomePage;
import com.framework.pages.Register;
import com.framework.reporting.TestLog;
import com.framework.utils.ScreenshotUtil;
import com.google.common.util.concurrent.Uninterruptibles;
import com.framework.factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;


    @BeforeMethod
    public void setUpDriver() {
        this.driver = DriverFactory.getDriver("firefox"); //Get The Name of Browser from either config files or as JENKINS Input
        TestLog.stepInfo("Chrome Driver Set Up Completed");

    }

    @AfterMethod
    public void quitDriver(ITestResult result) {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtil.captureScreenshot(driver, result.getName());
        }
        this.driver.quit();
        TestLog.stepInfo("Quit Driver Completed");

    }

    public void assertPageTitle(String actualTitle, String expectedTitle, String pageName) {
        Assert.assertEquals(actualTitle, expectedTitle, pageName + " title mismatch");
        TestLog.stepInfo("Title of the " + pageName + " is: " + actualTitle);
    }

    public HomePage launchHomePage() throws PageLoadException {
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

    public Register goToRegisterUserPage() throws PageLoadException {
        HomePage homePage = goToHomePage();
        Register registerPage = homePage.navigateToRegistrationPage();
        assertPageTitle(registerPage.getTitle(driver), Constant.REGISTER_PAGE_TITLE, "Register Page");
        TestLog.stepInfo("Registration Page Opened");
        return new Register(driver);
    }

    public void verifyLogoutPageElements(Account.Logout logoutPage) {
        Assert.assertTrue(logoutPage.isAccountLogoutDisplayed(), "Account Log Out Field is not Displayed");
        Assert.assertTrue(logoutPage.isContinueBtnEnabled(), "Continue Button is not Enabled");
        Assert.assertTrue(logoutPage.isLogOffMsgDisplayed(), "Log Off Msg Field is not Displayed");
        Assert.assertTrue(logoutPage.isLogOutMsgDisplayed(), "Log Out Msg Field is not Displayed");
        assertPageTitle(logoutPage.getTitle(driver), Constant.LOGOUT_PAGE_TITLE, "Log Out Page");
    }

    public void verifyElement(boolean condition, String elementName) {
        Assert.assertTrue(condition, elementName + " is not present/enabled");
        TestLog.stepInfo(elementName + " is Present and Enabled/Displayed");
    }

    public int returnProductIndex(String productName) {

        Map<String, Integer> map = Map.ofEntries(
                Map.entry("MacBook", 1),
                Map.entry("iPhone", 2),
                Map.entry("Apple Cinema 30", 3),
                Map.entry("Canon EOS 5D", 4)
        );

        return map.get(productName);
    }

    public HomePage goToHomePage() throws PageLoadException {
        HomePage homePage = launchHomePage();
        assertPageTitle(homePage.getTitle(driver), Constant.HOME_PAGE_TITLE, "Home Page");
        TestLog.stepInfo("User Is Logged In Successfully And on HomePage");
        return homePage;
    }

    public String priceExtractor(String priceText, String pricePattern) {
        if (priceText == null || priceText.trim().isEmpty()) {
            throw new IllegalArgumentException("Price text cannot be null or empty");
        }
        if (pricePattern == null || pricePattern.trim().isEmpty()) {
            throw new IllegalArgumentException("Price pattern cannot be null or empty");
        }

        try {
            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(pricePattern);
            java.util.regex.Matcher matcher = pattern.matcher(priceText);

            if (matcher.find()) {
                String extractedPrice = matcher.group();
                TestLog.stepInfo("Successfully extracted price: " + extractedPrice);
                return extractedPrice;
            } else {
                TestLog.stepInfo("No price found in text: " + priceText);
                return "0.00";
            }
        } catch (java.util.regex.PatternSyntaxException e) {
            String errorMsg = "Invalid price pattern: " + e.getMessage();
            TestLog.stepInfo(errorMsg);
            throw new IllegalArgumentException(errorMsg, e);
        }
    }
}
