package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	
	@FindBy(id="welcome")
	 public WebElement welcome;
	
	@FindBy(linkText="Logout")
	public WebElement Logout;
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
	

}
