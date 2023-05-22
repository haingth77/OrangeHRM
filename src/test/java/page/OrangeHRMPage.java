package page;

import common.Browser;
import org.openqa.selenium.By;

public class OrangeHRMPage {
    By usernameTextBox  = By.xpath("//input[@name='username']");
    By passwordTextBox  = By.xpath("//input[@name='password']");
    By loginButton      = By.xpath("//div[@class='oxd-form-actions orangehrm-login-action']/button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']");
    By accountNamefield = By.xpath("//span[@class='oxd-userdropdown-tab']/p[@class='oxd-userdropdown-name']");
    By widgetCard       = By.xpath("//div[@class='orangehrm-dashboard-widget-name']/p[@class='oxd-text oxd-text--p']");
    By dashBoardTitle   = By.xpath("//span[@class='oxd-topbar-header-breadcrumb']/h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']");
    By loginLogo        = By.xpath("//div[@class='orangehrm-login-layout-blob']/div[@class='orangehrm-login-logo']");
    By adminPageTitle   = By.xpath("//div[@class='oxd-topbar-header-title']/span[@class='oxd-topbar-header-breadcrumb']");
    By adminPageButton  = By.xpath("//li[@class='oxd-main-menu-item-wrapper']/a[@href='/web/index.php/admin/viewAdminModule']");
    By systemUserFilter = By.xpath("//div[@class='orangehrm-background-container']/div[@class='oxd-table-filter']");
    By systemUserRecord = By.xpath("//div[@class='orangehrm-background-container']/div[@class='orangehrm-paper-container']");


    public void login(String username, String password) {
        Browser.waitElement(usernameTextBox);
        Browser.fill(usernameTextBox, username);
        Browser.fill(passwordTextBox, password);
        Browser.click(loginButton);
        Browser.waitElement(widgetCard);
    }

    public String getAccountName() {
        return Browser.getText(accountNamefield);
    }

    public void gotoAdminPage() {
        Browser.click(adminPageButton);
    }

    public boolean checkVisibilityOfDashBoardTitle() {
        Browser.waitElement(dashBoardTitle);
        return Browser.checkVisibility(dashBoardTitle);
    }

    public boolean checkVisibilityOfLoginLogo() {
        Browser.waitElement(loginLogo);
        return Browser.checkVisibility(loginLogo);
    }

    public boolean checkVisibilityOfUsernameTextBox() {
        Browser.waitElement(usernameTextBox);
        return Browser.checkVisibility(usernameTextBox);
    }

    public boolean checkVisibilityOfWidgetCard() {
        Browser.waitElement(widgetCard);
        return Browser.checkVisibility(widgetCard);
    }

    public boolean checkVisibilityOfAdminPageTitle() {
        Browser.waitElement(adminPageTitle);
        return Browser.checkVisibility(adminPageTitle);
    }

    public boolean checkVisibilityOfSystemUserFilter() {
        Browser.waitElement(systemUserFilter);
        return Browser.checkVisibility(systemUserFilter);
    }

    public boolean checkVisibilityOfSystemUserRecord() {
        Browser.waitElement(systemUserRecord);
        return Browser.checkVisibility(systemUserRecord);
    }


}
