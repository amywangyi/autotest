package com.xc.autotest.pageobject;

import org.openqa.selenium.By;

/**
 * 员工操作页面模型
 * 
 * @author User
 *
 */
public class EmployeeOperationBy {

	/** 员工管理菜单定位器*/
	public static final By employeeMgmtMenu=By.xpath("//div[contains(text(),'员工管理')]");
	
	/** 新增员工按钮定位器*/
	public static final By addBtnLocator=By.xpath("//button[contains(.,'新建员工')]");

	/** 创建员工DIV定位器*/
	public static final By createEmployeeDiv=By.cssSelector("div[class='employee-create']");
	
	/** 员工名称定位器*/
	public static final By employeeName=By.cssSelector("input[placeholder='请输入员工名称']");
	
	/** 员工密码定位器*/
	public static final By employeePwd=By.cssSelector("input[placeholder='请输入密码']");
	
	/** 确认新增员工按钮定位器*/
	public static final By addEmployeeBtn=By.cssSelector("div[class='el-form-item__content']>button");
	
	/** 选择员工第一步骤*/
	public static final String selectRoleOne="$('.el-select--medium').click();";
	
	/** 选择员工第二步骤*/
	public static final String  selectRoleTwo="window.setTimeout(function(){$(\".el-select-dropdown__item>span:contains('代理商运营')\").click();},2000)";

	/** 设置客户*/
	public static final String setCustomer="window.setTimeout(function(){$('.el-checkbox.el-transfer-panel__item')[0].click();},3000)";
	
	/** 将选择的客户添加到购物车*/
	public static final String addCustomerToCar="window.setTimeout(function(){$(\".el-transfer__button>span>i[class='el-icon-arrow-right']\").click()},2000)";
}
