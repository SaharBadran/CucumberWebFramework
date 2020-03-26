package VodafoneFramework.utilities.actions;

import VodafoneFramework.base.WebDriverSingleton;
import VodafoneFramework.utilities.ConfigUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;


public class ElementActions {

    public static WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getInstance().getDriver(),ConfigUtil.WAIT_TIME);

    public static Actions action = new Actions(WebDriverSingleton.getInstance().getDriver());

    //To highlight which button and field the script is currently clicking or typing in
    static public void highlightElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) WebDriverSingleton.getInstance().getDriver();
        executor.executeScript("arguments[0].setAttribute('style', 'background: #ffffe6; border: 2px solid yellow;');", element);
    }

    //To remove the highlight
    static public void unhighlightElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) WebDriverSingleton.getInstance().getDriver();
        executor.executeScript("arguments[0].removeAttribute('style','')", element);
    }

    //wait till specific element disappear (used mainly for loaders)
    public static void checkDisappearanceOf(By locator) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            System.out.println("Element " + locator.toString() + " is currently located on the ViewPort");
        }
    }

    //Scroll to a specific element using the WebElement
    public static void scrollPageToElement(WebElement element) {
        ((JavascriptExecutor) WebDriverSingleton.getInstance().getDriver()).executeScript("arguments[0].scrollIntoViewIfNeeded(true);",element);
    }

    //Scroll to a specific element using Locator
    public static void scrollPageToElementByLocator(By locator) {
        checkPresenceOf(locator);
        ((JavascriptExecutor) WebDriverSingleton.getInstance().getDriver()).executeScript("arguments[0].scrollIntoViewIfNeeded(true);", WebDriverSingleton.getInstance().getDriver().findElement(locator));
    }

    //Return the status of a specific element is viewed or not
    public static boolean isViewed(By locator) {
        WebElement element = retrieveElement(locator);
        Dimension elementSize = element.getSize();
        Point elementLocation = element.getLocation();
        Dimension viewSize = WebDriverSingleton.getInstance().getDriver().manage().window().getSize();

        int x = viewSize.getWidth();
        int y = viewSize.getHeight();
        int elementAbsX = elementSize.getWidth() + elementLocation.getX();
        int elementAbsY = elementSize.getHeight() + elementLocation.getY();

        return elementAbsX <= x && elementAbsY <= y;
    }

    //return the WebElement but wait until it is clickable (can be called only to wait until the element is clickable)
    public static WebElement checkAvailabilityOf(By locator) throws Exception {
        WebElement element = retrieveElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        if (element.isEnabled())return element;
        else throw new Exception("Element " + locator.getClass().getName() + " is not interactable");
    }

    //wait till the element appears in the HTML DOM
    public static void checkPresenceOf(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            System.out.println("Element " + locator.toString() + " is not present on the current DOM");
        }
    }

    //wait till all the elements by the same locator appears in the HTML DOM
    public static void checkPresenceOfAllElements(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            System.out.println("Element " + locator.toString() + " is not present on the current DOM");
        }
    }

    //wait till the element visibly appear on the window itself
    public static void checkExistenceOf(By locator) {
        checkPresenceOf(locator);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            System.out.println("Element " + locator.toString() + " is not visible on the current DOM");
        }
    }

    //wait till all the elements with the same locator visibly appear on the window itself
    public static void checkExistenceOfAllElements(By locator) {
        checkPresenceOf(locator);
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            System.out.println("Element " + locator.toString() + " is not visible on the current DOM");
        }
    }

    //wait till the element is refreshed and not in stall status
    public static void refreshStalenessOf(By locator){
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(retrieveElement(locator))));
    }

    //used for drag and drop
    public static void dragAndDrop(By locator1,By locator2){
        wait.until(ExpectedConditions.elementToBeClickable(locator1));
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            System.out.println(e);
        }
        action.dragAndDrop(retrieveElement(locator1), retrieveElement(locator2)).build().perform();
    }

    //used to click on an element by locator(includes the scroll and wait)
    public static void clickOnElementByLocator(By locator) {
        try {
            WebElement element = checkAvailabilityOf(locator);
            scrollPageToElement(element);
            highlightElement(element);
            unhighlightElement(element);
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Used to click on an element by web element
    public static void clickOnWebElement(WebElement element){
        scrollPageToElement(element);
        highlightElement(element);
        unhighlightElement(element);
        element.click();
    }

    //used to force click on element using locator with java script(include scroll and wait)
    public static void forceClickOnElementByLocator(By locator) {
        checkPresenceOf(locator);
        WebElement element = WebDriverSingleton.getInstance().getDriver().findElement(locator);
        scrollPageToElement(element);
        highlightElement(element);
        unhighlightElement(element);
        ((JavascriptExecutor) WebDriverSingleton.getInstance().getDriver()).executeScript("arguments[0].click()", element);
    }

    //used to force click on element using WebElement with java script(include scroll and wait)
    public static void forceClickOnElement(WebElement element){
        scrollPageToElement(element);
        highlightElement(element);
        unhighlightElement(element);
        ((JavascriptExecutor) WebDriverSingleton.getInstance().getDriver()).executeScript("arguments[0].click()", element);
    }

    //Used to add date on calendar field using java script
    public static void setTimer(By locator, String date) {
        WebElement element = retrieveElement(locator);
        ((JavascriptExecutor) WebDriverSingleton.getInstance().getDriver()).executeScript("arguments[0].value=\'" + date + "\'", element);
    }

    //Some fields have problems with the normal clear() function so in this case use this instead
    public static void overwriteTextField(By locator, String value){
        try {
            WebElement element = checkAvailabilityOf(locator);
            scrollPageToElement(element);
            highlightElement(element);
            unhighlightElement(element);
            element.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END),value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //return the checkbox status (true or false)
    public static boolean isCheckBoxChecked(String locatorID){
        checkPresenceOf(By.id(locatorID));
        return  (Boolean)((JavascriptExecutor) WebDriverSingleton.getInstance().getDriver()).executeScript("return $('#"+locatorID+"').prop('checked')");
    }

    //Type in normal text field
    public static void typeInTextField(By locator, String string) {
        try {
            checkExistenceOf(locator);
            WebElement element = checkAvailabilityOf(locator);
            scrollPageToElement(element);
            highlightElement(element);
            unhighlightElement(element);
            element.clear();
            element.sendKeys(string);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
  //File upload by Robot Class
    public static void uploadFileWithRobot (String imagePath) {
        StringSelection stringSelection = new StringSelection(imagePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
 
        Robot robot = null;
 
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
 
        robot.delay(250);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(150);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
    ///////////type text
    public static void uploadFile(By locator, String string) {
        try {
        	
        	WebElement element = WebDriverSingleton.getInstance().getDriver().findElement(locator);
        	element.click();
        	Thread.sleep(1000);
        	
        	uploadFileWithRobot(string);
        	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //return the text of any element
    public static String getTextOfElement(By locator) {
        return retrieveElement(locator).getText();
    }

    //return specific attribute value for any element
    public static String getAttributeValueOfElement(By locator, String attribute) {
        return retrieveElement(locator).getAttribute(attribute);
    }

    //retrieve WebElement (use to define any WebElement object)
    public static WebElement retrieveElement(By locator) {
        checkExistenceOf(locator);
        return WebDriverSingleton.getInstance().getDriver().findElement(locator);
    }

    //retrieve WebElements (use to define any WebElements list)
    public static List<WebElement> retrieveElements(By locator) {
        checkExistenceOf(locator);
        return WebDriverSingleton.getInstance().getDriver().findElements(locator);
    }

    //retrieve WebElement using xpath with different attributes(use to define any WebElements Object)
    public static WebElement retrieveElementByAttributeValue(String attribute, String value) {
        checkExistenceOf(By.xpath("//*[@" + attribute + "='" + value + "']"));
        return WebDriverSingleton.getInstance().getDriver().findElement(By.xpath("//*[@" + attribute + "='" + value + "']"));
    }

    //Wrapper for using labels for overwriting text fields (Use according to the HTML hierarchy)
    public static void overwriteTextFieldByLabel(String label, String data){
        By locator = By.xpath("//*[label[text()='" + label + "']]//input");
        overwriteTextField(locator, data);
    }

    //Wrapper for using labels for writing in text fields (Use according to the HTML hierarchy)
    public static void typeInTextFieldByLabel(String Label, String Data) {
        By locator = By.xpath("//*[label[text()='" + Label + "']]//input");
        typeInTextField(locator, Data);
    }

    //Switching to frame
    public static void switchToFrame(By frameLocator){
        WebElement frame = retrieveElement(frameLocator);
        WebDriverSingleton.getInstance().getDriver().switchTo().frame(frame);
    }

    //Switching to Original frame
    public static void switchToOriginalFrame(By frameLocator){
        WebDriverSingleton.getInstance().getDriver().switchTo().defaultContent();
    }

    //Wrapper for using labels for writing in text areas (Use according to the HTML hierarchy)
    public static void typeInTextAreaByLabel(String Label, String Data) {
       typeInTextField(By.xpath("//*[label[text()='" + Label + "']]//textarea"), Data);
    }

    //Wrapper for using labels for clicking on elements with overlay label (Use according to the HTML hierarchy)
    public static void clickOnElementWithOverlayLabel(String Label) {
        forceClickOnElementByLocator(By.xpath("//*[self::a or self::input or self::div or self::button][contains(text(),'" + Label + "') or contains(@value,'" + Label + "')]"));
    }

    //Wrapper for using labels to check if element have specific text (Use according to the HTML hierarchy)
    public static void checkElementHaveTextByLabel(String label,String value){
        By locator = By.xpath("//*[label[text()='" + label + "']]//input");
        checkElementHaveTextByLocator(locator,value);
    }

    //wait till element have specific text
    public static void checkElementHaveTextByLocator(By locator,String value){
        wait.until(ExpectedConditions.textToBePresentInElementValue(locator,value));
    }

    //click on check box using label (Use according to the HTML hierarchy))
    public static void checkACheckBoxWithLabel(String Label) {
       clickOnElementByLocator(By.xpath("//label[contains(text(),'" + Label + "')]/preceding-sibling::Input"));
    }

    //Wrapper for using labels for clicking on elements with label (Use according to the HTML hierarchy)
    public static void clickOnElementWithLabel(String Label) {
       clickOnElementByLocator(By.xpath("//*[label[text()='" + Label + "']]/*[self::a or self::input or self::div or self::button]"));
    }


    //Wrapper for using labels for selecting an element from dropdown that is not implemented as Select item in the HTML (Use according to the HTML hierarchy)
    public static void selectElementFromLabeledDropdownMenuSwift(String Label, String Selection) {
       selectFromDropDownMenuSwift(By.xpath("//*[label[text()='" + Label + "']]/div"), Selection);
    }

    //Wrapper for using labels for selecting an element force click from dropdown that is not implemented as Select item in the HTML (Use according to the HTML hierarchy)
    public static void selectElementFromLabeledDropdownMenuSwiftForceClick(String Label, String Selection) {
        selectFromDropDownMenuSwiftForceClick(By.xpath("//*[label[text()='" + Label + "']]/div"), Selection);
    }

    //selecting an element force click from dropdown that is not implemented as Select item in the HTML
    public static void selectFromDropDownMenuSwiftForceClick(By locator, String YourSelection){
        checkExistenceOf(locator);
        forceClickOnElementByLocator(locator);
        searchWithinMenuSwift(locator, YourSelection);
    }

    //selecting an element from dropdown that is not implemented as Select item in the HTML
    public static void selectFromDropDownMenuSwift(By locator, String YourSelection) {
        checkExistenceOf(locator);
        clickOnElementByLocator(locator);
        searchWithinMenuSwift(locator, YourSelection);
    }

    //Radio button 
    public static void RadioButtonIndex(By locator, int index) {
    	WebElement element = WebDriverSingleton.getInstance().getDriver().findElements(locator).get(index) ;
        scrollPageToElement(element);
        highlightElement(element);
    	element.click();
    }
    
    //Searching and selecting item from open dropdown using XPATH
    public static void searchWithinMenuSwift(By locator, String yourSelection) {
        By elementLocator = By.xpath(getSelector(locator).trim()+"//following-sibling::ul//*[self::a or self::input or self::li][contains(text(),'" + yourSelection + "')]");
        clickOnElementByLocator(elementLocator);
    }

    //searching and selecting item from open dropdown selected by label using XPATH(according to HTML hierarchy)
    public static void searchWithinMenuSwiftByLabel(String label, String yourSelection) {
        By elementLocator = By.xpath("//*[label[text()='" + label + "']]/div//following-sibling::ul//*[self::a or self::input or self::li][contains(text(),'" + yourSelection + "')]");
       clickOnElementByLocator(elementLocator);
    }

    //Wrapper for using labels for selecting an element from dropdown that is not implemented as Select item in the HTML (Use according to the HTML hierarchy)
    public static void selectElementFromLabeledDropdownMenuSlow(String Label, String Selection) {
      selectFromDropDownMenuSlow(By.xpath("//*[label[text()='" + Label + "']]/div"), Selection);
    }

    //selecting an element force click from dropdown that is not implemented as Select item in the HTML (Use according to the HTML hierarchy)
    public static void selectFromDropDownMenuSlow(By locator, String YourSelection) {
        checkExistenceOf(locator);
        scrollPageToElementByLocator(locator);
        clickOnElementByLocator(locator);
        searchWithinMenuSlow(locator, YourSelection);
    }

    //search withing dropdown that is not using Select item in the HTML but by looping through all the items in the dropdown
    public static void searchWithinMenuSlow(By locator, String YourSelection) {
        List<WebElement> droppedItems = WebDriverSingleton.getInstance().getDriver().findElements(By.xpath(getSelector(locator).trim()+"//ul//*"));
        for (WebElement item : droppedItems) {
            String text = item.getAttribute("textContent").trim();
            if (text.equalsIgnoreCase(YourSelection)){
                highlightElement(item);
                unhighlightElement(item);
                BrowserActions.scrollDownToView(item);
                wait.until(ExpectedConditions.visibilityOf(item));
                if(!item.isDisplayed()) System.out.println("Element is not Displayed !!");
                item.click();
                //forceClickOnElement(item);
                break;
            }
        }
    }

    private static String getSelector(By by) {
        return (by.toString().split(":")[1]).trim();
    }

    private static String getLocatingMethod(By by) {
        return by.toString().split(":")[0]; //returns "By.cssSelector", "By.xpath" etc
    }

    //Selecting an element from a dropdown using the normal Select (if the dropdown is implemented as Select in the HTML)
    public static void selectUsingSelectDropDown(By locator, String yourSelection){
        checkPresenceOf(locator);
        WebElement element = retrieveElement(locator);
        highlightElement(element);
        unhighlightElement(element);
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(yourSelection);
    }

    //Hover on a specific element using locator
    public static void hoverOnElement(By locator){
        WebElement element = retrieveElement(locator);
        scrollPageToElement(element);
        highlightElement(element);
        unhighlightElement(element);
        action.moveToElement(element).build().perform();
    }
}
