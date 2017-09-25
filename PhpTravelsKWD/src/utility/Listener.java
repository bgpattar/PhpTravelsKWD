package utility;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Listener implements ITestListener, ISuiteListener, IInvokedMethodListener{

	@Override
	public void onStart(ISuite arg0){
		Reporter.log("About begin the test suite " + arg0.getName(), true);
	}
	
	@Override
	public void onFinish(ISuite arg0){
		Reporter.log("About end the test suite execution " + arg0.getName(), true);
	}
	
	public void onTestSuccess(ITestResult arg0){
		printTestResults(arg0);
	}
	
	public void onTestFailure(ITestResult arg0){
		printTestResults(arg0);
	}
	
	public void onTestStart(ITestResult arg0){
		System.out.println("The excution of main test starts now! ");
	}
	
	public void onTestSkipped(ITestResult arg0){
		printTestResults(arg0);
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result){ 
		System.out.println("am i got executed?");
	}
	
	private void printTestResults(ITestResult result){
		
		Reporter.log("Test method resides in " + result.getTestClass().getName(), true);
		
		if(result.getParameters().length != 0){
			String params = null;
			for (Object parameter: result.getParameters()){
				params += parameter.toString() + ",";
			}
			Reporter.log("Test method had the following parameters: " + params, true);
		}
		
		String status = null;
		switch (result.getStatus()){
		case ITestResult.SUCCESS:
			status = "Pass";
			break;
		case ITestResult.FAILURE:
			status = "Failure";
			break;
		case ITestResult.SKIP:
			status = "Skipped";
		}
		
		Reporter.log("Test Status: "+ status, true);
	}
	
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1 ){
		String txtMsg = "About beging the execution of following method: " + returnMethodName(arg0.getTestMethod());
		Reporter.log(txtMsg, true);
	}
	
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1){
		String txtMsg = "About to ending the execution of following method: " + returnMethodName(arg0.getTestMethod());
		Reporter.log(txtMsg, true);
	}
	
	private String returnMethodName(ITestNGMethod method){
		return method.getRealClass().getSimpleName() + "."+ method.getMethodName();
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
}
