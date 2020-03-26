package Demo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostNoteAPI {


	public static String postNoteResponseString;
	public static Response postNoteResponse;
	@Test
	public void postNote() {
		
		PostValidAttachmentAPI.postValidAttachment();
		
		RestAssured.baseURI= "https://wtt-api-test.skytap-tss.vodafone.com";
		
		Response postNoteResponse = given()
				.header("Authorization",GetValidTicketStatusAPI.Authorization)
				.header("RequestId","128")
				.header("Accept","application/json")
				.header("Accept-Language","en")
				.header("Content-Type","application/json")
				.body("{\"text\":\"ddd\",\n" + 
						"\"type\":\"StatusRequest\",\n" + 
						"\"attachmentRefIDs\":[\""+PostValidAttachmentAPI.AttachmentID+"\"]\n" + 
						"}")   // attachment ID is a variable that is taken from the validAttachment request
				.when()
				.post("/api/troubleTicket/v1/TA123456789006/tickettracker/note")
				.then()
				.assertThat()
				.statusCode(200)
				.log()
				.body()
				.extract()
				.response()
				;

		 postNoteResponseString = postNoteResponse.asString();

		JsonPath postNoteResponsejs = new JsonPath(postNoteResponseString);

		String noteStatus = postNoteResponsejs.get("status"); // for example we will grab the status field from the response
		System.out.println("Note Status is: " + noteStatus);
		
	}

	public static void postNoteRequest(String Authorization,String AttachmentID) {
		
        RestAssured.baseURI= "https://wtt-api-test.skytap-tss.vodafone.com";
		
		 postNoteResponse = given()
				.header("Authorization",Authorization)
				.header("RequestId","128")
				.header("Accept","application/json")
				.header("Accept-Language","en")
				.header("Content-Type","application/json")
				.body("{\"text\":\"ddd\",\n" + 
						"\"type\":\"StatusRequest\",\n" + 
						"\"attachmentRefIDs\":[\""+AttachmentID+"\"]\n" + 
						"}")   // attachment ID is a variable that is taken from the validAttachment request
				.when()
				.post("/api/troubleTicket/v1/TA123456789006/tickettracker/note")
				.then()
				.assertThat()
				.statusCode(200)
				.log()
				.body()
				.extract()
				.response()
				;
		 postNoteResponseString = postNoteResponse.asString();
	}
	
	public static void noteStatusCode() {
		JsonPath postNoteResponsejs = new JsonPath(postNoteResponseString);

		String noteStatus = postNoteResponsejs.get("status"); // for example we will grab the status field from the response
		System.out.println("Note Status is: " + noteStatus);
		
		
	}
	
}
