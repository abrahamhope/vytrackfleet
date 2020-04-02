package com.automation.vytrack.login;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTests {
    private WebDriver driver;
    private String URL = "https://qa2.vytrack.com/user/login";
    //credentials for store manager
    private String username = "storemanager51";
    private String password = "UserUser123";
    private By usernameBy = By.id("prependedInput");
    private By passwordBy =By.id("prependedInput2");
    private By loginBtnBy = By.id("_submit");
    private By warningMessageBy= By.xpath("//*[@id=\"login-form\"]/fieldset/div[1]");

    @Test(description = "verify that warning message displays when user enters invalid username")
    public void invalidUsername(){
        driver.get(URL);
        driver.findElement(usernameBy).sendKeys("invalidusername");
        driver.findElement(passwordBy).sendKeys("UserUser123");
        driver.findElement(loginBtnBy).click();
        BrowserUtils.wait(3);

        WebElement warningElement = driver.findElement(warningMessageBy);
        assertTrue(warningElement.isDisplayed());
        BrowserUtils.wait(2);
    }

    @Test(description = "login as store manager and verify that title equals to Dashboard")
    public void loginAsStoreManager(){
        driver.get(URL);
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password);
        driver.findElement(loginBtnBy).click();
        BrowserUtils.wait(3);
        assertEquals(driver.getTitle(),"Dashboard");
        BrowserUtils.wait(3);
    }


    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();

    }


    @AfterMethod
    public void tearDown(){
        // if WebDriver object alive,
        if(driver!=null){
            // close browser, close session
            driver.quit();
            // destroy driver object for sure.
            driver=null;
        }
    }


}
