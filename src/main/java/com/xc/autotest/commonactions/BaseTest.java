package com.xc.autotest.commonactions;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest extends BaseSuite {
	protected WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		System.out.println("Debug OutPut Info22:" + this.getClass().getSimpleName()
				+','+ new Throwable().getStackTrace()[0].getMethodName());
		
		URL url = service.getUrl();
		System.out.println("url:"+url);
		driver = new RemoteWebDriver(url,DesiredCapabilities.firefox());
		driver.manage().window().maximize();
	}
		
	@AfterTest
	public void afterTest() {
		System.out.println("Debug OutPut Info22:" + this.getClass().getSimpleName()
				+','+ new Throwable().getStackTrace()[0].getMethodName());
		
		driver.quit();
	}
}
