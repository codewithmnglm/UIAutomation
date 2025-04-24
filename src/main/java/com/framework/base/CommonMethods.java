package com.framework.base;

import com.framework.factory.Constant;
import com.framework.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Currency;
import java.util.Map;

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

    public int returnProductIndex(String productName) {

        Map<String, Integer> map = Map.ofEntries(
                Map.entry("MacBook", 1),
                Map.entry("iPhone", 2),
                Map.entry("Apple Cinema 30", 3),
                Map.entry("Canon EOS 5D", 4)
        );

        return map.get(productName);
    }

    public static String removeCurrencySymbols(String input) {
        if (input.contains(Constant.CURRENCY_DOLLAR))
            return input.replaceAll("[$,]", "");
        else if (input.contains(Constant.CURRENCY_EURO))
            return input.replaceAll("[€,]", "");
        else if (input.contains(Constant.CURRENCY_POUND)) {
            return input.replaceAll("[£,]", "");

        }
        return "";

    }
}
