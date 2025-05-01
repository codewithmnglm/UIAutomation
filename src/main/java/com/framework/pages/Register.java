package com.framework.pages;

import com.framework.base.BasePage;
import com.framework.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Register extends BasePage {

    public Register(WebDriver driver) {
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
    private WebElement telphone;

    @FindBy(id = "input-password")
    private WebElement pwd;

    @FindBy(id = "input-confirm")
    private WebElement cnfrmPwd;

    @FindBy(xpath = "//label[@class='radio-inline']/input[@name='newsletter' and @value='1']")
    private WebElement yesSubs;

    @FindBy(xpath = "//label[@class='radio-inline']/input[@name='newsletter' and @value='0']")
    private WebElement noSubs;

    @FindBy(xpath = "//input[contains(@name,'agree')]")
    private WebElement agreeAgreement;

    @FindBy(xpath = "//input[contains(@value,'Continue')]")
    private WebElement continueButton;

    @FindBy(xpath = "//div[contains(text(), 'Warning')]")
    private WebElement warningMsg;


    @FindBy(xpath = "//div[contains(@class, 'alert-danger') and contains(text(), 'Warning')]")
    private WebElement emailRegWarningMsg;

    @FindBy(xpath = "//a[contains(text(), 'login page')]")
    private WebElement loginLink;

    public Login navigateToLoginPage() {

        WaitUtils.waitForClickable(driver, loginLink).click();
        return new Login(driver);
    }

    public boolean isWarningMsgPresent() {

        return WaitUtils.waitForVisibility(driver, warningMsg).isDisplayed();
    }

    public boolean isEmailRegWarningMsgPresent() {

        return WaitUtils.waitForVisibility(driver, emailRegWarningMsg).isDisplayed();
    }

    public Account.Success registerNewUser(String firstNameValue, String lastNameValue, String emailValue,
                                           String telephoneValue, String pwdValue, String confirmPwdValue) {

        enterText(firstName, firstNameValue);
        enterText(lastName, lastNameValue);
        enterText(email, emailValue);
        enterText(telphone, telephoneValue);
        enterText(pwd, pwdValue);
        enterText(cnfrmPwd, confirmPwdValue);

        clickElement(noSubs);
        clickElement(agreeAgreement);
        clickElement(continueButton);

        return new Account.Success(driver);
    }

    private void clickElement(WebElement element) {
        click(element);
    }


}
