package executionEngine;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import config.ActionKeyWords;
import utility.Constants;
import utility.ExcelUtils;
import utility.Log;

import org.apache.log4j.xml.DOMConfigurator;

public class DriverScript {
	
	public static Properties ObjRep;
	public static ActionKeyWords aKwd;
	public static String sActKwd;
	public static String sPageObj;
	public static Method myMethod[];
	
	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunMode;
	public static String sData;
	
	public static boolean bResult;
	
		public DriverScript() throws NoSuchMethodException, SecurityException{
			aKwd = new ActionKeyWords();
			myMethod = aKwd.getClass().getMethods();
		}
	
	  
	    public static void main(String[] args) throws Exception {
	    		    	
	    	ExcelUtils.setExcelFile(Constants.PathTestData+Constants.FileTestData);
	    	
	    	DOMConfigurator.configure("log4j.xml");
	        
	    	FileInputStream fis = new FileInputStream(Constants.PathObjRep);
	    	
	    	ObjRep = new Properties(System.getProperties());
	    	
	    	ObjRep.load(fis);
	    	
	    	DriverScript startEngine = new DriverScript();
	    	
	    	startEngine.execute_TestCase();
	    }
	    
	    public void execute_TestCase()throws Exception{
	    		    	
	    	int iTotalTestCases = ExcelUtils.getRowCount(Constants.SheetTestCases);
	    		    	
	    	for (int iTestCase = 1; iTestCase<iTotalTestCases; iTestCase++){
	    		
	    		bResult = true;
	    			    		
	    		sTestCaseID = ExcelUtils.getCellData(iTestCase, Constants.Col_TestCaseID, Constants.SheetTestCases);
	    	
	    		sRunMode = ExcelUtils.getCellData(iTestCase, Constants.Col_RunMode, Constants.SheetTestCases);
	    		
	    		if(sRunMode.equalsIgnoreCase("Yes")){
	    			
	    			iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.SheetTestSteps);
	    			
	    			iTestLastStep = ExcelUtils.getTestStepsCount(Constants.SheetTestSteps, sTestCaseID, iTestStep);
	    		
	    			Log.startTestCase(sTestCaseID);
	    			
	    			bResult = true;
	    			
	    			for(;iTestStep<iTestLastStep;iTestStep++){
	    			
	    				sActKwd = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword, Constants.SheetTestSteps);
	    				sPageObj = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.SheetTestSteps);
	    				sData = ExcelUtils.getCellData(iTestStep, Constants.Col_DataSet, Constants.SheetTestSteps);
	    				
//	    				System.out.println("value kwd: " + sActKwd + " value PgOb: " + sPageObj + "value of data " + sData);
	    				
	    				execute_Actions();

	    				if(bResult == false){
	    					
	    					System.out.println("Testcase: false Inside loop");
	    					ExcelUtils.setCellData(Constants.Keyword_Fail, iTestCase, Constants.Col_TestCaseResult, Constants.SheetTestCases);
	    					Log.endTestCase(sTestCaseID);
	    					break;
	    				} 
	    						
	    			}

	    			if(bResult == true) {
	    				
	    				System.out.println("Testcase: Inside true loop");
    					ExcelUtils.setCellData(Constants.Keyword_Pass, iTestCase, Constants.Col_TestCaseResult, Constants.SheetTestCases);
    					Log.endTestCase(sTestCaseID);
    				}
	    			
	    		}
	    	}
	    	
	    }

	    public static void execute_Actions() throws Exception{
	    	
//	    	Log.info("executing actions");
	    		    	
	    	for (int i = 0; i < myMethod.length; i++){
	
//	    		System.out.println("array value:-- "+ myMethod[i].getName()+"-- excel kwd: " + sActKwd);
	    		
	    		if(myMethod[i].getName().equals(sActKwd)){
		
	    			myMethod[i].invoke(aKwd, sPageObj, sData);
	    			
	    			if(bResult == true){
	    				
	    				ExcelUtils.setCellData(Constants.Keyword_Pass, iTestStep, Constants.Col_TestStepResult, Constants.SheetTestSteps);
	    				break;
	    			}else{
	    					    				
	    				ExcelUtils.setCellData(Constants.Keyword_Fail, iTestStep, Constants.Col_TestStepResult, Constants.SheetTestSteps);
	    			
	    				ActionKeyWords.close_Browser("", "");
	    				break;
	    			}
			
	    		}
	    	}
	    }
	       
}
