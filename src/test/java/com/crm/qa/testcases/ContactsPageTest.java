package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testutil;
	ContactsPage contactsPage;
	
	public ContactsPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void SetUp()
	{
		Initialization();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		loginPage.clickOnLogin();
		TestUtil.ImplicitWait();
		homePage = loginPage.SignIn(prop.getProperty("username"), prop.getProperty("password"));
		homePage.clickOnContactsLink();
	}
	
	@AfterMethod
	public void close()
	{
		TestUtil.Sleep(3000);
		closeBrowser();
	}
	
	@Test(priority=1)
	public void verifyContactsLabelTest()
	{
		TestUtil.ImplicitWait();
		Assert.assertTrue(contactsPage.verifyContactsLabel());
	}
	
	@Test(priority=2)
	public void selectContactsTest()
	{
		//driver.findElement(By.xpath("//div[@id='ui']//span[text()='Home']")).click();
		//driver.findElement(By.xpath("//div[@id='ui']//span[text()='Contacts']")).click();
		TestUtil.ImplicitWait();
		contactsPage.selectContactsByName("User 1");
	}
	
	@Test(priority=3)
	public void verifyCreateNewContact()
	{
		contactsPage.clickOnNewContactButton();
		driver.navigate().refresh();
		//contactsPage.ExplicitWait();
		contactsPage.createNewContact("Tushar", "Kumar", "Google", "abc@gamil.com");
	}

}
