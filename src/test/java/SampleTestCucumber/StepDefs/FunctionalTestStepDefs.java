package SampleTestCucumber.StepDefs;

import PageObjects.ResultsPage;
import PageObjects.SearchPage;
import VodafoneFramework.utilities.actions.BrowserActions;
import VodafoneFramework.utilities.actions.ElementActions;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import ObjectRepo.IncidentLocators;

public class FunctionalTestStepDefs {
	
	SoftAssert softassert;

	
	@Before
	public void beforeScernrio()
	{
		softassert =new SoftAssert();
	}
	@Given("the User is on {string}")
	public void the_User_is_on(String string) {
	    // Write code here that turns the phrase above into concrete actions
	   BrowserActions.navigateToPage(string);
	    
	}

	@When("the user select request option number {string}")
	public void the_user_select_request_option_number(String Index) {
	    // Write code here that turns the phrase above into concrete actions
		ElementActions.RadioButtonIndex(IncidentLocators.requestLocator,Integer.valueOf(Index)-1);
	}

	@When("he add {string}")
	public void he_add(String string) {
	    // Write code here that turns the phrase above into concrete actions
		ElementActions.typeInTextField(IncidentLocators.commentLocator,string);
	}

	@When("he uploads the Incident file {string}")
	public void he_uploads_the_Incident_file(String string) {
	    // Write code here that turns the phrase above into concrete actions

		ElementActions.uploadFile(IncidentLocators.uploadButtonLocator,"C:\\German\\"+string);
	}

	@When("he submit the request")
	public void he_submit_the_request() {
	    // Write code here that turns the phrase above into concrete actions
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ElementActions.forceClickOnElementByLocator(IncidentLocators.sendRequestButtonLocator);
	}

	@Then("he should be navigated to success page with message {string}")
	public void he_should_be_navigated_to_success_page_with_message(String string) {
	    // Write code here that turns the phrase above into concrete actions
		String actual =ElementActions.getTextOfElement(IncidentLocators.successMessage);
		softassert.assertEquals(actual, string);
		softassert.assertAll();
	}

}
