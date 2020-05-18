package com.xc.autotest.pageobject;

import org.openqa.selenium.By;

/**
 * 员工操作页面模型
 * 
 * @author User
 *
 */
public class LoginBy {

	/** 登录页面用户名输入框定位器*/
	public static final By loginUserName=By.cssSelector("input[placeholder='请输入账户']");
	
	/** 登录页面用户密码输入框定位器*/
	public static final By loginPassword=By.cssSelector("input[placeholder='请输入密码']");
	
	/** 登录页面登录按钮*/
	public static final By loginButton=By.className("login-submit");
}
