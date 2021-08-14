import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Payload.CourseResponse;
import Payload.RawToJSON;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class AddbookLIBusingHASHMAP {

	@Test
	public void Addbook()
	{
		HashMap<String,Object> jsonHashmap=new HashMap<>();
		 jsonHashmap.put("name", "hello");
		 jsonHashmap.put("isbn", "dhsslk");
		 jsonHashmap.put("aisle", "4136");
		 jsonHashmap.put("author", "me");
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		
		String response=given().log().all()
		.headers("Content-Type","application/json")
		//changing json payload from here instead of hardcoding the req payload
		.body(jsonHashmap)
		.when()
		.post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		String BookID=RawToJSON.JSONResponse(response, "ID");
		System.out.println(BookID);
		
		
	}
	
}
