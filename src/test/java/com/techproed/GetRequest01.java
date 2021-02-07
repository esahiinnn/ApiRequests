package com.techproed;


import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
/*
      Given: Baslangıc icin gereklilikleri ifade eder.
      When: Kullanicinin aksiyonunu ifade eder.
      Then: Ciktilari ifade eder-- Assert ler genelde burada yapilir
      And: Herhangi iki coklu adim arasinda kullanilabilir.
       Positive Scenario
    When I send a GET Request to the URL https://restful-booker.herokuapp.com/booking/3
    Then
    HTTP Status code should be 200
    And  Content type should be Json
    And  Status Line should be HTTP/1.1 200 OK
   */
public class GetRequest01 {

    @Test
    public void get01(){
        // 1-) Url'i olustur.
        String url = "https://restful-booker.herokuapp.com/booking/3";

        // 2-) Beklenen datayi olusturmak


        // 3-) Request gonderme
        Response response = given().
                accept(ContentType.JSON). // accept type'inin Json formatinda olmasi icin
                when().
                        get(url);
        response.prettyPrint();// Gelen response'u console'da gormus oldum

        // 4-) Assert islemi
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

        // Console'da status bilgileri gorelim 
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getContentType());
    }
}
