import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Payload.RequestPayload;	
public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//endpoint = base url + resource + query / path params
		//given -all the input details
		//when -submit the API -resource, http method
		//then - validate the response

		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//RestAssured.basePath="/maps/api/place/add/json";
		given().log().all()
		.queryParam("key","qaclick123")
		.header("Content-Type","application/json")
		.body(RequestPayload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200)
		.body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)");
	}

}
