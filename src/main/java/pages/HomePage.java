package pages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.Excelutils;

public class HomePage {
	
	
	@FindBy(id="welcome")
	 public WebElement welcome;
	
	@FindBy(linkText="Logout")
	public WebElement Logout;
	
	@FindBy(id="menu_pim_viewPimModule")
	public WebElement PIM;
	@FindBy(id="menu_pim_viewEmployeeList")
	public WebElement EmpList;
	
	@FindBy(id="menu_admin_viewAdminModule")
	public WebElement Admin;
	@FindBy(id="menu_admin_UserManagement")
	public WebElement UserManagemnet;
	@FindBy(id="menu_admin_viewSystemUsers")
	public WebElement user_admin;
	@FindBy(id="systemUser_employeeName_empName")
	public WebElement Emplpyee_name;
	@FindBy(id="systemUser_userName")
	public WebElement systemUser_userName;
	@FindBy(id="systemUser_password")
	public WebElement systemUser_password;
	@FindBy(id="systemUser_confirmPassword")
	public WebElement systemUser_confirmPassword;
	@FindBy(id="btnSave")
	public WebElement btnSave_Admin;
	@FindBy(id="btnAdd")
	public WebElement btnAdd;
	
	@FindBy(id="empsearch_id")
	public WebElement Empsearch_id;
	@FindBy(id="searchBtn")
	public WebElement Empsearch_button;
	
	@FindBy(id="menu_pim_addEmployee")
	public WebElement AddEmp;
	
	@FindBy(id="firstName")
	public WebElement firstname;
	@FindBy(id="lastName")
	public WebElement lastname;
	@FindBy(name="personal[txtEmployeeId]")
	public WebElement Emmployeeid;
	@FindBy(id="btnSave")
	public WebElement btnSave;
	@FindBy(id="empsearch_id")
	public WebElement Empsearch;
	@FindBy(id="searchBtn")
	public WebElement searchBtn;
	
	
	
	@FindBy(id="resultTable")
	public WebElement table;
	@FindBy(id="search_form")
	public WebElement search_form;
	
	
	 WebDriver driver;
	 
	 public HomePage(WebDriver driver)
	 {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 public void Logout() throws InterruptedException
	 {
		 Thread.sleep(2000);
		 welcome.click();
		 Thread.sleep(2000);
		 Logout.click();
	 }
	
	 public void EmpREgistration(Excelutils exlu,String tcid) throws Exception
	 {
		 int testcaserow=exlu.getTestCaseRow(tcid);
		 String fstname=exlu.getData("TestData", testcaserow, 5);
		 String lsname=exlu.getData("TestData", testcaserow, 6);
		 PIM.click();
		 Thread.sleep(2000);
		 AddEmp.click();
		 Thread.sleep(2000);
		 firstname.sendKeys(fstname);
		 lastname.sendKeys(lsname);
		 btnSave.click();
		 
	 }
	 public void AddUserRegistration(Excelutils exlu,String tcid) throws Exception
	 {
		 int testcaserow=exlu.getTestCaseRow(tcid);
		 String emp_name=exlu.getData("TestData", testcaserow, 5);
		 String Uname=exlu.getData("TestData", testcaserow, 8);
		 String pwd=exlu.getData("TestData", testcaserow, 7);
		 
		 
		 Admin.click();
		 Thread.sleep(2000);
		 btnAdd.click();
		 Thread.sleep(2000);
		 Emplpyee_name.sendKeys(emp_name);
		 systemUser_userName.sendKeys(Uname);
		 systemUser_password.sendKeys(pwd);
		 systemUser_confirmPassword.sendKeys(pwd);
		 Thread.sleep(2000);
		 btnSave_Admin.click();
		 
	 }
	 
	 public void capturedata() throws IOException
	 {
		 System.out.println("entering capture data");
		 String Emp_id="";
		 Emp_id=Emmployeeid.getAttribute("value");
		 System.out.println("exp data"+ Emp_id);
		 FileWriter fr=new FileWriter("E:\\Srikanth_82\\Orangehrm_POM\\CaptureData\\Emp_id.txt");
		 BufferedWriter br=new BufferedWriter(fr);
		 br.write(Emp_id);
		 br.flush();
		 br.close();
		 
		 
	 }
	 public void tablevalidation(WebDriver driver) throws InterruptedException, IOException
	 {
		 FileReader fr=new FileReader("E:\\Srikanth_82\\Orangehrm_POM\\CaptureData\\Emp_id.txt");
		 BufferedReader br=new BufferedReader(fr);
		 String exp_data=br.readLine();
		 
		 
		 PIM.click();
		 Thread.sleep(2000);
		 EmpList.click();
		 Thread.sleep(2000);
		 Empsearch.sendKeys(exp_data);
		 searchBtn.click();
		    List<WebElement> rows=table.findElements(By.tagName("tr"));
		    System.out.println("Rows to be validation  :"+ rows);
//		    System.out.println("column no is  :"+column);
			String Act_data=driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a")).getText(); 
			Assert.assertEquals(Act_data, exp_data);
			System.out.println(Act_data+" "+exp_data);
		 
	 }


}
