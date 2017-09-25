package utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactory {
	
	private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	
	public static WebDriver getBrowser(String BrowserName){
		
		WebDriver driver = null;
		
		switch(BrowserName) {
		case "Firefox":
			driver = drivers.get("Firefox");
			if(driver == null){
				System.out.println("firefox driver is null");			
				System.setProperty("webdriver.gecko.driver","C:\\Users\\dell\\Documents\\SeleniumTest\\geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				drivers.put("Firefox", driver);
			}
			break;
		case "Chrome":
			driver = drivers.get("Chrome");
			if (driver == null){
				
				Properties myProp = System.getProperties();
				myProp.setProperty("webdriver.chrome.driver","C:\\Users\\dell\\Documents\\SeleniumTest\\chromedriver.exe");
//				System.setProperty("webdriver.chrome.driver","C:\\Users\\dell\\Documents\\SeleniumTest\\chromedriver.exe");
				
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				drivers.put("Chrome", driver);
			}
			break;
		case "IE":
			driver = drivers.get("IE");
			if(driver == null){
				System.setProperty("webdriver.ie.driver","C:\\Users\\dell\\Documents\\SeleniumTest\\IEDriverServer.exe");
				DesiredCapabilities myCap = DesiredCapabilities.internetExplorer();
				myCap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				myCap.setCapability("requiredWindowFocus", true);
			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			drivers.put("IE", driver);
			}
			break;
		}
		return driver;		
	}

	public static void tearDown(){
		for(String key: drivers.keySet()){
//			drivers.get(key).close();
			drivers.get(key).quit();
		}
	}
	
	
}
