package com.crm.qa.base;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.crm.qa.util.ExtentReportNG;

public class CustomListener extends TestBase implements ITestListener {
	
	ExtentTest test;
	ExtentReports report = ExtentReportNG.getReport();
	
	public void onTestStart(ITestResult result)
	{
		test = report.createTest(result.getMethod().getMethodName());
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		System.out.println("Failed Test");
		test.fail(result.getThrowable());
		String MethodName = result.getMethod().getMethodName();
		failed(MethodName);
		try {
			test.addScreenCaptureFromPath(failed(MethodName), MethodName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test.log(Status.SKIP, "Test Skipped");
	}

	public void onFinish(ITestContext context)
	{
		report.flush();
	}

}
