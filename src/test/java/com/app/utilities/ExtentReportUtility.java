package com.app.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportUtility {
	public static ExtentReports extent ;
	public static ExtentSparkReporter spark;
	public static ExtentTest testLogger;
	private static ExtentReportUtility extentObject;
	
	private ExtentReportUtility() {
		
	}
	
	public static ExtentReportUtility getInstance() {
		if(extentObject==null) {
			extentObject=new ExtentReportUtility();
		}

		return extentObject;
	}
	
	public void startExtentReport() {
		extent = new ExtentReports();
		spark = new ExtentSparkReporter(Constants.REPORTS_PATH);
		spark.config().setDocumentTitle("Test Execution Report");
		spark.config().setReportName("salesforce login test report");
		spark.config().setTheme(Theme.DARK);
		
		extent.setSystemInfo("App", "GeneralStore Mobile App");
		extent.setSystemInfo("Env", "QA_Automation-Android");
		extent.setSystemInfo("UserName", "Shivakumari");
		extent.attachReporter(spark);	
	}
	
	public void startSingleTestReport(String methodName) {
		testLogger=extent.createTest(methodName);
	}
	public void endextent() {
		extent.flush();
	}
	
	public void logTestInfo(String text) {
		//System.out.println("test logger object="+testLogger);
		testLogger.log(Status.INFO,text);
		//testLogger.info(text);
	}
	
	public void logTestpassed(String text) {
		testLogger.log(Status.PASS,MarkupHelper.createLabel(text, ExtentColor.GREEN));
		//testLogger.pass(MarkupHelper.createLabel(text, ExtentColor.GREEN));
	}
	
	public void logTestFailed(String text) {
		testLogger.log(Status.FAIL,MarkupHelper.createLabel(text, ExtentColor.RED));
	}
	
	public void logTestFailedWithException(Throwable e) {
		testLogger.log(Status.FAIL,e);
	
		}
	
	public void logTestWithscreenshot(String path) {
		testLogger.addScreenCaptureFromBase64String(path);
		//testLogger.fail(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		
	}
	

}
