package utilities;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonUtil {
    // Object Mapper kullanılarak De-Serialization yapma metodu

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }
        //Methodumuzu olusturucaz -- Json Data'sini Java Object'ine donusturucek.
        public static <T> T convertJsonToJava(String json, Class<T> cls){
        T javaResult = null;

            try {
                javaResult = mapper.readValue(json, cls);
            } catch (IOException e) {
                System.err.println("Json Data'sini Java'ya donusturemedi");
            }

            return javaResult;
    }

}
