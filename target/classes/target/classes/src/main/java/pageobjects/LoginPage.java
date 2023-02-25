package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    private @FindBy(xpath = "//*[@id='input-email']")
    WebElement userName;

    public WebElement userName(){
        return userName;
    }

    @FindBy(xpath = "//*[@id='input-password']")
    private WebElement passWord;

    public WebElement passWord(){
        return passWord;
    }

    @FindBy(css = "input[type='submit']")
    private WebElement loginButton;

    public WebElement loginButton(){
        return loginButton;
    }



}
