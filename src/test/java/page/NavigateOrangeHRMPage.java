package page;

import common.Browser;
import org.openqa.selenium.By;

public class NavigateOrangeHRMPage {
    By loginLogo        = By.xpath("//div[@class='orangehrm-login-layout-blob']/div[@class='orangehrm-login-logo']");
    By usernameTextBox  = By.xpath("//input[@name='username']");

    public boolean checkVisibilityOfLoginLogo() {
        Browser.waitElement(loginLogo);
        return Browser.checkVisibility(loginLogo);
    }

    public boolean checkVisibilityOfUsernameTextBox() {
        Browser.waitElement(usernameTextBox);
        return Browser.checkVisibility(usernameTextBox);
    }
}
