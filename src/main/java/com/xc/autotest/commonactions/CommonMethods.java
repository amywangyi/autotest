package com.xc.autotest.commonactions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;


/**
 * @author DELL
 *
 */
public class CommonMethods {

	public static void waitForPageLoaded(WebDriver driver) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			// Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 600);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	// wait element
	public static void waitElementVisible(By by, WebDriver driver) {
		int sleepSeconds = 5;
		int count = 0;
		while ((driver.findElements(by).size() == 0 || !driver.findElement(by).isDisplayed())
				&& count <= sleepSeconds) {
			sleep(3);
			count = count + 1;
		}
		printLog("wait count:" + count, "");
	}

	// sleep
	public static void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void printLog(String msg, String testName) {
		System.out.println(testName + "." + msg);
		Reporter.log(testName + "." + msg);
	}

	public static void refreshBrowser(WebDriver driver, String testName, String memo) {
		// printLog(memo,testName);
		driver.navigate().refresh();
		CommonMethods.waitForPageLoaded(driver);
	}

	public static void waitElement(By by, WebDriver driver) {
		int sleepSeconds = 20;
		int count = 0;
		while (driver.findElements(by).size() == 0 && count <= sleepSeconds) {
			sleep(3);
			count = count + 1;
		}
	}

	public static int getEleSize(WebDriver driver, By by) {
		return driver.findElements(by).size();
	}

	public static boolean getEleDisplay(WebDriver driver, By by) {
		return driver.findElement(by).isDisplayed();
	}

	public static void toBaseUrl(WebDriver driver, String baseUrl, ITestContext testContext) {
		String testName = testContext.getName();
		System.out.println("the baseUrl is :" + baseUrl);
		System.out.println("the driver is :" + driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		CommonMethods.printLog("baseUrl:" + baseUrl, testName);
		CommonMethods.waitForPageLoaded(driver);
		CommonMethods.sleep(3);
	}


	public static String getUserType(String userType) {
		String userTypeStr = "";
		// SUPPLIER
		if (userType.equals("SUPPLIER")) {
			userTypeStr = "supplierInfo";
			// PURCHASER
		} else if (userType.equals("PURCHASER")) {
			userTypeStr = "purchaserInfo";
			// THIRD
		} else {
			userTypeStr = "logistic usercenter";
		}
		return userTypeStr;
	}

	public static void sendKeys(WebDriver driver, By by, String value, String testName, String memo) {
		printLog(memo + ".value:" + value, testName);
		WebElement webElement = f(driver, by);
		printLog(webElement.isDisplayed() + "" + getEleSize(driver, by), testName);
		webElement.clear();
		webElement.sendKeys(value);
		printLog(webElement.getAttribute("value") + "", testName);
			}

	public static WebElement f(WebDriver driver, By by) {
		return driver.findElement(by);
	}

	public static void clickByMemo(WebDriver driver, By by, String memo, String testName) {
		printLog(memo, testName);
		waitElementVisible(by, driver);
		f(driver, by).click();
		CommonMethods.waitForPageLoaded(driver);
		printLog("after click url:" + driver.getCurrentUrl(), testName);
	}

	
	public static String getUrlTitle(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String title = (String) js.executeScript("return document.title");
		System.out.println("the current page's title:" + title);
		return title;
	}


	
	/**
	 * 清除任何元素的输入内容
	 */
	public static void clearElementContent(WebDriver driver,By whichClear,String testName) {
		CommonMethods.clickByMemo(driver, whichClear, "click CaB2bLoginBy."+whichClear+"", testName);
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.chord(Keys.CONTROL,"a")).perform();
		actions.sendKeys(Keys.BACK_SPACE).perform();
	}
	
	/**
	 * 刷新当前页面
	 */
	public static void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	/**
	 * 页面前进
	 */
	public static void forward(WebDriver driver) {
		driver.navigate().forward();	
	}
	
	
	/**
	 * 页面后退
	 */
	public static void back(WebDriver driver) {
		driver.navigate().back();
	}
	
	public static void moveToAElement(WebDriver driver,By by,String testName,String memo)
	{
		printLog(memo,testName);
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(by)).build().perform();
		CommonMethods.sleep(2);
	}
	
	public static String getEleText(WebDriver driver,By by,String testName,String memo)
	{
		printLog(memo,testName);
		waitElementVisible(by, driver);
		String text = f(driver, by).getText().trim();
		printLog(memo+".eletext:"+text,testName);
		return text;
	}
	
	public static void selectAOptionByText(WebDriver driver,By by,String text,String testName,String memo)
	{
		printLog(memo+".text:"+text,testName);
		waitElementVisible(by, driver);
		Select select = new Select(f(driver,by));
		select.selectByVisibleText(text);
		sleep(2);
	}
	
	
	
	
	
	
	
	
}
