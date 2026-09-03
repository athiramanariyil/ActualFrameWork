package RahulShettyAcademy.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Products extends reUsables{

	 WebDriver driver;

	public Products(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	String expectedMsg="Product added to cart";
	@FindBy(css=".col-lg-4")
	List<WebElement> pdts;
	
	@FindBy(xpath="//ul/li[3]")
	WebElement productsTab;
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> productColumn;
	
	@FindBy(xpath="//h1[(text()=\"Your Orders\")]")
	WebElement ProductHeader;
	//String item;
	
	
	
	By products= By.cssSelector(".text-muted.m-2");
	By toDisappear = By.xpath("//h3[contains(text(),'ZARA COAT 3')]");
	
	public String ProductsPage(String Item) {
		waitingLocator(products);
		WebElement Mypdt=pdts.stream().filter(s->s.getText().contains(Item)).findFirst().orElseThrow();
		Mypdt.findElement(By.cssSelector(".w-10")).click();
		String text=wait.until(d->{ String t=d.findElement(By.id("toast-container")).getText();
			return (t.equalsIgnoreCase(expectedMsg) ? t : null);
					});
		return text;
	}
	
	public Boolean ProductsTab(String Item) {
		waiting(productsTab);
		productsTab.click();
		waiting(ProductHeader);
		Boolean Match = productColumn.stream()
		        .anyMatch(s -> s.getText().contains(Item));
		return Match;
		
	}
	
}
