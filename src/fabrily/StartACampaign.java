package fabrily;

import org.testng.annotations.Test;

import weboniseCore.WeboAutomation;

public class StartACampaign extends WeboAutomation{
	
	@Test(dataProvider = "xml")
	public void startTextCampaign(Integer iteration, Boolean expectedResult) throws InterruptedException{
		updateTCData(iteration);
		FabrilyLogin login = new FabrilyLogin();
		login.homePageLogin(iteration, expectedResult);
		actions.click(getObjID("dashBoardStartCampaignLink"));
		Thread.sleep(8000);
		actions.select(getObjID("chooseStyleDropDown"),"Hoodies");
		//actions.click(getObjIDByDynamicXpath("subStyle", 2));
		
		
				
			
		}
		


}