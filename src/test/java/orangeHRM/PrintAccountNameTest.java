package orangeHRM;

import common.Browser;
import common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.PrintAccountNamePage;

import static common.Browser.login;


public class PrintAccountNameTest extends TestBase {
    PrintAccountNamePage printAccountNamePage;

    @Test(dataProvider = "testData")
    public void PrintAccountName(String url, String username, String password) {
        printAccountNamePage = new PrintAccountNamePage();
        login(username, password);
        System.out.println(printAccountNamePage.getAccountName());
        Assert.assertEquals(Browser.currentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        Assert.assertNotEquals(printAccountNamePage.getAccountName(), "");
    }
}
