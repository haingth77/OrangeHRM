package page;

import common.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class CorporateBrandingPage {
    public static void selectColorByDragAndDrop(By locator, int targetLeftPosition, int targetTopPosition) {
        WebElement element = Browser.element(locator);
        Point currentLocation = element.getLocation();
        int topDifference = targetTopPosition - currentLocation.getY();
        int leftDifference = targetLeftPosition - currentLocation.getX();
        Browser.dragAndDropBy(locator, leftDifference, topDifference);
    }

//    public static void selectColorByKeyEvent(By locator, String colorCode) {
//        for (int count =0; count < colorCode.length(); count ++) {
//            Browser.fill(locator, Keys+colorCode.charAt(count));
//        }
//    }
}














