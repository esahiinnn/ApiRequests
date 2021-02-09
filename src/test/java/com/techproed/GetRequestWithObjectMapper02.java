package com.techproed;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testbases.TestBaseHerOkuApp;
import utilities.JsonUtil;

import java.util.Map;

/*
		 	When
		 		I send GET Request to the URL https://restful-booker.herokuapp.com/booking/2

		 	Then
		 		Status code is 200
		 		And response body is like {
										    "firstname": "Mark",
										    "lastname": "Ericsson",
										    "totalprice": 726,
										    "depositpaid": true,
										    "bookingdates": {
										        "checkin": "2015-08-07",
										        "checkout": "2020-10-25"
										     }
										  }

		 */
public class GetRequestWithObjectMapper02 extends TestBaseHerOkuApp {

    @Test
    public void get(){
        // Url'i olustur
    spec02.pathParam("idPath", 2);

    // Expected data'yi olustur
    String expectedJson = "{\n" +
            "\"firstname\": \"Sally\",\n" +
            "\"lastname\": \"Wilson\",\n" +
            "\"totalprice\": 147,\n" +
            "\"depositpaid\": false,\n" +
            "\"bookingdates\": {\n" +
            "\"checkin\": \"2016-09-13\",\n" +
            "\"checkout\": \"2018-12-14\"\n" +
            "}\n" +
            "}";

    Map<String, Object> expectedDataMap = JsonUtil.convertJsonToJava(expectedJson, Map.class);
        System.out.println(expectedDataMap);

        //Request gonder
        Response response = RestAssured.
                given().
                contentType(ContentType.JSON).
                spec(spec02).
                when().
                get("/{idPath}");

        response.prettyPrint();
        response.
                then().
                assertThat().
                statusCode(200);

        Map<String, Object> actualDataMap = JsonUtil.convertJsonToJava(response.asString(), Map.class);
        Assert.assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"), ((Map)actualDataMap.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"), ((Map)actualDataMap.get("bookingdates")).get("checkout"));



    }
}
