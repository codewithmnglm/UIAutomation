package com.uiautomation.framework.pages;

import com.uiautomation.framework.base.BasePage;
import com.uiautomation.framework.utils.WaitUtils;
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
    private WebElement modifyAdsress;

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

    public EditAccountPage goToEditAccountPage() {
        editAccount.click();
        return new EditAccountPage(driver);
    }

    public class UpdatePassword extends Account {

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

    public class EditAccountPage extends Account {

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
}
