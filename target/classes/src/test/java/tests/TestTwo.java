package tests;

import base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestTwo extends CommonAPI {
    public WebDriver driver;
    @Test
    public void testTwo() throws IOException, InterruptedException {
        System.out.println("Inside testTwo");
        driver = initializeDriver();
        driver.get("http://tutorialsninja.com/demo/");
        Thread.sleep(2000);
        driver.close();


    }
}
