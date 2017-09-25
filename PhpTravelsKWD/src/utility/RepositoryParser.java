package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class RepositoryParser {
	
	private FileInputStream fisObj;
	private String fileName;
	private Properties pprtyFile = new Properties();
	
	//its a constructor
	public RepositoryParser(String fileName)throws IOException{
		this.fileName = fileName;
		fisObj = new FileInputStream(this.fileName);
		this.pprtyFile.load(fisObj);
		
	}

	public By getObjLocator(String LocatorName){
		String pprtyLocator = pprtyFile.getProperty(LocatorName);
		System.out.println("Locator found in file: " + pprtyLocator.toString());
		
		String pprtyLocaType = pprtyLocator.split(":")[0];
		System.out.println("Locator type: " + pprtyLocaType);
		String pprtyLocaValue = pprtyLocator.split(":")[1];
		System.out.println("Locator value is : " + pprtyLocaValue);
		
		By locator = null;
		
		switch(pprtyLocaType){
		
		case "Name":
			locator = By.name(pprtyLocaValue);
			break;
		case "Id":
			locator = By.id(pprtyLocaValue);
			break;
		case "TagName":
			locator = By.tagName(pprtyLocaValue);
			break;
		case "LinkText":
			locator = By.linkText(pprtyLocaValue);
			break;
		case "PartialLinkText":
			locator = By.partialLinkText(pprtyLocaValue);
			break;
		case "Xpath":
			locator = By.xpath(pprtyLocaValue);
			break;
		case "CssSelector":
			locator = By.cssSelector(pprtyLocaValue);
			break; 
		}
		return locator;
	}
}
