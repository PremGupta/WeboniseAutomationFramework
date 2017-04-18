package fabrily;

import org.testng.Assert;
import org.testng.annotations.Test;

import weboniseCore.WeboAutomation;

public class FabrilyLogin extends WeboAutomation {
	
	// For Reporter Login
	@Test(dataProvider = "xml")
	public void login(Integer iteration, Boolean expectedResult) {
		updateTCData(iteration);
		actions.launchBrowser("http://staging.fabrily.com/campaigners/sign_in");
		actions.sendKeys(getObjID("username"), getValue("username"));
		actions.sendKeys(getObjID("password"), getValue("paswd"));
		actions.click(getObjID("loginbtn"));
		Assert.assertTrue(verify.checkString(getObjID("homeLink"), "HOME"), "Successful login");
	}
	
	@Test(dataProvider = "xml")
	public void homePageLogin(Integer iteration, Boolean expectedResult){
		updateTCData(iteration);
		actions.launchBrowser("http://staging.fabrily.com/");
		actions.click(getObjID("homePageLoginLink"));
		actions.sendKeys(getObjID("username"), getValue("username"));
		actions.sendKeys(getObjID("password"), getValue("paswd"));
		actions.click(getObjID("loginbtn"));
		Assert.assertTrue(verify.checkString(getObjID("homeLink"), "HOME"), "Successful login");
		
	}
	
	@Test(dataProvider = "xml",priority = 1, enabled = true)
	public void testcasename(Integer iteration, Boolean expectedResult)	{
		updateTCData(iteration);
		actions.launchBrowser("");
		actions.click(getObjID("RegisterLink"));
		
		
	}
}
