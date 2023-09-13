package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.time.Duration;

public class SwipeVertically {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            MobileElement navFormScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navFormScreenBtnElem.click();

            //wait until screen is loaded
            WebDriverWait wait = new WebDriverWait(appiumDriver, 5L);
            wait.until(ExpectedConditions.
                    visibilityOfElementLocated(MobileBy.
                            AndroidUIAutomator("new UiSelector().textContains(\"Form Component\")")));
            //fill in the input field
            MobileElement inputFieldElem = appiumDriver.findElement(MobileBy.AccessibilityId("text-input"));
            inputFieldElem.sendKeys("abcde");

            //switch on/off
            MobileElement switchElem = appiumDriver.findElement(MobileBy.AccessibilityId("switch"));
            switchElem.click();

            //get the screen dimension
            Dimension screenDimension = appiumDriver.manage().window().getSize();
            int screenHeight = screenDimension.getHeight();
            int screenWidth = screenDimension.getWidth();

            //cal the start/end point
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 50 * screenWidth / 100;

            int yStartPoint = 50 * screenHeight / 100;
            int yEndPoint = 10 * screenHeight / 100;

            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            //using touch action to swipe
            TouchAction touchAction = new TouchAction(appiumDriver);

            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            //dropdown field
            MobileElement dropdownElem = appiumDriver.findElement(MobileBy.AccessibilityId("Dropdown"));
            dropdownElem.click();

            MobileElement dropdownSel = appiumDriver
                    .findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"This app is awesome\")"));
            dropdownSel.click();

            //click btn activate
            MobileElement btnActive = appiumDriver.findElement(MobileBy.AccessibilityId("button-Active"));
            btnActive.click();

            //get the text on the popup
            MobileElement popupMess = appiumDriver.findElement(MobileBy.id("android:id/message"));
            System.out.println(popupMess.getText());

            MobileElement cancelBtn = appiumDriver.findElement(MobileBy.id("android:id/button2"));
            cancelBtn.click();

            //swipe down
            touchAction
                    .longPress(endPoint)
                    .moveTo(startPoint)
                    .release()
                    .perform();

            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
