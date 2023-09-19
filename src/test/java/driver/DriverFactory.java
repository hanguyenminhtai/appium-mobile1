package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import platform.Platform;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory implements MobileCapabilityTypeEx {

    private AppiumDriver<MobileElement> appiumDriver;

    public static AppiumDriver<MobileElement> getDriver(Platform platform) {
        AppiumDriver<MobileElement> appiumDriver = null;
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(UDID, "emulator-5554");
        desiredCapabilities.setCapability(APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
        URL appiumServer = null;
        try {
            appiumServer = new URL("http://localhost:4723/wd/hub");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (appiumServer == null)
            throw new RuntimeException("URL is null");

        switch (platform) {
            case ANDROID:
                appiumDriver = new AndroidDriver<>(appiumServer, desiredCapabilities);
                break;
            case IOS:
                appiumDriver = new IOSDriver<>(appiumServer, desiredCapabilities);
                break;
        }

        appiumDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        return appiumDriver;
    }

    public AppiumDriver<MobileElement> getDriver(Platform platform, String udid, String systemPort) {
        if (appiumDriver == null) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(UDID, udid);
            desiredCapabilities.setCapability(APP_PACKAGE, "com.wdiodemoapp");
            desiredCapabilities.setCapability(APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
            desiredCapabilities.setCapability(SYSTEM_PORT, systemPort);
            String targetServer = "http://192.168.1.218:4444/wd/hub";
            URL appiumServer = null;
            try {
                appiumServer = new URL(targetServer);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (appiumServer == null)
                throw new RuntimeException("Can't connect to selenium grid.");

            switch (platform) {
                case ANDROID:
                    appiumDriver = new AndroidDriver<>(appiumServer, desiredCapabilities);
                    break;
                case IOS:
                    appiumDriver = new IOSDriver<>(appiumServer, desiredCapabilities);
                    break;
            }

            appiumDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }

        return appiumDriver;
    }

    public void quitAppiumDriver() {
        if (appiumDriver != null) {
            appiumDriver.quit();
            appiumDriver = null;
        }
    }
}
