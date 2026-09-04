package RahulShettyAcademy.SeleniumFrameworkDesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import RahulShettyAcademy.SeleniumFrameworkDesign.testutilities.baseTest;

public class errorValidation extends baseTest{
	String user = "athira@exmlish.com";
	String Passcode = "Example@2026";
	@Test
	public void incorrectUser() throws IOException, InterruptedException {
		LoginPage_Cart login = launchApp();
		Products pdt=login.enterCred(user,Passcode);
		String errorMsg=login.getErrorMsg();
		System.out.println(errorMsg);
		Assert.assertTrue(errorMsg.equalsIgnoreCase("Incorrect email or password."));
		//updated

		

}
}