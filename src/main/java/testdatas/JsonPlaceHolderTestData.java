package testdatas;

import org.json.JSONObject;

import java.util.HashMap;

public class JsonPlaceHolderTestData {

    public int userId = 10;
    public boolean completed = true;
    public int statusCode = 200;
    public HashMap<String, Object> expectedData = new HashMap<>();

    public HashMap<String, Object> setUpData() {
        expectedData.put("Status Code", 200);
        expectedData.put("userId", 2);
        expectedData.put("title", "porro aut necessitatibus eaque distinctio");
        expectedData.put("completed", false);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");
        return expectedData;
    }

    public JSONObject setUpData2() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("userId", 55);
        reqBody.put("title", "Tidy your room");
        reqBody.put("completed", false);

        return reqBody;
    }

    public JSONObject setUpData3() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("userId", 21);
        reqBody.put("title", "Wash the dishes");
        reqBody.put("completed", false);

        return reqBody;
    }

    public JSONObject setUpData4() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("title", "I love API");

        return reqBody;
    }
}
