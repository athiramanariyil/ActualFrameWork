package RahulShettyAcademy.SeleniumFrameworkDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class cartPage  extends reUsables{
	WebDriver driver;
	public cartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement CartSymbol;
	
	
	@FindBy(xpath="//h1[contains(text(),'My Cart')]")
	WebElement MyCart;
	
	
	public void ClickOnCart() {
	waiting(CartSymbol);
	CartSymbol.click();
	System.out.println("clciked on cart");
	
	}
	public WebElement VerifyCart(String item) {
		waiting(MyCart);
		//By product = By.xpath("//h3[contains(text(),'"+item+"')]");
		By product = By.xpath(
			    "//h3[contains(translate(text(), " + "'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), '" + item.toUpperCase() + "')]"
			);
		waitingPresence(product);
		System.out.println("verified cart"+item);
		return driver.findElement(product);		
	}
	
	
	
	
}
