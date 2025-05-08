package com.framework.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer  implements IRetryAnalyzer {


    private int retryCount = 0;
    private static final int maxRetry = 2;

    public boolean retry(ITestResult iTestResult){

        if(retryCount<maxRetry){
            retryCount++;
            return true;
        }
        return false;

    }
}
