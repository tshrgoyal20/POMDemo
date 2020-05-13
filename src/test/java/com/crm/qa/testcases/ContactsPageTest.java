package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.ExcelDataProvider;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testutil;
	ContactsPage contactsPage;
	ExcelDataProvider exceldata;
	
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
		exceldata = new ExcelDataProvider("/home/tushar/eclipse-workspace/CRMPRoject/src/main/"
				+ "java/com/crm/qa/testdata/ExcelData.xlsx");
		TestUtil.ImplicitWait();
		homePage = loginPage.SignIn(prop.getProperty("username"), prop.getProperty("password"));
		homePage.clickOnContactsLink();
	}
	
	@AfterMethod
	public void close()
	{
		TestUtil.Sleep(2000);
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
		TestUtil.ImplicitWait();
		boolean b = contactsPage.selectContactsByName("User 1");
		Assert.assertTrue(b);
	}
	
	@Test(priority=3)
	public void verifyCreateNewContact()
	{
		int rowCount = ExcelDataProvider.sheet.getPhysicalNumberOfRows();
		System.out.println("Total no. of rows: "+rowCount);
		for(int row=1; row<rowCount; row++)
		{
			contactsPage.clickOnNewContactButton();
			System.out.println("Row no. "+row);
			contactsPage.createNewContact(exceldata.getStringData("NewContact", row, 0),
					exceldata.getStringData("NewContact", row, 1), exceldata.getStringData("NewContact", row, 2),
					exceldata.getStringData("NewContact", row, 3));
		
			contactsPage.verifycreatedcontact();
			//Assert.assertEquals(contactsPage.verifycreatedcontact(), "Ram Kumar");
			System.out.println("Created user is: "+contactsPage.verifycreatedcontact());
			homePage.clickOnContactsLink();
			TestUtil.Sleep(1000);
		}
	}
	
	/*@DataProvider
	public Iterator<Object> getTestData()
	{
		ArrayList<Object> testdata = DataDriven.GetData();
		return testdata.iterator();
	}*/

}
