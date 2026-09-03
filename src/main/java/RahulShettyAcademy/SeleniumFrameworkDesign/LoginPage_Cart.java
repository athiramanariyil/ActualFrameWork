package RahulShettyAcademy.SeleniumFrameworkDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class LoginPage_Cart  extends reUsables{

	 WebDriver driver;

	public LoginPage_Cart(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="userEmail")
	WebElement Userin;
	@FindBy(id="userPassword")
	WebElement Passin;
	@FindBy(id="login")
	WebElement loginBtnin;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement error;
	
	
	
	
	
	
	public void getTheWebSite(String URL) {
		driver.get(URL);
		driver.manage().window().maximize();
		System.out.println("done from gettingwebsite");
	
	}
	public Products enterCred(String User , String Pass) {
		waiting(Userin);
		System.out.println("i see the input fields");

		Userin.sendKeys(User);
		Passin.sendKeys(Pass);
		loginBtnin.click();
		Products pdt = new Products(driver);
		return pdt;
	}
	 public String getErrorMsg() {
		 waiting(error);
		 return error.getText();
		 
	 }
	
}

