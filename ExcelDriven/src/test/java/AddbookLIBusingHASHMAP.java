import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AddbookLIBusingHASHMAP {

	@Test
	public void Addbook() throws IOException
	{
	DataDriven d=new DataDriven();
		ArrayList al=d.getTestdata("RestAddBook");
		HashMap<String,Object> jsonHashmap=new HashMap<>();
		 jsonHashmap.put("name", "hello");
		 jsonHashmap.put("isbn", "dhsslk");
		 jsonHashmap.put("aisle", "4136");
		 jsonHashmap.put("author", "me");
		/* jsonHashmap.put("name", al.get(1));
		 jsonHashmap.put("isbn", al.get(2));
		 jsonHashmap.put("aisle",al.get(3));
		 jsonHashmap.put("author", al.get(4));*/
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
