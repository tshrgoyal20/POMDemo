package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class LoginPageTest extends TestBase {
	
	LoginPage loginPage;
	TestUtil testutil;
	HomePage homepage;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void SetUp()
	{
		Initialization();
		loginPage = new LoginPage();
		homepage = new HomePage();
	}
	
	@AfterMethod
	public void close()
	{
		TestUtil.Sleep(2000);
		closeBrowser();
	}
	
	@Test(priority=1)
	public void loginPageTitleTest()
	{
		String title = loginPage.verifyLoginPageTitle();
		Assert.assertEquals(title, "Free CRM #1 cloud software for any business large or small");
		System.out.println("Login Page title is: "+title);
	}
	
	@Test(priority=2)
	public void signInTest()
	{
		loginPage.clickOnLogin();
		TestUtil.ImplicitWait();
		loginPage.SignIn(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(homepage.verifyCorrectUsername());
	}

}
