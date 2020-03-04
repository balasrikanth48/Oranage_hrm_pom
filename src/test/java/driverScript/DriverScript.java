package driverScript;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctionLibrary.FunctionLibrary;
import pages.HomePage;
import pages.LoginPage;
import utilities.Excelutils;

public class DriverScript {

	
	 static WebDriver driver;
	 static ExtentReports report;
	 static ExtentTest test = null;
	 static String status=null;
		static HomePage hp;
	
	public static void main(String[] args) throws Exception
	{
		  Excelutils ex=new Excelutils();
		
		
		for (int i = 1; i <=ex.getrowcount("MasterTestCases"); i++)
		{
			if(ex.getData("MasterTestCases", i, 3).equalsIgnoreCase("Y"))
			{
				String testcaseid_mastersheet=ex.getData("MasterTestCases", i,0);
				String testcasename=ex.getData("MasterTestCases", i, 2);
				report=new ExtentReports("E:\\Srikanth_82\\Orangehrm_POM\\Reports\\"+testcasename+"_"+FunctionLibrary.generateDate()+".html");
				
				for (int j = 1; j <=ex.getrowcount(testcasename); j++) 
				{
					String StepDescription=ex.getData(testcasename,j,1);
					System.out.println("Description  :"+StepDescription);
					String FunctionName=ex.getData(testcasename, j, 2);
					test=report.startTest(testcasename);
				try{
					if (FunctionName.equalsIgnoreCase("startbrowser")) 
					{
						driver=FunctionLibrary.startbrowser();
						test.log(LogStatus.INFO, StepDescription);
						
					}else if(FunctionName.equalsIgnoreCase("openapplication"))
					{
					FunctionLibrary.openapplication();
					test.log(LogStatus.INFO, StepDescription);
					}else if(FunctionName.equalsIgnoreCase("login"))
					{
						LoginPage lp=new LoginPage(driver);
						lp.Login(ex, testcaseid_mastersheet);
						test.log(LogStatus.INFO, StepDescription);
					}else if(FunctionName.equalsIgnoreCase("logout"))
					{
						hp=new HomePage(driver);
						hp.Logout();
						test.log(LogStatus.INFO, StepDescription);
					}else if (FunctionName.equalsIgnoreCase("closeapplication")) 
					{
					FunctionLibrary.closeapplication();
					test.log(LogStatus.INFO, StepDescription);
				    }else if(FunctionName.equalsIgnoreCase("userReg"))
				    {
				    	hp=new HomePage(driver);
				    	hp.EmpREgistration(ex, testcaseid_mastersheet);
				    	test.log(LogStatus.INFO, StepDescription);
				    }else if(FunctionName.equalsIgnoreCase("empReg"))
				    { 
				    	hp=new HomePage(driver);
				    	hp.AddUserRegistration(ex, testcaseid_mastersheet);
				    	test.log(LogStatus.INFO, StepDescription);
				    }else if (FunctionName.equalsIgnoreCase("capturedata")) {
				    	hp=new HomePage(driver);
				    	hp.capturedata();
				    	test.log(LogStatus.INFO, StepDescription);
					}else if (FunctionName.equalsIgnoreCase("tablevalidation"))
					{
						hp=new HomePage(driver);
						hp.tablevalidation(driver);
						test.log(LogStatus.INFO, StepDescription);
					}
					
					ex.SetCellData(testcasename, j, 3, "PASS");
					status="PASS";
				}catch(Exception e){
						e.printStackTrace();	
					  status="FAIL";
					 ex.SetCellData(testcasename, j, 3, "FAIL");
					 test.log(LogStatus.FAIL, testcasename);	
					 File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					 FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"\\Screenshots\\"+StepDescription+FunctionLibrary.generateDate()+".png"));
					 break;	
					}
				}
				 report.endTest(test);
				 report.flush();
				 if(status.equalsIgnoreCase("Pass"))
				 {
					 ex.SetCellData("MasterTestCases", i, 4, "Pass");
				 }
				 else{
					 ex.SetCellData("MasterTestCases", i, 4, "Fail");
				 }
			}else
			{
				ex.SetCellData("MasterTestCases", i, 4, "NotExcuted");
			}	
		}
	}

}
