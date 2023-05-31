package orangeHRM;

import common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.NavigateAdminPage;

import static common.Browser.*;

public class NavigateAdminPageTest extends TestBase {
    NavigateAdminPage navigateAdminPage;

    @Test(dataProvider = "testData")
    public void NavigateAdminPage(String url,String username, String password) {
        visit(url);
        login(username, password);
        navigateAdminPage = new NavigateAdminPage();
        navigateAdminPage.gotoAdminPage();
        Assert.assertEquals(currentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        Assert.assertTrue(navigateAdminPage.checkVisibilityOfAdminPageTitle());
        Assert.assertTrue(navigateAdminPage.checkVisibilityOfSystemUserFilter());
        Assert.assertTrue(navigateAdminPage.checkVisibilityOfSystemUserRecord());
    }
}
