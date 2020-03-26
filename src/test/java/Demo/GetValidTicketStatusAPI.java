package Demo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetValidTicketStatusAPI {
public static String Authorization ;

@Test
public static void getValidTicketStatus() {


	RestAssured.baseURI= "https://wtt-api-test.skytap-tss.vodafone.com";

	Response validTicketStatusResponse = given()
			.header("Authentication","AiYEwS/OHOATRHy/h2q30hsu2UIWbyy6wN9q1WMG7Jw=")
			.header("Accept-Language","DE")
			.when()
			.get("/api/troubleTicket/v1/TA123456789001/tickettracker/")
			.then()
			.assertThat()
			.statusCode(200)
			.log()
			.body()
			.extract()
			.response()
			;

	String validTicketStatusResponseString = validTicketStatusResponse.asString();
	System.out.println(validTicketStatusResponseString);
	
	Authorization = validTicketStatusResponse.getHeader("Authorization");
	System.out.println("Authorization token is: " + Authorization);
	
}

}
