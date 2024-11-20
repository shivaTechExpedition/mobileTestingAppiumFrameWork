package com.app.base;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.utilities.ActionsUtility;
import com.app.utilities.ExtentReportUtility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage {
	public AppiumDriver driver;
	protected Logger mylog = LogManager.getLogger(BaseTest.class);
	protected ExtentReportUtility extentReportsUtility = ExtentReportUtility.getInstance();
	
	public BasePage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public BasePage(IOSDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	// Extract text
	public  String extractText(WebElement ele ) {
		if(ele.isDisplayed()) {
			return ele.getText();
		}
		
		return null;
	}
	
	// verify if text box present, if present, send keys to the text box
	public  void enterText(WebElement ele,String data,String objectName) {
		if(ele.isDisplayed()) {
			ele.clear();
			ele.sendKeys(data);
			mylog.info("data is entered in the "+ objectName);
		}
		else{
			mylog.error(objectName+" textbox is not diplayed");
		}
	}
	
	// dropdrown menu particular element click out of a list of menu dropdown elements
	public void dropDownElementSelection(List<WebElement> elems, String dropDownOptionText, String optionName) {
			// mylog.info( list.size());
			addImplicitWait(4);
			for(WebElement ele : elems) {
				if(ele.getText().equals(dropDownOptionText)) {
					ele.click();
					break;
				}
			}
			mylog.info(optionName + " option in the drop down menu clicked");
	}
	

	
	// Add fluent wait
	public Wait<AndroidDriver> addFluentWait(int seconds) {
		
		Wait<AndroidDriver> wait = new FluentWait<AndroidDriver>((AndroidDriver) driver)
			.withTimeout(Duration.ofSeconds(seconds))
			.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
		mylog.info("Adding fluent wait of "+ seconds +" seconds.");
		return wait;
	
	}
	
	// Add implicit wait
	public void addImplicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
		mylog.info("Adding implicit wait of "+ seconds +" seconds.");
	}
	
	// Add WebdriverWait
	public WebDriverWait  addWebDriverWait(int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		mylog.info("Adding WebDriverWait of "+ seconds +" seconds.");
		return wait;
	}
	
	//click on WebElement
	public void clickButton(WebElement ele, String elementName) {
		
		if(ele.isDisplayed()) {
			//ele.click();
			ActionsUtility.singleTap((AndroidDriver) driver, 538, 1729);
			mylog.info(elementName + " is clicked.");
		}
		else {
			mylog.info("Not able to click the button : " + elementName);
		}
		
	}
	
	
	
	

}
