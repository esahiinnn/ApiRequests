package testdatas;


import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyTestData {
/*
Status code is 200
	 		And the highest salary is 725000
	 		And the minimum age is 19
	 		And the second highest salary is 675000
 */

    public List<Map<String, Object>> expectedDataList = new ArrayList<>();


    public List<Map<String, Object>> setUpData() {
        HashMap<String, Object> expectedMap1 = new HashMap<>();
        HashMap<String, Object> expectedMap2 = new HashMap<>();
        HashMap<String, Object> expectedMap3 = new HashMap<>();
        HashMap<String, Object> expectedMap4 = new HashMap<>();
        HashMap<String, Object> expectedMap5 = new HashMap<>();
        HashMap<String, Object> expectedMap6 = new HashMap<>();
        HashMap<String, Object> expectedMap7 = new HashMap<>();

        expectedMap1.put("Status Code", 200);
        expectedDataList.add(expectedMap1);
        expectedMap2.put("Selected Employee Name", "Airi Satou");
        expectedDataList.add(expectedMap2);
        expectedMap3.put("NumOfEmployees", 24);
        expectedDataList.add(expectedMap3);
        expectedMap4.put("SelectedSalary", "106450");
        expectedDataList.add(expectedMap4);

        List<String> ageList = new ArrayList<>();
        ageList.add("40");
        ageList.add("19");
        ageList.add("23");

        expectedMap5.put("MultipleAges", ageList);
        expectedDataList.add(expectedMap5);
        System.out.println(expectedMap5);

        Map<String, String> empDetailsMap = new HashMap<>();
        empDetailsMap.put("employee_name", "Jena Gaines");
        empDetailsMap.put("employee_salary", "90560");
        empDetailsMap.put("id", "11");
        empDetailsMap.put("employee_age", "30");
        empDetailsMap.put("profile_image", "");
        expectedMap6.put("AllDetailsAboutEmployee", empDetailsMap);
        expectedDataList.add(expectedMap6);
        System.out.println(expectedDataList);

        expectedMap7.put("employee_name", "Jena Gaines");
        expectedMap7.put("employee_salary", "90560");
        expectedMap7.put("id", "11");
        expectedMap7.put("employee_age", "30");
        expectedMap7.put("profile_image", "");
        expectedDataList.add(expectedMap7);

        return expectedDataList;
    }

    public Map<String, Integer> expectedDataList2 = new HashMap<>();
    public Map<String, Integer> setUpData2(){

        expectedDataList2.put("Status code", 200);
        expectedDataList2.put("highest_salary", 725000);
        expectedDataList2.put("minimum_age", 19);
        expectedDataList2.put("2ndhighest_salary", 675000);

        return expectedDataList2;
    }

    public Map<String, String> setUpData3(){
        Map<String, String> reqBodyMap = new HashMap<String, String>();
        reqBodyMap.put("name","Ahmet Aksoy");
        reqBodyMap.put("salary","1000");
        reqBodyMap.put("age","18");
        reqBodyMap.put("profile_image","");
        return reqBodyMap;
    }

    public Map<String, String> setUpMessageData(){

        Map<String, String> massageMap = new HashMap<String, String>();
        massageMap.put("status","success");
        massageMap.put("message","Successfully! Record has been added.");

        return massageMap;

    }

    public JSONObject setUpPostReqBodyByUsingJSONObject() {

        JSONObject reqBodyJSONObject = new JSONObject();
        reqBodyJSONObject.put("name","Ahmet Aksoy");
        reqBodyJSONObject.put("salary","1000");
        reqBodyJSONObject.put("age","18");
        reqBodyJSONObject.put("profile_image","");

        return reqBodyJSONObject;

    }

    public JSONObject setUpMessageDataByUsingJSONObject() {
        JSONObject messageJSONObject = new JSONObject();
        messageJSONObject.put("status", "success");
        messageJSONObject.put("message", "Successfully! Record has been added.");

        return messageJSONObject;
    }

    public Map<String, Object> setUpExpectedDeleteDataByUsingMap(){

        Map<String, Object> expectedDataMap = new HashMap<String, Object>();
        expectedDataMap.put("statuscode", 200);
        expectedDataMap.put("status", "success");
        expectedDataMap.put("data", "2");
        expectedDataMap.put("message", "Successfully! Record has been deleted");
        return expectedDataMap;

    }
}
