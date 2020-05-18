package com.xc.autotest.testcase;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.servlet.http.HttpUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.xc.autotest.commonactions.CommonMethods;
import com.xc.autotest.commonactions.ZTestReport;
import com.xc.autotest.testcase.BaseUtils;

@Listeners({ZTestReport.class})
public class BaseUtils {

	public WebDriver driver;
	public WebDriverWait m_Wait;
	public int mWaitTimeout = 600;

	public WebDriver getDriver() {
		return driver;
	}

	private String getHubLink(String testName) {

		String hubLink = "";
		Properties prop = new Properties();
		try {
			prop.load(BaseUtils.class.getClassLoader().getResourceAsStream("config.properties"));
			hubLink = prop.getProperty("hubLink");
			CommonMethods.printLog("hublink:" + hubLink, testName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hubLink;
	}
	@Parameters({ "browser", "baseUrl" })
	@BeforeTest(description="获取driver")
	public void before(String browseName, String baseUrl, ITestContext testContext) {
		String testName = testContext.getName();
		if (!browseName.isEmpty()) {

			String hubLink = getHubLink(testName);

			try {
				getWebDriver(browseName, hubLink, baseUrl, testName);
			} catch (Exception e) {
				CommonMethods.printLog("getwebdriver...", testName);
				
				//出现异常，关闭driver进程
				if(driver!=null){
					driver.close();
				}
			}

		}
		if (!baseUrl.isEmpty()) {
			CommonMethods.toBaseUrl(driver, baseUrl, testContext);
			while (driver.getCurrentUrl().equals("data:,")) {
				CommonMethods.toBaseUrl(driver, baseUrl, testContext);
			}
			CommonMethods.waitForPageLoaded(driver);
			CommonMethods.printLog("....before out:" + driver.getCurrentUrl() + "..baseUrl:" + baseUrl, testName);

		}
	}

	@AfterTest(description="释放driver")
	@Parameters({ "browser" })
	public void after(String browseName, ITestContext test) {
		if (!browseName.isEmpty() && driver != null) {
			try {
				//driver.quit();
			} catch (Exception e) {

			}
		}

	}

	public void getWebDriver(String browseName, String hubLink, String baseUrl, String testName) {
		if (browseName.toUpperCase().equals("CHROME")) {
			getWebDriverChrome(hubLink, baseUrl, testName);
		} else if (browseName.toUpperCase().equals("IE")) {
			getWebdriverIE(hubLink);
		} else if (browseName.toUpperCase().equals("FIREFOX")) {
			getWebdriverFirefox(hubLink, testName);
		} else if (browseName.toUpperCase().equals("PHANTOMJS")) {
			getPhantomjsDriver(hubLink, baseUrl, testName);
		}
		
		driver.manage().window().maximize();
		
	}

	private void getWebDriverChrome(String hubLink, String baseUrl, String testName) {
		try {
			ChromeOptions options = new ChromeOptions();
			File file = new File(getBrowserPath(testName, "chromePath"));
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver();
		} catch (Exception e) {
			CommonMethods.printLog(e.getMessage() + ".." + e.getStackTrace(), testName);
		}

	}
	
	protected JavascriptExecutor getJavascriptExecutor(){
		//添加jQuery的脚本
		String jqueryScript=loadResource("jquery-3.1.1.min.js");
		JavascriptExecutor executor=(JavascriptExecutor)driver;
		executor.executeScript(jqueryScript);
		return executor;
	}

	private String loadResource(String path) {
		InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		BufferedReader reader=new BufferedReader(new InputStreamReader(in));
		String line=null;
		StringBuilder sb=new StringBuilder();
		try{
			while((line=reader.readLine())!=null){
				sb.append(line);
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	private String getBrowserPath(String testName, String browserPath) throws IOException {

		String chromePath = "";
		Properties prop = new Properties();
		try {
			Properties propSystem = System.getProperties();
			String osName = propSystem.getProperty("os.name");
			CommonMethods.printLog("os:" + osName, testName);
			prop.load(BaseUtils.class.getClassLoader().getResourceAsStream("config.properties"));

			InputStream in = BaseUtils.class.getClassLoader().getResourceAsStream("config.properties");
			System.out.println("in is :"+in);
			if (osName.toUpperCase().contains("WINDOWS")) {
				chromePath = prop.getProperty(browserPath);
			} else if (osName.toUpperCase().contains("LINUX")) {
				chromePath = prop.getProperty(browserPath + "Linux");
			}
			CommonMethods.printLog("Path:" + chromePath, testName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chromePath;

	}

	private void getWebdriverIE(String hubLink) {
		try {
			DesiredCapabilities ieDesiredcap = DesiredCapabilities.internetExplorer();
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new RemoteWebDriver(new URL(hubLink), ieDesiredcap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private void getWebdriverFirefox(String hubLink, String testName) {
		try {
			File file = new File(getBrowserPath(testName, "firefoxPath"));
			System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
			driver = new FirefoxDriver();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getPhantomjsDriver(String hubLink, String baseUrl, String testName) {
		File file = new File(getPhantomjsPath(testName));
		@SuppressWarnings("deprecation")
		DesiredCapabilities phantomjsCapabilities = DesiredCapabilities.phantomjs();
		// DesiredCapabilities phantomjsCapabilities = new DesiredCapabilities();
		try {
			phantomjsCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
					file.getAbsolutePath());
			phantomjsCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
					new String[] { "--web-security=false", "--ignore-ssl-errors=yes", "--ssl-protocol=tlsv1" });
			driver = new RemoteWebDriver(new URL(hubLink), phantomjsCapabilities);
		} catch (Exception e) {
			CommonMethods.printLog(e.getMessage() + ".." + e.getStackTrace(), testName);
			e.printStackTrace();
		}
	}

	private String getPhantomjsPath(String testName) {
		String phantomjsPath = "";
		Properties prop = new Properties();
		try {
			Properties propSystem = System.getProperties();
			String osName = propSystem.getProperty("os.name");
			CommonMethods.printLog("os:" + osName, testName);
			prop.load(BaseUtils.class.getClassLoader().getResourceAsStream("config.properties"));
			if (osName.toUpperCase().contains("WINDOWS")) {
				phantomjsPath = prop.getProperty("phantomjsPath");
			} else if (osName.toUpperCase().contains("LINUX")) {
				phantomjsPath = prop.getProperty("phantomjsPathLinux");
			}
			CommonMethods.printLog("phantomjsPath:" + phantomjsPath, testName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return phantomjsPath;
	}

	public void iniMWait() {
		if (null == m_Wait) {
			m_Wait = new WebDriverWait(driver, mWaitTimeout);
		}
	}
	
	
	public WebElement f(By by)
	{
		//m_Wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return driver.findElement(by);
	}
	
	

}
