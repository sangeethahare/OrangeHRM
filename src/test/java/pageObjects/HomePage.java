package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
//constructor
	public HomePage(WebDriver driver) 
	{
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//title[text()='OrangeHRM']") WebElement title;
	@FindBy(xpath="//div[contains(@class,'userarea')]//p") WebElement adminTest;
	@FindBy(xpath="//a[text()='Logout']") WebElement loginlnk;
	//Actions
	public String getTitle()
	{
		String text=title.getText();
		return text;
	}
	public void clickLogout()
	{
		adminTest.click();
		loginlnk.click();
	}
}
