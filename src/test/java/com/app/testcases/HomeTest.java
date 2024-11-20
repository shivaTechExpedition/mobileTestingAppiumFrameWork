package com.app.testcases;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import com.app.base.BasePage;
import com.app.base.BaseTest;
import com.app.pages.HomePage;
import com.app.pages.ProductsPage;
import com.app.utilities.ActionsUtility;
import com.app.utilities.Utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;


public class HomeTest extends BaseTest{
	
	@Test(priority = 1, description = "TestCase1 : Verify if the general store app installed to the anroid device and if installed launch the app")
	public void test_IfAppInstalledAndLaunch() throws InterruptedException{
		
		AndroidDriver driver = (AndroidDriver) getDriver("android");
		BasePage bPage = new BasePage(driver);
		
		bPage.addImplicitWait(10);
		
		String appPackage = "com.androidsample.generalstore";
		List<String> actualList = Utils.runProcess(true, "adb shell cmd package list packages | Findstr package:com.androidsample.generalstore");
		
		String currentPackage = driver.getCurrentPackage();
		String currentActivity = driver.currentActivity();
		
		mylog.info("current app package and current app activity is: "+ currentPackage+ " and "+ currentActivity);
		
		String activity = driver.currentActivity();
		mylog.info("current activity"+ activity);
		
		assertEquals(actualList.get(0), "package:"+appPackage, " app is not installed" );
		assertEquals(appPackage, currentPackage);
		assertEquals(currentActivity, ".SplashActivity");
		
		String mainActivity = driver.currentActivity();
		System.out.println("Main activity" + mainActivity);
		
	}
	
	
	@Test(priority = 2, dependsOnMethods = {"test_IfAppInstalledAndLaunch"}, description = "TestCase2 : Verify Launch page fields")
	
	public void verifyLaunchPageFields() throws InterruptedException {
		
		AndroidDriver driver = (AndroidDriver) getDriver("android");
		
		BasePage bPage = new BasePage(driver);
		HomePage hPage = new HomePage(driver);
		
		bPage.addImplicitWait(10);
		
		String toolBarTitleText = hPage.getWebElementText(hPage.toolBarTitle);
		assertEquals(toolBarTitleText, "General Store");
		
		String dropDownMenuText = hPage.getWebElementText(hPage.dropDownMenuEle);
		assertEquals(dropDownMenuText, "Afghanistan");
		
		String EnterNameLabelTxt = hPage.getWebElementText(hPage.enterNameLabel);
		assertEquals(EnterNameLabelTxt, "Your Name");
		
		String namePlaceHolderText = hPage.getWebElementText(hPage.namePlaceHolder);
		assertEquals(namePlaceHolderText, "Enter name here");
		
		String genderLabelText = hPage.getWebElementText(hPage.genderLabel);
		assertEquals(genderLabelText, "Gender");

		WebDriverWait wait = bPage.addWebDriverWait(0);
		wait.until(ExpectedConditions.elementToBeClickable(hPage.letsShopBtn));
		String letsShopBtnText = hPage.getWebElementText(hPage.letsShopBtn);
		assertEquals(letsShopBtnText, "Let's  Shop");
		
	}
	
	// not able to get the toast message : needs revisit
	@Test(priority = 3, dependsOnMethods = { "test_IfAppInstalledAndLaunch" }, description = "TestCase3 : NegativeTestCase : Verify launch page mandatory fields")
	public void verifyLaunchPageMandatoryFields() throws InterruptedException {
		AndroidDriver driver = (AndroidDriver) getDriver("android");
		
		BasePage bPage = new BasePage(driver);
		HomePage hPage = new HomePage(driver);
		
		bPage.addImplicitWait(10);
		
//		Boolean beforeLetsShopBtnClick = hPage.toastMessage.isDisplayed();
//		assertEquals(beforeLetsShopBtnClick, false, "Toast Message is present before clicking the Let's shop button : Test Fail");
		
		bPage.clickButton(hPage.letsShopBtn, "Lets Shop Button");
		
		bPage.addWebDriverWait(5).until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.Toast")));
		
		
		String toastMessageText = bPage.extractText(hPage.toastMessage);
		assertEquals(toastMessageText, "Please enter your name");
		
	}
	
	@Test(dependsOnMethods = {"test_IfAppInstalledAndLaunch"}, description = "TestCase4 : Verify LaunchPage providing valid inputs")
	public void verifyLaunchPage() throws InterruptedException {
		AndroidDriver driver = (AndroidDriver) getDriver("android");
		
		BasePage bPage = new BasePage(driver);
		HomePage hPage = new HomePage(driver);
		bPage.addImplicitWait(5);
		
		// bPage.dropDownElementSelection(hPage.dropDownElements, "India", "India");
		ActionsUtility.scrollForward(driver, "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Norway\"]");
		
		bPage.enterText(hPage.namePlaceHolder, "name", "EnterName text field");
		
		// bPage.clickButton(hPage.letsShopBtn, "Lets Shop Button");
		ActionsUtility.singleTap(driver, 304, 1530);
		String currentActivity = driver.currentActivity();
		
		assertEquals(currentActivity, ".AllProductsActivity");
		
	}
	
	
	
	
	
	
		
}
