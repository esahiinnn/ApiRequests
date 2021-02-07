package testdatas;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/*
"firstname": "Eric",
           "lastname": "Smith",
           "totalprice": 555,
           "depositpaid": false,
           "bookingdates": {
       "checkin": "2016-09-09",
               "checkout": "2017-09-21"
 */
public class HerOkuAppTestData {

    protected Map<String, Object> bookingDetails = new HashMap<>();

    public Map<String, Object> setUpData() {
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2015-08-25");
        bookingDates.put("checkout", "2016-12-13");

        bookingDetails.put("firstname", "Susan");
        bookingDetails.put("lastname", "Jackson");
        bookingDetails.put("totalprice", 582);
        bookingDetails.put("depositpaid", false);
        bookingDetails.put("bookingdates", bookingDates);

        return bookingDetails;
    }

    public JSONObject setUpData2(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname", "Selim");
        jsonObject.put("lastname", "Ak");
        jsonObject.put("totalprice", 11111);
        jsonObject.put("depositpaid", true);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("checkin", "2020-09-09");
        jsonObject1.put("checkout", "2020-09-21");

        jsonObject.put("bookingdates", jsonObject1);

        return jsonObject;

    }
}
