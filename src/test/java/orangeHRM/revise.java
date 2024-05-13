package orangeHRM;

import common.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.RevisePage;

import java.awt.*;
import java.time.Duration;

public class revise {
    By userName = By.xpath("//div[@class='oxd-form-row']/div/div/input[@name='username']");
    By passWord = By.xpath("//div[@class='oxd-form-row']/div/div/input[@name='password']");
    By loginButton = By.xpath("//div[@class='oxd-form-actions orangehrm-login-action']/button[@type='submit']");
    By breadCrumb = By.xpath("//span[@class='oxd-topbar-header-breadcrumb']/h6[1]");

    By firstUserName = By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border']/div[@class='oxd-table-cell oxd-padding-cell']");
    public int TIMEWAIT = 10;
    RevisePage revisePage;
    @Test
    public void NavigateAdminPage() {
        revisePage = new RevisePage();
        revisePage.openBrowser();
//        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
        revisePage.navigateUrl("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
//        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        revisePage.loginAccount("Admin", "admin123");
//        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEWAIT));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
//        driver.findElement(userName).sendKeys("Admin");
//        driver.findElement(passWord).sendKeys("admin123");
//        driver.findElement(loginButton).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(breadCrumb));
        String currentUrl = revisePage.getCurrentUrl();
        String breadCrumbContext = revisePage.getBreadCrumbContext();
        String resultUsername = revisePage.getList(1);
        Assert.assertEquals(currentUrl,"https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        Assert.assertEquals(breadCrumbContext,"Admin");
        Assert.assertEquals(resultUsername,"Admin");
    }

}
