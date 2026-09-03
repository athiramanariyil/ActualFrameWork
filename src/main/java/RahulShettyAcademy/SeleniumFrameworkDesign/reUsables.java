package RahulShettyAcademy.SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class reUsables {

	 WebDriver driver;
	 WebDriverWait wait;

	public reUsables(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}
	public void waiting(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitingLocator(By loc) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loc));
	}
	public void waitingToDisappear(By toDisappear) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(toDisappear));
	}
	public void waitingPresence(By loc) {
		wait.until(ExpectedConditions.presenceOfElementLocated(loc));
	}
	
	
}
