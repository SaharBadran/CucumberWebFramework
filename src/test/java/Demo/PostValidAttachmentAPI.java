package Demo;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Properties;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostValidAttachmentAPI {

	Properties DataInsideAttachment = new Properties();

	public static String AttachmentID ;
	public static String validAttachmentResponseString;
	
	@Test
	public static void postValidAttachment() {
				
		GetValidTicketStatusAPI.getValidTicketStatus();// To run the validTicketStatus API firsy to have the Authorization
		
		File Attachment = new File("C:\\German\\Service.xlsx");
			//	+ "/Users/mac/Downloads/Automation.xlsx"); //please insert any file path on your machine
		
		RestAssured.baseURI= "https://wtt-api-test.skytap-tss.vodafone.com";

		Response validAttachmentResponse = given()
				.header("Authorization",GetValidTicketStatusAPI.Authorization)
				.header("RequestId","1234")
				.multiPart("file",Attachment)
				.when()
				.post("/api/troubleTicket/v1/TA123456789006/tickettracker/attachment")
				.then()
				.assertThat()
				.statusCode(201)
				.log()
				.body()
				.extract()
				.response()
				;

		String validAttachmentResponseString = validAttachmentResponse.asString();
		
		JsonPath validAttachmentResponsejs = new JsonPath(validAttachmentResponseString);
		AttachmentID = validAttachmentResponsejs.get("attachmentId");
		System.out.println("Attachment ID is: " + AttachmentID);
		
	}
	public static void postValidAttachmenttest(String ValidTicketStatus)
	{

		
		File Attachment = new File("C:\\German\\Service.xlsx");
			//	+ "/Users/mac/Downloads/Automation.xlsx"); //please insert any file path on your machine
		
		RestAssured.baseURI= "https://wtt-api-test.skytap-tss.vodafone.com";

		Response validAttachmentResponse = given()
				.header("Authorization",ValidTicketStatus)
				.header("RequestId","1234")
				.multiPart("file",Attachment)
				.when()
				.post("/api/troubleTicket/v1/TA123456789006/tickettracker/attachment")
				.then()
				.assertThat()
				.statusCode(201)
				.log()
				.body()
				.extract()
				.response()
				;

		 validAttachmentResponseString = validAttachmentResponse.asString();
		
	
	}
	public static String getAttachment_ID()
	{
		JsonPath validAttachmentResponsejs = new JsonPath(validAttachmentResponseString);
		AttachmentID = validAttachmentResponsejs.get("attachmentId");
		System.out.println("Attachment ID is: " + AttachmentID);
		return AttachmentID;
	}
	
}
