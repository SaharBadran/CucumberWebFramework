package VodafoneFramework.utilities.actions;

import VodafoneFramework.base.WebDriverSingleton;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

public class BrowserActions {

    public static void maximizeWindow() {
       WebDriverSingleton.getInstance().getDriver().manage().window().maximize();
    }
    public static void refreshPage() {
        WebDriverSingleton.getInstance().getDriver().navigate().refresh();
    }
    public static void closeBrowser() {
        WebDriverSingleton.getInstance().getDriver().close();
    }
    public static void restoreView() {
        WebDriverSingleton.getInstance().getDriver().manage().window().maximize();
        WebDriverSingleton.getInstance().getDriver().navigate().refresh();
    }
    public static void navigateToPage(String url) {
        WebDriverSingleton.getInstance().getDriver().navigate().to(url);
    }


    //Scrolling
    public static void scrollDownToView(WebElement element) {
        ((JavascriptExecutor) WebDriverSingleton.getInstance().getDriver()).
                executeScript("arguments[0].scrollIntoView({behavior:'smooth',block: 'center'});",
                element);
    }

    public static void scrollToTop() {
        ((JavascriptExecutor) WebDriverSingleton.getInstance().getDriver()).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }
    public static void zoomOutOrIn(double percentageIndex){
        ((JavascriptExecutor) WebDriverSingleton.getInstance().getDriver()).executeScript( "document.body.style.zoom='"+percentageIndex+"'");
    }
    public static void scrollVertically(int pixels) {
        ((JavascriptExecutor) WebDriverSingleton.getInstance().getDriver()).executeScript("window.scrollBy(0," + pixels + ")", "");

    }

    public static void scrollHorizontally(int pixels) {
        ((JavascriptExecutor) WebDriverSingleton.getInstance().getDriver()).executeScript("window.scrollBy(" + pixels + ",0)");
    }

    public static void scrollDownToBottomOfPage(){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) WebDriverSingleton.getInstance().getDriver();
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    //Alerts
    public static void approveAlert() {
        WebDriverSingleton.getInstance().getDriver().switchTo().alert().accept();
    }

    public static boolean checkIfAlertIsPresent() {
        try {
            WebDriverSingleton.getInstance().getDriver().switchTo().alert();
            return true;
        }
        catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    //check if window is scrolled up or not (Used to test the back to top button)
    public static boolean isWindowScrolledUp(){
        JavascriptExecutor executor = (JavascriptExecutor) WebDriverSingleton.getInstance().getDriver();
        Long value =  (Long) executor.executeScript("return window.pageYOffset;");
        if (value == 0){
            return true;
        } else {
            return false;
        }
    }

    //Get Browser Title
    public static String getBrowserTitle(){
        return WebDriverSingleton.getInstance().getDriver().getTitle();
    }

    //Check if window is scrolled down or not
    public static boolean isWindowScrolledDown(){
        JavascriptExecutor executor = (JavascriptExecutor) WebDriverSingleton.getInstance().getDriver();
        Object currValueInnerHeightObj =  executor.executeScript("return window.innerHeight");
        Object currValueYOffsetObj = executor.executeScript("return window.pageYOffset");
        Object docHeightObj = executor.executeScript("return document.body.scrollHeight");
        Long currValue = 0L;
        Long docHeight = 0L;

        System.out.println(currValueInnerHeightObj.getClass().toString());
        if(currValueInnerHeightObj.getClass().toString().toLowerCase().contains("long")){
            currValue = (Long) currValueInnerHeightObj;
        }
        else {
            currValue = Math.round((Double) currValueInnerHeightObj);
        }

        if(currValueYOffsetObj.getClass().toString().toLowerCase().contains("long")){
            currValue += (Long) currValueYOffsetObj;
        }
        else {
            currValue += Math.round((Double) currValueYOffsetObj);
        }

        if(docHeightObj.getClass().toString().toLowerCase().contains("long")){
            docHeight = (Long) docHeightObj;
        }
        else {
            docHeight = Math.round((Double) docHeightObj);
        }
        currValue++;
        System.out.println();
        System.out.println("currValue: "+ currValue);
        System.out.println("docHeight: "+docHeight);
        return currValue >= docHeight;
    }
}
