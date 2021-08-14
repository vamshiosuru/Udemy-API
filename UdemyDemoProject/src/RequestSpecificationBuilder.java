import org.testng.Assert;

		import org.testng.annotations.DataProvider;
		import org.testng.annotations.Test;

		import Payload.CourseResponse;
		import Payload.RawToJSON;
		import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Addplacepojo;
import pojo.GetCourse;
import pojo.Locationpojo;
import pojo.Webautomation;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.parsing.Parser;

import static io.restassured.RestAssured.*;
		import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
		import org.openqa.selenium.Keys;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.chrome.ChromeDriver;
public class RequestSpecificationBuilder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").
				setContentType("application/json").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectContentType("application/json").expectStatusCode(200).build();
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		Addplacepojo place=new Addplacepojo();
		Locationpojo l=new Locationpojo();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		place.setLocation(l);
		List<String> a=new ArrayList<String>();
		a.add("shoe park");
		a.add("shop");
		place.setTypes(a);
		place.setLocation(l);
		place.setAccuracy(20);
		place.setAddress("28, side layout, cohen 09");
		place.setName("rest assured");
		place.setLanguage("English");
		place.setPhone_number("\"(+91) 983 893 3937");
		
		
		RequestSpecification res=given().log().all().spec(req)
		.body(place) ;
		Response response=res.when().post("maps/api/place/add/json")
		.then().log().all().spec(resSpec).extract().response();
		
		String Response=response.asString();
		System.out.println(Response);
	}

}
