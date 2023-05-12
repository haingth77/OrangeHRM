package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Browser {
    private static WebDriver driver;
    private static final int TIME_OUT_IN_SECONDS = 10;
    private static WebDriverWait wait ;

    public static void openBrowser() {
        driver = new ChromeDriver();
    }

    public static void visit(String url) {
        driver.get(url);
    }

    public static String currentUrl() {
        return driver.getCurrentUrl();
    }

    public static void fill(By locator, String withText) {
        driver.findElement(locator).sendKeys(withText);
    }

    public static void click(By locator) {
        driver.findElement(locator).click();
    }

    public static void wait(By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public static Boolean checkVisibility(By locator) {
        return driver.findElement(locator).isDisplayed();
                //driver.findElement(By.xpath("//li[@class='oxd-main-menu-item-wrapper']/a[@href='/web/index.php/admin/viewAdminModule']")).isDisplayed();
    }

    public static void closeBrowser() {
        if (driver!=null) {
            driver.quit();
        }
    }

}
