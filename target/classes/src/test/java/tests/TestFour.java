package tests;

import base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestFour extends CommonAPI {

    public WebDriver driver;

    public TestFour() throws IOException {
    }

    @Test
    public void TestFour() throws IOException, InterruptedException {
        System.out.println("Test four");
        driver = initializeDriver();
        driver.get("http://tutorialsninja.com/demo/");
        Thread.sleep(2000);
        Assert.assertTrue(false);
        //driver.close();

     }
     @AfterMethod
     public void closingBrowser(){
        driver.close();
     }
  }

