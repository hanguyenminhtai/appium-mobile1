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

public class SwipeHorizontal {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            MobileElement navSwipeScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Swipe"));
            navSwipeScreenBtnElem.click();

            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions.
                    visibilityOfElementLocated(MobileBy.
                            AndroidUIAutomator("new UiSelector().textContains(\"Swipe horizontal\")")));

            //get the screen dimension
            Dimension screenDimension = appiumDriver.manage().window().getSize();
            int screenHeight = screenDimension.getHeight();
            int screenWidth = screenDimension.getWidth();

            //cal the start/end point
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 10 * screenWidth / 100;

            int yStartPoint = 70 * screenHeight / 100;
            int yEndPoint = 70 * screenHeight / 100;

            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            //using touch action to swipe
            TouchAction touchAction = new TouchAction(appiumDriver);
            for (int time = 0; time < 5; time++) {
                touchAction
                        .press(startPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                        .moveTo(endPoint)
                        .release()
                        .perform();
            }

            for (int time = 0; time < 5; time++) {
                touchAction
                        .longPress(endPoint)
                        .moveTo(startPoint)
                        .release()
                        .perform();
            }

            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
