package utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class LocatorReader {

    private final Map<String, String> androidLocators = new HashMap<>();

    public LocatorReader(String fileName) {
        try {
            String filePath = "src/test/resources/locators/" + fileName;
            JSONParser parser = new JSONParser();
            JSONArray locatorArray = (JSONArray) parser.parse(new FileReader(filePath));

            for (Object obj : locatorArray) {
                JSONObject locator = (JSONObject) obj;
                String key = (String) locator.get("key");
                String androidValue = (String) locator.get("androidValue");
                androidLocators.put(key, androidValue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAndroidLocator(String key) {
        return androidLocators.get(key);
    }
}