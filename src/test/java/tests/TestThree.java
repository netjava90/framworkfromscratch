package tests;

import base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestThree extends CommonAPI {

    public WebDriver driver;
    @Test
    public void TestThree() throws InterruptedException, IOException {
        System.out.println("testThree");
        driver = initializeDriver();
        driver.get("http://tutorialsninja.com/demo/");
        Thread.sleep(2000);
        driver.close();
    }
}
