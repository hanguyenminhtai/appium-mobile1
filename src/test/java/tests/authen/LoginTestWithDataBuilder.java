package tests.authen;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import platform.Platform;
import test_data.DataObjBuilder;
import test_data.models.LoginCred;
import test_flows.authentication.LoginFlow;

public class LoginTestWithDataBuilder {

    @Test(dataProvider = "loginCredData")
    public void testLogin(LoginCred loginCred) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCred.getEmail(), loginCred.getPassword());
            loginFlow.gotoLoginScreen();
            loginFlow.login();
            loginFlow.verifyLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }

    @DataProvider
    public LoginCred[] loginCredData() {
        String filePath = "/src/test/java/test_data/authen/LoginCreds.json";
        return DataObjBuilder.buildDataObj(filePath, LoginCred[].class);
    }
}
