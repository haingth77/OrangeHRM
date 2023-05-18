package orangeHRM;

import common.Browser;
import common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.OrangeHRMPage;


public class PrintAccountNameTest extends TestBase {
    OrangeHRMPage orangeHRMPage;

    @Test(dataProvider = "testData")
    public void PrintAccountName(String url, String username, String password) {
        orangeHRMPage = new OrangeHRMPage();
        orangeHRMPage.login(username, password);
        System.out.println(orangeHRMPage.getAccountName());
        Assert.assertEquals(Browser.currentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        Assert.assertNotEquals(orangeHRMPage.getAccountName(), "");
    }
}
