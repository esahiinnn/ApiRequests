package com.techproed;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testbases.TestBaseHerOkuApp;
import testdatas.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

/*
   When
   I send GET Request to https://restful-booker.herokuapp.com/booking/1
   Then
   Response body should be like that;
   {
       "firstname": "Eric",
           "lastname": "Smith",
           "totalprice": 555,
           "depositpaid": false,
           "bookingdates": {
       "checkin": "2016-09-09",
               "checkout": "2017-09-21"
   }
   }
   */
public class GetRequest13 extends TestBaseHerOkuApp {

    @Test
    public void get(){
        // 1. Url
        spec02.pathParam("id", "1");

        // Beklenen datayi olustur
        HerOkuAppTestData expectedOBj = new HerOkuAppTestData();
        Map<String, Object> expectedDataMap = expectedOBj.setUpData();
        System.out.println(expectedDataMap);
        //Request gonder
        Response response = RestAssured.
                given().
                spec(spec02).
                when().
                get("/{id}");

        //response.prettyPrint();

        //Assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);
        Assert.assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"), ((Map)actualDataMap.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"), ((Map)actualDataMap.get("bookingdates")).get("checkout"));

    }
}
