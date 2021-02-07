package com.techproed;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest03 {
    /*
	 Positive Scenario:
	 When Asagidaki Endpoint'e bir GET request yolladim
	 https://restful-booker.herokuapp.com/booking/7
     And Accept type “application/json” dir
     Then
     HTTP Status Code 200
     And Response format "application/json"
     And firstname "Sally"
     And lastname "Jackson"
     And checkin date "2017-04-19"
     And checkout date "2020-03-22"
	*/

    @Test
    public void getRequest01(){
        String url = "https://restful-booker.herokuapp.com/booking/7";

        Response response = given().
                accept("application/json").
                when().
                get(url);

        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json");

        response.
                then().
                assertThat().
                body("firstname", equalTo("Mark")).
                body("lastname", equalTo("Jones")).
                body("totalprice", equalTo(975)).
                body("depositpaid", equalTo(true)).
                body("bookingdates.checkin", equalTo("2019-12-04")).
                body("bookingdates.checkout", equalTo("2020-10-26")).
                body("additionalneeds", equalTo("Breakfast"));

        response.
                then().
                assertThat().
                body("firstname",equalTo("Jim"),
                        "lastname",equalTo("Jones"),
                        "totalprice",equalTo(932),
                        "depositpaid",equalTo(false),
                        "bookingdates.checkin",equalTo("2018-02-17"),
                        "bookingdates.checkout",equalTo("2020-09-24"));
    }
}
