import org.testng.Assert;

import org.testng.annotations.Test;

import Payload.CourseResponse;
import io.restassured.path.json.JsonPath;
public class TotalPriceValidation {

	
	@Test
	public void PriceValidation()
	{
		JsonPath js=new JsonPath(CourseResponse.CourseJSON());
		int SumofAllCourseprices=0;

		int purchaseamount= js.getInt("dashboard.purchaseAmount");

		int numberOfTotalCourses=js.getInt("courses.size()");
		
		for(int k=0;k<numberOfTotalCourses;k++)
		{
			int CoursePrice=js.getInt("courses["+k+"].price");
			int Copies=js.getInt("courses["+k+"].copies");
			SumofAllCourseprices=SumofAllCourseprices +( CoursePrice * Copies);
		}
		System.out.println("Courses price:"+SumofAllCourseprices);
		Assert.assertEquals(SumofAllCourseprices, purchaseamount);

	}
	
}
