package com.techproed;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import testbases.TestBaseDummy;
import testdatas.DummyTestData;

import java.util.HashMap;
import java.util.Map;

/*
	 	When
	 		I send DELETE Request to the Url http://dummy.restapiexample.com/api/v1/delete/2
	 	Then
		 	Status code is 200
		 	And Response body is {
								    "status": "success",
								    "data": "2",
								    "message": "Successfully! Record has been deleted"
								 }
	*/
public class DeleteRequest01 extends TestBaseDummy {
    @Test
    public void delete() {
        spec03.pathParams("deletePath", "delete", "id", 2);

        DummyTestData expectedObj = new DummyTestData();
        Map<String, Object> expectedMap = expectedObj.setUpExpectedDeleteDataByUsingMap();
        Response response = RestAssured.
                given().
                spec(spec03).
                contentType(ContentType.JSON).
                when().
                delete("/{deletePath}/{id}");

        response.prettyPrint();

        response.
                then().
                assertThat().
                body("status", Matchers.equalTo(expectedMap.get("status")),
                "data", Matchers.equalTo(expectedMap.get("data")),
                "message", Matchers.equalTo(expectedMap.get("message")));

        JsonPath json = response.jsonPath();

        Assert.assertEquals(expectedMap.get("status"), json.getString("status"));
        Assert.assertEquals(expectedMap.get("data"), json.getString("data"));
        Assert.assertEquals(expectedMap.get("message"), json.getString("message"));

        Map<String, Object> actualMap = response.as(HashMap.class);
        Assert.assertEquals(expectedMap.get("status"), actualMap.get("status"));
        Assert.assertEquals(expectedMap.get("data"), actualMap.get("data"));
        Assert.assertEquals(expectedMap.get("message"), actualMap.get("message"));
    }
}
