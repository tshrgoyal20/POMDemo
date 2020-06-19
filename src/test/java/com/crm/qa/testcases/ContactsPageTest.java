package com.crm.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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
	String s;
	public ContactsPageTest()
	{
		super();
	}
	
	@BeforeClass
	public void SetUp()
	{
		Initialization();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		loginPage.clickOnLogin();
		exceldata = new ExcelDataProvider("/home/tushar/eclipse-workspace/CRMPRoject/src/main/"
				+ "java/com/crm/qa/testdata/ExcelData.xlsx", "NewContact");
		homePage = loginPage.SignIn(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterClass
	public void close()
	{
		TestUtil.Sleep(2000);
		closeBrowser();
	}
	
	@Test(priority=1)
	public void verifyContactsLabelTest()
	{
		homePage.clickOnContactsLink();
		Assert.assertTrue(contactsPage.verifyContactsLabel());
	}
	
	@Test(priority=2)
	public void selectContactsTest()
	{
		contactsPage.selectContactsByName("User 1");
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'checked')]")));
		if(driver.findElement(By.xpath("//div[contains(@class,'checked')]")).getAttribute("class").contains("checked"))
		{
			System.out.println("User checkbox has been selected");
			Assert.assertTrue(true);
		}
		else
		{
			System.out.println("Checkbox is unselected");
		}
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
			contactsPage.createNewContact(exceldata.getStringData(row, 0),
					exceldata.getStringData(row, 1), exceldata.getStringData(row, 2),
					exceldata.getStringData(row, 3));
		
			contactsPage.verifycreatedcontact();
			//Assert.assertEquals(contactsPage.verifycreatedcontact(), "Ram Kumar");
			System.out.println("Created user is: "+contactsPage.verifycreatedcontact());
			homePage.clickOnContactsLink();
			TestUtil.Sleep(1000);
		}
	}

}
