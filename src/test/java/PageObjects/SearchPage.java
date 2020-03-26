package PageObjects;

import ObjectRepo.SearchPageLocators;
import VodafoneFramework.common.Strings;
import VodafoneFramework.utilities.ImageValidator;
import VodafoneFramework.utilities.ScreenshotUtil;
import VodafoneFramework.utilities.actions.ElementActions;

public class SearchPage {
    public void enterSearchKeyword(String keyword){
        ElementActions.typeInTextField(SearchPageLocators.SEARCH_FIELD,keyword);
    }

    public void goToSearchResults(){
        ElementActions.clickOnElementByLocator(SearchPageLocators.SEARCH_RESULTS_BTN);
    }

    public void goToGMail(){
        ElementActions.clickOnElementByLocator(SearchPageLocators.GMAIL_BTN);
    }

    public boolean isOnSearchPage(){
        try{
            ElementActions.retrieveElement(SearchPageLocators.SEARCH_RESULTS_BTN);
        } catch (Exception e){
            return false;
        } return true;
    }

    public boolean isSearchPageVisuallyCorrect(){
        ScreenshotUtil.captureScreenShotVisualTest(Strings.ACTUAL_GOOGLE_HOME_SCREEN);
        return ImageValidator.validateImage(Strings.ACTUAL_GOOGLE_HOME_SCREEN,Strings.EXPECTED_GOOGLE_HOME_SCREEN
        ,Strings.RESULT_GOOGLE_HOME_SCREEN);
    }
}
