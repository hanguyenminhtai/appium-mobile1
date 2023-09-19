package driver;

import io.appium.java_client.remote.MobileCapabilityType;

public interface MobileCapabilityTypeEx extends MobileCapabilityType {

    String PLATFORM_NAME = "appium:platformName";
    String APP_PACKAGE = "appium:appPackage";
    String APP_ACTIVITY = "appium:appActivity";
    String SYSTEM_PORT = "appium:systemPort";
    String AUTOMATION_NAME = "appium:automationName";
    String UDID = "appium:udid";
}
