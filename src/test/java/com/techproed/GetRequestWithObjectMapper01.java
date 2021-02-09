package com.techproed;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.junit.Assert;
import org.junit.Test;
import testbases.TestBaseJsonPlaceHolder;
import utilities.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/*
	 	When
	 		I send GET Request to the URL https://jsonplaceholder.typicode.com/todos/198

	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }

     */
public class GetRequestWithObjectMapper01 extends TestBaseJsonPlaceHolder {

    @Test
    public void getWithObjectMapper() {

        spec01.pathParams("todosPath", "todos", "idPath", 198);


        String expectedJson = "{\n" +
                " \"userId\": 10,\n" +
                "\"id\": 198,\n" +
                "\"title\": \"quis eius est sint explicabo\",\n" +
                "\"completed\": true\n" +
                "}";
        Map<String, Object> expectedMap = JsonUtil.convertJsonToJava(expectedJson, Map.class);
        System.out.println(expectedMap);


        Response response = RestAssured.
                given().
                contentType(ContentType.JSON).
                spec(spec01).
                when().
                get("/{todosPath}/{idPath}");

        // Response body'yi Map'e ceviricem
        Map<String, Object> actualDataMap = JsonUtil.convertJsonToJava(response.asString(), Map.class);
        System.out.println(actualDataMap);

        // Assertion
        Assert.assertEquals(expectedMap.get("id"), actualDataMap.get("id"));
        Assert.assertEquals(expectedMap.get("userId"), actualDataMap.get("userId"));
        Assert.assertEquals(expectedMap.get("title"), actualDataMap.get("title"));
        Assert.assertEquals(expectedMap.get("completed"), actualDataMap.get("completed"));
    }
}
