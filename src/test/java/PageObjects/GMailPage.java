package PageObjects;

import ObjectRepo.SearchPageLocators;
import VodafoneFramework.common.Strings;
import VodafoneFramework.utilities.ImageValidator;
import VodafoneFramework.utilities.ScreenshotUtil;
import VodafoneFramework.utilities.actions.ElementActions;

public class GMailPage {

    public boolean isGMailScreenVisuallyCorrect(){
        ScreenshotUtil.captureScreenShotVisualTest(Strings.ACTUAL_GMAIL_SCREEN);
        return ImageValidator.validateImage(Strings.ACTUAL_GMAIL_SCREEN,Strings.EXPECTED_GMAIL_SCREEN
                ,Strings.RESULT_GMAIL_SCREEN);
    }
}
