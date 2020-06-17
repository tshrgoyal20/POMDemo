package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testutil;
	ContactsPage contactsPage;
	
	public HomePageTest()
	{
		super();
	}
	
	@BeforeClass
	public void SetUp()
	{
		Initialization();
		loginPage = new LoginPage();
		loginPage.clickOnLogin();
		TestUtil.ImplicitWait();
		homePage = loginPage.SignIn(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterClass
	public void close()
	{
		TestUtil.Sleep(3000);
		closeBrowser();
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest()
	{
		String homepagetitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homepagetitle, "Cogmento CRM");
		System.out.println(homepagetitle);
	}
	
	@Test(priority=2)
	public void verifyCorrectUsernameTest()
	{
		Assert.assertTrue(homePage.verifyCorrectUsername());
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest()
	{
		contactsPage = homePage.clickOnContactsLink();
		Assert.assertTrue(contactsPage.verifyContactsLabel());
	}

}
