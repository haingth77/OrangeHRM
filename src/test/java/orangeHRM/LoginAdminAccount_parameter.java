package orangeHRM;

import common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page.OrangeHRMPage;

import static common.Browser.currentUrl;

public class LoginAdminAccount_parameter extends TestBase {
    OrangeHRMPage orangeHRMPage;

    @Test
    @Parameters({"username","password"})
    public void loginAdminAccount(String username, String password) {
        orangeHRMPage = new OrangeHRMPage();
        orangeHRMPage.login(username, password);
        Assert.assertEquals(currentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfWidgetCard());
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfDashBoardTitle());
    }
}
