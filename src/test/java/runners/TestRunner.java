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

    private static ThreadLocal<String> threadDeviceName = new ThreadLocal<>();
    private static ThreadLocal<String> threadUdid = new ThreadLocal<>();
    private static ThreadLocal<String> threadPlatformVersion = new ThreadLocal<>();
    private static ThreadLocal<String> threadSystemPort = new ThreadLocal<>();
    private static ThreadLocal<String> threadAppiumPort = new ThreadLocal<>();

    @Parameters({"deviceName", "udid", "platformVersion", "systemPort", "appiumPort"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("defaultDevice") String deviceName,
                      @Optional("emulator-5554") String udid,
                      @Optional("11.0") String platformVersion,
                      @Optional("8200") String systemPort,
                      @Optional("4723") String appiumPort) {
        threadDeviceName.set(deviceName);
        threadUdid.set(udid);
        threadPlatformVersion.set(platformVersion);
        threadSystemPort.set(systemPort);
        threadAppiumPort.set(appiumPort);

        System.out.println(">>>> Parallel Test Setup: " +
                "DeviceName=" + deviceName +
                ", UDID=" + udid +
                ", PlatformVersion=" + platformVersion +
                ", SystemPort=" + systemPort +
                ", AppiumPort=" + appiumPort);

        DriverFactory.setDriver(deviceName, udid, platformVersion, systemPort, appiumPort);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        System.out.println(">>>> Tearing down device: " + threadUdid.get());
        DriverFactory.quitDriver();
    }
}
