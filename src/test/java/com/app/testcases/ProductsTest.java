package com.app.testcases;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.app.base.BasePage;
import com.app.base.BaseTest;
import com.app.pages.CartPage;
import com.app.pages.HomePage;
import com.app.pages.ProductsPage;
import com.app.utilities.ActionsUtility;
import com.app.utilities.Utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
;

public class ProductsTest extends BaseTest{
	
	@Test(description = " Testcase5: Verifying to be able to navigate to products page and add a product to the cart")
	public void verifyAddingProductToCart() throws InterruptedException {
		AndroidDriver driver = (AndroidDriver) getDriver("android");
		
		BasePage bPage = new BasePage(driver);
		HomePage hPage = new HomePage(driver);
		ProductsPage pPage = new ProductsPage(driver);
		
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
		
	}
	
	
	@Test(dependsOnMethods = {"verifyAddingProductToCart"}, description = " Testcase6: Verify the product and price in the cart")
	public void verifyProductAndProductPrice() throws InterruptedException {
		
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
		
		String noOfitems = bPage.extractText(pPage.noOfItemsinCart);
		Boolean bool = noOfitems.equals("1");
		assertEquals(bool, true);
		
		// bPage.clickButton(pPage.cartBtn, "Cart Button");
		//ActionsUtility.clickGestureById(driver, "com.androidsample.generalstore:id/appbar_btn_cart");
		
		ActionsUtility.singleTap(driver, 968, 139);
		Thread.sleep(8000);
		
		String cartTitleText = bPage.extractText(cPage.cartToolBarTitle);
		assertEquals(cartTitleText, "Cart");
		
		String productNameText = bPage.extractText(cPage.productName);
		assertEquals(productNameText, "Jordan 6 Rings");
		
		String afterPrice = bPage.extractText(cPage.productPrice);
		String purchasePriceText = bPage.extractText(cPage.purchasePrice);
		String[] priceList = purchasePriceText.split(" ");
		assertEquals(beforePrice, afterPrice);
		assertEquals(priceList[1], "165.0");
		
	}
	
	@Test(description = "TestCase7 : Verify able to navigate to products page and add 2 products to the cart and verify the price in the Cart Page")
	public void VerifyAddingTwoProductsAndVerifyTotalPrice() throws InterruptedException {
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
		
//		String productNameText = bPage.extractText(cPage.productName);
//		assertEquals(productNameText, "Jordan 6 Rings");
		
		List<WebElement> productNames = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName"));
		List<String> productNameText = new ArrayList<String>();
		for(WebElement product : productNames) {
			String text = bPage.extractText(product);
			productNameText.add(text);
		}
		
		assertEquals(productNameText.get(0), "Jordan 6 Rings");
		assertEquals(productNameText.get(1), "PG 3");
		
		
		List<WebElement> productPrices = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
		List<String> productPriceText = new ArrayList<String>();
		for(WebElement product : productPrices) {
			String text = bPage.extractText(product);
			productPriceText.add(text);
		}
		
		String price = "";
		for(int i = 0; i < productPriceText.size();i++) {
			for(int j = 1; j < productPriceText.get(i).length(); j++) {
				price += productPriceText.get(i).charAt(j);
			}
		}
		
		System.out.println(price);
		
		assertEquals(productPriceText.get(0), "$165.0");
		assertEquals(productPriceText.get(1), "$110.0");
		
}
	
	
	
	

}
