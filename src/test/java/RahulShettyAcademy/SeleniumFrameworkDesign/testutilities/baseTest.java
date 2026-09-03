package RahulShettyAcademy.SeleniumFrameworkDesign.testutilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import RahulShettyAcademy.SeleniumFrameworkDesign.LoginPage_Cart;
	
 
public class baseTest  {
		
	  public WebDriver driver;
		private static ExtentReports extend;
		 String URL = "https://rahulshettyacademy.com/client/#/auth/login";

	 public WebDriver initialize() throws IOException {
		 Properties prop= new Properties();
		 FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\GlobalProperties.properties");
		 prop.load(fis);;
		 String browserN=prop.getProperty("browser");
		 
		 if(browserN.equalsIgnoreCase("chrome")) {
		 driver= new ChromeDriver();
		 }
		 else if(browserN.equalsIgnoreCase("edge")) {
			 driver=new EdgeDriver();
		 }
		return driver;

		 
	 }
	 public LoginPage_Cart launchApp() throws IOException {
		 driver = initialize();
		 LoginPage_Cart login = new LoginPage_Cart(driver);
		 login.getTheWebSite(URL);
		 return login;
	 }
	 //----------------------------------------------------------------------------------------------------------------------------------
	 //Json to hashmap conversion - dataprovider.
	 public List<HashMap<String, String>> GetDataFromJson() throws StreamReadException, DatabindException, IOException {
		 String jsonData = System.getProperty("user.dir")+"\\src\\test\\java\\resources\\jsonDataset.json";
			File jsonContent = new File(jsonData);
			ObjectMapper mapper= new ObjectMapper();
			List<HashMap<String, String>> dataSetJson= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
				});
			return dataSetJson;
	 }
	 
	 
	 // screenshot utility
	 public static String TakeSS(String TCname ,WebDriver driver) throws IOException {
		 	
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			File imgFile= new File(System.getProperty("user.dir")+"//reports//"+TCname+".png");
			FileUtils.copyFile(source,imgFile );
			return System.getProperty("user.dir")+"//reports//"+TCname+".png";
		}
	 
	 //Report configuration
	 
	 
	 
	 
	 @DataProvider(name="dataset")
		public Object[][] dataSet(){
			
			return new Object[][] {
				{"athira@exmplish.com" ,"Example@2026" , "ZARA COAT 3" },
				{"athira@exmplish.com" ,"Example@2026" , "ADIDAS ORIGINAL" },
				{"athira@exmplish.com" ,"Example@2026" , "IPHONE 13 PRO" }
				
				};
			}
		
		@DataProvider(name="datasetToVerify")
		public Object[][] dataSetToVerify(){
			
			return new Object[][] {
				{"athira@exmplish.com" ,"Example@2026" , "ZARA COAT 3" }
				
				};
			}
		@DataProvider(name="fromJson")
		public Object[][] fromJson() throws StreamReadException, DatabindException, IOException{
			List<HashMap<String , String>> datafromJson=GetDataFromJson();
			return new Object[][]{{datafromJson.get(0)},{datafromJson.get(1)},{datafromJson.get(2)}};
		}
}
