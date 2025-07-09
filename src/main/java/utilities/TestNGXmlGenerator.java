package utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestNGXmlGenerator {

    public static void main(String[] args) {
        try {
            // JSON config dosyasını oku
            String jsonPath = "src/test/resources/config/config.json";
            String content = new String(Files.readAllBytes(Paths.get(jsonPath)));

            // JSON parse et
            JSONParser parser = new JSONParser();
            JSONArray devices = (JSONArray) parser.parse(content);

            // testng.xml dosyasını oluştur
            PrintWriter writer = new PrintWriter(new FileWriter("testng.xml"));

            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println("<suite name=\"ParallelAppiumTestSuite\" parallel=\"tests\" thread-count=\"" + devices.size() + "\">");

            for (Object obj : devices) {
                JSONObject device = (JSONObject) obj;

                String name = (String) device.get("deviceName");
                String udid = (String) device.get("udid");
                String version = (String) device.get("platformVersion");
                String systemPort = String.valueOf(device.get("systemPort"));
                String appiumPort = String.valueOf(device.get("appiumPort"));

                writer.println("    <test name=\"" + name + "\">");
                writer.println("        <parameter name=\"deviceName\" value=\"" + name + "\"/>");
                writer.println("        <parameter name=\"udid\" value=\"" + udid + "\"/>");
                writer.println("        <parameter name=\"platformVersion\" value=\"" + version + "\"/>");
                writer.println("        <parameter name=\"systemPort\" value=\"" + systemPort + "\"/>");
                writer.println("        <parameter name=\"appiumPort\" value=\"" + appiumPort + "\"/>");
                writer.println("        <classes>");
                writer.println("            <class name=\"runners.TestRunner\"/>");
                writer.println("        </classes>");
                writer.println("    </test>");
            }

            writer.println("</suite>");
            writer.close();

            System.out.println("✅ testng.xml başarıyla oluşturuldu.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}