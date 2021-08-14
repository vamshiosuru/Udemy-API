import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import Payload.RequestPayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class IntegratingMultipleAPIs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//RestAssured.basePath="maps/api/place/add/json";
		String response =given().log().all()
		.queryParam("key","qaclick123")
		.header("Content-Type","application/json")
		.body(RequestPayload.AddPlace())
		.when().post("maps/api/place/add/json") //-- above base URI and basepath would be same as this line
		//.when().post()
		.then().log().all().assertThat().statusCode(200)
		.body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		JsonPath js=new JsonPath(response);
		String placeid=js.getString("place_id");
		System.out.println(placeid);
		
		//get place details for above place id
		given().log().all()
		.queryParam("place_id", placeid)
		.queryParam("key","qaclick123")
		//.header("Content-Type","application/json") //==for get as there is not req body req header content type is not needed
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200)
		.body("name",equalTo( "Frontline house"));
		
	}

}
