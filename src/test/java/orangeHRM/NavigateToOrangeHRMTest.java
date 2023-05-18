package orangeHRM;

import common.Browser;
import common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.OrangeHRMPage;

public class NavigateToOrangeHRMTest extends TestBase {
    OrangeHRMPage orangeHRMPage;

    @Test
    public void navigateToOrangeHRM() {
        orangeHRMPage = new OrangeHRMPage();
        Assert.assertEquals(Browser.currentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfLoginLogo());
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfUsernameTextBox());
    }
}
