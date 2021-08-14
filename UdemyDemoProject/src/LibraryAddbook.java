import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Payload.CourseResponse;
import Payload.RawToJSON;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class LibraryAddbook {

	@Test(dataProvider="testdata")
	public void Addbook(String name ,String isbn,String aisle)
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String id=isbn+aisle;
		
		String response=given().log().all()
		.headers("Content-Type","application/json")
		//changing json payload from here instead of hardcoding the req payload
		.body(CourseResponse.AddBookPayload( name , isbn, aisle))
		.when()
		.post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		String BookID=RawToJSON.JSONResponse(response, "ID");
		System.out.println(BookID);
		
		//after adding delete the added books to avoid duplication error
		
		given().headers("Content-Type","application/json")
		.body("{\r\n" + 
				"    \"ID\": \""+id+"\"\r\n" + 
				"}")
		.when()
		.delete("Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200);
	}
	
	//for parameterization test data
	@DataProvider(name="testdata")
	public Object[][] testdata()
	{
		//int a[]=new int[]{1,2,3};
		Object[][] data =new  Object[][] {{"name01","isbn01","0121"},
				                          {"name02","isbn02","0131"},
				                          {"name03","isbn03","0141"}
		                                 };
		return data;
	}
}
