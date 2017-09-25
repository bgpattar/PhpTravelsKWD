package config;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import static executionEngine.DriverScript.ObjRep;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import executionEngine.DriverScript;
import utility.Constants;
import utility.Log;

public class ActionKeyWords {
	
//adding some comments
	public static WebDriver driver;

	public static void open_Browser(String Obj, String Data){
		try{
			
			Log.info("entered opening Browser");
				
		switch(Data) {
		case "Firefox":
				System.out.println("firefox driver is null");			
				System.setProperty("webdriver.gecko.driver","C:\\Users\\dell\\Documents\\SeleniumTest\\geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				break;
			
		case "Chrome":
				Properties myProp = System.getProperties();
				myProp.setProperty("webdriver.chrome.driver","C:\\Users\\dell\\Documents\\SeleniumTest\\chromedriver.exe");				
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				break;
				
		case "IE":
				System.setProperty("webdriver.ie.driver","C:\\Users\\dell\\Documents\\SeleniumTest\\IEDriverServer.exe");
				DesiredCapabilities myCap = DesiredCapabilities.internetExplorer();
				myCap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				myCap.setCapability("RequiredWindowFocus", true);
				myCap.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);								
				driver = new InternetExplorerDriver(myCap);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				break;
			}
		
		}catch(Exception e){
			Log.info("Not able to luanch browser " + e.getMessage() );
			DriverScript.bResult = false;
		}
		
	}

	public static void navigate_URL(String Obj, String Data){	
		try{
			Log.info("navigate to URL");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(Constants.sURL2);
		}catch(Exception e){
			Log.info("method: navigate URL " + e.getMessage() );
			DriverScript.bResult = false;
		}
	}

	public static void click_Account (String Obj, String Data){
			try{
				Log.info("click_Account");
			
				driver.findElement(By.linkText(ObjRep.getProperty(Obj))).click();
				Set<String> sHandles = driver.getWindowHandles();
				//Can this be removed??
				driver.switchTo().window(sHandles.iterator().next());
				String s1 = sHandles.iterator().next();
				sHandles.remove(s1);
				driver.switchTo().window(sHandles.iterator().next());
			}catch(Exception e){
				Log.info("method: click account " + e.getMessage() );
				DriverScript.bResult = false;
			}
	       
	}

	public static void input(String Obj, String Data){
		try{
		Log.info("Entering the text value: " + Data);	
		 driver.findElement(By.id(ObjRep.getProperty(Obj))).sendKeys(Data);
		}catch(Exception e){
			Log.info("method: input, Not able to input text " + e.getMessage() );
			DriverScript.bResult = false;
		}
	}
/*
	public static void input_Password(String Obj){
		try{
		Log.info("input_Password");
		  driver.findElement(By.id(ObjRep.getProperty(Obj))).sendKeys(Constants.PassWord);
		}catch(Exception e){
			Log.info("method: Password " + e.getMessage() );
			DriverScript.bResult = false;
		}
	}
*/
	public static void click_Login(String Obj, String Data){
		try{
		Log.info("clicking Login button");
		 driver.findElement(By.id(ObjRep.getProperty(Obj))).click();
		}catch(Exception e){
			Log.info("method: login, not able to click login button " + e.getMessage() );
			DriverScript.bResult = false;
		}
	}

	public static void waitFor(String Obj, String Data) throws Exception{
		try{
		
		Log.info("wait for application to load");
		Thread.sleep(3000);
		
		}catch(Exception e){
			Log.info("method:waitFor Not able to wait for anything " + e.getMessage() );
			DriverScript.bResult = false;
		}
	}

	public static void click_MyAccount(String Obj, String Data){
		
		try{
			
		
		Log.info("clicking MyAccount menu on home page");
		WebElement xWL = driver.findElement(By.xpath(ObjRep.getProperty(Obj)));
        Actions xAct = new Actions(driver);
        xAct.moveToElement(xWL);
        xAct.click();
        xAct.perform();
        
		}catch(Exception e){
			Log.info("method: my Account, Not able to click on menu " + e.getMessage() );
			DriverScript.bResult = false;
		}
	}
	
	public static void click_Logout(String Obj, String Data){
		try{
		Log.info("clicking Logout menu option under account menu");

		driver.findElement(By.xpath(ObjRep.getProperty(Obj))).click(); //correct command
		}catch(Exception e){
			Log.info("method: logout, not able to click on sub-menu option" + e.getMessage() );
			DriverScript.bResult = false;
		}
	}
	

	public static void close_Browser(String Obj, String Data){
		try{
		Log.info("time to close the Browser");
		driver.quit();
		}catch(Exception e){
			Log.info("method: close browser, not able to close " + e.getMessage() );
			DriverScript.bResult = false;
		}
	}
}
