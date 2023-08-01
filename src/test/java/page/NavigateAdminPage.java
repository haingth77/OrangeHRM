package page;

import common.Browser;
import org.openqa.selenium.By;

public class NavigateAdminPage {
    By adminPageButton  = By.xpath("//li[@class='oxd-main-menu-item-wrapper']/a[@href='/web/index.php/admin/viewAdminModule']");
    By systemUserFilter = By.xpath("//div[@class='orangehrm-background-container']/div[@class='oxd-table-filter']");
    By systemUserRecord = By.xpath("//div[@class='orangehrm-background-container']/div[@class='orangehrm-paper-container']");
    By adminPageTitle   = By.xpath("//div[@class='oxd-topbar-header-title']/span[@class='oxd-topbar-header-breadcrumb']");

    public void gotoAdminPage() {
        Browser.click(adminPageButton);
    }

    public boolean checkVisibilityOfAdminPageTitle() {
        Browser.waitElement(adminPageTitle);
        return Browser.isDisplayed(adminPageTitle);
    }

    public boolean checkVisibilityOfSystemUserFilter() {
        Browser.waitElement(systemUserFilter);
        return Browser.isDisplayed(systemUserFilter);
    }

    public boolean checkVisibilityOfSystemUserRecord() {
        Browser.waitElement(systemUserRecord);
        return Browser.isDisplayed(systemUserRecord);
    }
}
