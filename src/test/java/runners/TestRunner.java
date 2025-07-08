package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;
import utilities.DriverFactory;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Parameters({"deviceName", "udid", "platformVersion", "systemPort", "appiumPort"})
    @BeforeClass
    public void setUp(
            @Optional("defaultDevice") String deviceName,
            @Optional("emulator-5554") String udid,
            @Optional("11.0") String platformVersion,
            @Optional("8200") String systemPort,
            @Optional("4723") String appiumPort) {

        DriverFactory.setDriver(deviceName, udid, platformVersion, systemPort, appiumPort);
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
