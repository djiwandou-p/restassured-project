package evm.restassured;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(response.statusCode());
        System.out.println(response.asString());
		System.out.println(response.getBody().asString());
		System.out.println(response.statusLine());
        
    }
    
    @Test 
    public void test_Get_Response_Success() {
    	Response response = RestAssured.get("https://reqres.in/api/users?page=2");
    	int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
    }
    
    @Test 
    public void test_Get_Response_Fail() {
    	Response response = RestAssured.get("https://reqres.in/api/users?page=2");
    	int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 400);
    }
    
    @Test
	public void test_Get_Eightth_Item() {
		given().get("https://reqres.in/api/users?page=2").then().
		statusCode(200).
		body("data.id[1]", equalTo(8)).
		body("data.first_name", hasItems("Lindsay")).
		log().all();

	}
}
