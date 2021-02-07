package com.techproed;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import testbases.TestBaseDummy;
import testdatas.DummyTestData;

import java.util.HashMap;
import java.util.Map;

/*
	 	 When
	 	  	I send a POST Request to the Url http://dummy.restapiexample.com/api/v1/create
	 	  	by using the following Request Body {
												    "name":"Ahmet Aksoy",
												    "salary":"1000",
												    "age":"18",
												    "profile_image": ""
												}
	 	 Then
	 	  	Status code is 200
	 	  	And response body should be like {
											    "status": "success",
											    "data": {
											        "name": "Ahmet Aksoy",
											        "salary": "1000",
											        "age": "18",
											        "profile_image": null
											    },
											    "message": "Successfully! Record has been added."
											 }
	*/
public class PostRequest01 extends TestBaseDummy {

    @Test
    public void xD() {
        spec03.pathParam("create", "create");

        DummyTestData expectedObj = new DummyTestData();
        Map<String, String> reqBodyMap = expectedObj.setUpData3();


        Response response = RestAssured.
                given().
                contentType(ContentType.JSON).
                spec(spec03).
                auth().
                basic("admin", "password123").
                body(reqBodyMap).
                when().
                post("/{create}");

        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(reqBodyMap.get("name"), jsonPath.getString("data.name"));
        Assert.assertEquals(reqBodyMap.get("salary"), jsonPath.getString("data.salary"));
        Assert.assertEquals(reqBodyMap.get("age"), jsonPath.getString("data.age"));

        if (reqBodyMap.get("profile_image").equals("")) {
            reqBodyMap.put("profile_image", null);
        }

        Assert.assertEquals(reqBodyMap.get("profile_image"), jsonPath.getString("data.profile_image"));

        Map<String, String> expectedMsgMap = expectedObj.setUpMessageData();
        Assert.assertEquals(expectedMsgMap.get("status"), jsonPath.getString("status"));
        Assert.assertEquals(expectedMsgMap.get("message"), jsonPath.getString("message"));

        // 2.yol GSon --> De-Serialization
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        Assert.assertEquals(reqBodyMap.get("name"), ((Map) actualDataMap.get("data")).get("name"));
        Assert.assertEquals(reqBodyMap.get("salary"), ((Map) actualDataMap.get("data")).get("salary"));
        Assert.assertEquals(reqBodyMap.get("age"), ((Map) actualDataMap.get("data")).get("age"));

        /*
         if(reqBodyMap.get("profile_image").equals("")){
            reqBodyMap.put("profile_image", null);
        }
        Yukarida if statement'i kullandigimiz icin tekrara gerek yok
        Cunku ayni
         */
        Assert.assertEquals(reqBodyMap.get("profile_image"), ((Map) actualDataMap.get("data")).get("profile_image"));
        Assert.assertEquals(expectedMsgMap.get("message"), actualDataMap.get("message"));
        Assert.assertEquals(expectedMsgMap.get("status"), actualDataMap.get("status"));

        // 3.yol JSonObject + jsonPath with softAssert
        SoftAssert softAssert = new SoftAssert();
        JSONObject expectedDataJsonObj = expectedObj.setUpPostReqBodyByUsingJSONObject();
        System.out.println(expectedDataJsonObj);

        softAssert.assertEquals(jsonPath.getString("data.name"), expectedDataJsonObj.getString("name"));
        softAssert.assertEquals(jsonPath.get("data.salary"), expectedDataJsonObj.getString("salary"));
        softAssert.assertEquals(jsonPath.get("data.age"), expectedDataJsonObj.getString("age"));
        //softAssert.assertEquals(jsonPath.get("data.profile_image"), expectedDataJsonObj.getString("profile_image"));

        JSONObject expectedMsgJsonObj = expectedObj.setUpMessageDataByUsingJSONObject();
        softAssert.assertEquals(jsonPath.get("message"), expectedMsgJsonObj.getString("message"));
        softAssert.assertEquals(jsonPath.get("status"), expectedMsgJsonObj.getString("status"));

        softAssert.assertAll();
    }
}
