package fabrily;

import org.testng.annotations.Test;

import weboniseCore.WeboAutomation;

public class StartACampaign extends WeboAutomation {

	String dropDownItem,extraStyleProduct1,extraStyleProduct2,extraStyleProduct3;
	
	@Test(dataProvider = "xml")
	public void startCampaign(Integer iteration, Boolean expectedResult)throws InterruptedException {
		updateTCData(iteration);
		FabrilyLogin login = new FabrilyLogin();
		login.homePageLogin(iteration, expectedResult);
		actions.click(getObjID("dashBoardStartCampaignLink"));
		Thread.sleep(15000);
		/*for (int i = 0; i < actions.getDropDownItem(getObjID("chooseStyleDropDown"),"option"); i++) {
			dropDownItem = Actions.dropDownList.get(i);
			System.out.println(dropDownItem);
			actions.select(getObjID("chooseStyleDropDown"), dropDownItem);
			System.out.println("item selected");*/
			actions.click(getObjID("subStyle", 2));
			System.out.println("subStyle selected");
			actions.click(getObjID("selectColor"));
			System.out.println("color selected");
			campaignWithText();
			actions.click(getObjID("nextButton"));
			actions.click(getObjID("DisplayRearProductByDefault"));
			actions.sendKeys(getObjID("addtionalProfit"),getValue("addtionalProfit"));
			selectAdditionalColor("AddtionalChooseColor",12);
			// Additional Style 1
			setExtraStyle(16, "Canvas - Unisex", "addtionalStyleDropDown1");
			// Additional Style 2
			setExtraStyle(22,"AWD College - Hoodie", "addtionalStyleDropDown2");
			// Additional Style 3
			setExtraStyle(30,"Braintree - Hemp t-shirts", "addtionalStyleDropDown3");
			actions.click(getObjID("lastStep"));
			actions.sendKeys(getObjID("campaignTittle"), "Automation Campaign Tittle");
			actions.sendKeys(getObjID("campaignDescription"), "Automation Campaign Description");
			actions.sendKeys(getObjID("campaignURL"), "Test-Campaign");
			System.out.println("Text Campaign "+dropDownItem+" successfully created");
		
	}
	
	private void campaignWithText(){
		actions.click(getObjID("campaignWithText"));
		actions.sendKeys(getObjID("campaignInputTextFront"),"Text CAMPAIGN,Front" );
		actions.click(getObjID("backDesign"));
		actions.click(getObjID("campaignWithText"));
		actions.sendKeys(getObjID("campaignInputTextBack"),"Text CAMPAIGN,Back");
	}
	
	private void setExtraStyle(int num, String style, String styleDropDown) throws InterruptedException{
		actions.click(getObjID("addExtraStyle"));
		actions.select(getObjID(styleDropDown), style);
		Thread.sleep(2000);
		selectAdditionalColor("AddtionalChooseColor",num);
	}
	
	private void selectAdditionalColor(String chooseColor, int num){
		for (int i = num; i<num+3;i++){
		actions.click(getObjID("addtionalColor"));
		actions.click(getObjID(chooseColor, i));
		}
	}
	
}