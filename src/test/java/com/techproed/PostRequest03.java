package com.techproed;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import testbases.TestBaseJsonPlaceHolder;
import testdatas.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

/*
	 	When
	  		I send POST Request to the Url https://jsonplaceholder.typicode.com/todos
	  		with the request body {
								    "userId": 55,
								    "title": "Tidy your room",
								    "completed": false
								   }
		Then
			Status code is 200
			And response body is like {
									    "userId": 55,
									    "title": "Tidy your room",
									    "completed": false,
									    "id": 201
									   }
	 */
public class PostRequest03 extends TestBaseJsonPlaceHolder {

    @Test
    public void post(){

        spec01.pathParam("create", "todos");

        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();
        JSONObject expectedJson = expectedData.setUpData2();

        Response response = RestAssured
                .given().
                        contentType(ContentType.JSON).
                        spec(spec01).
                        auth().
                        basic("admin", "password123").
                        when().
                        body(expectedJson.toString()).
                        post("/{create}");

        response.prettyPrint();

        // Assertion

        //1. Yol Body ve Matchers
        response.
                then().
                assertThat().
                statusCode(expectedData.statusCode).
                body("userId", Matchers.equalTo(expectedJson.getInt("userId")),
                        "title", Matchers.equalTo(expectedJson.get("title")),
                        "completed", Matchers.equalTo(expectedJson.getBoolean("completed")));


        //2. Yol JSONObject ve JsonPath
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(expectedData.statusCode, response.getStatusCode());
        Assert.assertEquals(expectedJson.get("userId"), jsonPath.getInt("userId"));
        Assert.assertEquals(expectedJson.get("title"), jsonPath.getString("title"));
        Assert.assertEquals(expectedJson.get("completed"), jsonPath.getBoolean("completed"));


        // 3.Yol GSON De-Serialization ve JSONObject ile
        Map<String, Object> actualData = response.as(HashMap.class);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actualData.get("userId"), expectedJson.getInt("userId"));
        softAssert.assertEquals(actualData.get("title"), expectedJson.getString("title"));
        softAssert.assertEquals(actualData.get("completed"), expectedJson.getBoolean("completed"));
    }
}
