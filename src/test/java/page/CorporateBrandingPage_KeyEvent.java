package page;

import common.Browser;
import org.openqa.selenium.By;

public class CorporateBrandingPage_KeyEvent {
    public void inputColorCode (By locator, By inputTarget, String colorcode) throws InterruptedException {
        Browser.waitElement(locator);
        Browser.click(locator);
        Browser.keyboardPaste(inputTarget,colorcode);
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
