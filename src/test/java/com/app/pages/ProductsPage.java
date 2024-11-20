package com.app.pages;

import org.openqa.selenium.WebElement;

import com.app.base.BasePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends BasePage{
	public AppiumDriver driver;
	
	public ProductsPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public ProductsPage(IOSDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	public WebElement productsTitle;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/rvProductList")
	public WebElement ProductsList;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\" and @text=\"Jordan 6 Rings\"]")
	public WebElement productDetail;
	
	
	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[1]")
	public WebElement addToCart;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement cartBtn;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/counterText")
	public WebElement noOfItemsinCart;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\" and @text=\"$165.0\"]")
	public WebElement productPrice;
	
	
	
}
