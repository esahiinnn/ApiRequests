package com.techproed;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import testbases.TestBaseHerOkuApp;

public class PostRequestWithPojo02 extends TestBaseHerOkuApp {
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
    @Test
    public void postWithPojo(){

        //spec02.pathParam("bookingPath", "booking");

        // Data olustur
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2020-09-09", "2020-09-21");
        BookingPojo expectedPojoData = new BookingPojo("Chris", "Brown", 11111, true, bookingDatesPojo);

        // Request gonder
        Response response = RestAssured.
                given().
                contentType(ContentType.JSON).
                spec(spec02).
                body(expectedPojoData).
                when().
                post();

        //response.prettyPrint();

        // Assertion
        // 1.Yol body with pojo


        // 2.Yol jsonPath with pojo


        /* 3.Yol Gson with pojo NOT: Assertion hata verir. Cunku BookingPojo'nun formati gelen response'dan farklidir
         Bu tarz problemlerle karsilasilirsa ya Gson kullanilmaz yada yeni bir Pojo Class'i olusturup
                                                                 response'dan gelenleri icine eklemeliyim */

        // BookingPojo actualBooking = response.as(BookingPojo); ======== FAILURE
        BookingResponsePojo actualBooking = response.as(BookingResponsePojo.class);
        System.out.println(actualBooking);

        Assert.assertEquals(expectedPojoData.getFirstname(),actualBooking.getBooking().getFirstname());
        Assert.assertEquals(expectedPojoData.getLastname(),actualBooking.getBooking().getLastname());
        Assert.assertEquals(expectedPojoData.getTotalprice(),actualBooking.getBooking().getTotalprice());
        Assert.assertEquals(expectedPojoData.isDepositpaid(),actualBooking.getBooking().isDepositpaid());
        Assert.assertEquals(expectedPojoData.getBookingdates().getCheckin(),actualBooking.getBooking().getBookingdates().getCheckin());
        Assert.assertEquals(expectedPojoData.getBookingdates().getCheckout(),actualBooking.getBooking().getBookingdates().getCheckout());

    }
}
