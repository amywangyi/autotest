package com.xc.autotest.pageobject;

import org.openqa.selenium.By;

public class AdminTestCommonBy {
	public static By bd_search = By.id("kw");
	public static final By bd_click = By.id("su");
	public static By console_username = By.name("username");
	public static By console_password = By.name("password");
	public static By console_login = By.xpath("//*[@id=\"app\"]/div[1]/form/button");
	public static By is_login = By.cssSelector("p[class=task_name]");
	

	/* publish back msg */
	public static By msg_center_mng = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div/ul/div[3]/li/div/span");
	public static By msg_mng = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div/ul/div[3]/li/ul/a[2]/li/span");
	public static By back_msg_tab = By.cssSelector("#tab-2");
	public static By pub_msg = By.xpath("//*[@id=\"pane-2\"]/div/div[2]/a/span");

	public static By input_title = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/section/div/div[1]/div[3]/div[2]/div/input");

	public static By msg_pub_btn = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/section/div/div[1]/div[5]/div/button[4]/span");

	public static By sure_pub_btn = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/section/div/div[2]/div/div[3]/p/button[1]/span");

	/* show personal info */
	public static By sys_set = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div/ul/div[14]/li/div/span");
	public static By person_info = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div/ul/div[14]/li/ul/a[4]/li/span");
	public static By usr_info = By.cssSelector("label[for=username]");

	// mall godds audit--待指派处理
	public static By toTaskNo = By.xpath("//*[@id=\"no_assign_task\"]/div[1]/div/div[1]/input");
	public static By toTaskQueryBtn = By.xpath("//*[@id=\"no_assign_task\"]/div[1]/div/button[1]/span");
	public static By selectTask = By.cssSelector(".el-checkbox__inner");
	public static By pointTaskBtn = By.xpath("//*[@id=\"no_assign_task\"]/div[1]/div/button[3]/span");
	public static By roleInput = By.xpath("//*[@id=\"home\"]/div[6]/div/div/div[2]/div/form/div[2]/div/div/input");
	public static By roleQueryBtn = By
			.xpath("//*[@id=\"home\"]/div[6]/div/div/div[2]/div/form/div[3]/div/button[1]/span");
	public static By radioSelectRole = By.cssSelector(".el-radio__inner");
	// *[@id="home"]/div[6]/div/div/div[2]/div/div[3]/button[1]/span
	public static By sureRoleBtn = By.xpath("//*[@id=\"home\"]/div[6]/div/div/div[2]/div/div[3]/button[1]/span");

	// hando task pointed to me
	public static By taskToMeNo = By.xpath("//*[@id=\"own_task\"]/div[1]/div/div[1]/input");
	public static By taskToMeQueryBtn = By.xpath("//*[@id=\"own_task\"]/div[1]/div/button[1]/span");
	public static By clickTaskContent = By
			.xpath("//*[@id=\"own_task\"]/div[2]/div[3]/table/tbody/tr/td[2]/div/button/span");

	public static By mallGoodsAuditBtn = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/section/div/div[5]/span/button[1]/span");
	public static By mallGoodsAuditSureBtn = By.xpath("/html/body/div[2]/div/div[3]/button[2]/span");

	// goodAuditResult
	public static By goodAuditResult = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/section/div/div[4]/div/div[3]/table/tbody/tr[2]/td[2]/div/div/span");

	public static void main(String args[]) {
		System.out.println("var info " + AdminTestCommonBy.bd_search);
	}
}
