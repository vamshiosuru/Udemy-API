package com.qa.utils;

import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestUtils {

	RequestSpecBuilder r1;
	ResponseSpecBuilder r2;
	RequestSpecification a1;
	ResponseSpecification a2;
	
	public void endPoint(){
		
		
	}
	
	public RequestSpecification getRequestSpec(){
		r1= new RequestSpecBuilder();
		
		r1.setBasePath("");
		r1.addHeader("", "");
	a1=	r1.build();
	a1.queryParam("", "");
	return a1;
		
	}
	public ResponseSpecification getResponseSpec(){
r2= new ResponseSpecBuilder();
		
		r2.expectStatusCode(200);
		r2.expectResponseTime(lessThan(3L), TimeUnit.SECONDS);
	a2=	r2.build();
	return a2;
		
	}
}
