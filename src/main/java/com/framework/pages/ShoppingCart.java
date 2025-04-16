package com.framework.pages;

import com.framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ShoppingCart extends BasePage {

    public ShoppingCart(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//a[text()='Shopping Cart']")
    private WebElement pageHeader;

    @FindBy(xpath = "//div[@class='col-sm-12']//p[text()='Your shopping cart is empty!']")
    private WebElement msg;

    public void verifyPage() {
        msg.getText();

    }

}
