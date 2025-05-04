package com.framework.pages;

import com.framework.base.BasePage;
import com.framework.reporting.TestLog;
import com.framework.utils.WaitUtils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Account extends BasePage {

    public Account(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//li//a[contains(@href,'https://naveenautomationlabs.com/opencart/index.php?route=common/home')]")
    private WebElement homePageIcon;

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

    @FindBy(name = "search")
    private WebElement searchBar;

    @FindBy(xpath = "//i[@class='fa fa-search']")
    private WebElement searchIcon;

    public Account.WishList navigateToWishListPage() {

        click(modifyWishList);
        return new WishList(this.driver);
    }

    public Search searchProduct(String productName) {

        enterText(searchBar, productName);
        searchIcon.click();
        return new Search(driver);
    }

    public HomePage navigateToHomePage() {
        click(homePageIcon);
        return new HomePage(this.driver);
    }


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
       // WaitUtils.waitFor(ExpectedConditions.visibilityOf(editAccount), this.driver, 5);
        verifyElementDisplayed(changePwd, "Change Password");
        verifyElementDisplayed(modifyAddress, "Modify Address Book");
        verifyElementDisplayed(modifyWishList, "Modify Wish List");
        verifyElementDisplayed(viewOrderHistory, "View Order History");
        verifyElementDisplayed(yourRewardPoints, "Your Reward Points");
        verifyElementDisplayed(viewReturnReq, "View Return Requests");
        verifyElementDisplayed(yourTxns, "Your Transactions");
        verifyElementDisplayed(recurringPayments, "Recurring Payments");
    }

    private void verifyElementDisplayed(WebElement element, String elementName) {

        Optional.ofNullable(element).
                filter(e -> e.isDisplayed() && e.isEnabled()).ifPresentOrElse(
                        e -> TestLog.stepInfo(elementName + " is visible and enabled."), () ->
                        {
                            throw new AssertionError(elementName + " is not visible or enabled.");
                        }

                );


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

            enterText(enterPwd, currentPwd);
            enterText(cnfirmPwd, newPwd);
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

            enterText(firstName, firstNameValue);
            enterText(lastName, lastNameValue);
            enterText(email, emailValue);
            enterText(telephone, phoneValue);
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

        @FindBy(xpath = "//a[text()='Continue']")
        private WebElement continueBtn;

        public HomePage navigateToHomePage() {
            click(continueBtn);
            return new HomePage(driver);
        }

        public boolean isLogOffMsgDisplayed() {
            return WaitUtils.waitFor(ExpectedConditions.visibilityOf(loggOffMsg),driver,5).isDisplayed();
        }

        public boolean isLogOutMsgDisplayed() {
            return WaitUtils.waitFor(ExpectedConditions.visibilityOf(logoutMsg),driver,5).isDisplayed();
        }

        public boolean isAccountLogoutDisplayed() {
            return WaitUtils.waitFor(ExpectedConditions.visibilityOf(accountLogout),driver,5).isDisplayed();
        }

        public boolean isContinueBtnEnabled() {
            return WaitUtils.waitFor(ExpectedConditions.elementToBeClickable(continueBtn),driver,5).isEnabled();
        }


    }

    public static class Success extends Account {

        public Success(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);

        }

        @FindBy(xpath = "//h1[contains(text(), 'Your Account Has Been Created!')]")
        WebElement accountCreation;

        @FindBy(xpath = "//p[contains(text(), 'Congratulations! Your new account has been successfully created!')]")
        WebElement congratulationMsg;

        @FindBy(xpath = "//p[contains(text(), 'If you have ANY questions about the operation of this online shop, please e-mail the store owner.')]")
        WebElement queryMsg;

        @FindBy(xpath = "//a[contains(@href, 'account/account') and contains(text(), 'Continue')]")
        WebElement continueBtn;

        public Account navigateToAccountPage() {

            WaitUtils.waitForClickable(driver, continueBtn).click();
            return new Account(driver);
        }

        public void verifyAccountSuccessPageElements() {
            verifyElementVisibleAndEnabled(accountCreation, "Account Creation Heading");
            verifyElementVisibleAndEnabled(congratulationMsg, "Congratulations Message");
            verifyElementVisibleAndEnabled(queryMsg, "Query Message");
            verifyElementVisibleAndEnabled(continueBtn, "Continue Button");
        }

        private void verifyElementVisibleAndEnabled(WebElement element, String elementName) {
            if (element.isDisplayed() || element.isEnabled()) {
                TestLog.stepInfo(elementName + " is visible or enabled.");
            } else {
                throw new AssertionError(elementName + " is NOT visible or enabled.");
            }
        }


    }

    public static class WishList extends Account {

        public WishList(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);

        }

        @FindBy(xpath = "//p[contains(text(), 'Your wish list is empty.')]")
        WebElement emptyWishList;

        @FindBy(xpath = "//a[contains(@href, 'account/account') and contains(text(), 'Continue')]")
        WebElement continueBtn;

        @Getter
        @FindBy(xpath = "//table[contains(@class,'table table-bordered table-hover')]/tbody/tr")
        List<WebElement> wishList;

        @FindBy(xpath = "//div[contains(@class,'alert alert-success alert-dismissible')and contains(text(),' Success: You have modified your wish list!')]")
        WebElement wishListModification;

        @FindBy(xpath = "//a[@title='Shopping Cart']")
        WebElement shoppingCartBtn;

        public boolean verifyEmptyWishList() {

            return WaitUtils.waitForVisibility(this.driver, emptyWishList).isDisplayed();
        }

        public String getProductName(int productIndex) {

            String xpath = "//table[contains(@class,'table table-bordered table-hover')]/tbody/tr[" + productIndex + "]//td[2]/a";
            return WaitUtils.waitForVisibility(this.driver, driver.findElement(By.xpath(xpath))).getText();
        }

        public String getProductModel(int productIndex) {

            String xpath = "//table[contains(@class,'table table-bordered table-hover')]/tbody/tr[" + productIndex + "]//td[3]";
            return WaitUtils.waitForVisibility(this.driver, driver.findElement(By.xpath(xpath))).getText();
        }

        public String getProductUnitPrice(int productIndex) {

            String xpath = "//table[contains(@class,'table table-bordered table-hover')]/tbody/tr[" + productIndex + "]//td[5]/div[contains(@class,'price')]";
            return WaitUtils.waitForVisibility(this.driver, driver.findElement(By.xpath(xpath))).getText();
        }

        public ShoppingCart addToShoppingCart(int productIndex) {
            String xpath = "//table[contains(@class,'table table-bordered table-hover')]/tbody/tr[" + productIndex + "]//td[6]//button[contains(@data-original-title,'Add to Cart')]";
            click(this.driver.findElement(By.xpath(xpath)));
            TestLog.stepInfo("Adding product to shopping cart at Index: " + productIndex);
            return new ShoppingCart(driver);

        }

        public void removeFromWishList(int productIndex) {

            String xpath = "//table[contains(@class,'table table-bordered table-hover')]/tbody/tr[" + productIndex + "]//td[6]//a[contains(@data-original-title,'Remove')]";
            click(this.driver.findElement(By.xpath(xpath)));
            TestLog.stepInfo("Product at Index " + productIndex + " Removed From WishList");

        }

        public boolean isAvailable(int productIndex) {

            String xpath = "//table[contains(@class,'table table-bordered table-hover')]/tbody/tr[" + productIndex + "]//td[4]";
            String productAvailability = WaitUtils.waitForVisibility(this.driver, driver.findElement(By.xpath(xpath))).getText();

            Map<String, Boolean> map = Map.of(
                    "Out Of Stock", false,
                    "In Stock", true
            );
            return map.get(productAvailability);
        }


        public Account navigateToAccountPage() {

            WaitUtils.waitForClickable(driver, continueBtn).click();
            return new Account(driver);
        }

        public ShoppingCart navigateToCartPage() {

            WaitUtils.waitForClickable(driver, shoppingCartBtn).click();
            return new ShoppingCart(driver);
        }


    }
}
