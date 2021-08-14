import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import Payload.RawToJSON;
import Payload.RequestPayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class EndToEndTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
				RestAssured.baseURI="https://rahulshettyacademy.com";
				
				//RestAssured.basePath="maps/api/place/add/json";
				
				//adding  new place by generating new place id
				String response =given().log().all()
				.queryParam("key","qaclick123")
				.header("Content-Type","application/json")
				.body(RequestPayload.AddPlace())
				.when().post("maps/api/place/add/json") //-- above base URI and basepath would be same as this line
				//.when().post()
				.then().log().all().assertThat().statusCode(200)
				.body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
				
				//JsonPath js=new JsonPath(response);
				//String placeid=js.getString("place_id");
				String placeid=RawToJSON.JSONResponse(response,"place_id"); // when method is static ,we can invoke by classname.method name
				System.out.println(placeid);
				
				//get place details for above place id
				given().log().all()
				.queryParam("place_id", placeid)
				.queryParam("key","qaclick123")
				//.header("Content-Type","application/json") //==for get as there is not req body req header content type is not needed
				.when().get("maps/api/place/get/json")
				.then().log().all().assertThat().statusCode(200)
				.body("name",equalTo( "Frontline house"));
				
				//update place details for above place id
				String newplace="780 winter walk, USA";
				given().log().all()
				.queryParam("key","qaclick123")
				.header("Content-Type","application/json")
				.body("{\r\n" + 
						"\"place_id\": \""+placeid+"\",\r\n" + 
						"\"address\": \""+newplace+"\",\r\n" + 
						"\"key\": \"qaclick123\"\r\n" + 
						"}")
				.when().put("maps/api/place/update/json")
				.then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));
				
				//trying to get the place details for updated name for above place id
				String updatedresponse=given().log().all()
				.queryParam("place_id", placeid)
				.queryParam("key","qaclick123")
				//.header("Content-Type","application/json") //==for get as there is not req body req header content type is not needed
				.when().get("maps/api/place/get/json")
				.then().log().all().assertThat().statusCode(200)
				.body("address",equalTo( newplace)).extract().response().asString();
				
				//JsonPath js1=new JsonPath(updatedresponse);
				//String updatedAddress=js1.getString("address");
				String updatedAddress=RawToJSON.JSONResponse(updatedresponse,"address");
				
		
				
				//delete the above place id
				given().log().all()
				.queryParam("key","qaclick123")
				.header("Content-Type","application/json")
				.body("{\r\n" + 
						"\"place_id\":\""+placeid+"\"\r\n" + 
						"\r\n" + 
						"}")
				.when().delete("maps/api/place/delete/json")
				.then().log().all().assertThat().statusCode(200)
				.body("status", equalTo("OK"));
				
				//trying to get details of deleted place id
				given().log().all()
				.queryParam("place_id", placeid)
				.queryParam("key","qaclick123")
				//.header("Content-Type","application/json") //==for get as there is not req body req header content type is not needed
				.when().get("maps/api/place/get/json")
				.then().log().all().assertThat().statusCode(404)
				.body("msg",equalTo( "Get operation failed, looks like place_id  doesn't exists"));
				
				
				// validating address after updating address
				Assert.assertEquals(updatedAddress, newplace);
				
				
	}

}
