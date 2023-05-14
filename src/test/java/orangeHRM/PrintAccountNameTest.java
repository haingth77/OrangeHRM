package orangeHRM;

import common.Browser;
import org.testng.Assert;
import org.testng.annotations.*;
import page.OrangeHRMPage;

import static common.Browser.currentUrl;
import static common.Browser.visit;

public class PrintAccountNameTest {
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
    public void PrintAccountName(String username, String password) {
        orangeHRMPage.login(username, password);
        System.out.println(orangeHRMPage.getAccountName());
        Assert.assertEquals(Browser.currentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        Assert.assertNotEquals(orangeHRMPage.getAccountName(), "");
    }

    @AfterMethod
    void closeBrowser() {
        Browser.closeBrowser();
    }
}
