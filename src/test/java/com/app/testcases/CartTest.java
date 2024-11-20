package com.app.testcases;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.app.base.BasePage;
import com.app.base.BaseTest;
import com.app.pages.CartPage;
import com.app.pages.HomePage;
import com.app.pages.ProductsPage;
import com.app.utilities.ActionsUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class CartTest extends BaseTest{
	
	@Test(description = "TestCase8 : Verify Cart Page Terms and Notifications checkbox")
	public void verifyCartPageTermsAndCheckBox() throws InterruptedException {
		AndroidDriver driver = (AndroidDriver) getDriver("android");
		
		BasePage bPage = new BasePage(driver);
		HomePage hPage = new HomePage(driver);
		ProductsPage pPage = new ProductsPage(driver);
		CartPage cPage = new CartPage(driver);
		
		bPage.addImplicitWait(10);
		
		bPage.enterText(hPage.namePlaceHolder, "name", "EnterName text field");
		bPage.clickButton(hPage.letsShopBtn, "Let's shop");
		
		String currentActivity = driver.currentActivity();
		assertEquals(currentActivity, ".AllProductsActivity");

		
		String productTitle = bPage.extractText(pPage.productsTitle);
		assertEquals(productTitle, "Products");
		
		ActionsUtility.scrollUp(driver, 0.9, 0.1);
		ActionsUtility.scrollUp(driver, 0.9, 0.1);
		
		ActionsUtility.clickGestureByXpath(driver, "(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]");
		
		String noOfitems = bPage.extractText(pPage.noOfItemsinCart);
		Boolean bool = noOfitems.equals("1");
		assertEquals(bool, true);
		
		// bPage.clickButton(pPage.cartBtn, "Cart Button");
		//ActionsUtility.clickGestureById(driver, "com.androidsample.generalstore:id/appbar_btn_cart");
		
		ActionsUtility.singleTap(driver, 968, 139);
		Thread.sleep(8000);
		
		String cartTitleText = bPage.extractText(cPage.cartToolBarTitle);
		assertEquals(cartTitleText, "Cart");
		
		ActionsUtility.longPress((AndroidDriver) driver, 575, 1986);
		
		ActionsUtility.singleTap((AndroidDriver) driver, 863, 1388);
		
		ActionsUtility.singleTap(driver, 76, 160);
		Thread.sleep(5000);
		
		assertEquals(productTitle, "Products");
		
		String addedToCartText = bPage.extractText(cPage.AddedToCart);
		assertEquals(addedToCartText, "ADDED TO CART");
		
		ActionsUtility.singleTap(driver, 968, 139);
		Thread.sleep(3000);
		
		String checkBoxText = bPage.extractText(cPage.checkBox);
		Boolean boolenValue = checkBoxText.contains("Send me e-mails on discounts");
		assertEquals(boolenValue, true);
		
		
	}
	
	@Test(description = "verify Webview of the general store application")
	public void verifyWebviewOfTheApp() throws InterruptedException {
		AndroidDriver driver = (AndroidDriver) getDriver("android");
		
		BasePage bPage = new BasePage(driver);
		HomePage hPage = new HomePage(driver);
		ProductsPage pPage = new ProductsPage(driver);
		CartPage cPage = new CartPage(driver);
		
		bPage.addImplicitWait(10);
		
		bPage.enterText(hPage.namePlaceHolder, "name", "EnterName text field");
		bPage.clickButton(hPage.letsShopBtn, "Let's shop");
		
		String currentActivity = driver.currentActivity();
		assertEquals(currentActivity, ".AllProductsActivity");
	
		
		String productTitle = bPage.extractText(pPage.productsTitle);
		assertEquals(productTitle, "Products");
		
		
		ActionsUtility.scrollUp(driver, 0.9, 0.1);
		ActionsUtility.scrollUp(driver, 0.9, 0.1);
		
		
		String beforePrice = bPage.extractText(pPage.productPrice);
		ActionsUtility.clickGestureByXpath(driver, "(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]");
		
		ActionsUtility.scrollUp(driver, 0.9, 0.1);
		ActionsUtility.scrollUp(driver, 0.9, 0.1);
		ActionsUtility.clickGestureByXpath(driver, "(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[1]");
		
		String noOfitems = bPage.extractText(pPage.noOfItemsinCart);
		Boolean bool = noOfitems.equals("2");
		assertEquals(bool, true);
		
		// bPage.clickButton(pPage.cartBtn, "Cart Button");
		//ActionsUtility.clickGestureById(driver, "com.androidsample.generalstore:id/appbar_btn_cart");
		
		ActionsUtility.singleTap(driver, 968, 139);
		Thread.sleep(4000);
		
		String cartTitleText = bPage.extractText(cPage.cartToolBarTitle);
		assertEquals(cartTitleText, "Cart");
		
		ActionsUtility.singleTap(driver, 527, 1808);
		
		Thread.sleep(8000);
		
		Set<String> contextHandles = driver.getContextHandles();
		for(String contextHandle : contextHandles) {
			System.out.println(contextHandle);
			mylog.info(contextHandle);
		}
		
		
		
		driver.context(contextHandles.toArray()[1].toString());
		Boolean booleanVal = contextHandles.toArray()[1].toString().equals("WEBVIEW_com.androidsample.generalstore");
		assertEquals(booleanVal, true, "Not a WebView : TestCase failed");
		
		
		ActionsUtility.singleTap(driver, 845, 2147);
		Thread.sleep(3000);
		Set<String> contexts = driver.getContextHandles();
		for(String contextHandle : contexts) {
			System.out.println(contextHandle);
			mylog.info(contextHandle);
		}
		
		driver.context(contexts.toArray()[0].toString());
		Boolean booleanValue = contexts.toArray()[1].toString().equals("NATIVE_APP");
		assertEquals(booleanValue, true, "Not a Native View : TestCase failed");
		
		
	}

}
