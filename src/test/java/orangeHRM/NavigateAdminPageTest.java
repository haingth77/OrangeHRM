package orangeHRM;

import common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.OrangeHRMPage;

import static common.Browser.currentUrl;

public class NavigateAdminPageTest extends TestBase {
    OrangeHRMPage orangeHRMPage;

    @Test(dataProvider = "testData")
    public void NavigateAdminPage(String url,String username, String password) {
        orangeHRMPage = new OrangeHRMPage();
        orangeHRMPage.login(username, password);
        orangeHRMPage.gotoAdminPage();
        Assert.assertEquals(currentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfAdminPageTitle());
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfSystemUserFilter());
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfSystemUserRecord());
    }
}
