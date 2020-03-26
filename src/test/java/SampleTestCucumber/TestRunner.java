package SampleTestCucumber;

import VodafoneFramework.base.BrowserType;
import VodafoneFramework.base.WebDriverSingleton;
import VodafoneFramework.common.Paths;
import VodafoneFramework.utilities.ConfigUtil;
import VodafoneFramework.utilities.ScreenshotUtil;
import VodafoneFramework.utilities.actions.BrowserActions;
import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.testng.AbstractTestNGCucumberTests;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

@CucumberOptions(features = {"src/test/java/Features"}, glue = "SampleTestCucumber/StepDefs",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:target/cucumber-reports/report.html"})

public class TestRunner extends AbstractTestNGCucumberTests {
    //Used to initialize the browser before cucumber feature files run
	@BeforeClass  
	//@Before(order=1)
    public void initialization(){
        ConfigUtil.loadTestConfigurations();
        WebDriverSingleton.getInstance().setBrowserType(BrowserType.CHROME);
        BrowserActions.maximizeWindow();
    }


    //Used to close the driver after cucumber feature files run
    @AfterClass
    public void tearDown(){
       BrowserActions.closeBrowser();
    }
	    
}

