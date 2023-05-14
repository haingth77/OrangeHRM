package orangeHRM;

import common.Browser;
import org.testng.Assert;
import org.testng.annotations.*;
import page.OrangeHRMPage;

import static common.Browser.currentUrl;
import static common.Browser.visit;

public class LoginAdminAccountTest {
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

    @Test(dataProvider = "testData")
    public void loginAdminAccount(String username, String password) {
        orangeHRMPage.login(username, password);
        Assert.assertEquals(currentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfWidgetCard());
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfDashBoardTitle());
    }

    @AfterMethod
    void closeBrowser() {
        Browser.closeBrowser();
    }
}
