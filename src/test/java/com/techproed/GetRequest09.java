package com.techproed;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.Assert;
import testbases.TestBaseDummy;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class GetRequest09 extends TestBaseDummy {
/*
		 				Warm Up (10 Minutes)
		 1)Create a class and name it as GetRequest09
		 2)When
		 	 I send a GET Request to http://dummy.restapiexample.com/api/v1/employees
		 Then
			 status code is 200
			 And the name of the 5th employee is "Airi Satou"
			 And the salary of the 6th employee is "372000"
			 And there are "24" employees
			 And "Rhona Davidson" is one of the employees
			 And "21", "23", "61" are among the employee ages
		 3)Do the assertions by using Hard Assertion
	*/

    @Test
    public void get() {
        spec03.pathParam("employees", "employees");

        Response response = RestAssured.
                given().
                spec(spec03).
                when().
                get("/{employees}");

        //response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                body("data.employee_name[4]", equalTo("Airi Satou"),
                        "data.employee_salary[5]", equalTo("372000"),
                        "data.id", hasSize(24),
                        "data.employee_name", hasItem("Rhona Davidson"),
                        "data.employee_age", hasItems("21","23", "61"));

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals("Airi Satou", jsonPath.getString("data.employee_name[4]"));
        Assert.assertEquals("372000", jsonPath.getString("data.employee_salary[5]"));
        Assert.assertEquals(24, jsonPath.getList("data.id").size());

        System.out.println(jsonPath.getList("data.id"));
        System.out.println(jsonPath.getList("data.employee_name"));
        Assert.assertTrue(jsonPath.getList("data.employee_name").contains("Rhona Davidson"));
        List<String> ages = new ArrayList<>();
        ages.add("21");
        ages.add("23");
        ages.add("61");

        Assert.assertTrue(jsonPath.getList("data.employee_age").containsAll(ages));
    }
}
