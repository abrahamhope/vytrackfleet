package com.automation.vytrack.fleet;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class VehiclesTests {
    private WebDriver driver;
    private String URL = "https://qa2.vytrack.com/user/login";
    //credentials for store manager
    private String username = "storemanager51";
    private String password = "UserUser123";
    private By usernameBy = By.id("prependedInput");
    private By passwordBy =By.id("prependedInput2");
    private By loginBtnBy = By.id("_submit");
    private By fleetBy = By.xpath("//span[@class='title title-level-1' and contains(text(),'Fleet')]");
    private By subtitleBy = By.className("oro-subtitle");

   @Test
    public void verifyPageSubTitle(){
        //login
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password, Keys.ENTER);
        //put more wait here as well, if didn't click
        BrowserUtils.wait(5);
        //click on fleet
//        driver.findElement(fleetBy).click();

       Actions actions = new Actions(driver);
       // move to element instead of click
       actions.moveToElement(driver.findElement(fleetBy)).perform();
       // perform to execute command
       // every action should end with perform.


        BrowserUtils.wait(2);
        //click on Vehicles
        driver.findElement(By.linkText("Vehicles")).click();
        //put more wait time if you are getting Cars, Dashboard...
        //this application is slooooow...
        BrowserUtils.wait(5);
        //find subtitle element
        WebElement subTitleElement = driver.findElement(subtitleBy);
        System.out.println(subTitleElement.getText());
    }

    @Test
    public void verifyPageNumber(){
        WebElement pageNumber=driver.findElement(By.xpath("//input[@value='1']"));
        assertEquals(pageNumber.getAttribute("value"),"1");

    }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password, Keys.ENTER);
        BrowserUtils.wait(5);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(fleetBy)).perform();
        BrowserUtils.wait(2);
        driver.findElement(By.linkText("Vehicles")).click();
        BrowserUtils.wait(5);
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
