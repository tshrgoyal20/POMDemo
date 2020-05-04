package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	@FindBy(xpath="//a/span[text()='Log In']")
	WebElement Login;
	
	@FindBy(name="email")
	WebElement emailid;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//div[text()='Login']")
	WebElement LoginBtn;
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String verifyLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	public void clickOnLogin()
	{
		Login.click();
	}
	
	public HomePage SignIn(String uname, String pswd)
	{
		emailid.sendKeys(uname);
		password.sendKeys(pswd);
		LoginBtn.click();
		return new HomePage();
	}

}
