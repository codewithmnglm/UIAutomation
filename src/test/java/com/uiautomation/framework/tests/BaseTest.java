package com.uiautomation.framework.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.uiautomation.framework.factory.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;


    @BeforeTest
    public void setUpDriver() {
       System.setProperty("webdriver.chrome.driver", "/Users/kumar.mangalam/Downloads/chromedriver-mac-x64/chromedriver");


        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.addArguments("--disable-extensions");  // Disable extensions
        chromeOptions.addArguments("--no-sandbox");         // Bypass OS security model
        chromeOptions.addArguments("--disable-dev-shm-usage");

        ChromeDriverService service = new ChromeDriverService.Builder()
                .withLogOutput(System.out) // Send logs to console again
                .build();
       // this.driver = DriverFactory.getDriver("chrome");
        this.driver = new ChromeDriver(service,chromeOptions);
        System.out.println("Chrome Driver Set Up Completed");

        this.driver=DriverFactory.getDriver("chrome");
       // TestLog.stepInfo("Chrome Driver Set Up Completed");

    }

    @AfterTest
    public void quitDriver() {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        this.driver.quit();
        System.out.println("All Drivers Closed");

    }
}
