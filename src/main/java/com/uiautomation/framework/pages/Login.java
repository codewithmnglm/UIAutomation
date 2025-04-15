package com.uiautomation.framework.pages;

import com.uiautomation.framework.base.BasePage;
import com.uiautomation.framework.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login extends BasePage {

    public Login(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='Continue']")
    private WebElement clickToCreateNewUser;

    @FindBy(id = "input-email")
    private WebElement email;

    @FindBy(id = "input-password")
    private WebElement password;

    @FindBy(xpath = "//input[@class='btn btn-primary']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[text()='Forgotten Password']")
    private WebElement forgotPwd;

    @FindBy(xpath="//div[@class=\"alert alert-danger alert-dismissible\"]")
    private WebElement invalidUserAlert;

    public Account loginAsExistingUser(String userName, String pwd) {

        type(email,userName);
        type(password,pwd);
        click(loginButton);
        return new Account(driver);
    }

    public String invalidUser(){
        return WaitUtils.waitForVisibility(driver,invalidUserAlert).getText();
    }





}
