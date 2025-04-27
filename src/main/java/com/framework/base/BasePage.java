package com.framework.base;

import com.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    protected WebDriver driver;
    public String product = "2";

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void click(WebElement element) {
        String attribute = element.getAttribute("class");
        if(attribute.contains("hidden")){
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.display='block';", element);
            element.click();
        }
        else WaitUtils.waitForClickable(driver, element).click();

    }

    public void click(By locator, int timeout, int polling) {
        WaitUtils.fluentWait(driver, locator, 10, 1).click();
    }

    public void clear(WebElement element) {
        WaitUtils.waitForClickable(driver, element).clear();
    }


    public void type(WebElement element, String text) {
        clear(element);
        element.sendKeys(text);

    }

    public String getTitle(WebDriver driver) {
        return driver.getTitle();
    }


}
