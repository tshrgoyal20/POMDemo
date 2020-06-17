package com.crm.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class LoginPageTest extends TestBase {
	
	LoginPage loginPage;
	TestUtil testutil;
	HomePage homepage;
	
	Logger log = Logger.getLogger(LoginPageTest.class);
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeClass
	public void SetUp()
	{
		Initialization();
		loginPage = new LoginPage();
		homepage = new HomePage();
	}
	
	@AfterClass
	public void close()
	{
		TestUtil.Sleep(2000);
		closeBrowser();
	}
	
	@Test(priority=1)
	public void loginPageTitleTest()
	{
		log.info("******** Test Case loginPageTitleTest started ********");
		String title = loginPage.verifyLoginPageTitle();
		Assert.assertEquals(title, "Free CRM #1 cloud software for any business large or small");
		System.out.println("Login Page title is: "+title);
		log.info("******** Test Case loginPageTitleTest end ********");
	}
	
	@Test(priority=2)
	public void signInTest()
	{
		log.info("******** Test Case signInTest started ********");
		loginPage.clickOnLogin();
		//TestUtil.ImplicitWait();
		loginPage.SignIn(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(homepage.verifyCorrectUsername());
		System.out.println("User logged in successfully");
		log.info("******** Test Case signInTest end ********");
	}

}
