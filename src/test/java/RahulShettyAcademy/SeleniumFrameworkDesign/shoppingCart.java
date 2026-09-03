package RahulShettyAcademy.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class shoppingCart {
WebDriver driver;
@BeforeTest
public void setup() {
	driver = new ChromeDriver();
	driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	
}
@Test
public void shopping() throws InterruptedException {
	driver.manage().window().maximize();
	//login
	driver.findElement(By.id("userEmail")).sendKeys("athira@exmplish.com");
	driver.findElement(By.id("userPassword")).sendKeys("Example@2026");
	driver.findElement(By.id("login")).click();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(
		    By.className("toast-container")));
	
	//get product list
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("col-lg-4")));
	List<WebElement> products=driver.findElements(By.cssSelector(".col-lg-4"));
	//Get zara coat filtered , add to cart
	WebElement Mypdt=products.stream().filter(s->s.getText().contains("ZARA COAT")).findFirst().orElseThrow();
	Mypdt.findElement(By.cssSelector(".w-10")).click();
	//Thread.sleep(10);
	//wait for the message , and loading button 
	String expectedMsg="product added to cart";
	String text=wait.until(d->{ String t=d.findElement(By.id("toast-container")).getText();
		return (t.equalsIgnoreCase(expectedMsg) ? t : null);
				});

	System.out.println(text);
	Assert.assertTrue(text.equalsIgnoreCase(expectedMsg));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(
		    By.className("ngx-spinner-overlay")));
	// checkout
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@routerlink='/dashboard/cart']")));
	driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	
	// verify cart
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'My Cart')]")));
	WebElement cartItem=driver.findElement(By.xpath("//h3[contains(text(),'ZARA COAT 3')]"));
	Assert.assertTrue(cartItem.isDisplayed(), "not added to cart");
	//checkout
	driver.findElement(By.xpath("//button[text()='Checkout']")).click();
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".payment__title:nth-child(1)"))));
	driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-item.list-group-item.ng-star-inserted")));
	List<WebElement> SuggestedCountries = driver.findElements(By.cssSelector(".ta-item.list-group-item.ng-star-inserted"));
					SuggestedCountries.stream()
					.filter(s->s.getText()
					.equalsIgnoreCase("India")).findFirst().orElseThrow().click();
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ta-item.list-group-item.ng-star-inserted")));
	driver.findElement(By.cssSelector("a.action__submit ")).click();
	//Thank you page
	Thread.sleep(3000);
	WebElement thnks= driver.findElement(By.cssSelector(".hero-primary"));
	//wait.until(ExpectedConditions.visibilityOf(thnks));
	
	Assert.assertTrue(thnks.isDisplayed());
					
}
@AfterTest
public void closeall() {
	//driver.quit();
}
}