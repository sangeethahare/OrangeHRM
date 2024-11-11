package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
//constructor
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	//locators
	@FindBy(name="username") WebElement txtUN;
	@FindBy(name="password") WebElement txtPWD;
	@FindBy(xpath="//button[normalize-space()='Login']") WebElement btnlogin;
	
	//Actions
	public void setUserName(String name)
	{
		txtUN.sendKeys(name);
	}
	public void setPassword(String pwd)
	{
		txtPWD.sendKeys(pwd);
	}
	public void click()
	{
		btnlogin.submit();
	}
}
