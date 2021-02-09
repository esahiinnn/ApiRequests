package com.techproed;
/*
POJO'da olmasi gerekenler;
 1)JSON'da key olanlar icin variable olusturun ve variable'larin access modifier'larini private yapin
 2)Her variable icin getter ve setter method'lari olustrurn
 3)Parametresiz constructor olusturun (
 4)Olusturdugunuz variable'lari parametre kabul eden parametreli constructor olustrun
 5)toString() methodu olusturun
*/

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.TodosPojo;
import testbases.TestBaseJsonPlaceHolder;

public class PostRequestWithPojo01 extends TestBaseJsonPlaceHolder {

    /*
	 	When
	 		I send POST Request to the URL https://jsonplaceholder.typicode.com/todos
	 		with Post Request body  {
									    "userId": 21,
									    "id": 201,
									    "title": "Tidy your room",
									    "completed": false
									  }
	 	Then
	 		Status code is 201
	 		And response body is like {
									    "userId": 21,
									    "id": 201,
									    "title": "Tidy your room",
									    "completed": false
									  }

	 */

    @Test
    public void postPojo01(){

        // url olusturma
        spec01.pathParam("todosPath", "todos");

        // ExpectedDatayi olustur
        TodosPojo expectedPojoData = new TodosPojo(21, 201, "Tidy yoour room", false);

        // Request gonder
        Response response = RestAssured.
                given().
                contentType(ContentType.JSON).
                spec(spec01).
                body(expectedPojoData).
                when().
                post("/{todosPath}");

        response.prettyPrint();

        // Assertsion
        // 1.Yol body -- Pojo

        response.
                then().
                assertThat().
                statusCode(201).
                body("userId", Matchers.equalTo(expectedPojoData.getUserId()),
                        "id", Matchers.equalTo(expectedPojoData.getId()),
                        "title", Matchers.equalTo(expectedPojoData.getTitle()),
                        "completed", Matchers.equalTo(expectedPojoData.isCompleted()));

        // 2.Yol jsonPath ve pojos
        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedPojoData.getUserId(), json.getInt("userId"));
        Assert.assertEquals(expectedPojoData.getId(), json.getInt("id"));
        Assert.assertEquals(expectedPojoData.getTitle(), json.getString("title"));
        Assert.assertEquals(expectedPojoData.isCompleted(), json.getBoolean("completed"));

        // 3. Yol Gson
        TodosPojo actualTodosData = response.as(TodosPojo.class);
        System.out.println(actualTodosData);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTodosData.getId(), expectedPojoData.getId());
        softAssert.assertEquals(actualTodosData.getUserId(), expectedPojoData.getUserId());
        softAssert.assertEquals(actualTodosData.getTitle(), expectedPojoData.getTitle());
        softAssert.assertEquals(actualTodosData.isCompleted(), expectedPojoData.isCompleted());

    }
}
