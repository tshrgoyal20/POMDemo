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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.crm.qa.util.ExcelDataProvider;
import com.crm.qa.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static ExcelDataProvider exceldata;
	
	public TestBase() {
		
		prop = new Properties();
		
		try {
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/"
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
		//String browserName = System.getProperty("browser");
		String browserName = prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			System.setProperty("webdriver.chrome.silentOutput", "true");
			ChromeOptions option = new ChromeOptions();
			if(browserName.contains("headless"))
			{
				option.addArguments("headless");
			}
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(option);
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
		TestUtil.PageLoadTimeOut();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
	
	public static void closeBrowser()
	{
		driver.close();
	}
	
	public String failed(String testMethodName)
	{
		String path = "./Screenshots/"+testMethodName+".jpg";
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
