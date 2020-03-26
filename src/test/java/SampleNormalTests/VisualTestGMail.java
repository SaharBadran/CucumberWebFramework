package SampleNormalTests;

import PageObjects.GMailPage;
import PageObjects.SearchPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VisualTestGMail extends BaseTest{

    SearchPage searchPage;
    GMailPage gMailPage;
    SoftAssert softAssert;

    @Test
    public void visualTestGMailPage(){
        searchPage = new SearchPage();
        gMailPage = new GMailPage();
        softAssert = new SoftAssert();
        searchPage.goToGMail();
        softAssert.assertTrue(gMailPage.isGMailScreenVisuallyCorrect(),"GMail Page Visual Test Failed");
        softAssert.assertAll();
    }
}
