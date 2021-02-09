package com.techproed;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import pojos.Data;
import pojos.EmployeePojos;
import testbases.TestBaseDummy;

import java.util.Map;
/*
		 	When
		 		I send GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
		 	Then
		 		Status code is 200
		 		And response body is like {
										    "status": "success",
										    "data": {
										        "id": 1,
										        "employee_name": "Tiger Nixon",
										        "employee_salary": 320800,
										        "employee_age": 61,
										        "profile_image": ""
										    },
										    "message": "Successfully! Record has been fetched."
										   }
										   */

public class GetRequestWithPojo extends TestBaseDummy {

    @Test
    public void get(){
        spec03.pathParams("employeePath", "employee", "idPath", 1);

        Data data = new Data(1, "Tiger Nixon", 320800, 61, "");
        EmployeePojos expectedData = new EmployeePojos("success", data, "Successfully! Record has been fetched.");

        Response response = RestAssured.
                given().
                contentType(ContentType.JSON).
                spec(spec03).
                when().
                get("/{employeePath}/{idPath}");

        //response.prettyPrint();

        //Assertion'lar
        // body ile
        response.
                then().
                assertThat().
                statusCode(200).
                body("status", Matchers.equalTo(expectedData.getStatus()),
                        "data.id", Matchers.equalTo(expectedData.getData().getId()),
                        "data.employee_name", Matchers.equalTo(expectedData.getData().getEmployee_name()),
                        "data.employee_salary", Matchers.equalTo(expectedData.getData().getEmployee_salary()),
                        "data.employee_age", Matchers.equalTo(expectedData.getData().getEmployee_age()),
                        "data.profile_image", Matchers.equalTo(expectedData.getData().getProfile_image()));

        // GSON
        EmployeePojos actualDataPojo = response.as(EmployeePojos.class);
        System.out.println(actualDataPojo);

        Assert.assertEquals(expectedData.getStatus(), actualDataPojo.getStatus());
        Assert.assertEquals(expectedData.getData().getId(), actualDataPojo.getData().getId());
        Assert.assertEquals(expectedData.getData().getEmployee_name(), actualDataPojo.getData().getEmployee_name());
        Assert.assertEquals(expectedData.getData().getEmployee_salary(), actualDataPojo.getData().getEmployee_salary());
        Assert.assertEquals(expectedData.getData().getEmployee_age(), actualDataPojo.getData().getEmployee_age());
        Assert.assertEquals(expectedData.getData().getProfile_image(), actualDataPojo.getData().getProfile_image());
        Assert.assertEquals(expectedData.getMessage(), actualDataPojo.getMessage());

        // JsonPath
        JsonPath jsonActual = response.jsonPath();
        Assert.assertEquals(expectedData.getStatus(), jsonActual.getString("status"));
        Assert.assertEquals(expectedData.getData().getId(), jsonActual.getInt("data.id"));
        Assert.assertEquals(expectedData.getData().getEmployee_name(), jsonActual.getString("data.employee_name"));
        Assert.assertEquals(expectedData.getData().getEmployee_salary(), jsonActual.getInt("data.employee_salary"));
        Assert.assertEquals(expectedData.getData().getEmployee_age(), jsonActual.getInt("data.employee_age"));
        Assert.assertEquals(expectedData.getMessage(), jsonActual.getString("message"));

        System.out.println("=======================================");
        // Serialicaztion islemi nasil yapilir?

        // 1. adım: GSON Object olsturucaz.
        Gson gson = new Gson();

        // 2. adom toJson metodunu kullanıcaz.

        String jsonFromJavaObject = gson.toJson(expectedData);
        System.out.println(jsonFromJavaObject);
    }
}
