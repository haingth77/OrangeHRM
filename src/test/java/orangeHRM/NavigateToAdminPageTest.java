package orangeHRM;

import common.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavigateToAdminPageTest {
    @BeforeMethod
    void openBrowser() {
        Browser.openBrowser();
        Browser.visit("https://opensource-demo.orangehrmlive.com/");
    }

    @Test
    public void NavigateAdminPage() {
        Browser.loginAccount("Admin", "admin123");
        Browser.gotoAdminPage();
        Assert.assertEquals(Browser.currentUrl(),"https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
    }

    @AfterMethod
    void closeBrowser() {
        Browser.closeBrowser();
    }
}
