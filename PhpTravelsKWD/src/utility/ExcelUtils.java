package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import executionEngine.DriverScript;

public class ExcelUtils {
	
	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	private static XSSFCell Cell;
	private static XSSFRow Row;
		
	public static void setExcelFile(String Path)throws Exception{
	
		try{
			FileInputStream ExcelFile = new FileInputStream(Path);
			
			ExcelWBook = new XSSFWorkbook(ExcelFile);
		}catch(Exception e){
			Log.info("method: set excel file " + e.getMessage() );
			DriverScript.bResult = false;
		}
	}
	
	public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception {
		
		try{
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			Cell =ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
			}catch(Exception e){
				Log.info("util method: get cell data " + e.getMessage() );
				DriverScript.bResult = false;
				return "";
			}
			
	}
	
	public static int getRowCount(String SheetName){
		int rCount = 0;
		try{
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		rCount = ExcelWSheet.getLastRowNum()+1;
		}catch(Exception e){
			Log.info("method: get row count " + e.getMessage() );
			DriverScript.bResult = false;
		}
		return rCount;
	}
	
	public static int getRowContains(String sTestCaseName, int colNum, String SheetName)throws Exception{
		int iRowNum = 0;
		try{
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		
		int rowCount = ExcelUtils.getRowCount(SheetName);
		for (iRowNum = 0; iRowNum<rowCount; iRowNum++){
			if(ExcelUtils.getCellData(iRowNum, colNum, SheetName).equalsIgnoreCase(sTestCaseName)){
		
				break;
			}
		}
		}catch(Exception e){
			Log.info("method: get row contains " + e.getMessage() );
			DriverScript.bResult = false;
		}
		return iRowNum;
	}
	
	public static int getTestStepsCount(String SheetName, String sTestCaseId, int iTestCaseStart ) throws Exception {
	
		try{
		for(int i = iTestCaseStart; i<=ExcelUtils.getRowCount(SheetName);i++){
			if(!sTestCaseId.equalsIgnoreCase(ExcelUtils.getCellData(i, Constants.Col_TestCaseID, SheetName))){
				int iNumber = i;
				return iNumber;
			}
		}
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int iNumber = ExcelWSheet.getLastRowNum()+1;
		return iNumber;
		}catch(Exception e){
			Log.info("class: util method: getTestStepsCount " + e.getMessage() );
			DriverScript.bResult = false;
			return 0;
		}
	}
	
	public static void setCellData(String sResult, int RowNum, int ColNum, String SheetName)throws Exception {
		try{
			
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum);
			if (Cell == null){
//				System.out.println("Cell is null");
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(sResult);
			} else {
//				System.out.println("Cell is not null");
				Cell.setCellValue(sResult);
			}
			
			FileOutputStream fos = new FileOutputStream(Constants.PathTestData+Constants.FileTestData);
			ExcelWBook.write(fos);
			fos.close();
			ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.PathTestData+Constants.FileTestData));
					
		} catch (Exception e){
			
			Log.info("Class:util - Method: setCellData " + e.getMessage() );
			DriverScript.bResult = false;
			
		}
	}
}
