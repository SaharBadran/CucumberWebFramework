package SampleTestCucumber.StepDefs;

import static io.restassured.RestAssured.given;

import java.io.File;

import Demo.GetValidTicketStatusAPI;
import Demo.PostNoteAPI;
import Demo.PostValidAttachmentAPI;
import VodafoneFramework.common.Strings;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestAPIStepDefs {

	Response validTicketStatusResponse;
	public static String Authorization ;
	public static String AttachmentID;
	Response validAttachmentResponse;
	Response postNoteResponse;
	
	
	@Given("User is calling the Get Request and set Headers")
	public void User_is_calling_the_Get_Request_and_set_Headers() {
	    // Write code here that turns the phrase above into concrete actions
		RestAssured.baseURI= "https://wtt-api-test.skytap-tss.vodafone.com";

		 validTicketStatusResponse = given()
				.header("Authentication","6RT0aR4zy5JiL/sIaZtHcm5KyVmW1aaRXP57CmY0CmQ=")
				.header("Accept-Language","DE")
				.when()
				.get("/api/troubleTicket/v1/TA123456789006/tickettracker/")
				.then()
				.assertThat()
				.statusCode(200)
				.log()
				.body()
				.extract()
				.response()
				;    
	}

	@And("User print the response on Console")
	public void User_print_the_response_on_Console() {
		String validTicketStatusResponseString = validTicketStatusResponse.asString();
		System.out.println(validTicketStatusResponseString);
	}
	
	@Then("response should return the valid Authorization value")
	public void response_should_return_the_valid_Authorization_value() {
		Authorization = validTicketStatusResponse.getHeader("Authorization");
		System.out.println("Authorization token is: " + Authorization);
	}
	
	@Given("User is calling the Post Attachment Request and set Headers")
	public void User_is_calling_the_Post_Attachment_Request_and_set_Headers() {
	
		System.out.println("Authorization  : " + Authorization);
		PostValidAttachmentAPI.postValidAttachmenttest(Authorization);

	}
	
	@Then("response should return the valid Attachment ID")
	public void response_should_return_the_valid_Attachment_ID() {
		
		AttachmentID = PostValidAttachmentAPI.getAttachment_ID();

	}
	
	@Given("User is calling the Post Note Request and set Headers")
	public void User_is_calling_the_Post_Note_Request_and_set_Headers() {

		PostNoteAPI.postNoteRequest(Authorization,AttachmentID);
	}
	
	@Then("response should return the note status")
	public void response_should_return_the_note_statusD() {
		PostNoteAPI.noteStatusCode();
		
	}
}
