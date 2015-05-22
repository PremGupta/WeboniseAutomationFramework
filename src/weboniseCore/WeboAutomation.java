package weboniseCore;
import java.lang.reflect.Method;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import weboniseCore.verification.Verification;


public class WeboAutomation {

	
	public static WebDriver driver;
	By we = null;
	String objectLocator;
	String locatorType;
	String locatorValue;
	public static ObjectRepository objRepo = new ObjectRepository();
	static TestData testdata = new TestData();
	
	public static Actions actions = new Actions();
	public static Verification verify = new Verification();
	
	
	private static void initializeLocalBrowser(String type) throws Exception {
        if (type == null) {
            type = "phantomJs";
        }
        else if (type.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        else if (type.equalsIgnoreCase("chrome")) {
        	System.setProperty("webdriver.chrome.driver", "/home/ankit/Ankit work/chromedriver");
        	driver = new ChromeDriver();
        }
        actions.setDriver(driver);
        //driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT, TimeUnit.MILLISECONDS);
    }
	
	@BeforeMethod(alwaysRun = true)
    public static void setUp(Method method) throws Exception {
    //public static void setUp(String methodName) throws Exception {
    	
    	System.out.println("Inside SetUp function");
    	
        if (driver == null) {
        	Configuration.initializeSettings();
        	objRepo.initialize();
        	testdata.initialize();
        	initializeLocalBrowser(Configuration.browser);
        	
            
        } else {
            System.out.println("Driver already initialized");
        }
        System.out.println("Method Name is : "+ method.getName());
        objRepo.setTCNode(method.getName());
        testdata.setTCNode(method.getName());
        
    }
    
    
    @AfterMethod
    public static void TearDown() throws Exception {
    	//System.out.println("Inside TearDown function");
    	System.out.println("Closing browser");
    	driver.close();
    	System.out.println("Browser closed");
    	System.out.println("Driver set to null");
    	driver = null;
    }
    
    /*public By getObjIDByDynamicXpath(String objName,Integer Number){
    	 objectLocator = objRepo.getObjIDByDynamic(objName, Number);
    	 System.out.println("objectLocator "+objectLocator);
			we = By.xpath(objectLocator);
		return we;
    	
    }*/
	
	public By getObjID(String objName){
		
		//return objRepo.getObjID(objName);
		
		objectLocator = objRepo.getObjID(objName);
		//System.out.println("Object locator is - " + objectLocator);
		
		locatorType = objectLocator.split("=")[0];
		locatorValue = objectLocator.split("=")[1];
		
		switch(locatorType.toLowerCase()){
			case "id" : //System.out.println("Identifier type is ID");
						we =  By.id(locatorValue);
						break;
			case "name" : //System.out.println("Identifier type is name");
						we =  By.name(locatorValue);
						break;
			case "class" : //System.out.println("Identifier type is class");
						we =  By.className(locatorValue);
						break;
						
			case "linktext" : //System.out.println("Identifier type is linktext");
						we = By.linkText(locatorValue);
						break;
						
			case "xpath" : //System.out.println("Identifier type is xpath");
						we = By.xpath(locatorValue);
						break;
						
			case "cssselector" : //System.out.println("Identifier type is cssSelector");
						we = By.cssSelector(locatorValue);
						break;
			default : System.out.println("Unknow type of identifier");	
				
		}
		
		return we;
		
	}
	
	public void updateTCData(Integer iteration){
		testdata.updateTCData(iteration);
	}
	
	public String getValue(String varName){
		return testdata.getValue(varName);
	}
	
	
	@DataProvider(name = "xml")
	   public static Object[][] getTestData(Method method) {
		try {
			setUp(method);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("in WeboAutomation.GetTCData");
	      //return new Object[][] {{2, true}, {6, false}, {19, true}, {22, false}, {23, true}};
		  return testdata.getTCData();
		
	   }
	
	
//	
//	@Test 
//	@Parameters("myName")
//	private void login(String myName){
//		System.out.println("Parameterized value is : " + myName);
//	}
//	
	  
//	   @BeforeMethod
//	   public void initialize() {
//	      primeNumberChecker = new PrimeNumberChecker();
//	   }
		
//	   @DataProvider(name = "test1")
//	   public static Object[][] primeNumbers() {
//	       return new Object[][] {{2, true}, {6, false}, {19, true}, {22, false}, {23, true}};
//	   }
//
//	   // This test will run 4 times since we have 5 parameters defined
//	   @Test(dataProvider = "test1")
//	   public void testPrimeNumberChecker(Integer inputNumber, Boolean expectedResult) {
//		   
//		   System.out.println(inputNumber + " " + expectedResult);
//	      
//	   }
	
	
		
	

}
