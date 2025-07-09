package utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DeviceManager {

    public static class DeviceInfo {
        public String deviceName;
        public String udid;
        public String platformVersion;
        public String systemPort;
        public String appiumPort;

        public DeviceInfo(String deviceName, String udid, String platformVersion, String systemPort, String appiumPort) {
            this.deviceName = deviceName;
            this.udid = udid;
            this.platformVersion = platformVersion;
            this.systemPort = systemPort;
            this.appiumPort = appiumPort;
        }
    }

    public static List<DeviceInfo> getDevicesFromJson() {
        List<DeviceInfo> devices = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("src/test/resources/config/config.json"));

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                String deviceName = (String) jsonObject.get("deviceName");
                String udid = (String) jsonObject.get("udid");
                String platformVersion = (String) jsonObject.get("platformVersion");
                String systemPort = (String) jsonObject.get("systemPort");
                String appiumPort = (String) jsonObject.get("appiumPort");

                devices.add(new DeviceInfo(deviceName, udid, platformVersion, systemPort, appiumPort));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return devices;
    }
}