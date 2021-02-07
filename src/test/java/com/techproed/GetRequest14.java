package com.techproed;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testbases.TestBaseDummy;
import testdatas.DummyTestData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
	 	When
	 		I send a request to http://dummy.restapiexample.com/api/v1/employees
	 	Then
	 		Status code is 200
	 		And the highest salary is 725000
	 		And the minimum age is 19
	 		And the second highest salary is 675000
	*/
public class GetRequest14 extends TestBaseDummy {

    @Test
    public void get() {
        spec03.pathParam("employees", "employees");

        DummyTestData expectedObj = new DummyTestData();
        Map<String, Integer> expectedData = expectedObj.setUpData2();
        System.out.println(expectedData);

        Response response = RestAssured.
                given().
                spec(spec03).
                when().
                get("/{employees}");

        //response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);
        //System.out.println(actualData);

        int size = ((List) actualData.get("data")).size();
        int[] salaries = new int[size];
        for (int i = 0; i < size; i++) {
            salaries[i] = Integer.parseInt((String) (((Map) ((List) actualData.get("data")).get(i)).get("employee_salary")));
        }
        Arrays.sort(salaries);
        int highestInMap = salaries[size - 1];
        int secondHighestInMap = salaries[size - 2];

        int[] ages = new int[size];
        for (int i = 0; i < size; i++) {
            ages[i] = Integer.parseInt((String) (((Map) ((List) actualData.get("data")).get(i)).get("employee_age")));
        }
        Arrays.sort(ages);
        int minAge = ages[0];

        Assert.assertEquals((int) expectedData.get("highest_salary"), highestInMap);
        Assert.assertEquals((int) expectedData.get("second_highest_salary"), secondHighestInMap);
        Assert.assertEquals((int) expectedData.get("lowest_age"), minAge);


    }
}

/*  int hig = 0;
for (int i = 0; i < size; i++) {
            if (highestInMap < Integer.parseInt((String) (((Map) ((List) actualData.get("data")).get(i)).get("employee_salary")))) {
                highestInMap = Integer.parseInt((String) (((Map) ((List) actualData.get("data")).get(i)).get("employee_salary")));
            }
        }
        System.out.println(highestInMap);

        int secondHighestInMap = 0;
        for (int i = 0; i < size; i++) {
            if (secondHighestInMap < (Integer.parseInt((String) (((Map) ((List) actualData.get("data")).get(i)).get("employee_salary")))) && secondHighestInMap!=highestInMap) {
                secondHighestInMap = Integer.parseInt((String) (((Map) ((List) actualData.get("data")).get(i)).get("employee_salary")));
            }
        }
        System.out.println(secondHighestInMap);
 */