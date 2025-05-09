package com.framework.utils;


import com.framework.reporting.TestLog;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil  {


    public static void captureScreenshot(WebDriver driver, String testName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/src/test/resources/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
        try {
            FileHandler.copy(src, new File(path));
            TestLog.stepInfo("Screenshot saved at: " + path);
        } catch (IOException e) {
            TestLog.stepInfo("Screenshot capture failed: " + e.getMessage());
        }
    }


}
