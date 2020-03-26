package SampleNormalTests;

import PageObjects.ResultsPage;
import PageObjects.SearchPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SampleTest extends BaseTest {
    SearchPage searchPage;
    ResultsPage resultsPage;
    String searchKeyword = "Hello World";
    SoftAssert softAssert;

    @Test
    public void searchTest(){
//        searchPage = new SearchPage();
//        resultsPage = new ResultsPage();
//        softAssert = new SoftAssert();
//        searchPage.enterSearchKeyword(searchKeyword);
//        searchPage.goToSearchResults();
//        softAssert.assertTrue(resultsPage.getSearchPageTitle().contains(searchKeyword),"Search keyword should appear in the browser title");
//        softAssert.assertAll();
    }
}
