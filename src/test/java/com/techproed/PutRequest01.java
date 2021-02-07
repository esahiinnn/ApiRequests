package com.techproed;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testbases.TestBaseJsonPlaceHolder;
import testdatas.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

/*
	 	When
	 		I send PUT Requst to the Url https://jsonplaceholder.typicode.com/todos/198
	 		with the PUT Request body like {
										    "userId": 21,
										    "title": "Wash the dishes",
										    "completed": false
										   }
	   Then
	   	   Status code is 200
	   	   And response body is like  {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false,
									    "id": 198
									  }
	 */
public class PutRequest01 extends TestBaseJsonPlaceHolder {

    @Test
    public void put(){
        spec01.pathParams("todos", "todos", "id", "198");

        // ReqBody olustur
        JsonPlaceHolderTestData expectedObj = new JsonPlaceHolderTestData();
        JSONObject expectedDataJSON = expectedObj.setUpData3();



        Response response = RestAssured.
                given().
                contentType(ContentType.JSON).
                spec(spec01).
                body(expectedDataJSON.toString()).
                when().
                put("/{todos}/{id}");

       // response.prettyPrint();

        //Assertion
        // 1.Yol odev body ve JsonObject
        response.
                then().
                assertThat().
                body("userId", Matchers.equalTo(expectedDataJSON.getInt("userId")),
                "completed", Matchers.equalTo(expectedDataJSON.getBoolean("completed")),
                "title", Matchers.equalTo(expectedDataJSON.getString("title")));

        // 2.Yol odev-- jsonpath ve JsonOBject
        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(expectedDataJSON.getInt("userId"), jsonPath.getInt("userId"));
        Assert.assertEquals(expectedDataJSON.getBoolean("completed"), jsonPath.getBoolean("userId"));
        Assert.assertEquals(expectedDataJSON.getString("title"), jsonPath.getString("title"));

        // 3.Yol Gson ve JsonObject
        response.then().assertThat().statusCode(200);

        Map<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);
        Assert.assertEquals(expectedDataJSON.getInt("userId"), actualDataMap.get("userId"));
        Assert.assertEquals(expectedDataJSON.getBoolean("completed"), actualDataMap.get("completed"));
        Assert.assertEquals(expectedDataJSON.getString("title"), actualDataMap.get("title"));
    }
}
