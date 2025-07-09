package utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class LocatorReader {

    private static class LocatorData {
        String type;
        String value;

        LocatorData(String type, String value) {
            this.type = type;
            this.value = value;
        }
    }

    private final Map<String, LocatorData> androidLocators = new HashMap<>();

    public LocatorReader(String fileName) {
        try {
            String filePath = "src/test/resources/locators/" + fileName;
            JSONParser parser = new JSONParser();
            JSONArray locatorArray = (JSONArray) parser.parse(new FileReader(filePath));

            for (Object obj : locatorArray) {
                JSONObject locator = (JSONObject) obj;
                String key = (String) locator.get("key");
                String type = (String) locator.get("androidType");
                String value = (String) locator.get("androidValue");
                androidLocators.put(key, new LocatorData(type, value));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAndroidLocator(String key) {
        LocatorData data = androidLocators.get(key);
        return data != null ? data.value : null;
    }

    public By getAndroidBy(String key) {
        LocatorData data = androidLocators.get(key);
        if (data == null) {
            throw new IllegalArgumentException("No locator found for key: " + key);
        }

        switch (data.type.toLowerCase()) {
            case "id":
                return By.id(data.value);
            case "xpath":
                return By.xpath(data.value);
            case "classname":
                return By.className(data.value);
            default:
                throw new IllegalArgumentException("Unsupported locator type: " + data.type);
        }
    }
}