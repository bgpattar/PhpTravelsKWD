package utility;

import org.apache.log4j.Logger;

import org.apache.log4j.LogManager;

public class Log {
	
	private static Logger Log = LogManager.getLogger(Log.class.getName());
	
	public static void startTestCase(String sTestCaseName){
		Log.info("--------------------------------------------------------");
		Log.info("-----------------" + sTestCaseName + "------------------");
		Log.info("--------------------------------------------------------");
	}
	public static void endTestCase(String sTestCaseName){
		Log.info("---------------------------------------------------------------------");
		Log.info("----------------- E  N D - O F - "+ sTestCaseName + "----------------");
		Log.info("---------------------------------------------------------------------");
		Log.info("X");
		Log.info("X");
		Log.info("X");
		
	}
	public static void info(String message){
	
		Log.info(message);
	}
	public static void warn(String message){
		
		Log.warn(message);
	}
	public static void error(String message){
		
		Log.error(message);
	}
	public static void debug(String message){
		
		Log.debug(message);
	}
	public static void fatal(String message){
		
		Log.fatal(message);
	}
}
