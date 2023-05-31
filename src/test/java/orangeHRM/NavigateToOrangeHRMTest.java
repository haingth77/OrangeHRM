package orangeHRM;

import common.Browser;
import common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.NavigateOrangeHRMPage;

import static common.Browser.*;

public class NavigateToOrangeHRMTest extends TestBase {
    NavigateOrangeHRMPage navigateOrangeHRMPage;

    @Test
    public void navigateToOrangeHRM() {
        visit("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        navigateOrangeHRMPage = new NavigateOrangeHRMPage();
        Assert.assertEquals(Browser.currentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertTrue(navigateOrangeHRMPage.checkVisibilityOfLoginLogo());
        Assert.assertTrue(navigateOrangeHRMPage.checkVisibilityOfUsernameTextBox());
    }
}
