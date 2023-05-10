package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {
    private static WebDriver driver;
    private static int TIME_OUT_IN_MILLISECONDS = 10000;

    public static void openBrowser() {
        driver = new ChromeDriver();
    }

    public static void visit(String url) {
        driver.get(url);
        try {
            Thread.sleep(TIME_OUT_IN_MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String currentUrl() {
        return driver.getCurrentUrl();
    }

    public static void loginAccount(String username, String password) {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//div[@class='oxd-form-actions orangehrm-login-action']/button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAccountName() {
        return driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']/p[@class='oxd-userdropdown-name']")).getText();
    }

    public static void gotoAdminPage() {
        driver.findElement(By.xpath("//li[@class='oxd-main-menu-item-wrapper']/a[@href='/web/index.php/admin/viewAdminModule']")).click();
    }

    public static void closeBrowser() {
        if (driver!=null) {
            driver.quit();
        }
    }

}
