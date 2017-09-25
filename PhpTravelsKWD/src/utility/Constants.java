package utility;

public class Constants {
	
	
	//application URLs
	
	public static final String sURL = "http://www.store.demoqa.com";
	
	public static final String sURL2 = "http://phptravels.com/demo/";
	
	public static final String sURL3 = "https://phptravels.org/clientarea.php";
	
	//Login credentials
	
	public static final String UserName = "bgpattar@gmail.com";
	
	public static final String PassWord = "@phpTravels1-2";
	
	//Test Data File name paths
	
	public static final String PathObjRep = "C:\\Users\\dell\\workspace\\PhpTravelsKWD\\src\\config\\ObjRep.txt";
	
	public static final String PathTestData = "C:\\Users\\dell\\workspace\\PhpTravelsKWD\\src\\dataEngine\\";
	
	//File names
	
	public static final String FileTestData = "DataEngine.xlsx";
	
	//Test Data workbook sheet names
	
	public static final String SheetTestSteps = "TestSteps";
	
	public static final String SheetTestCases = "TestCases";
	
	// Columns in Test Data Sheet - INPUT
	
	public static final int Col_TestCaseID = 0;
	
	public static final int Col_TestScenarioID = 1 ;
	
	public static final int Col_PageObject = 3 ;
	
	public static final int Col_ActionKeyword = 4 ;
	
	public static final int Col_RunMode = 2 ;
	
	public static final int Col_DataSet = 5;
	
	//Result columns in Test data sheet - OUTPUT
	
	public static final int Col_TestCaseResult = 3;
	
	public static final int Col_TestStepResult = 6;
	
	//Result values
	
	public static final String Keyword_Pass = "PASS";
	
	public static final String Keyword_Fail = "FAIL";
	

}
