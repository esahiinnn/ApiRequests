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
	 		I send PATCH Request to the Url https://jsonplaceholder.typicode.com/todos/198
	 		with the PUT Request body like {
										    "title": "Tidy your room",
										   }
	   Then
	   	   Status code is 200
	   	   And response body is like  {
									    "userId": 10,
									    "title": "Tidy your room",
									    "completed": true,
									    "id": 198
									  }
   */
public class PatchRequest01 extends TestBaseJsonPlaceHolder {

    @Test
    public void patch() {
        spec01.pathParams("todos", "todos", "id", "198");

        JsonPlaceHolderTestData expectedObj = new JsonPlaceHolderTestData();
        JSONObject expectedJsonObj = expectedObj.setUpData4();
        Response response = RestAssured.
                given().
                contentType(ContentType.JSON).
                spec(spec01).
                body(expectedJsonObj.
                        toString()).
                when().
                patch("/{todos}/{id}");

        response.prettyPrint();

        // Assertion

        //1. Yol body, Matchers ve JsonObject
        response.
                then().
                assertThat().
                statusCode(200).
                body("title", Matchers.equalTo(expectedJsonObj.getString("title")),
                        "userId", Matchers.equalTo(expectedObj.userId),
                        "completed", Matchers.equalTo(expectedObj.completed));

        //2.Yol jsonPAth ve jsonObject
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(expectedJsonObj.getString("title"), jsonPath.getString("title"));
        Assert.assertEquals(expectedObj.completed, jsonPath.getBoolean("completed"));
        Assert.assertEquals(expectedObj.userId, jsonPath.getInt("userId"));

        // 3.Yol GSON ve JSONObject
        Map<String, Object> actualTitle = response.as(HashMap.class);
        Assert.assertEquals(expectedJsonObj.getString("title"), actualTitle.get("title"));
        Assert.assertEquals(expectedObj.userId, actualTitle.get("userId"));
        Assert.assertEquals(expectedObj.completed, actualTitle.get("completed"));

    }
}
