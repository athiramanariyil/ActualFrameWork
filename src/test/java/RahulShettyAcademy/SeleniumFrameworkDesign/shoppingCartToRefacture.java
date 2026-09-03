package RahulShettyAcademy.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.HashMap;
import RahulShettyAcademy.SeleniumFrameworkDesign.testutilities.retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import RahulShettyAcademy.SeleniumFrameworkDesign.testutilities.baseTest;

public class shoppingCartToRefacture extends baseTest{
	
	LoginPage_Cart Login;
//	String userName = "athira@exmplish.com";
//	String Pass = "Example@2026";
//	String item ="ZARA COAT 3";
	//ExtentReports extend = baseTest.ReportConfig() ;
	String TCname="testq1";

	@BeforeMethod
	public void setup() throws IOException {
		
		 Login= launchApp();
		
		
		
	}

	
	@Test(dataProvider="fromJson" ,retryAnalyzer=retry.class)
	public void shopping(HashMap<String, String> testData) throws InterruptedException {
		//extend.createTest("Shopping");
		String userName=testData.get("user");
		String Pass=testData.get("pass");
		String item=testData.get("item");

		Products pdts = Login.enterCred(userName, Pass);
		String text = pdts.ProductsPage(item);
		// get product list
		System.out.println(text);
		Assert.assertTrue(text.equalsIgnoreCase(pdts.expectedMsg));
		// checkout
		cartPage cart = new cartPage(driver);
		// verify cart
		cart.ClickOnCart();
		WebElement cartItem = cart.VerifyCart(item);
		Assert.assertTrue(cartItem.isDisplayed(), "not added to cart");
		System.out.println(cartItem.getText());
		// checkout
		checkOut checkout = new checkOut(driver);
		System.out.println("checout page");
		checkout.btnCheckout();
		// Thank you page
		Thread.sleep(3000);
		WebElement thnks = checkout.thnks();
		Assert.assertTrue(thnks.isDisplayed());
		//extend.flush();

	}
	@Test(dataProvider="datasetToVerify")
	public void VerifyInOrder(String userName, String Pass, String item ) throws IOException {
		//extend.createTest("abc");
		Products pdts = Login.enterCred(userName, Pass);
		//Products pdtTab= new Products(driver);
		Boolean Match=pdts.ProductsTab(item);
		System.out.println(Match);
		Assert.assertTrue(Match);
		TakeSS("abc", driver);
		//extend.flush();
		
	}
	
	

	
	
	
	@AfterMethod
	public void closeall() {
		driver.quit();
	}
}