package com.bit.rough;

import com.bit.base.BaseTest;
import com.bit.pages.LoginPage;
//import com.bit.pages.LoginPage;
import com.bit.utilities.*;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Date;

//import static com.bit.utilities.Constants.SUITENAME_COL;
//import static com.bit.utilities.TestUtil.screenshotName;
import static org.testng.Assert.assertEquals;


public class TestConfiguration extends BaseTest {
	
	@Test
	public void firstTest()
	{
		System.out.print("First Test");
	}

//  @Test(dataProviderClass = DataProviders.class, dataProvider = "dp",description = "Login to Swags Labs Application", enabled = false)
//     @Description("Test Description: Login test with valid and invalid credentials")
@Test(dataProviderClass = DataProviders.class, dataProvider = "dp")
    public void loginTest(Hashtable<String, String> data)
    {
        try {

            log.info("In validate Basic Test Configuration test");
            String screenshotName = "Img_"+new SimpleDateFormat("yyyyMMddHHmm'.jpg'").format(new Date());
            // ExcelReader excel = new ExcelReader(Constants.SUITE_XL_PATH);
//            DataUtil.checkExecution(SUITENAME_COL, "LoginTest", data.get("runmode"), excel);
            // DataUtil.checkExecution("LoginTest", "LoginTest", data.get("runmode"), excel);
            String username = data.get("username");
            String password = data.get("password");
            String actPageTitle = null, expPageTitle = "Swag Labs";
            log.info("Page title is: " + driver.getTitle());
            log.info("Username: " + username + " and password is: " + password);  //For debugging
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(username, password);
            TestUtil.captureScreenshot(userDir + "/target/" + screenshotName);
            actPageTitle = driver.getTitle();
            Assert.assertEquals(actPageTitle, expPageTitle, "Login Test is not executed successfully!");
            log.info("In end of the loginTest()");
//        assertEquals(0,1);
        }catch(Exception e)
        {
            log.info("Exception message is: "+e.getMessage());
        }
    }
}
