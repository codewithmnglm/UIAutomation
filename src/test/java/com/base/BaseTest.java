package com.base;

import com.framework.reporting.TestLog;
import com.google.common.util.concurrent.Uninterruptibles;
import com.framework.factory.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;


    @BeforeTest
    public void setUpDriver() {
        this.driver = DriverFactory.getDriver("firefox"); //Get The Name of Browser from either config files or as JENKINS Input
        TestLog.stepInfo("Chrome Driver Set Up Completed");


    }

    @AfterTest
    public void quitDriver() {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        this.driver.quit();
        System.out.println("All Drivers Closed");

    }
}
