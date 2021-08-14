package Payload;

import io.restassured.path.json.JsonPath;

public class RawToJSON {

	public static String  JSONResponse (String response ,String attribute)
	{
		JsonPath js= new JsonPath(response);
		return js.getString(attribute);
	}
}
