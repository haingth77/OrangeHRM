package orangeHRM;

import common.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PrintAccountNameTest {
    @BeforeMethod
    void openBrowser() {
        Browser.openBrowser();
        Browser.visit("https://opensource-demo.orangehrmlive.com/");
    }

    @Test
    public void PrintAccountName() {
        Browser.loginAccount("Admin", "admin123");
        System.out.println(Browser.getAccountName());
        Assert.assertEquals(Browser.currentUrl(),"https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        Assert.assertNotEquals(Browser.getAccountName(),"");
    }

    @AfterMethod
    void closeBrowser() {
        Browser.closeBrowser();
    }
}
