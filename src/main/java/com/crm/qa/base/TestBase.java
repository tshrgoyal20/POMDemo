package com.crm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.crm.qa.util.ExcelDataProvider;
import com.crm.qa.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static ExcelDataProvider exceldata;
	/*ExtentSparkReporter htmlreporter;
	public static ExtentReports extent;
	public static ExtentTest extenttest;*/
	
	public TestBase() {
		
		prop = new Properties();
		
		try {
			FileInputStream ip = new FileInputStream("/home/tushar/eclipse-workspace/CRMPRoject/src/main/java/com/"
					+ "crm/qa/config/config.properties");
			prop.load(ip);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void Initialization()
	{
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.silentOutput", "true");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,".\\CRMPRoject\\log.txt");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("Please enter valid browser like chrome or firefox");
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
	
	public static void closeBrowser()
	{
		driver.close();
	}
	
	public void failed(String testMethodName)
	{
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("./Screenshots/"+testMethodName+".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*public void setExtent()
	{
		htmlreporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/MyReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
	}
	
	public void endReport()
	{
		extent.flush();
	}*/

}
