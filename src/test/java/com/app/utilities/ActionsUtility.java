package com.app.utilities;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.Origin;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ActionsUtility{
	
	public static final Origin VIEW = Origin.viewport();
	public static final Duration ZERO_SEC = Duration.ZERO;
	
	
	// single tap / click / touch
	public static void singleTap(AndroidDriver driver, int x, int y) {
		PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
		Point tapPoint = new Point(x, y);
		Sequence dt = new Sequence(finger, 1);
		
		dt.addAction(finger.createPointerMove(ZERO_SEC, VIEW, tapPoint.x, tapPoint.y));
		dt.addAction(finger.createPointerDown(0));
		dt.addAction(finger.createPointerUp(0));
		driver.perform(Arrays.asList(dt));
	}
	
	// click gesture
	public static void clickGestureByXpath(AndroidDriver driver, String xpath) {
		WebElement element = driver.findElement(AppiumBy.xpath(xpath));
		driver.executeScript("mobile:clickGesture",	ImmutableMap.of("elementId", ((RemoteWebElement)element).getId()));
	}
	
	// click gesture
		public static void clickGestureById(AndroidDriver driver, String id) {
			WebElement element = driver.findElement(AppiumBy.xpath(id));
			driver.executeScript("mobile:clickGesture",	ImmutableMap.of("elementId", ((RemoteWebElement)element).getId()));
		}
	
	// double tap/ click / touch
	public static void doubleTap(AndroidDriver driver, int x, int y) {
		PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
		Point tapPoint = new Point(x, y);
		Sequence dt = new Sequence(finger, 1);
		dt.addAction(finger.createPointerMove(ZERO_SEC, VIEW, tapPoint.x,tapPoint.y));
		dt.addAction(finger.createPointerDown(0));
		dt.addAction(finger.createPointerUp(0));
		dt.addAction(finger.createPointerDown(0));
		dt.addAction(finger.createPointerUp(0));
		driver.perform(Arrays.asList(dt));
	}
	
	// long press using Pause class
	public static void longPress(AndroidDriver driver, int x, int y) {
		PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
		Point tapPoint = new Point(x, y);
		Sequence tap = new Sequence(finger, 1);
		tap.addAction(finger.createPointerMove(ZERO_SEC, VIEW, tapPoint.x,tapPoint.y));
		tap.addAction(finger.createPointerDown(0));
		tap.addAction(new Pause(finger, Duration.ofSeconds(1)));
		tap.addAction(finger.createPointerUp(0));
		driver.perform(Arrays.asList(tap));
		
	}
	
	// scroll up : to scroll up perform scrolling from bottom to top 
	
	public static void scrollUp(AndroidDriver driver,double offsetY1,double offsetY2) {
		Dimension windowSize = driver.manage().window().getSize();
		int center = (int)(windowSize.width/2);
		int startY = (int)(windowSize.height * offsetY1);
		int endY = (int)(windowSize.height * offsetY2);
		
		PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
		Sequence scroll = new Sequence(finger, 0);
		scroll.addAction(finger.createPointerMove(ZERO_SEC, VIEW, center, startY));
		scroll.addAction(finger.createPointerDown(0));
		scroll.addAction(finger.createPointerMove(Duration.ofSeconds(1), VIEW, center, endY));
		scroll.addAction(finger.createPointerUp(0));
		driver.perform(Arrays.asList(scroll));
		
	}
	
	// scroll using UiScrollable and UiSelector class
	public static void scrollForward(AndroidDriver driver, String locator) {
		
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().xpath("+ locator +").scrollForward()"));
	}
	

}
