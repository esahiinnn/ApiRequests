package com.techproed;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.Assert;
import testbases.TestBaseDummy;
import testdatas.DummyTestData;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
  When
  I send get request to yje URL http://dummy.restapiexample.com/api/v1/employees
  Then
  Status Code 200
  5. calisanin ismi "Airi Satou"
  Calisan sayisi 24
  Sondan ikinci calisanin maasi "106450"
  40,21 ve 19 yaslarinda calisanlar olup olmadıgı
  11. calisanin bilgileri { "id": "11",
							            "employee_name": "Jena Gaines",
							            "employee_salary": "90560",
							            "employee_age": "30",
							            "profile_image": "" }
							            seklinde mi
							            Assert edelim.

 */
public class GetRequest12 extends TestBaseDummy {

    @Test
    public void get() {
        // Url olustur
        spec03.pathParam("employees", "employees");

        Response response = RestAssured.
                given().
                spec(spec03).
                when().
                get("/{employees}");

        //response.prettyPrint();

        // Expected datayi olustur
        DummyTestData expectedObj = new DummyTestData();
        List<Map<String, Object>> expectedDataList = expectedObj.setUpData();
        //System.out.println(expectedDataList);

        // 1.yol body ile
        response.
                then().
                assertThat().
                statusCode((Integer) expectedDataList.get(0).get("Status Code")).
                body("data[4].employee_name", Matchers.equalTo(expectedDataList.get(1).get("Selected Employee Name")),
                        "data.id", Matchers.hasSize((Integer) expectedDataList.get(2).get("NumOfEmployees")),
                        "data[-2].employee_salary", Matchers.equalTo(expectedDataList.get(3).get("SelectedSalary")),
                        "data.employee_age", Matchers.hasItems(((List) expectedDataList.get(4).get("MultipleAges")).get(0),
                                ((List) expectedDataList.get(4).get("MultipleAges")).get(1),
                                ((List) expectedDataList.get(4).get("MultipleAges")).get(2)),
                        "data[10].employee_name", Matchers.equalTo((expectedDataList.get(6).get("employee_name"))),
                        "data[10].employee_salary", Matchers.equalTo((expectedDataList.get(6).get("employee_salary"))),
                        "data[10].employee_age", Matchers.equalTo(((Map) expectedDataList.get(5).get("AllDetailsAboutEmployee")).get("employee_age")),
                        "data[10].profile_image", Matchers.equalTo(((Map) expectedDataList.get(5).get("AllDetailsAboutEmployee")).get("profile_image")));


        // 2.Yol DE-Serialization
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);
        Assert.assertEquals(expectedDataList.get(0).get("Status Code"), response.getStatusCode());
        Assert.assertEquals(expectedDataList.get(1).get("Selected Employee Name"), ((Map)(((List)actualDataMap.get("data")).get(4))).get("employee_name"));
        Assert.assertEquals(expectedDataList.get(2).get("NumOfEmployees"), ((List)actualDataMap.get("data")).size());
        int sondanIkinciIndex = ((List)actualDataMap.get("data")).size()-2;
        Assert.assertEquals(expectedDataList.get(3).get("SelectedSalary"), ((Map)((List)actualDataMap.get("data")).get(sondanIkinciIndex)).get("employee_salary"));

        // Butun yaslari iceren bir list olusturucam ve test case'deki yaslar var mi yok mu containsAll methoduyla kontrol edicem
        List<String> ageList = new ArrayList<>();
        for(int i = 0; i<sondanIkinciIndex+2; i++){
            ageList.add((String)((Map)((List) actualDataMap.get("data")).get(i)).get("employee_age"));
        }
        Assert.assertTrue(ageList.containsAll((List)expectedDataList.get(4).get("MultipleAges")));
        Assert.assertEquals(expectedDataList.get(5).get("AllDetailsAboutEmployee"), ((List) actualDataMap.get("data")).get(10));
        Assert.assertEquals(((Map) expectedDataList.get(5).get("AllDetailsAboutEmployee")).get("employee_age"), ((Map)((List) actualDataMap.get("data")).get(10)).get("employee_age"));
        Assert.assertEquals(((Map)expectedDataList.get(5).get("AllDetailsAboutEmployee")).get("employee_salary"),(((Map)((List) actualDataMap.get("data")).get(10)).get("employee_salary")));
        Assert.assertEquals(((Map)expectedDataList.get(5).get("AllDetailsAboutEmployee")).get("profile_image"),(((Map)((List) actualDataMap.get("data")).get(10)).get("profile_image")));
    }
}
