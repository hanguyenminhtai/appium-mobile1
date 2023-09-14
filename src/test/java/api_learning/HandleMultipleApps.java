package api_learning;

import driver.AppPackages;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import platform.Platform;

import java.time.Duration;

public class HandleMultipleApps {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            MobileElement navLoginScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtnElem.click();

            MobileElement emailInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-email"));
            MobileElement passwordInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-password"));
            MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));

            emailInputElem.sendKeys("robert@gmail.com");
            passwordInputElem.sendKeys("123456789");
            loginBtnElem.click();

            //Put the app under test to background | simulate pressing home button > relaunch
            //appiumDriver.runAppInBackground(Duration.ofSeconds(2));

            //Put the app under test to background till we call it back
            appiumDriver.runAppInBackground(Duration.ofSeconds(-1));

            //Switch into another app | go to Settings toggle wifi
            appiumDriver.activateApp(AppPackages.SETTINGS);

            By netLabelSel = MobileBy.xpath("//*[@text='Network & internet']");
            appiumDriver.findElement(netLabelSel).click();

            By wifiLabelSel = MobileBy.xpath("//*[@text='Wi-fi']");
            appiumDriver.findElement(wifiLabelSel).click();

            //Toggle on/off
            By wifiStatusSel = MobileBy.id("com.android.settings:id/switchWidget");
            MobileElement wifiStatusElem = appiumDriver.findElement(wifiStatusSel);
            String wifiStatus = wifiStatusElem.getText().trim();
            if (wifiStatus.equalsIgnoreCase("on"))
                wifiStatusElem.click();

            //Come back to the app > interact with other elems
            appiumDriver.activateApp(AppPackages.WEBDRIVER_IO);
            appiumDriver.findElement(MobileBy.xpath("//*[@text='OK']")).click();

            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
