package PageObjects;

import ObjectRepo.ResultsPageLocators;
import VodafoneFramework.utilities.actions.BrowserActions;
import VodafoneFramework.utilities.actions.ElementActions;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultsPage {
    public String getSearchPageTitle(){
        return BrowserActions.getBrowserTitle();
    }

    public void clickOnResult(int resultIndex){
        List<WebElement> results = ElementActions.retrieveElements(ResultsPageLocators.resultsLocator);
        ElementActions.clickOnWebElement(results.get(resultIndex));
    }

    public boolean isOnResultsPage(){
        try{
           ElementActions.retrieveElement(ResultsPageLocators.resultsLocator);
        } catch (Exception e){
            return false;
        } return true;
    }
}
