package day2;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class PositiveContactListTest {
	
	
  @Test(enabled = true,description="Getting all Contact List")
  public void getAllContactListInfo() {
	  given()
	  .when()
	  	.get("http://3.13.86.142:3000/contacts\n")
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(200); 
  }
  
  @Test(enabled = true,description="Getting Specific Contact1")
  public void getSpecificContactInfo1() {
	  given()
	  .when()
	  	.get("http://3.13.86.142:3000/contacts/615fa5ecf2967f0ec893ae91")
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(200); 
  }
  
  @Test(enabled = true,description="Getting Specific Contact2")
  public void getSpecificContactInfo2() {
 Response res = given()
	  .when()
	  	.get("http://3.13.86.142:3000/contacts/615fa67cf2967f0ec893ae98");
	  
	  System.out.println(res.getTime());
	  
	  res.then()
	  	.log()
	  	.body()
	  	.statusCode(200); 
  }
  

  @Test(enabled = true,description="Adding Contact")
  public void addingContact() {
	  JSONObject details = new JSONObject();
	  JSONObject loc = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  loc.put("city", "Pune");
	  loc.put("country", "India");
	  emp.put("jobTitle", "TE");
	  emp.put("company", "LTI");
	  details.put("firstName", "Indira");
	  details.put("lastName", "Patole");
	  details.put("email", "indira.patole@lnt.com");
	  details.put("location", loc);
	  details.put("employer", emp);
	  
	  ExtractableResponse<Response> ex = given()

	 //String id = given()
	  			.header("Content-Type","application/json")
	  			.body(details.toJSONString())
	  		.when()
	  			.post("http://3.13.86.142:3000/contacts")
	  		.then()
	  			.log()
	  			.body()
	  			.statusCode(200)
	  			.extract()
	  			.path("_id");
	  		
	  System.out.println(ex.path("_id"));
	  System.out.println(ex.path("firstName"));
	  System.out.println(ex.path("lastName"));
	  System.out.println(ex.path("location"));

	 
  }
}