package baseTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
	 static public  WebDriver driver;
	public Properties p;
	public Logger logger;
	@BeforeClass(groups= {"sanity"})
	@Parameters ({"browser"})
	public void setUp(String br) throws IOException
	{
		logger=LogManager.getLogger(this.getClass());
		
		FileReader fr=new FileReader(".\\src\\test\\resources\\commondata.properties");
				
		p=new Properties();
		p.load(fr);
		switch(br.toLowerCase()) {
		case "chrome":driver=new ChromeDriver();break;
		case "firefox":driver=new FirefoxDriver();break;
		case "edge":driver=new EdgeDriver();break;
		default: System.out.println("invalid browser");
		return;
		
		
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("url"));
		driver.manage().window().maximize();
	}
	@AfterClass(groups= {"sanity"})
	public void tearDown() throws InterruptedException
	{Thread.sleep(2000);
		driver.quit();
	}
public String captureScreen(String tname)
{
	String timestamp=new SimpleDateFormat("YYYY.MM.DD.HH.mm.ss").format(new Date());
	TakesScreenshot ts=(TakesScreenshot)driver;
	File sourefile = ts.getScreenshotAs(OutputType.FILE);
	String targetpath = System.getProperty("user.dir")+".//Screenshots//"+timestamp;
	File targetfile=new File(targetpath);
	sourefile.renameTo(targetfile);
	return targetpath;
	}

}

