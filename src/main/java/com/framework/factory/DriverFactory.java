package com.framework.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverFactory {


    public static WebDriver getDriver(String driverName) {
        WebDriver driver;

        switch (driverName.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "/Users/kumar.mangalam/Downloads/chromedriver-mac-x64/chromedriver");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.addArguments("--disable-extensions");  // Disable extensions
                chromeOptions.addArguments("--no-sandbox");         // Bypass OS security model
                chromeOptions.addArguments("--disable-dev-shm-usage");
                //chromeOptions.addArguments("--headless");

                ChromeDriverService service = new ChromeDriverService.Builder()
                        .withLogOutput(System.out) // Send logs to console again
                        .build();
                driver = new ChromeDriver(service, chromeOptions);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;

            default:
                throw new RuntimeException("Driver Not Supported " + driverName);


        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return driver;
    }

}
