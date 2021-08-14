
import org.kohsuke.rngom.digested.Main;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Payload.CourseResponse;
import Payload.RawToJSON;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class StaticJsonPayload {

	@Test
	public void Addbook() throws IOException, URISyntaxException
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		
		String response=given().log().all()
		.headers("Content-Type","application/json")
		//getting req payload from static file
		//converting file data in to bytes and bytes to String
		.body(new String(Files.readAllBytes(Paths.get(("‪C:\\Users\\pc\\Desktop\\Addplacepayload.json").trim()))))
		.when()
		.post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		String BookID=RawToJSON.JSONResponse(response, "ID");
		System.out.println(BookID);
		
		
	}
	
	
}
