import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Payload.CourseResponse;
import Payload.RawToJSON;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class JIRAaddcomment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
        RestAssured.baseURI="http://localhost:8080";
      //create session id
        //regular approach
      	/*	String response=given().headers("Content-Type","application/json")
      				.body("{ \"username\": \"1282vamshi\",\r\n" + 
      						" \"password\": \"O12824136\" \r\n" + 
      						" }")
      				.when()
      				.post("rest/auth/1/session")
      				.then().log().all().extract().response().asString();
      		JsonPath js=new JsonPath(response);
      		String sessionID=js.getString("session.value");*/
        
        //using session filter
        SessionFilter session=new SessionFilter();
        given().relaxedHTTPSValidation().headers("Content-Type","application/json")
			.body("{ \"username\": \"1282vamshi\",\r\n" + 
					" \"password\": \"O12824136\" \r\n" + 
					" }")
			.filter(session)
			.when()
			.post("rest/auth/1/session")
			.then().log().all().extract().response().asString();

        //Add comments
        String comment="Adding comment from rest Assured";
        String response=given().log().all()
        .pathParam("key", "CREAT-3")
        .headers("Content-Type","application/json")
        //.headers("Cookie","JSESSIONID="+sessionID+"")
        .body("{\r\n" + 
        		"    \"body\": \""+comment+"\"\r\n" + 
        		"}")
        .filter(session)
		.when()
        .post("rest/api/2/issue/{key}/comment")
        .then().log().all().assertThat().statusCode(201).extract().response().asString();
        
        JsonPath js=new JsonPath(response);
        String commentID=js.getString("id");
        
        //get issue
        String issuedetails=given().log().all()
        .pathParam("key", "CREAT-3") //using both path and query params
        .queryParam("fields","comment")
       
        .filter(session)
        .when().get("rest/agile/1.0/issue/{key}")
        .then().log().all().extract().response().asString();
        
        JsonPath js1=new JsonPath(issuedetails);
        int commentCount=js1.getInt("fields.comment.comments.size()");
        
        for(int i=0;i<commentCount;i++)
        {
        	String commentid=js1.get("fields.comment.comments["+i+"].id").toString();
        	if(commentid.equals(commentID))
        	{
        		System.out.println(commentid);
        		String actualcomment=js1.getString("fields.comment.comments["+i+"].body");
        		Assert.assertEquals(actualcomment, comment);
        		System.out.println(actualcomment);
        		break;
        	}
        }
        
	}

}
