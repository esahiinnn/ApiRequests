package com.techproed;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testbases.TestBaseDummy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
	 When
		 I send GET Request to URL http://dummy.restapiexample.com/api/v1/employees
	 Then
		 Status code is 200
		 1)Print all ids greater than 10 on the console
		   Assert that there are 14 ids greater than 10
		 2)Print all ages less than 30 on the console
		   Assert that maximum age is 23
		 3)Print all employee names whose salaries are greater than 350,000
		   Assert that Charde Marshall is one of the employees whose salary is greater than 350,000
	 */
public class GetRequest10 extends TestBaseDummy {

    @Test
    public void get() {
        spec03.pathParam("employees", "employees");

        Response response = RestAssured.given().spec(spec03).when().get("/{employees}");
        //response.prettyPrint();
        response.then().assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        /* 1)Print all ids greater than 10 on the console
		   Assert that there are 14 ids greater than 10 */
        // id-- this gorevi gorur
        List<String> idList = jsonPath.getList("data.findAll{Integer.valueOf(it.id)>10}.id");
        System.out.println(idList);
        Assert.assertEquals(14, idList.size());

        /*2)Print all ages less than 30 on the console
        Assert that maximum age is 23 */
        List<String> ageList = jsonPath.getList("data.findAll{Integer.valueOf(it.employee_age)<30}.employee_age");
        List<Integer> ageListInt = new ArrayList<>();
        for (String w : ageList) {
            ageListInt.add(Integer.parseInt(w));
        }
        Collections.sort(ageListInt);
        Assert.assertEquals(23, (int) ageListInt.get(ageListInt.size() - 1));

        /* 3)Print all employee names whose salaries are greater than 350,000
		   Assert that Charde Marshall is one of the employees whose salary is greater than 350,000 */
        List<String> nameList = jsonPath.getList("data.findAll{Integer.valueOf(it.employee_salary)>350000}.employee_name");
        System.out.println(nameList);
        Assert.assertTrue(nameList.contains("Charde Marshall"));
    }
}
 /*             ALTERNATIF COZUM
        List<String> idGreaterThanTen = new ArrayList<>();
            for(int i = 0; i<jsonPath.getList("data.id").size(); i++){
                if(Integer.parseInt(jsonPath.getString("data.id[" + i + "]"))>10){
                    idGreaterThanTen.add(jsonPath.getString("data.id[" + i + "]"));
                }
            }
        System.out.println(idGreaterThanTen);
        Assert.assertEquals(14, idGreaterThanTen.size());

        List<Integer> agesLessThan30 = new ArrayList<>();
        for(int i = 0; i<jsonPath.getList("data.employee_age").size(); i++){
            if(Integer.parseInt(jsonPath.getString("data.employee_age[" + i + "]"))<30){
                agesLessThan30.add(Integer.parseInt(jsonPath.getString("data.employee_age[" + i + "]")));
            }
        }
        Collections.sort(agesLessThan30);
        System.out.println(agesLessThan30);
        */