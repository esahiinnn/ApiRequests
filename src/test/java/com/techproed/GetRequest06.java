package com.techproed;

 /*
    1)
    When I send a GET request to REST API URL
    https://jsonplaceholder.typicode.com/todos/123
    Then HTTP Status Code should be 200
    And "Server" in Headers should be "cloudflare"
    And response content type is “application/JSON”
    And "userId" should be 7,
    And "title" should be "esse et quis iste est earum aut impedit"
    And "completed" should be false
            */


import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import testbases.TestBaseJsonPlaceHolder;



public class GetRequest06 extends TestBaseJsonPlaceHolder {
    @Test
    public void get01(){
        // Url'i olustur
        spec01.pathParams("name", "todos", "id", 123);

        // Datayi olusturmak

        // Request gonder
        Response response = RestAssured.given().accept("application/json").spec(spec01).when().get("/{name}/{id}");

        response.prettyPrint();

        // Assertion yap
        response.
                then()
                .assertThat().
                statusCode(200).
                contentType("application/JSON").
                header("Server", "cloudflare").
                body("userId", equalTo(7),
                        "id", equalTo(123),
                        "title", equalTo("esse et quis iste est earum aut impedit"),
                        "completed", equalTo(false));


   }
}
