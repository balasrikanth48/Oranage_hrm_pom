package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Excelutils;

public class LoginPage {
	@FindBy(id="txtUsername")
	public WebElement username;
	 @FindBy(id="txtPassword")
	 public WebElement password;
	 @FindBy(id="btnLogin")
	 public WebElement login;
	 
	 WebDriver driver;
	 
	 
	 public LoginPage(WebDriver driver){
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
//		 PageFactory.initElements(driver, this);
		 
	 }
	 
	public void Login(Excelutils exl,String tcid)
	{
		int testcaserow=exl.getTestCaseRow(tcid);
		String uname=exl.getData("TestData", testcaserow, 1);
		String pwd=exl.getData("TestData", testcaserow, 2);
		username.sendKeys(uname);
		password.sendKeys(pwd);
		login.click();
		
	}
	

}
