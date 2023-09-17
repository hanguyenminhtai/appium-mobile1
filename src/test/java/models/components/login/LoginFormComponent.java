package models.components.login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class LoginFormComponent {

    private final AppiumDriver<MobileElement> appiumDriver;
    private final static By usernameSel = MobileBy.AccessibilityId("input-email");
    private final static By incorrectEmailTxtSel = MobileBy.xpath("//*[contains(@text, 'Please enter a valid email address')]");
    private final static By passwordSel = MobileBy.AccessibilityId("input-password");
    private final static By getIncorrectPasswordTxtSel = MobileBy.xpath("//*[contains(@text, 'Please enter at least 8 characters')]");
    private final static By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");
    private final static By loginSuccessfullySel = MobileBy.id("android:id/alertTitle");

    public LoginFormComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void inputUsername(String usernameTxt) {
        if (!usernameTxt.isEmpty()) {
            MobileElement usernameElem = appiumDriver.findElement(usernameSel);
            usernameElem.clear();
            usernameElem.sendKeys(usernameTxt);
        }
    }

    public String getInvalidEmailStr() {
        return appiumDriver.findElement(incorrectEmailTxtSel).getText();
    }

    public void inputPassword(String passwordTxt) {
        if (!passwordTxt.isEmpty()) {
            MobileElement passwordElem = appiumDriver.findElement(passwordSel);
            passwordElem.clear();
            passwordElem.sendKeys(passwordTxt);
        }
    }

    public String getInvalidPasswordStr() {
        return appiumDriver.findElement(getIncorrectPasswordTxtSel).getText();
    }

    // TODO: 9/17/2023 change inplicit wait -> exlicit wait 
    public void clickOnLoginBtn(){
        appiumDriver.findElement(loginBtnSel).click();
        appiumDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public String getLoginSuccessfullySel() {
        return appiumDriver.findElement(loginSuccessfullySel).getText();
    }
}
