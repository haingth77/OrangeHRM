package page;

import common.Browser;
import org.openqa.selenium.By;

public class LoginAdminAccountPage {
    By widgetCard       = By.xpath("//div[@class='orangehrm-dashboard-widget-name']/p[@class='oxd-text oxd-text--p']");
    By dashBoardTitle   = By.xpath("//span[@class='oxd-topbar-header-breadcrumb']/h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']");



    public boolean checkVisibilityOfDashBoardTitle() {
        Browser.waitElement(dashBoardTitle);
        return Browser.checkVisibility(dashBoardTitle);
    }



    public boolean checkVisibilityOfWidgetCard() {
        Browser.waitElement(widgetCard);
        return Browser.checkVisibility(widgetCard);
    }

}
