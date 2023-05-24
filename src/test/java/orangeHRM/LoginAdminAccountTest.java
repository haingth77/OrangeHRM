package orangeHRM;

import common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginAdminAccountPage;

import static common.Browser.currentUrl;
import static common.Browser.login;

public class LoginAdminAccountTest extends TestBase {
    LoginAdminAccountPage loginAdminAccountPage;

    @Test(dataProvider = "testData")
    public void loginAdminAccount(String url, String username, String password) {
        loginAdminAccountPage = new LoginAdminAccountPage();
        login(username, password);
        Assert.assertEquals(currentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        Assert.assertTrue(loginAdminAccountPage.checkVisibilityOfWidgetCard());
        Assert.assertTrue(loginAdminAccountPage.checkVisibilityOfDashBoardTitle());
    }
}
