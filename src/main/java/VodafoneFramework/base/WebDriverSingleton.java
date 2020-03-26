package VodafoneFramework.base;

import VodafoneFramework.common.Paths;
import VodafoneFramework.utilities.ConfigUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.HashMap;

public class WebDriverSingleton {
    WebDriver driver;

    private WebDriverSingleton() {
        // Do-nothing..Do not allow to initialize this class from outside
    }

    private static WebDriverSingleton instance = new WebDriverSingleton();

    public static WebDriverSingleton getInstance() {
     //   if (instance == null) instance = new WebDriverSingleton();
        return instance;
    }



    public void setBrowserType(BrowserType type){
        switch (type) {
            case CHROME:
                //String downloadFilepath = "/path/to/download";
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", Paths.TEST_DOWNLOAD_DIRECTORY);
                final ChromeOptions options = new ChromeOptions();

                options.setCapability("prefs", chromePrefs);

                //CICD Integration Properties
                if(ConfigUtil.HEADLESS) options.addArguments("--headless");
                options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                options.addArguments("--no-sandbox"); // Bypass OS security model

                WebDriverManager.chromedriver().clearPreferences();
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);

                break;

            case IE:
                WebDriverManager.iedriver().clearPreferences();
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;

            case EDGE:
                WebDriverManager.iedriver().clearPreferences();
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
        }
    }

    public WebDriver getDriver() // call this method to get the driver object and launch the browser
    {
        //String browser = context.getCurrentXmlTest().getParameter("browser");
        //if (browser.equalsIgnoreCase("edge")) return edge.get();
        //else
        return driver;
    }
}
