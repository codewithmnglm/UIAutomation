package com.framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.*;

import java.time.Duration;

import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 50;


    public static WebElement waitForVisibility(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement fluentWait(WebDriver driver, By locator, int timeoutSec, int pollingSec) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSec))
                .pollingEvery(Duration.ofSeconds(pollingSec))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return wait.until(webDriver -> webDriver.findElement(locator)); // avoid shadowing 'driver'
    }

    public static <T> T waitFor(ExpectedCondition<T> condition, WebDriver driver, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(condition);
    }

}
