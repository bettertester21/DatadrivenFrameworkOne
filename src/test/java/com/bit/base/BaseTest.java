package com.bit.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.bit.utilities.ExcelReader;
import com.bit.utilities.ExtentManager;
import com.bit.utilities.PropertyUtils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.core.Logger;
// import java.util.logging.Logger;

public class BaseTest {

	protected static WebDriver driver = null;
	protected WebDriverWait wait = null;
	public  ExcelReader excel = null;
	protected Properties config = new Properties();
	protected FileInputStream fis = null;
	//    protected static Logger log;
	// protected static final Logger log = Logger.getLogger(BaseTest.class.getName());
	protected static final Logger log = LogManager.getLogger(BaseTest.class);
	protected static String browser, appUrl;
	public  String userDir = System.getProperty("user.dir");
//	public String excelPath = userDir + "/src/test/resources/testdata/Testdata.xlsx";
	public String excelPath = "/home/balaji/JavaSeleniumFiles/EclipseWorkspace/DatadrivenFramework/src/test/resources/testdata/Testdata.xlsx";
	public ExtentReports extent = null;
    public ExtentTest test = null;
    

	@BeforeSuite
	public void setUp() {
		
		// Set path programmatically
File file = new File(userDir  + "/src/test/resources/config/log4j2.xml");
LoggerContext context = (LoggerContext) LogManager.getContext(false);
context.setConfigLocation(file.toURI());
		log.info("In start of the setUp()");
//		String userDir, browser, url;
		int implicitWait, explicitWait;
		//    Platform platform = Platform.getCurrent();
		Platform platform = Platform.getCurrent();        
		log.info("Platform is: " + platform.toString());
		// extent = ExtentManager.createExtentReports();
		extent = ExtentManager.getInstance();
//		userDir = System.getProperty("user.dir");
//		String excelPath = userDir + "src/test/resources/testdata/Testdata.xlsx";
		log.info("userDir: "+userDir);
//		String excelPath = userDir + "/src/test/resources/testdata/Testdata.xlsx";
		log.info("excelPath: "+excelPath);
//		String excelPath =System.getProperty("user.dir") +
//         "/src/test/resources/excel/SwagLabSuite.xlsx";
		String configPath = userDir + "/src/test/resources/properties/config.properties";
		log.info("configPath: "+configPath);
		
		excel = new ExcelReader(excelPath);
		
		if (driver == null) {
			try {
				//               
				config = PropertyUtils.propertyLoader(configPath);

				browser = config.getProperty("browser", "CHROME");
				log.info("Browser: " + browser);
				            appUrl = config.getProperty("saucelabs.url");
				            log.info("appUrl: " + appUrl);
				            implicitWait = Integer.parseInt(config.getProperty("implicit.wait"));
				            explicitWait = Integer.parseInt(config.getProperty("explicit.wait"));
				                ChromeOptions options = new ChromeOptions();
				                options.addArguments("--no-sandbox");
				//                options.addArguments("--headless"); //!!!should be enabled for Jenkins
				                options.addArguments("--disable-dev-shm-usage"); //!!!should be enabled for Jenkins
				                options.addArguments("--window-size=1920x1080"); //!!!should be enabled for Jenkins
				            if (browser.equalsIgnoreCase("CHROME")) {
				                switch (platform) {
				                    case MAC:
				                        log.info("MAC Platform");
				//                        driver = new ChromeDriver();
				                        driver = new ChromeDriver(options);
				                        break;
				                    case WINDOWS:
				                        log.info("WINDOWS Platform");
				//                        driver = new ChromeDriver();
				                        driver = new ChromeDriver(options);
				                        break;
				
				                    case LINUX:
				                        log.info("LINUX Platform");
				//                        driver = new ChromeDriver();
				                        driver = new ChromeDriver(options);
				                        break;
				                    default:
				                        log.info("Please select valid browser!\n");
				                        break;
				                }
				
				
				//                driver = new ChromeDriver(options);

				
				                wait = new WebDriverWait(driver, Duration.ofMinutes(explicitWait));
				                log.info("Chrome browser is loaded!");
				            }
				            driver.manage().window().maximize();

				        driver.get(appUrl);

				log.info("In end of the setUp()");
			}catch(Exception e)
			{
				log.info(e.getMessage());
			}

		}
	}

	@AfterSuite
	public void tearDown() {
		log.info("In start of the tearDown()");
		if (driver != null) {
			driver.quit();
			log.info("Browser is closed!");
		}
		log.info("In end of the tearDown()");
	}

	public WebDriver getDriver() {
		return driver;
	}

}