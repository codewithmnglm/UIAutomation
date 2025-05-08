package com.framework.listener;

import org.testng.ITestListener;

import org.testng.ITestResult;

public class CustomListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());

    }


    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
    }
}
