package com.app.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.app.base.BasePage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;


public class HomePage extends BasePage{
	
	AppiumDriver driver;
	
	public HomePage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public HomePage(IOSDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	public WebElement toolBarTitle;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	public WebElement dropDown;
	
	@AndroidFindBy(className = "android.widget.ListView")
	public List<WebElement> dropDownElementList; 
	
	@AndroidFindBy(id = "android:id/text1")
	public WebElement dropDownMenuEle;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Your Name\"]" )
	public WebElement enterNameLabel;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	public WebElement namePlaceHolder;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Gender\"]")
	public WebElement genderLabel;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	public WebElement letsShopBtn;
	
	
	@AndroidFindBy(className  = "android.widget.Toast")
	public WebElement toastMessage;
	
	public String getWebElementText(WebElement ele) {
		String text = extractText(ele);
		mylog.info("Extracted text is: " + text );
		return text;
	}
	
	public void dropDownClick() {
		clickButton(dropDown, "DropDown");
	}
	
	
		
	
}

