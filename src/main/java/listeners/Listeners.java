package listeners;

import base.CommonAPI;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ExtentReporter;

import java.io.IOException;

public class Listeners extends CommonAPI implements ITestListener {

    WebDriver driver = null;
    ExtentReports extentReport = ExtentReporter.getExtentReport();
    ExtentTest extentTest;
    ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        String testName = iTestResult.getName();
        extentTest = extentReport.createTest(testName);
        extentTestThread.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        String testName = iTestResult.getName();
        //extentTest.log(Status.PASS, testName+ "got passed");
        extentTestThread.get().log(Status.PASS, testName+ "got passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String testName = iTestResult.getName();

        //extentTest.fail(iTestResult.getThrowable());
        extentTestThread.get().fail(iTestResult.getThrowable());

        try{
            driver = (WebDriver) iTestResult.getTestClass().getRealClass().getDeclaredField("driver").get(iTestResult.getInstance());
        } catch(Exception e) {
            e.printStackTrace();
        }try {
            String screenshotFilePath = takeScreenshots(testName, driver);
            extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath, testName);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
       extentReport.flush();

    }
}
