package page;

import common.Browser;
import org.openqa.selenium.By;

public class PrintAccountNamePage {
    By accountNamefield = By.xpath("//span[@class='oxd-userdropdown-tab']/p[@class='oxd-userdropdown-name']");

    public String getAccountName() {
        return Browser.getText(accountNamefield);
    }

}
