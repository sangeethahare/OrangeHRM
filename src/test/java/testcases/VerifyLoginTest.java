package testcases;

import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObjects.LoginPage;

public class VerifyLoginTest extends BaseTest {

	@Test(groups= {"sanity"})
	public void verify_login()
	{
		logger.info("-------------verify_login testcase started------------");
		//login page
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(p.getProperty("username"));
		lp.setPassword(p.getProperty("password"));
        lp.click();	
        logger.info("------testcase finished--------");
	}

}
