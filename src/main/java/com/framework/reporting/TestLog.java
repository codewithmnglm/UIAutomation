package com.framework.reporting;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;

import java.awt.*;

public class TestLog {

    static Logger logger = Logger.getLogger(TestLog.class.getName());

    static {
        //PropertyConfigurator.configure("src/main/resources/log4j.properties");

        PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/main/resources/log4j.properties");
    }

    public static void stepInfo(String message) {
        logger.info(message);
        Reporter.log(message);
    }

    public static void stepInfoInGreenColor(String message) {
        String foncolor = Color.GREEN.toString();
        logger.info(message);
        Reporter.log("<font  size='+2' color='green'>" + message + "</font>");
    }

    public static void testFail(String message) {
        logger.error(message);
    }

    public static void testPass(String message) {
        logger.info(message);
    }

    public static void testStart(String message, String desc) {
        logger.info(message);
    }
}
