package com.automation.vytrack.login;

import com.automation.pages.LoginPage;
import com.automation.vytrack.vytrack.AbstractTestBase;
import com.automation.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewLoginTest extends AbstractTestBase {

    @Test
    public void verifyPageTitle(){
        //test --> ExtentTest object
        //we must add to every test at the beginning
        //test = report.createTest("Test name");
        test = report.createTest("Verify page title");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        Assert.assertEquals(Driver.getDriver().getTitle(), "Dashboard");
        //if assertion passed, it will set test status in report to passed
        test.pass("Page title Dashboard was verified");
    }



    /**
     * Enter
     */
    @Test
    public void verifyWarningMessage(){
       LoginPage loginPage = new LoginPage();
       loginPage.login("wrong", "wrong");
       Assert.assertEquals(loginPage.getWarningMessageText(),"Invalid user name or password.");

    }
}
