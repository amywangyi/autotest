package com.xc.autotest.testcase;
import org.testng.ITestContext;

import com.xc.autotest.commonactions.CommonMethods;
import com.xc.autotest.commonactions.ZTestReport;
import com.xc.autotest.pageobject.EmployeeOperationBy;
import com.xc.autotest.pageobject.LoginBy;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


/**
 * 模拟创建代理商员工账号
 * 
 * @author User
 *
 */
@Listeners({ZTestReport.class})
public class CreateEmployee  extends BaseUtils{
	

	/**
	 * 创建代理商员工 
	 * @param searchKeyword
	 * @param testContext
	 */
	@Test
	@Parameters({"username","password","homeUrl","employeeName"})
	public void createEmployee(String username, String password,String homeUrl,String employeeName,ITestContext testContext)throws Exception {
		
		//1.登录管理系统  http://ht.agent.boleme.net/#/login
		CommonMethods.toBaseUrl(driver, homeUrl, testContext);
		
		//输入账号
		WebElement userNameInput=driver.findElement(LoginBy.loginUserName);
		Assert.assertNotNull(userNameInput,"未定位到准确的登录用户名输入框!");
		userNameInput.clear(); 
		userNameInput.sendKeys(username);
		
		//输入密码
		WebElement passwordInput=driver.findElement(LoginBy.loginPassword);
		Assert.assertNotNull(passwordInput,"未定位到准确的登录密码输入框!");
		passwordInput.clear();
		passwordInput.sendKeys(password);
		
		//登录按钮
		WebElement loginButton=driver.findElement(LoginBy.loginButton);
		Assert.assertNotNull(loginButton,"未定位到登录按钮!");
		
		loginButton.click();
		
		//等待页面加载
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(EmployeeOperationBy.employeeMgmtMenu));
		
		//找到员工管理菜单并点击进入员工管理
		driver.findElement(EmployeeOperationBy.employeeMgmtMenu).click();
		
		//新增员工
		new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(EmployeeOperationBy.addBtnLocator));
		WebElement createEmployeeButton=driver.findElement(EmployeeOperationBy.addBtnLocator);
		createEmployeeButton.click();
		
		createNewEmployee(employeeName,password);

	}

	private void createNewEmployee(String employeeName,String password) throws Exception {
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(EmployeeOperationBy.createEmployeeDiv));
		
		//1.输入员工名称
		WebElement employeeNameInput=driver.findElement(EmployeeOperationBy.employeeName);
		employeeNameInput.clear();
		
		//为避免员工号重复，员工姓名加上时间戳
		String seed="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		int lengthSeed=new Random().nextInt(8);
		if(lengthSeed<=0){
			lengthSeed=1;
		}
		
		StringBuilder suffix=new StringBuilder();
		Random random=new Random(seed.length());
		for(int i=0;i<lengthSeed;i++){
			int index=random.nextInt(seed.length());
			if(index<=0){
				index=0;
			}
			suffix.append(seed.charAt(index));
		}
		
		employeeNameInput.sendKeys(employeeName+suffix.toString());
		
		//2.员工密码
		WebElement employeePasswordInput=driver.findElement(EmployeeOperationBy.employeePwd);
		employeePasswordInput.clear();
		employeePasswordInput.sendKeys(password);
		
		//3.员工角色（由于不是标准的select下拉框，需要单独模拟）
		//3.1 先模拟点击下拉框
		
		JavascriptExecutor executor=getJavascriptExecutor();
		executor.executeScript(EmployeeOperationBy.selectRoleOne);
		
		
		//3.2根据弹出的选择框，选择第一个(客户端js等待2秒后执行，否则无法选中下拉框的值）
		executor.executeScript(EmployeeOperationBy.selectRoleTwo);
		
		//4.分配客户，分配第一个客户(css选择器钟多个class用.号分割)
		executor.executeScript(EmployeeOperationBy.setCustomer);
		
		Thread.sleep(1000*2);
		//4.1 加入到购物车中
		executor.executeScript(EmployeeOperationBy.addCustomerToCar);
		
		Thread.sleep(1000*2);
		//5.创建员工按钮
		WebElement createEmployeeButton=driver.findElement(EmployeeOperationBy.addEmployeeBtn);
		createEmployeeButton.click();
		
		//创建完成后的校验
		
	}

}
