package test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.login.LoginFormComponent;
import models.pages.LoginScreen;
import org.apache.commons.validator.routines.EmailValidator;
import test_flows.webview.BaseFlow;

public class LoginFlow extends BaseFlow {

    private String username;
    private String password;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver, String username, String password) {
        super(appiumDriver);
        this.username = username;
        this.password = password;
    }

    public void login() {
        LoginScreen loginScreen = new LoginScreen(appiumDriver);
        LoginFormComponent loginFormComp = loginScreen.loginFormComp();
        if (!username.isEmpty()) loginFormComp.inputUsername(username);

        if (!password.isEmpty()) loginFormComp.inputPassword(username);

        loginFormComp.clickOnLoginBtn();
    }

    public void verifyLogin() {
        LoginScreen loginScreen = new LoginScreen(appiumDriver);
        LoginFormComponent loginFormComp = loginScreen.loginFormComp();
        boolean isEmailValid = EmailValidator.getInstance().isValid(username);
        boolean isPasswordValid = password.length() >= 8;

        if (isEmailValid && isPasswordValid) {
            verifyCorrectLoginCreds(loginFormComp);
        }

        if (!isEmailValid) {
            verifyIncorrectEmail(loginFormComp);
        }

        if (!isPasswordValid) {
            verifyIncorrectPassword(loginFormComp);
        }
    }

    private void verifyCorrectLoginCreds(LoginFormComponent loginFormComp) {
        String actualLoginStr = loginFormComp.getLoginSuccessfullySel();
        String expectedLoginStr = "Success";
        System.out.println("Actual Login Successfully Str: " + actualLoginStr);
        System.out.println("Expected Login Successfully Str: " + expectedLoginStr);
    }

    private void verifyIncorrectEmail(LoginFormComponent loginFormComp) {
        String actualInvalidEmailStr = loginFormComp.getInvalidEmailStr();
        String expectedInvalidEmailStr = "Please enter a valid email address";
        System.out.println("Actual Invalid Email Str: " + actualInvalidEmailStr);
        System.out.println("Expected Invalid Email Str: " + expectedInvalidEmailStr);
    }

    private void verifyIncorrectPassword(LoginFormComponent loginFormComp) {
        String actualInvalidPasswordStr = loginFormComp.getInvalidPasswordStr();
        String expectedInvalidPasswordStr = "Please enter at least 8 characters";
        System.out.println("Actual Invalid Pass Str: " + actualInvalidPasswordStr);
        System.out.println("Expected Invalid Pass Str: " + expectedInvalidPasswordStr);
    }

}
