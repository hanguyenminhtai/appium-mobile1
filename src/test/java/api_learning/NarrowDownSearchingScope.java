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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NarrowDownSearchingScope {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            MobileElement navFormScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navFormScreenBtnElem.click();

            //get the screen dimension
            Dimension screenDimension = appiumDriver.manage().window().getSize();
            int screenHeight = screenDimension.getHeight();
            int screenWidth = screenDimension.getWidth();

            //cal the start/end point
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 50 * screenWidth / 100;

            int yStartPoint = 0;
            int yEndPoint = 50 * screenHeight / 100;

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

            List<MobileElement> notificationElems =
                    appiumDriver.findElements(MobileBy.id("android:id/notification_main_column"));
            Map<String, String> notificationContents = new HashMap<>();
            for (MobileElement notificationElem : notificationElems) {
                MobileElement titleElem = notificationElem.findElement(MobileBy.id("android:id/title"));
                MobileElement contentElem = notificationElem.findElement(MobileBy.id("android:id/big_text"));
                notificationContents.put(titleElem.getText().trim(), contentElem.getText().trim());
            }

            //verification
            if(notificationContents.keySet().isEmpty())
                throw new RuntimeException("No notification");

            for (String s : notificationContents.keySet()) {
                System.out.println(s);
                System.out.println(notificationContents.get(s));
            }

            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
