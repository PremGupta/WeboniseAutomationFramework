package weboniseCore;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Actions {
	
	static WebDriver driver ;
	public static ArrayList<String> dropDownList;
	public static WebElement ulContainer;
	
	protected void setDriver(WebDriver mydriver){
		
		driver = mydriver;
	}
	
	public void launchBrowser(String url){
		driver.get(url);
		System.out.println("Launched browser with URL " +  url);
		driver.manage().window().maximize();
		System.out.println("Window maximized");
	}
	
	public void click(By byObj){
		try{
			driver.findElement(byObj).click();
			System.out.println("Clicked on " + byObj.toString());
		}
		catch(Exception e){
			takeSS();
			e.printStackTrace();
		}
	}
	
	public void sendKeys(By byObj, String textToSet){
		try{
			driver.findElement(byObj).sendKeys(textToSet);
			System.out.println("Setting text \"" + textToSet  + "\" on " + byObj.toString());
		}
		catch(Exception e){
			takeSS();
			e.printStackTrace();
		}
	}
	
	public String getText(By byObj){
		return driver.findElement(byObj).getText();
	}
	
	public String getTitle(){
		return driver.getTitle();
	}
	
	private void takeSS(){
		System.out.println("Take screenshot");
		
	}

	public int getDropDownItem(By byObj){
		dropDownList = new ArrayList<String>();
		ulContainer = driver.findElement(byObj);
		List<WebElement> dropDownvalue = ulContainer.findElements(By.tagName("li"));
		for (WebElement firstElement:dropDownvalue)
			dropDownList.add(firstElement.getText());
		return dropDownList.size();
		
	}
	
	public void select(By byObj, String textToSet){
		try{
		WebElement dropDown = driver.findElement(byObj);
		Select element = new Select(dropDown);
		element.selectByVisibleText(textToSet);
		System.out.println(textToSet+" text selected");
		}
		catch(Exception e){
			takeSS();
			e.printStackTrace();
		}
	}
	
	
}
