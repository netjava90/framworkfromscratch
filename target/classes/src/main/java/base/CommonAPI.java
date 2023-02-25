package base;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CommonAPI {
        WebDriver driver;
        public Properties prop;

    public WebDriver initializeDriver() throws IOException {

        prop = new Properties();
        String propPath = System.getProperty("user.dir")+"\\src\\main\\java\\base\\data.properties";
        FileInputStream fis = new FileInputStream(propPath);
        prop.load(fis);

        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
           driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("IE")) {
           WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }


    public String takeScreenshots(String testName, WebDriver driver ) throws IOException {
        File SourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationFilePath = System.getProperty("user.dir")+"\\screenshot\\"+testName+".png";
        FileUtils.copyFile(SourceFile,new File(destinationFilePath));
        return destinationFilePath;
    }
}
