package com.techproed;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Test;
import testbases.TestBaseHerOkuApp;

import static io.restassured.RestAssured.*;


public class GetRequest07 extends TestBaseHerOkuApp {
    /*
     * When I send a GET request to REST API URL
     * https://restful-booker.herokuapp.com/booking/5
     * Then HTTP Status Code should be 200
     * And response content type is “application/JSON”
     * And response body should be like;
     * {"firstname": Sally,
     *   "lastname": "Smith",
     *   "totalprice": 789,
     *   "depositpaid": false,
     *   "bookingdates": { "checkin": "2017-12-11",
     *                     "checkout":"2020-02-20" }
     * }
     */
    @Test
    public void get01() {
        // Url olusturmak
        spec02.pathParam("bookingid", 5);

        //Request olusturmak
        Response response = given().
                spec(spec02).
                when().
                get("/{bookingid}");
        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

        //jsonPath ();
        // response un icerisinde hareket edebilmezi ve degerlere ulasabilmemizi saglar.
        JsonPath jsonPath = response.jsonPath();
        System.out.println("First name " + jsonPath.getString("firstname"));
        Assert.assertEquals("Firstname istenilen gibi degil", "Mary", jsonPath.getString("firstname"));
        System.out.println("Last name " + jsonPath.getString("lastname"));
        Assert.assertEquals("Lastname istenilen gibi degil", "Ericsson", jsonPath.getString("lastname"));
        System.out.println("Total price " + jsonPath.getInt("totalprice"));
        Assert.assertEquals("Totalprice istenilen gibi degil ", 558, jsonPath.getInt("totalprice"));
        System.out.println("Deposit paid " + jsonPath.getBoolean("depositpaid"));
        Assert.assertTrue("Deposit paid beklenen gibi degil ", jsonPath.getBoolean("depositpaid"));
        System.out.println("Check in tarihi " + jsonPath.getString("bookingdates.checkin"));
        Assert.assertEquals("Check in tarihi istenilen gibi degil ", "2019-10-20", jsonPath.getString("bookingdates.checkin"));
        System.out.println("Check out tarihi " + jsonPath.getString("bookingdates.checkout"));
        Assert.assertEquals("Check out tarihi istenilen gibi degil ", "2020-08-25", jsonPath.getString("bookingdates.checkout"));

        // 2. yol
        response.
                then().
                assertThat().
                body("firstname", equalTo("Eric"),
                        "lastname", equalTo("Wilson"),
                        "totalprice", equalTo(276),
                        "depositpaid", equalTo(false),
                        "bookingdates.checkin", equalTo("2020-08-17"),
                        "bookingdates.checkout", equalTo("2020-09-05"));

    }

}