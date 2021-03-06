package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class ContactsPage extends TestBase {
	
	WebDriverWait wait = new WebDriverWait(driver, 10);

	@FindBy(xpath="//div[@id='dashboard-toolbar']/div[text()='Contacts']")
	WebElement contactsLabel;
	
	@FindBy(xpath="//div[@id='dashboard-toolbar']//button[text()='New']")
	WebElement newContactBtn;
	
	@FindBy(xpath="//div[@class='ui header item mb5 light-black'][text()='Create New Contact']")
	WebElement newContactsLabel;
	
	@FindBy(name="first_name")
	WebElement firstName;
	
	@FindBy(name="last_name")
	WebElement lastName;
	
	@FindBy(xpath="//div[@name='company']/input")
	WebElement companyName;
	
	@FindBy(name="status")
	WebElement status;
	
	@FindBy(xpath="//div[@id='dashboard-toolbar']//button[text()='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//div[@id='dashboard-toolbar']//i[contains(@class,'red')]//parent::div")
	WebElement createduser;
	
	
	public ContactsPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactsLabel()
	{
		return contactsLabel.isDisplayed();
	}
	
	public void selectContactsByName(String name)
	{
		WebElement contactselect = driver.findElement(By.xpath("//td[text()='"+name+"']//preceding-sibling::td/div"));
		contactselect.click();
	}
	
	public void clickOnNewContactButton()
	{
		newContactBtn.click();
	}
	
	public boolean verifyNewContactLabel()
	{
		return newContactsLabel.isDisplayed();
	}
	
	public void createNewContact(String fname, String lname, String comname, String statusValue)
	{
		wait.until(ExpectedConditions.visibilityOf(firstName));
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		companyName.sendKeys(comname);
		status.click();
		TestUtil.Sleep(1000);
		driver.findElement(By.xpath("//div[@name='status']//span[text()='"+statusValue+"']")).click();
		saveBtn.click();
	}
	
	public String verifycreatedcontact()
	{
		
		wait.until(ExpectedConditions.visibilityOf(createduser));
		return createduser.getText();
	}
}
