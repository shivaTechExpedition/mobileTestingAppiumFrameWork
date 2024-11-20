package com.app.pages;

import org.openqa.selenium.WebElement;

import com.app.base.BasePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CartPage extends BasePage {
	public AppiumDriver driver;
	
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public CartPage(IOSDriver driver){
		super(driver);
		this.driver = driver;
	}
	

	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	public WebElement cartToolBarTitle;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	public WebElement productName;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	public WebElement productPrice;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	public WebElement purchasePrice;
	
	@AndroidFindBy(id = "android:id/button1")
	public WebElement popupCloseBtn;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\" and @text=\"ADDED TO CART\"]")
	public WebElement AddedToCart;
	
	@AndroidFindBy(className = "android.widget.CheckBox")
	public WebElement checkBox;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	public WebElement visitToWebsite;
	

}
