package com.crm.qa.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.crm.qa.base.TestBase;

public class ExtentReportNG extends TestBase {
	
	static ExtentReports report;
	
	public static ExtentReports getReport()
	{
		String path = System.getProperty("user.dir")+"/Reports/index.html";
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		spark.config().setReportName("CRMProject Automation Report");
		spark.config().setDocumentTitle("CRMProject Extent Report");
		
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Tester", "Tushar");
		return report;
	}

}
