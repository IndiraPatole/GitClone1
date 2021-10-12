package day2;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class GitHub {
	@BeforeTest
	public void beforeTest() {
	baseURI="https://api.github.com/user/repos";
		authentication= RestAssured.oauth2("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");
	}
	
	
  @Test (enabled = true)
  public void gettingAllRepository() {
	  
	 given()
	   //.auth()
	   //.oauth2("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")
	   
	  .when()
	    .get()
	  .then()
	     .log()
	     .body()
	     .statusCode(200);
  }
  
  @Test (enabled = true)
  public void createRepository() {
	  JSONObject data = new JSONObject();
	  data.put("name", "RestAssuredCreations4");
	  data.put("description", "I am created by a RestAssured Tool");
	  data.put("homepage", "https://github.com/IndiraPatole");
	  
	  
	 given()
	   
	   .header("Content-Type","application/json")
	   .body(data.toJSONString())
	  .when()
	    .post()
	  .then()
	     .log()
	     .headers()
	     .statusCode(201)
	     .time(Matchers.lessThan(8000L), TimeUnit.MILLISECONDS);
  }
}
