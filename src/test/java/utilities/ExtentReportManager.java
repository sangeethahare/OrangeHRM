package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseTest.BaseTest;

public class ExtentReportManager implements ITestListener{
public ExtentSparkReporter sparkreporter;
public ExtentReports reports;
public ExtentTest test;
String repName;


	public void onStart(ITestContext context) {
		String timeStamp=new SimpleDateFormat("YYYY.MM.DD.HH.MM.ss").format(new Date());
		repName="Test-Report"+timeStamp+".html";
		
		sparkreporter=new ExtentSparkReporter(".//Reports//"+repName);
		sparkreporter.config().setDocumentTitle("OrangeHRMTestReport");
		sparkreporter.config().setReportName("funtional testing report");
		sparkreporter.config().setTheme(Theme.STANDARD);
		reports=new ExtentReports();
		reports.attachReporter(sparkreporter);
		reports.setSystemInfo("Application","OrangeHRM");
		reports.setSystemInfo("Module","Admin");
		reports.setSystemInfo("Username",System.getProperty("user.name"));
	String	browser=context.getCurrentXmlTest().getParameter("browser");
	reports.setSystemInfo("Browser", browser);
	List<String> include = context.getCurrentXmlTest().getIncludedGroups();
	for(String group:include)
	{
		if(!group.isEmpty())
		{
			reports.setSystemInfo("Groups",group);
		}
	}
		
		
		
		
		
		
		
	}

	public void onTestStart(ITestResult result) {
	
		test=reports.createTest(result.getClass().getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.log(Status.PASS,result.getName()+"got successfully executed");
      
	}

	public void onTestFailure(ITestResult result) {
		test=reports.createTest(result.getClass().getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.log(Status.FAIL,result.getName()+"Failed");
    	test.log(Status.INFO,result.getThrowable().getMessage());
    	try {
    	String imagepath =new BaseTest().captureScreen(result.getName());
    	test.addScreenCaptureFromPath(imagepath);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
      
	}

	public void onTestSkipped(ITestResult result) {
	
		test=reports.createTest(result.getClass().getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.log(Status.SKIP,result.getName()+"Skipped");
      
	}

	

	public void onFinish(ITestContext context) {
		
		reports.flush();
		String extentReportpath = System.getProperty("user.dir")+".\\Reports\\"+repName;
    	File extentReport=new File(extentReportpath);
    	try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
