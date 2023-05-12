package orangeHRM;

import common.Browser;
import org.testng.Assert;
import org.testng.annotations.*;
import page.OrangeHRMPage;

import static common.Browser.*;

public class OrangeHRMTest {
    OrangeHRMPage orangeHRMPage;

    @DataProvider
    Object[][] testData() {
        return new Object[][]{
                {"Admin", "admin123"}
        };
    }

    @BeforeClass
    void createPage() {
        orangeHRMPage = new OrangeHRMPage();
    }

    @BeforeMethod
    void openBrowser() {
        Browser.openBrowser();
        visit("https://opensource-demo.orangehrmlive.com/");
    }

    @Test
    public void navigateToOrangeHRM() {
        Assert.assertEquals(Browser.currentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfLoginLogo());
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfUsernameTextBox());
    }

    @Test(dataProvider = "testData")
    public void loginAdminAccount(String username, String password) {
        orangeHRMPage.login(username, password);
        Assert.assertEquals(currentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfWidgetCard());
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfDashBoardTitle());
    }

    @Test(dataProvider = "testData")
    public void PrintAccountName(String username, String password) {
        orangeHRMPage.login(username, password);
        System.out.println(orangeHRMPage.getAccountName());
        Assert.assertEquals(Browser.currentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        Assert.assertNotEquals(orangeHRMPage.getAccountName(), "");
    }

    @Test(dataProvider = "testData")
    public void NavigateAdminPage(String username, String password) {
        orangeHRMPage.login(username, password);
        orangeHRMPage.gotoAdminPage();
        Assert.assertEquals(currentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfAdminPageTitle());
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfSystemUserFilter());
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfSystemUserRecord());
    }

    @AfterMethod
    void closeBrowser() {
        Browser.closeBrowser();
    }
}
