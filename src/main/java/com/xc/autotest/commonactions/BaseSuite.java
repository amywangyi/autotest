package com.xc.autotest.commonactions;

import java.io.File;
import java.io.IOException;

//import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseSuite {
	protected GeckoDriverService service;

	@BeforeSuite
	public void beforeSuite() throws IOException {
		System.out.println("Debug OutPut Info11:" + this.getClass().getSimpleName()
				+','+ new Throwable().getStackTrace()[0].getMethodName());
		/*
		 * //<<note:if not using the FirefoxBinary File>> service = new
		 * GeckoDriverService.Builder().usingDriverExecutable(new
		 * File("C:\\geckodriver.exe")) .usingPort(5555).usingFirefoxBinary(new
		 * FirefoxBinary(new File("C:\\geckodriver"))).build(); service.start();
		 */
		 
		
		service = new GeckoDriverService.Builder().usingDriverExecutable(new File("src/main/java/resources/geckodriver.exe"))
				.usingPort(5555).build();
		System.out.println("service:"+service);
		service.start();
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("Debug OutPut Info11:" + this.getClass().getSimpleName()
				+','+ new Throwable().getStackTrace()[0].getMethodName());
		service.stop();
	}
}
