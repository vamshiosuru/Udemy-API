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

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class OauthTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		
/*		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\pc\\Downloads\\chromedriver.exe");
		//driver exe should be of same version as that of browser

		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		
    driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
	
	driver.manage().window().maximize();
	
		
		
		
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("1282vamshi@gmail.com");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("O12824136");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		String url=driver.getCurrentUrl();*/
		//as google isnot allowing gmail automation ,we need to get url by signing in manually
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g7FEU2yTDceNiw0DiMMBI7rLejfMXda4ptwHdb_NRC2-YedgNdSryhP2pa1mSld8A&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String s[]=url.split("code=");
		String code=s[1].split("&scope")[0];
		
		System.out.println(code);
		
		String accesstokenresponse=given().urlEncodingEnabled(false).log().all().queryParam("code", code)
		.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code")
		.headers("content-type","application/json")
		.when().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js=new JsonPath(accesstokenresponse);
		String accesstoken=js.getString("access_token");
		
		System.out.println(accesstoken);
		
		
		String response=given()
		.queryParam("access_token", accesstoken)
		.when().get("https://rahulshettyacademy.com/getCourse.php")
		.asString();
		
		System.out.println(response);
	}

}
