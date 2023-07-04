package page;

import common.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class CorporateBrandingPage {
    public void selectColorByDragAndDrop(By locator, int targetLeftPosition, int targetTopPosition) {
        WebElement element = Browser.element(locator);
        Point currentLocation = element.getLocation();
        int topDifference = targetTopPosition - currentLocation.getY();
        int leftDifference = targetLeftPosition - currentLocation.getX();
        Browser.dragAndDropBy(locator, leftDifference, topDifference);
    }

    public String extractFileName (String filePath) {
        String result;
        int[] count = new int[filePath.length()];
        int c=0;
        char[] arrayStrg= filePath.toCharArray();
        for (int i = 0; i<arrayStrg.length; i++) {
            if (arrayStrg[i] == '\\') {
                count[c] = i;
                c++;
            }
        }
        return result = filePath.substring(count[c-1]+1);
    }
}














