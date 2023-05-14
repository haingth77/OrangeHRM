package orangeHRM;

import common.Browser;
import org.testng.Assert;
import org.testng.annotations.*;
import page.OrangeHRMPage;

import static common.Browser.visit;

public class NavigateToOrangeHRMTest {
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

    @AfterMethod
    void closeBrowser() {
        Browser.closeBrowser();
    }
}
