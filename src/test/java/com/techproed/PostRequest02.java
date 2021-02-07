package com.techproed;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import testbases.TestBaseHerOkuApp;
import testdatas.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

/*
	 	When
	 		I send POST Request to the Url https://restful-booker.herokuapp.com/booking
	 		with the request body {
								    "firstname": "Selim",
								    "lastname": "Ak",
								    "totalprice": 11111,
								    "depositpaid": true,
								    "bookingdates": {
								        "checkin": "2020-09-09",
								        "checkout": "2020-09-21"
								     }
								  }
	 	Then
	 		Status code is 200
	 		And response body should be like {
											    "bookingid": 11,
											    "booking": {
											        "firstname": "Selim",
											        "lastname": "Ak",
											        "totalprice": 11111,
											        "depositpaid": true,
											        "bookingdates": {
											            "checkin": "2020-09-09",
											            "checkout": "2020-09-21"
											        }
											    }
											 }
	 */
public class PostRequest02 extends TestBaseHerOkuApp {

    @Test
    public void post(){

        HerOkuAppTestData expectedObj = new HerOkuAppTestData();
        JSONObject expectedJson = expectedObj.setUpData2();
        System.out.println(expectedJson);
        Response response = RestAssured.given().
                contentType(ContentType.JSON).
                spec(spec02).
                        auth().
                        basic("admin", "password123").
                when().
                        body(expectedJson.toString()).
                        post();



        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                body("booking.firstname", Matchers.equalTo(expectedJson.get("firstname")),
                        "booking.lastname", Matchers.equalTo(expectedJson.get("lastname")),
                        "booking.totalprice", Matchers.equalTo(expectedJson.get("totalprice")),
                        "booking.depositpaid", Matchers.equalTo(expectedJson.get("depositpaid")),
                        "booking.bookingdates.checkin", Matchers.equalTo(expectedJson.getJSONObject("bookingdates").get("checkin")),
                        "booking.bookingdates.checkout", Matchers.equalTo(expectedJson.getJSONObject("bookingdates").get("checkout")));

        //2.yol HardAssert JSonObject ve json

        JsonPath json = response.jsonPath();

        Assert.assertEquals(expectedJson.get("firstname"), json.get("booking.firstname"));
        Assert.assertEquals(expectedJson.get("lastname"), json.get("booking.lastname"));
        Assert.assertEquals(expectedJson.get("totalprice"), json.get("booking.totalprice"));
        Assert.assertEquals(expectedJson.get("depositpaid"), json.get("booking.depositpaid"));
        Assert.assertEquals(expectedJson.getJSONObject("bookingdates").get("checkin"), json.get("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedJson.getJSONObject("bookingdates").get("checkout"), json.get("booking.bookingdates.checkout"));



        // 3.Yl soft Assert ve JSONObject ve GSON De-serialization
       Map<String, Object> actualDataMap = response.as(HashMap.class);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(((Map)actualDataMap.get("booking")).get("firstname"), expectedJson.getString("firstname"));
        softAssert.assertEquals(((Map)actualDataMap.get("booking")).get("lastname"), expectedJson.getString("lastname"));
        softAssert.assertEquals(((Map)actualDataMap.get("booking")).get("totalprice"), expectedJson.getInt("totalprice"));
        softAssert.assertEquals(((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkin"), expectedJson.getJSONObject("bookingdates").get("checkin"));
        softAssert.assertEquals(((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkout"), expectedJson.getJSONObject("bookingdates").get("checkout"));
    }

}
