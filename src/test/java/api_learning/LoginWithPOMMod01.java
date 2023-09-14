package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreenMod01;
import platform.Platform;

public class LoginWithPOMMod01 {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtn.click();

            LoginScreenMod01 loginScreen = new LoginScreenMod01(appiumDriver);
            loginScreen.usernameElem().sendKeys("robert@gmail.com");
            loginScreen.passwordElem().sendKeys("1234354456");
            loginScreen.loginBtnElem().click();
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
