package orangeHRM;

import common.Browser;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CorporateBrandingTest {
    By primaryColorButton = By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters']/div/div/div/div[@class='oxd-color-input-preview']");
    By pickerRangePointer = By.xpath("//input[@class='oxd-color-picker-range']");
    By pickerIndicatorPointer = By.xpath("//div[@class='oxd-color-picker-indicator']");
    By pickerIndicatorTarget = By.xpath("//div[@class='oxd-color-picker-indicator' and @style='top: 40px; left: 205px;']");
    By submitButton = By.xpath("//div[@class='orangehrm-actions-group']/button[@type='submit']");
    @Test
    public void CorporateBrandingTest() {
        Browser.openBrowser();
        Browser.visit("https://opensource-demo.orangehrmlive.com/web/index.php/admin/addTheme");
        Browser.login("Admin","admin123");
        Browser.waitElement(primaryColorButton);
        Browser.click(primaryColorButton);
        Browser.waitElement(pickerIndicatorPointer);
        Browser.dragAndDropBy(pickerIndicatorPointer,300, 500);
        //Browser.click(submitButton);
    }
}
