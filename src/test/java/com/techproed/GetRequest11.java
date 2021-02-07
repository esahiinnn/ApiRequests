package com.techproed;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.Assert;
import org.testng.asserts.SoftAssert;
import testbases.TestBaseJsonPlaceHolder;
import testdatas.JsonPlaceHolderTestData;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

/*When
	 I send GET Request to jsonplaceholder api/todos/2
	 Status code: 200
	 "completed": is false
	 "title = "quis ut nam facilis et officia qui"
	 "userId" = 2
	 header "via"
	 header "Server"

De-Serialization: Json datasini Java objelerine(Map, List, List of Map, Set) cevirme islemidir
GSON dependency kullanarak De-Serialization ve Serialization yapilabilir.
	 */
public class GetRequest11 extends TestBaseJsonPlaceHolder {

    @Test
    public void get(){
        // Url'i olusturduk
        spec01.pathParams("todos", "todos", "id", 34);

        //Expected data'yi olustur


        // Request olustur
        Response response = RestAssured.
                given().
                spec(spec01).
                when().
                get("/{todos}/{id}");
        response.prettyPrint();

        JsonPlaceHolderTestData expectedObj = new JsonPlaceHolderTestData();
        expectedObj.setUpData();
        // 1.yol body kullanarak assert edelim
        response.
                then().
                statusCode((Integer) expectedObj.expectedData.get("Status Code")).
                body("userId", equalTo(expectedObj.expectedData.get("userId")),
                        "title", equalTo(expectedObj.expectedData.get("title")),
                        "completed", equalTo(expectedObj.expectedData.get("completed"))).
                headers("Via", equalTo(expectedObj.expectedData.get("Via")),
                        "Server", equalTo(expectedObj.expectedData.get("Server")));

        // De-Serialization ile 2.yol

        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        Assert.assertEquals(expectedObj.expectedData.get("Status Code"), response.getStatusCode());
        Assert.assertEquals(expectedObj.expectedData.get("userId"), actualDataMap.get("userId"));
        Assert.assertEquals(expectedObj.expectedData.get("title"), actualDataMap.get("title"));
        Assert.assertEquals(expectedObj.expectedData.get("completed"), actualDataMap.get("completed"));
        Assert.assertEquals(expectedObj.expectedData.get("Via"), response.getHeader("Via"));
        Assert.assertEquals(expectedObj.expectedData.get("Server"), response.getHeader("Server"));

        // 3.yol softAssert
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), expectedObj.expectedData.get("Status Code"));
        softAssert.assertEquals(actualDataMap.get("userId"), expectedObj.expectedData.get("userId"));
        softAssert.assertEquals(actualDataMap.get("title"), expectedObj.expectedData.get("title"));
        softAssert.assertEquals(actualDataMap.get("completed"), expectedObj.expectedData.get("completed"));
        softAssert.assertEquals(response.getHeader("Via"), expectedObj.expectedData.get("Via"));
        softAssert.assertEquals(response.getHeader("Server"), expectedObj.expectedData.get("Server"));


        softAssert.assertAll();
    }
}
