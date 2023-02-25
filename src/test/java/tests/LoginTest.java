package tests;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import java.io.IOException;

public class LoginTest extends CommonAPI {
    public WebDriver driver;
    Logger log;

    @Test(dataProvider = "getLoginData")
    public void login(String username, String password, String expectedResult) throws IOException {

        LandingPage landingPage = new LandingPage(driver);
        landingPage.myAccountDropDown().click();
        log.debug("Clicked on my account dropDown");
        landingPage.loginBtn().click();
        log.debug("Clicked on login option");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.userName().sendKeys(username);
        log.debug("userName got entered");
        loginPage.passWord().sendKeys(password);
        log.debug("password got entered");
        loginPage.loginButton().click();
        log.debug("Clicked on login button");

        AccountPage accountPage = new AccountPage(driver);
        String actualResult = null;
        try{
            if(accountPage.editAccountInformationOption().isDisplayed()) {
                actualResult = "successful";
                log.debug("user got logged in");
            }
            
        }catch(Exception e){
            actualResult = "failure";
            log.debug("user did not log in");
        }

        Assert.assertEquals(actualResult, expectedResult);
        log.info("login test got passed");
    }
    
    @BeforeMethod
    public void openApplication() throws IOException {
        log = LogManager.getLogger(LoginTest.class.getName());
        driver = initializeDriver();
        log.debug("Browser got launched");
        driver.get(prop.getProperty("url"));
        log.debug("Navigated to application URL");
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
        log.debug("Browser got closed");
    }

    @DataProvider
    public Object[][] getLoginData(){
        Object[][] data = {{"akselaymar@gmail.com", "Amchiche5","successful"},{"aksel@gmail.com","amchiche","failure"}};
        return data;
    }
}
