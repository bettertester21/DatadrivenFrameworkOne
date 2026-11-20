package com.bit.testcases;

import com.bit.base.BaseTest;
//import com.bit.pageobject.swaglabs.LoginPage;
import com.bit.pages.LoginPage;
import com.bit.utilities.DataProviders;
import com.bit.utilities.PropertyUtils;
import com.bit.utilities.TestUtil;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;

public class LoginTestOne extends BaseTest
{
//	@Test(dataProviderClass = DataProviders.class, dataProvider = "dp",description = "Login to Swags Labs Application")
//	
//	@Description("Test Description: Login test with valid and invalid credentials")
//	
	@Test(dataProviderClass = DataProviders.class, dataProvider = "dp",description = "Login to Swags Labs Application")
	public void loginTest(Hashtable<String, String> data)
	{
		log.info("In start of the loginTest()");

		if (!data.get("runmode").equals("Y")) {
			throw new SkipException("Skipping the case as the Run mode for data set is NO");
		}
		String userDir = System.getProperty("user.dir");
		String configPath = userDir + "/src/test/resources/properties/config.properties";
		//String excelPath = userDir + "/src/test/resources/testdata/Testdata.xlsx";
		String screenshotName = "Img_"+new SimpleDateFormat("yyyyMMddHHmm'.jpg'").format(new Date());
		String url = PropertyUtils.propertyLoader(configPath).getProperty("saucelabs.url");
		String username = data.get("username");
		String password = data.get("password");
		driver.get(url);
		log.info("Test application URL is loaded!");
		log.info("Page title is: "+driver.getTitle());
		log.info("Username: "+username+" and password is: "+password);  //For debugging
		String expPageTitle = "Swag Labs",actPageTitle;
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username,password);
		TestUtil.captureScreenshot(userDir + "/target/"+screenshotName);
		actPageTitle = driver.getTitle();
		System.out.println("driver.findElements(loginPage.getErrorMessage())");



		// {
		// 	log.info("Login Test is failed with invalid credentials!");
		// }
		// else
		// {
		// 	log.info("Login Test is passed with valid credentials!");
		// }
		Assert.assertEquals(actPageTitle,expPageTitle,"Login Test is not executed successfully!");
		log.info("In end of the loginTest()");
	}
}
