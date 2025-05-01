package com.framework.pages;

import com.framework.base.BasePage;

import com.framework.utils.WaitUtils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Search extends BasePage {

    public Search(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='input-search']")
    private WebElement searchCriteria;

    @FindBy(xpath = "//input[@id='button-search']")
    private WebElement searchBtn;

    @FindBy(xpath = "//button[@id='list-view']")
    private WebElement listView;

    @FindBy(xpath = "//button[@id='grid-view']")
    private WebElement gridView;

    @FindBy(xpath = "//select[@id='input-sort']")
    private WebElement sortBy;

    @FindBy(xpath = "//select[@name='category_id']")
    private WebElement searchCategory;

    @FindBy(xpath="//p[text()='There is no product that matches the search criteria.']")
    private WebElement noProductFound;

    @Getter
    @FindBy(xpath = "//div[@id='content']/div[3]/div")
    private List<WebElement> noOfProduct;



    public String getProductName(int productIndex) {

        String productName = "//div[@id='content']/div[3]/div[" + productIndex + "]//div[2]//a";

        return WaitUtils.waitForVisibility(driver, driver.findElement(By.xpath(productName))).getText();

    }

    public void addToCart(int productIndex) {

        String addToCart = "//div[@id='content']/div[3]/div[" + productIndex + "]//div[2]//div[2]//span[contains(text(),'Add to Cart')]";

        WaitUtils.waitForVisibility(driver, driver.findElement(By.xpath(addToCart))).click();

    }

    public void addToWishList(int productIndex) {

        try{
            String addToWishList = "//div[@id='content']/div[3]/div[" + productIndex + "]//div[2]//div[2]//button[contains(@data-original-title,'Add to Wish List')]";
            WaitUtils.waitForVisibility(driver, driver.findElement(By.xpath(addToWishList))).click();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Product At Index " + productIndex + " Not Found ❌");
        }

    }

    public String getProductPrice(int productIndex) {

        String price = "//div[@id='content']/div[3]/div[" + productIndex + "]//div[2]//div[1]//p[2][@class='price']";

        return WaitUtils.waitForVisibility(driver, driver.findElement(By.xpath(price))).getText();
    }

    public String getProductFeature(int productIndex) {

        String productFeature = "//div[@id='content']/div[3]/div[" + productIndex + "]//div[2]//div[1]/p[1]";

        return WaitUtils.waitForVisibility(driver, driver.findElement(By.xpath(productFeature))).getText();
    }

    public void getGridView() {

        WaitUtils.waitForVisibility(driver, gridView).click();
    }

    public void getListView() {

        WaitUtils.waitForVisibility(driver, listView).click();
    }

    public boolean isNoProductFound(){

        return (WaitUtils.waitForVisibility(this.driver,noProductFound).isDisplayed()) ;
    }


    public void sortProductByPriceHighToLow(){

        searchFromDropdownByText(sortBy,"Price (High > Low)");
    }

    public void sortProductByPriceLowToHigh(){

        searchFromDropdownByText(sortBy,"Price (Low > High)");
    }

    public void sortProductByRatingLowToHigh(){

        searchFromDropdownByText(sortBy,"Rating (Lowest)");
    }

    public void sortProductByRatingHighToLow(){

        searchFromDropdownByText(sortBy,"Rating (Highest)");
    }

    public void sortProductByNameA2Z(){

        searchFromDropdownByText(sortBy,"Name (A - Z)");
    }

    public void sortProductByNameZ2A(){

        searchFromDropdownByText(sortBy,"Name (Z - A)");
    }




}
