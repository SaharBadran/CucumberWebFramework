package SampleNormalTests;

import PageObjects.SearchPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VisualTestSearch extends BaseTest{
    SearchPage searchPage;
    SoftAssert softAssert;

    @Test
    public void visualTestSearchPage(){
        searchPage = new SearchPage();
        softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.isSearchPageVisuallyCorrect(),"Search Page Visual Test Failed");
        softAssert.assertAll();
    }
}
