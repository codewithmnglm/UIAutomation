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

import java.util.List;


public class ShoppingCart extends BasePage {

    public ShoppingCart(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//a[text()='Shopping Cart']")
    private WebElement pageHeader;

    @FindBy(xpath = "//div[@class='col-sm-12']//p[text()='Your shopping cart is empty!']")
    private WebElement emptyCartMessage;

    @FindBy(xpath = "//a[contains(text(), 'Continue Shopping')]")
    private WebElement continueShopping;

    @FindBy(xpath = "//a[contains(text(), 'Checkout')]")
    private WebElement checkout;

    @Getter
    @FindBy(xpath = "//div[@class='table-responsive']/table/tbody/tr")
    List<WebElement> shoppingCartItems;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible' and contains(text(), 'You have modified your shopping cart')]")
    private WebElement modifyShoppingCartAlert;

    public String getProductName(int productIndex) {

        String xpath = "//div[@class='table-responsive']/table/tbody/tr[" + productIndex + "]//td[2]/a";
        WebElement webElement = this.driver.findElement(By.xpath(xpath));
        return webElement.getText();

    }

    public boolean emptyShoppingCartMessage() {

        return WaitUtils.waitForVisibility(this.driver, emptyCartMessage).isDisplayed();
    }

    public String getProductModel(int productIndex) {

        String xpath = "//div[@class='table-responsive']/table/tbody/tr[" + productIndex + "]//td[3]";
        WebElement webElement = this.driver.findElement(By.xpath(xpath));
        return WaitUtils.waitForVisibility(this.driver, webElement).getText();


    }

    public int getQuantityOfProduct(int productIndex) {

        String xpath = "//div[@class='table-responsive']/table/tbody/tr[" + productIndex + "]//td[4]//input";
        WebElement webElement = this.driver.findElement(By.xpath(xpath));
        return Integer.parseInt(WaitUtils.waitForVisibility(this.driver, webElement).getAttribute("value"));
    }

    public void updateQuantityOfProduct(int productIndex, String quantity) {

        String xpath = "//div[@class='table-responsive']/table/tbody/tr[" + productIndex + "]//td[4]//input";
        WebElement webElement = this.driver.findElement(By.xpath(xpath));
        type(WaitUtils.waitForClickable(this.driver, webElement), quantity);

    }

    public void refreshProductQuantity(int productIndex) {

        String xpath = "//div[@class='table-responsive']/table/tbody/tr[" + productIndex + "]//td[4]//button[1][contains(@class, 'btn-primary') and @data-original-title='Update']";
        WebElement webElement = this.driver.findElement(By.xpath(xpath));
        click(webElement);
    }

    public void removeFromCartByIndex(int productIndex) {
        String productXpath = "//div[@class='table-responsive']/table/tbody/tr[" + productIndex + "]//td[2]/a";
        String productName = this.driver.findElement(By.xpath(productXpath)).getText();
        String xpath = "//div[@class='table-responsive']/table/tbody/tr[" + productIndex + "]//td[4]//button[2][contains(@class, 'btn btn-danger') and @data-original-title='Remove']";
        WebElement webElement = this.driver.findElement(By.xpath(xpath));
        click(webElement);

        TestLog.stepInfo(productName + " Successfully Removed from Shopping Cart ");
    }

    public String getProductPrice(int productIndex) {

        String xpath = "//div[@class='table-responsive']/table/tbody/tr[" + productIndex + "]//td[5]";
        WebElement webElement = this.driver.findElement(By.xpath(xpath));
        return WaitUtils.waitForVisibility(this.driver, webElement).getText();
    }

    public String getTotalProductPrice(int productIndex) {

        String xpath = "//div[@class='table-responsive']/table/tbody/tr[" + productIndex + "]//td[6]";
        WebElement webElement = this.driver.findElement(By.xpath(xpath));
        return WaitUtils.waitForVisibility(this.driver, webElement).getText();
    }


}
