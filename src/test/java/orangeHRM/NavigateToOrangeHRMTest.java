package orangeHRM;

import common.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavigateToOrangeHRMTest {
    @BeforeMethod
    void openBrowser() {
        Browser.openBrowser();
        Browser.visit("https://opensource-demo.orangehrmlive.com/");
    }
    @Test
    public void navigateToOrangeHRM() {
        Assert.assertEquals(Browser.currentUrl(),"https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterMethod
    void closerBrowser() {
        Browser.closeBrowser();
    }
}
