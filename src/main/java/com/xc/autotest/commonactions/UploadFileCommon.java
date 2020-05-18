package com.xc.autotest.commonactions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

/**
 * @description: use uploadFile(WebDriver driver, By uploadFileButton,
 *               ITestContext testContext)
 * @name: UploadFileCommon.java
 * @author: heheda
 * @date: 21:06 2019/04/19
 **/

public class UploadFileCommon {

	public static void uploadFile(WebDriver driver, By uploadFileButton, String filePath, ITestContext testContext) {
		String testName = testContext.getName();
		// define the path of img that you will upload
		StringSelection filept = new StringSelection(filePath);
		// copy the imgPath into setContents
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filept, null);
		System.out.println("selection" + filept);
		// click button to upload file,get file from ftp server???
		CommonMethods.clickByMemo(driver, uploadFileButton, "click GoodsRleaseBy.goodsImg", testName);
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}