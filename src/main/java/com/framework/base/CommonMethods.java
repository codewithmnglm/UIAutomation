package com.framework.base;

import com.framework.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonMethods {

    public boolean isDisplayed(WebDriver driver, WebElement wb) {

        return WaitUtils.waitForVisibility(driver, wb).isDisplayed();
    }

    public boolean isEnabled(WebDriver driver, WebElement wb) {

        return WaitUtils.waitForVisibility(driver, wb).isEnabled();
    }

    public boolean isSelected(WebDriver driver, WebElement wb) {

        return WaitUtils.waitForVisibility(driver, wb).isSelected();
    }

}
