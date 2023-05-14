package orangeHRM;

import common.Browser;
import org.testng.Assert;
import org.testng.annotations.*;
import page.OrangeHRMPage;

import static common.Browser.currentUrl;
import static common.Browser.visit;

public class NavigateAdminPageTest {
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
