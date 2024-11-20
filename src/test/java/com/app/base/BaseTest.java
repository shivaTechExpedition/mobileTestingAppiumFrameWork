package com.app.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.app.utilities.Constants;
import com.app.utilities.PropertiesUtility;
import com.google.common.io.Files;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	protected static AppiumDriver driver = null;
	AppiumDriverLocalService service;
	protected  static Logger mylog = LogManager.getLogger(BaseTest.class);
	
	
	@BeforeSuite
	public void startServer() {

		PropertiesUtility.loadProperty();
		
		// setup the appium server
		service = new AppiumServiceBuilder().
				withAppiumJS(new File(Constants.APPIUM_SERVER_PATH)).
				withIPAddress(PropertiesUtility.getProperty("SERVER_IP_ADDRESS")).
				usingPort(Integer.parseInt(PropertiesUtility.getProperty("SERVER_PORT_NUMBER")))
				.build();
		
		// start the server
		service.start();
		
	}
	
	// Stop the service / server
	@AfterSuite
	public void stopServer() {
		service.stop();
	}
	
	
	public static AppiumDriver getDriver(String driverName) throws InterruptedException {
		String name =driverName.toLowerCase();
		switch(name) {
		case "android":
			UiAutomator2Options uio = new UiAutomator2Options();
			uio.setPlatformName(PropertiesUtility.getProperty("platform_android"));
			uio.setPlatformVersion(PropertiesUtility.getProperty("platformVersion_android"));
			uio.setDeviceName(PropertiesUtility.getProperty("deviceName_android"));
			
			uio.setAutomationName(PropertiesUtility.getProperty("automationName_android"));
			uio.setAppPackage(PropertiesUtility.getProperty("appPackage"));
			uio.setAppActivity(PropertiesUtility.getProperty("appActivity"));
			
			// automatically install the app under test to the android device
			uio.setApp(Constants.APP_PATH);
			driver = new AndroidDriver(uio);
			break;
			
		case "ios":
			XCUITestOptions xct = new XCUITestOptions();
			xct.setPlatformName(PropertiesUtility.getProperty("platform_ios"));
			xct.setPlatformVersion(PropertiesUtility.getProperty("platformVersion_ios"));
			xct.setDeviceName(PropertiesUtility.getProperty("deviceName_ios"));
			
			xct.setAutomationName(PropertiesUtility.getProperty("automationName_ios"));
			// uio.setAppPackage(PropertiesUtility.getProperty("appPackage"));
			// uio.setAppActivity(PropertiesUtility.getProperty("appActivity"));
			
			// automatically install the app under test to the android device 
			xct.setApp(Constants.APP_PATH);
			driver = new IOSDriver(xct);
			break;
			
		default:
			break;
			
		}
		return driver;
	}
	
	// Take screenshot on test failure
		public  void takeScreenShot(String filepath) {
			// String path = Constants.SCREENSHOTS_PATH + CommonUtils.getStringDateAndTimeStamp()+".png";
			TakesScreenshot screen = (TakesScreenshot) driver;
			File src = screen.getScreenshotAs(OutputType.FILE);
			File dst = new File(filepath);
			try {
				Files.copy(src, dst);
				mylog.info("screen captured");
			}
			catch (IOException e) {
				mylog.error(e.getMessage());
				mylog.error("Not able to capture the screenshot");
				
			}
			
		}
		
		
		
	

}
