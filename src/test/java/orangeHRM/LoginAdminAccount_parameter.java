package orangeHRM;

import common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page.LoginAdminAccountPage;

import static common.Browser.*;

public class LoginAdminAccount_parameter extends TestBase {
    LoginAdminAccountPage loginAdminAccountPage;

    @Test
    @Parameters({"username","password"})
    public void loginAdminAccount(String username, String password) {
        visit("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        login(username, password);
        loginAdminAccountPage = new LoginAdminAccountPage();
        Assert.assertEquals(currentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        Assert.assertTrue(loginAdminAccountPage.checkVisibilityOfWidgetCard());
        Assert.assertTrue(loginAdminAccountPage.checkVisibilityOfDashBoardTitle());
    }
}
