package stepdefinitions;

import base.CommonAPI;
import io.cucumber.java.After;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;

import java.io.IOException;

public class Login extends CommonAPI {
    WebDriver driver;
    LandingPage landingPage;
    LoginPage loginPage;
    AccountPage accountPage;

    @Given("^Open any Browser$")
    public void open_any_browser() throws IOException {
        driver = initializeDriver();

    }
    @And("^Navigate to Login page$")
    public void navigate_to_login_page() throws InterruptedException {
        driver.get(prop.getProperty("url"));
        landingPage = new LandingPage(driver);
        landingPage.myAccountDropDown().click();
        landingPage.loginBtn().click();
        Thread.sleep(3000);
    }

    @When("^User enters username as \"([^\"]*)\" and password as \"([^\"]*)\" into the fields$")
    public void user_enters_username_as_something_and_password_as_something_into_the_fields(String email, String password) {
        loginPage = new LoginPage(driver);
        loginPage.userName().sendKeys(email);
        loginPage.passWord().sendKeys(password);

    }

    @And("^User Clicks on Login button$")
    public void user_clicks_on_login_button()  {
        loginPage.loginButton().click();

    }

    @Then("^Verify user is able to successfully login$")
    public void verify_user_is_able_to_successfully_login() {
        accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.editAccountInformationOption().isDisplayed());
    }
    @After
    public void closeBrowser(){
        driver.close();
    }

}

