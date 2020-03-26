package SampleNormalTests;

import VodafoneFramework.base.BrowserType;
import VodafoneFramework.base.WebDriverSingleton;
import VodafoneFramework.common.Paths;
import VodafoneFramework.utilities.ConfigUtil;
import VodafoneFramework.utilities.ScreenshotUtil;
import VodafoneFramework.utilities.actions.BrowserActions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public class BaseTest {
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    public void setUp(){
        htmlReporter = new ExtentHtmlReporter(Paths.TEST_REPORTS+"ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        ConfigUtil.loadTestConfigurations();
    }

    @BeforeTest
    public void initializeTestRun(final ITestContext testContext){
        test = extent.createTest(testContext.getName());
        test.log(Status.INFO, "Test suite currently being executed : " + testContext.getName());
        System.out.println("Browser - "+ConfigUtil.BROWSER);
        WebDriverSingleton.getInstance().setBrowserType(BrowserType.CHROME);
        System.out.println("Base URL - "+ConfigUtil.BASE_URL);
        BrowserActions.navigateToPage(ConfigUtil.BASE_URL);
    }

    @BeforeClass
    public void restoreWindowSize(){
        BrowserActions.restoreView();
        BrowserActions.navigateToPage(ConfigUtil.BASE_URL);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
                    ExtentColor.RED));
            test.fail(result.getThrowable());
            String path = ScreenshotUtil.captureScreenShot(result.getMethod().getTestClass().getName());
            test.fail("Snapshot below: " + test.addScreenCaptureFromPath(path));
            // This will attach the screenshot only for Failed test cases
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getMethodName()+ " Test Case PASSED", ExtentColor.GREEN));
        } else {
            test.log(Status.SKIP,
                    MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }

    @AfterTest
    public void quit() {
      //  BrowserActions.closeBrowser();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }

}
