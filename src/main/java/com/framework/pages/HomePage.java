package com.framework.pages;

import com.framework.factory.Constant;
import com.framework.base.BasePage;
import com.framework.reporting.TestLog;
import com.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//img[@title='naveenopencart']")
    private WebElement pageLogo;

    @FindBy(name = "search")
    private WebElement searchBar;

    @FindBy(className = "swiper-button-prev")
    private WebElement prev;

    @FindBy(className = "swiper-button-next")
    private WebElement next;

    @FindBy(xpath = "//span[text()='Currency']")
    private WebElement currencyDropDown;

    @FindBy(xpath = "//button[text()='£ Pound Sterling']")
    private WebElement pound;

    @FindBy(xpath = "//button[text()='$ US Dollar']")
    private WebElement dollar;

    @FindBy(xpath = "//button[text()='€ Euro']")
    private WebElement euro;

    @FindBy(xpath = "//span[@id='cart-total' and contains(text(), '0.00€')]")
    private WebElement verifyEuro;

    @FindBy(xpath = "//span[@id='cart-total' and contains(text(), '$0.00')]")
    private WebElement verifyDollar;

    @FindBy(xpath = "//span[@id='cart-total' and contains(text(), '£0.00')]")
    private WebElement verifyPound;

    @FindBy(xpath = "//i[@class='fa fa-search']")
    private WebElement searchIcon;

    @FindBy(className = "img-responsive")
    private WebElement banners;

    @FindBy(xpath = "//span[text()='123456789']")
    private WebElement mobileContact;

    @FindBy(xpath = "//a[@title='My Account']")
    private WebElement accountHeader;

    @FindBy(xpath = "//a[@id='wishlist-total']")
    private WebElement wishList;

    By wishListByLocators = By.id("wishlist-total");

    @FindBy(xpath = "//a[@title='Shopping Cart']")
    private WebElement shoppingCart;

    @FindBy(xpath = "//a[@title='Shopping Cart']")
    private WebElement checkOut;

    @FindBy(xpath = "//li//a[@href='https://naveenautomationlabs.com/opencart/index.php?route=account/register' and contains(text(),'Register')]")
    private WebElement registerLink;

    @FindBy(xpath = "//li//a[@href='https://naveenautomationlabs.com/opencart/index.php?route=account/login']")
    private WebElement login;

    @FindBy(xpath = "//a[@title='My Account']")
    private WebElement myAccount;

    @FindBy(xpath = "//a[text()='Order History']")
    private WebElement orderHistory;

    @FindBy(xpath = "//a[contains(@title,'Transactions')]")
    private WebElement transaction;

    @FindBy(xpath = "//a[contains(@title,'Downloads')]")
    private WebElement downloads;

    @FindBy(xpath = "//a[contains(@title,'Logout')]")
    private WebElement logout;

    @FindBy(xpath = "//button[contains(@class, 'btn') and .//span[@id='cart-total']]")
    private WebElement cartItems;

    @FindBy(xpath = "//p[text()='Your shopping cart is empty!']")
    private WebElement emptyCartMsg;


    public void goTo() {
        this.driver.get(Constant.BASE_URL);
        this.driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

    @FindBy(xpath = "//div[@id='content']//div[@class='row']/div")
    private List<WebElement> listOfFeaturedProducts;

    @FindBy(xpath = "//a[text()='Desktops']")
    private WebElement desktops;

    @FindBy(xpath = "//a[text()='Laptops & Notebooks']")
    private WebElement laptopAndDesktops;

    @FindBy(xpath = "//a[text()='Components']")
    private WebElement components;

    @FindBy(xpath = "//a[text()='Tablets']")
    private WebElement tablets;

    @FindBy(xpath = "//a[text()='Software']")
    private WebElement software;

    @FindBy(xpath = "//a[text()='Phones & PDAs']")
    private WebElement phoneAndPDAs;

    @FindBy(xpath = "//a[text()='Cameras']")
    private WebElement cameras;

    @FindBy(xpath = "//a[text()='MP3 Players']")
    private WebElement mp3Players;


    public Register navigateToRegistrationPage() {

        click(myAccount);
        click(registerLink);
        return new Register(driver);

    }

    public Account.WishList navigateToWishListPage() {

        click(wishList);
        return new Account.WishList(this.driver);
    }

    public Login navigateToLoginPage() {
        click(myAccount);
        click(login);
        return new Login(driver);
    }

    public ShoppingCart navigateToShoppingCartPage() {
        click(myAccount);
        click(shoppingCart);
        return new ShoppingCart(driver);
    }

    public String returnCartItem() {
        return WaitUtils.waitForClickable(driver, cartItems).getText();
    }

    public String returnWishListItem() {

        return WaitUtils.waitForClickable(driver, wishList).getText();

    }

    public boolean isPageLogoDisplayed() {
        return WaitUtils.waitForVisibility(driver, pageLogo).isDisplayed();
    }

    public boolean isMyAccountEnabled() {
        return WaitUtils.waitForVisibility(driver, myAccount).isEnabled();
    }

    public boolean isWishListEnabled() {
        return WaitUtils.waitForVisibility(driver, wishList).isDisplayed();
    }

    public boolean isSearchBarEnabled() {
        return WaitUtils.waitForVisibility(driver, searchBar).isEnabled();
    }

    public boolean isSearchIconEnabled() {
        return WaitUtils.waitForVisibility(driver, searchIcon).isEnabled();
    }

    public String verifyCurrency(String currency) {

        click(currencyDropDown);

        return switch (currency.toLowerCase()) {
            case "pound" -> {
                click(pound);
                yield verifyPound.getText();
            }
            case "dollar" -> {
                click(dollar);
                ;
                yield verifyDollar.getText();
            }
            case "euro" -> {
                click(euro);
                yield verifyEuro.getText();
            }
            default -> throw new RuntimeException("Currency Not Supported ::" + currency);
        };


    }

    private By locateProductByIndex(int productIndex, String productFeature) {
        if (productFeature.equalsIgnoreCase("AddToCart")) {
            return By.xpath("//div[@id='content']//div[@class='row']/div[" + productIndex + "]/div[1]/div[3]//button[1]");
        } else if (productFeature.equalsIgnoreCase("AddToWishList")) {
            return By.xpath("//div[@id='content']//div[@class='row']/div[" + productIndex + "]/div[1]/div[3]//button[@data-original-title='Add to Wish List']");
        } else
            return By.xpath("//div[@id='content']//div[@class='row']/div[" + productIndex + "]/div[1]/div[3]//button[@data-original-title='Compare this Product']");

    }

    private By locateProductByName(String productName, String productFeature) {
        if (productFeature.equalsIgnoreCase("AddToCart")) {
            return By.xpath("//h4/a[contains(text(),'" + productName + "')]/ancestor::div[@class='product-thumb transition']//button//span[contains(text(), 'Add to Cart')]");
        } else if (productFeature.equalsIgnoreCase("AddToWishList")) {
            return By.xpath("//h4/a[contains(text(),'" + productName + "')]/ancestor::div[@class='product-thumb transition']//button[@data-original-title='Add to Wish List']");
        }
        return By.xpath("");
    }

    public void addToCartByIndex(int productIndex) {
        if (productIndex > 4 || productIndex < 1) {
            throw new RuntimeException("Product Index Out Of Range ❌");
        }
        WaitUtils.waitForClickable(driver, this.driver.findElement(locateProductByIndex(productIndex, "AddToCart"))).click();
        TestLog.stepInfo(" ✅ Product at index " + productIndex + " is Successfully Added to Cart");
    }

    public void addToCartByProductName(String productName) {
        try {
            WaitUtils.waitForClickable(driver, this.driver.findElement(locateProductByName(productName, "AddToCart"))).click();
            TestLog.stepInfo(productName + " is Successfully Added to Cart ✅");
        } catch (Exception e) {
            throw new RuntimeException("Product With Name " + productName + " Not Found ❌");
        }

    }

    public void addToWishListByIndex(int productIndex) {
        if (productIndex > 4 || productIndex < 1) {
            throw new RuntimeException("Product Index Out Of Range ❌");
        }
        WaitUtils.waitForClickable(driver, this.driver.findElement(locateProductByIndex(productIndex, "AddToWishList"))).click();
        TestLog.stepInfo("✅Product at index " + productIndex + " is Successfully Added to Wish List");
    }

    public void addToWishListByProductName(String productName) {
        try {
            WaitUtils.waitForClickable(driver, this.driver.findElement(locateProductByName(productName, "AddToWishList"))).click();
            TestLog.stepInfo(productName + " is Successfully Added to Wish List ✅");
        } catch (Exception e) {
            throw new RuntimeException("Product With Name " + productName + " Not Found ❌");
        }

    }

    public Search searchProduct(String productName) {

        enterText(searchBar, productName);
        searchIcon.click();
        TestLog.stepInfo("Search Page Title Is ::::" + this.driver.getTitle());
        return new Search(driver);
    }

    public void validatePages(){

        WaitUtils.waitFor(ExpectedConditions.elementToBeClickable(searchBar),this.driver,5);
    }


}
