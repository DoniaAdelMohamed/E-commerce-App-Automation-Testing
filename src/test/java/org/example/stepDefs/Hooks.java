package org.example.stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Hooks {

    public static WebDriver driver = null;

    @Before
    public static void openBrowser()
    {

        //1- Bridge
        String chromePath = System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.diver",chromePath);

        //2- create new object from chrome driver
        driver = new ChromeDriver();

        //3- Configuration
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //4-Navigate
        driver.navigate().to("https://demo.nopcommerce.com/");
    }

    @After
    public static void quitBrowser() throws InterruptedException
    {
        Thread.sleep(3000);
        driver.quit();
    }
}
