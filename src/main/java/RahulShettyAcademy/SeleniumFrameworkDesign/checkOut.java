package RahulShettyAcademy.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class checkOut extends reUsables{
	
	WebDriver driver;
	public checkOut(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkOut;
	@FindBy(css=".payment__title:nth-child(1)")
	WebElement titleOfCartPage;
	@FindBy(css="input[placeholder='Select Country']")
	WebElement selectCountry;
	
	public void btnCheckout() {
		waiting(checkOut);
		checkOut.click();
		waiting(titleOfCartPage);
		selectCountry.sendKeys("Ind");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-item.list-group-item.ng-star-inserted")));
		List<WebElement> SuggestedCountries = driver.findElements(By.cssSelector(".ta-item.list-group-item.ng-star-inserted"));
						SuggestedCountries.stream()
						.filter(s->s.getText()
						.equalsIgnoreCase("India")).findFirst().orElseThrow().click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ta-item.list-group-item.ng-star-inserted")));
		driver.findElement(By.cssSelector("a.action__submit ")).click();
		
	}
	
	public WebElement thnks() {
		WebElement thnks= driver.findElement(By.cssSelector(".hero-primary"));
		return thnks;

	}
}
