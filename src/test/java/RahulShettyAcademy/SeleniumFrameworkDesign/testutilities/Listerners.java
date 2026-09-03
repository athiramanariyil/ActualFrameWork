package RahulShettyAcademy.SeleniumFrameworkDesign.testutilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Listerners  implements ITestListener{
	static ExtentReports extend;
	ExtentTest test;
	//String filePath = baseTest.TakeSS("failure", driver);
	ThreadLocal<ExtentTest> threads = new ThreadLocal<ExtentTest>();
	public Listerners() {

	        String path = System.getProperty("user.dir") + "\\reports\\index.html";

	        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	        reporter.config().setReportName("test results");

	        extend = new ExtentReports();
	        extend.attachReporter(reporter);
	    }
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		 test=extend.createTest(result.getMethod().getMethodName());
		 threads.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		test.log(Status.PASS,"passed" );
	}

	
	
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		//WebDriver driver = null;
		WebDriver driver = ((baseTest) result.getInstance()).driver;
		threads.get().fail(result.getThrowable());
		 
		String filePath=null;
		try {
			filePath=baseTest.TakeSS(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		extend.flush();
		
	}
//	@BeforeTest
//	public static ExtentReports ReportConfig() {
//		String path = System.getProperty("user.dir")+"\\reports\\index.html";
//		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
//		reporter.config().setReportName("test results");
//		
//		extend = new ExtentReports();
//		extend.attachReporter(reporter);
//		return extend;
//		
//		
//		
//	}
}
