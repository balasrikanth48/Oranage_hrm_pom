package commonFunctionLibrary;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utilities.Propertyfile;

public class FunctionLibrary {
	static WebDriver driver;
	
	public  static WebDriver startbrowser() throws IOException
	{
		
		if (Propertyfile.getValueForKey("Browser").equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "E:\\Srikanth_82\\Orangehrm_POM\\Drivers\\chromedriver.exe");
		    driver=new ChromeDriver();
			driver.manage().window().maximize();
		}else if(Propertyfile.getValueForKey("Browser").equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.chrome.driver", "E:\\Srikanth_82\\Orangehrm_POM\\Drivers\\chromedriver.exe");
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
		}else
		{
			System.out.println("Invalid browser");
		}
		return driver;
	}
	
	
	public static  void openapplication() throws IOException{
		
		driver.get(Propertyfile.getValueForKey("Url"));
	
	}
	
	public static void closeapplication()
	{
		driver.close();
	}


	public static String generateDate() {
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_DD_hh_mm_ss");
		String req=sdf.format(d);
		System.out.println(req);
		return req;
	}

}
