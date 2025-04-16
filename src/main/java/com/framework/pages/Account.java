package com.framework.pages;

import com.framework.base.BasePage;
import com.framework.reporting.TestLog;
import com.framework.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Account extends BasePage {

    public Account(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//a[text()='Edit your account information']")
    private WebElement editAccount;

    @FindBy(xpath = "//a[text()='Change your password']")
    private WebElement changePwd;

    @FindBy(xpath = "//a[text()='Modify your address book entries']")
    private WebElement modifyAddress;

    @FindBy(xpath = "//a[text()='Modify your wish list']")
    private WebElement modifyWishList;

    @FindBy(xpath = "//a[text()='View your order history']")
    private WebElement viewOrderHistory;

    @FindBy(xpath = "//a[text()='Downloads']")
    private WebElement downloads;

    @FindBy(xpath = "//a[text()='Your Reward Points']")
    private WebElement yourRewardPoints;

    @FindBy(xpath = "//a[text()='View your return requests']")
    private WebElement viewReturnReq;

    @FindBy(xpath = "//a[text()='Your Transactions']")
    private WebElement yourTxns;

    @FindBy(xpath = "//a[text()='Recurring payments']")
    private WebElement recurringPayments;

    @FindBy(xpath = "//span[text()='My Account']")
    private WebElement myAccount;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logout;

    public Logout logout() {
        click(myAccount);
        click(logout);
        return new Logout(driver);
    }

    public EditAccountPage goToEditAccountPage() {
        editAccount.click();
        return new EditAccountPage(driver);
    }

    public void verifyAccountPageElements() {
        verifyElementDisplayed(editAccount, "Edit Account");
        verifyElementDisplayed(changePwd, "Change Password");
        verifyElementDisplayed(modifyAddress, "Modify Address Book");
        verifyElementDisplayed(modifyWishList, "Modify Wish List");
        verifyElementDisplayed(viewOrderHistory, "View Order History");
        verifyElementDisplayed(downloads, "Downloads");
        verifyElementDisplayed(yourRewardPoints, "Your Reward Points");
        verifyElementDisplayed(viewReturnReq, "View Return Requests");
        verifyElementDisplayed(yourTxns, "Your Transactions");
        verifyElementDisplayed(recurringPayments, "Recurring Payments");
    }

    private void verifyElementDisplayed(WebElement element, String elementName) {
        if (WaitUtils.waitForVisibility(driver, element).isEnabled() || WaitUtils.waitForVisibility(driver, element).isDisplayed()) {
            TestLog.stepInfo(elementName + " is visible and enabled.");
        } else {
            throw new AssertionError(elementName + " is NOT visible or enabled.");
        }

    }


    public static class UpdatePassword extends Account {

        public UpdatePassword(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
        }

        @FindBy(id = "input-password")
        private WebElement enterPwd;

        @FindBy(id = "input-confirm")
        private WebElement cnfirmPwd;

        @FindBy(xpath = "//input[@value='Continue']")
        private WebElement continueBtn;

        @FindBy(xpath = "//a[text()='Back']")
        private WebElement bckBtn;

        public void updatePwd(String currentPwd, String newPwd) {

            type(enterPwd, currentPwd);
            type(cnfirmPwd, newPwd);
            click(continueBtn);

        }

        public Account naviagteToAccountsPage(WebDriver driver) {

            click(continueBtn);
            return new Account(driver);

        }

    }

    public static class EditAccountPage extends Account {

        public EditAccountPage(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);

        }

        @FindBy(id = "input-firstname")
        private WebElement firstName;

        @FindBy(id = "input-lastname")
        private WebElement lastName;

        @FindBy(id = "input-email")
        private WebElement email;

        @FindBy(id = "input-telephone")
        private WebElement telephone;

        @FindBy(xpath = "//a[text()='Back']")
        private WebElement backBtn;

        @FindBy(xpath = "//input[@value='Continue']")
        private WebElement continueBtn;

        public void updateAccount(String firstNameValue, String lastNameValue, String emailValue, String phoneValue) {

            type(firstName, firstNameValue);
            type(lastName, lastNameValue);
            type(email, emailValue);
            type(telephone, phoneValue);
            click(continueBtn);
        }


        public Account backToAccountPage() {

            click(backBtn);
            return new Account(driver);

        }


    }

    public static class Logout extends Account {

        public Logout(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
        }

        @FindBy(xpath = "//h1[text()='Account Logout']")
        private WebElement accountLogout;

        @FindBy(xpath = "//p[text()='You have been logged off your account. It is now safe to leave the computer.']")
        private WebElement loggOffMsg;

        @FindBy(xpath = "//p[text()='Your shopping cart has been saved, the items inside it will be restored whenever you log back into your account.']")
        private WebElement logoutMsg;

        @FindBy(xpath="//a[text()='Continue']")
        private WebElement continueBtn;

        public HomePage navigateToHomePage(){
            click(continueBtn);
           return new HomePage(driver);
        }

        public boolean isLogOffMsgDisplayed() {
            return WaitUtils.waitForVisibility(driver, loggOffMsg).isDisplayed();
        }

        public boolean isLogOutMsgDisplayed() {
            return WaitUtils.waitForVisibility(driver, logoutMsg).isDisplayed();
        }

        public boolean isAccountLogoutDisplayed() {
            return WaitUtils.waitForVisibility(driver, accountLogout).isDisplayed();
        }

        public boolean isContinueBtnEnabled() {
            return WaitUtils.waitForVisibility(driver, continueBtn).isEnabled();
        }




    }
}
