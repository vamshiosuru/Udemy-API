import org.jvnet.staxex.StAxSOAPBody.Payload;
import org.testng.Assert;

import Payload.CourseResponse;
import io.restassured.path.json.JsonPath;

public class CourseJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=0;
		int SumofAllCourseprices=0;
JsonPath js=new JsonPath(CourseResponse.CourseJSON());

int purchaseamount= js.getInt("dashboard.purchaseAmount");

int numberOfTotalCourses=js.getInt("courses.size()");

String firstCourseTitle=js.getString("courses[0].title");
int FirstCoursePrice=js.getInt("courses[0].price");

String secondCourseTitle=js.getString("courses[1].title");
int secondCoursePrice=js.getInt("courses[1].price");

String thirdCourseTitle=js.getString("courses[2].title");
int thirdCoursePrice=js.getInt("courses[2].price");

int CopiesofFirstCourse=js.getInt("courses[0].copies");
int CopiesofSecondtCourse=js.getInt("courses[1].copies");
int CopiesofRPACourse=js.getInt("courses[2].copies");

for(int k=0;k<numberOfTotalCourses;k++)
{
	int CoursePrice=js.getInt("courses["+k+"].price");
	int Copies=js.getInt("courses["+k+"].copies");
	SumofAllCourseprices=SumofAllCourseprices +( CoursePrice * Copies);
}

//int SumofAllCourseprices= (FirstCoursePrice * CopiesofFirstCourse) + (secondCoursePrice * CopiesofSecondtCourse)+(thirdCoursePrice *CopiesofRPACourse);

System.out.println("numberOfTotalCourses are :"+numberOfTotalCourses);
System.out.println("purchaseamount is :"+purchaseamount);
for(int j=0;j<numberOfTotalCourses;j++)
{
	String CourseTitle=js.getString("courses["+j+"].title");
	if(CourseTitle.equals("RPA"))
	{
		int Copies=js.getInt("courses["+j+"].copies");
		System.out.println("number of copies sold by RPA :"+Copies);
		break;
	}
	
}
//System.out.println("number of copies sold by RPA :"+CopiesofRPACourse);
System.out.println("firstCourseTitle is :"+firstCourseTitle);
//System.out.printf("firstCourseTitle is : %s And Price is: %s",firstCourseTitle,FirstCoursePrice);

while (i<numberOfTotalCourses)
{
	String CourseTitle=js.getString("courses["+i+"].title");
	int CoursePrice=js.getInt("courses["+i+"].price");
	System.out.printf("CourseTitle : %s and Course Price : %s",CourseTitle,CoursePrice);
	System.out.println("");
	i++;
}

System.out.println("TotalCoursePrice :"+SumofAllCourseprices);
if(purchaseamount==SumofAllCourseprices)
{
	System.out.println("SumofAllCourseprices is equal to  purchaseamount");
}
Assert.assertEquals(SumofAllCourseprices, purchaseamount);




	}

}
