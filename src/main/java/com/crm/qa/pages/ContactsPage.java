package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {
	
	@FindBy(xpath="//div[@id='dashboard-toolbar']/div[text()='Contacts']")
	WebElement contactsLabel;
	
	@FindBy(xpath="//div[@id='dashboard-toolbar']//button[text()='New']")
	WebElement newContactBtn;
	
	@FindBy(name="first_name")
	WebElement firstName;
	
	@FindBy(name="last_name")
	WebElement lastName;
	
	@FindBy(name="company")
	WebElement companyName;
	
	@FindBy(xpath="//input[@name='value' and @placeholder='Email address']")
	WebElement email;
	
	@FindBy(name="//div[@id='dashboard-toolbar']//button[text()='Save']")
	WebElement saveBtn;
	
	
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
		driver.findElement(By.xpath("//td[text()='"+name+"']//preceding-sibling::td/div")).click();
		//div[@id='dashboard-toolbar']//following-sibling::div[contains(@class,'main-content')]//div//
	}
	
	public void clickOnNewContactButton()
	{
		newContactBtn.click();
	}
	
	public void createNewContact(String fname, String lname, String comname, String emailid)
	{
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		companyName.sendKeys(comname);
		email.sendKeys(emailid);
		saveBtn.click();
	}
	
}
