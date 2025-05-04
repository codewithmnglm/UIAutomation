package com.framework.base;

import com.framework.factory.Constant;
import com.framework.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Currency;
import java.util.List;
import java.util.Map;

public class CommonMethods {

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

    //return sorted ascending or descending list
    public static <T extends Comparable<T>> boolean isSorted(List<T> list, boolean ascending) {
        for (int i = 0; i < list.size() - 1; i++) {
            int cmp = list.get(i).compareTo(list.get(i + 1));
            if ((ascending && cmp > 0) || (!ascending && cmp < 0)) {
                return false;
            }
        }
        return true;
    }



}
