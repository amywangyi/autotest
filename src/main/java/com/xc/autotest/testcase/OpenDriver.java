package com.xc.autotest.testcase;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class OpenDriver {

	public WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	@Parameters({ "browser", "baseUrl" })
	@BeforeTest
	public void before(String browseName, String baseUrl, ITestContext testContext) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

		driver = new ChromeDriver();
		System.out.println("In OpenDriver: " + this.driver);
		driver.manage().window().maximize();
		driver.get(baseUrl);

		// waitForPageLoaded();

	}

	@AfterTest
	@Parameters({ "browser" })
	public void after(String browseName, ITestContext test)

	{
		driver.quit();

	}

	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			// Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 500);
			// wait.until(arg0)
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

}
