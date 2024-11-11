package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.DataProviders1;

public class DDT_VerifyLogin extends BaseTest {
	@Test(dataProvider="login",dataProviderClass=DataProviders1.class)
	public void verifyLogin(String name,String pwd)
	{
		try {
		LoginPage lp=new LoginPage(driver);
		lp.setUserName("name");
		lp.setPassword("pwd");
		HomePage hp=new HomePage(driver);
		
		Assert.assertEquals(hp.getTitle(), "OrangeHRM");
		
		
		
		}
		catch(Exception e)
		{Assert.fail();
		}
		HomePage hp=new HomePage(driver);
	
		hp.clickLogout();
		
	}

}
