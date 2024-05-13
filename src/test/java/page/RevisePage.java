package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class RevisePage {
    By userNameTextBox = By.xpath("//div[@class='oxd-form-row']/div/div/input[@name='username']");
    By passWordTextBox = By.xpath("//div[@class='oxd-form-row']/div/div/input[@name='password']");
    By loginButton = By.xpath("//div[@class='oxd-form-actions orangehrm-login-action']/button[@type='submit']");
    By breadCrumb = By.xpath("//span[@class='oxd-topbar-header-breadcrumb']/h6[1]");
    By firstUserName = By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border']/div[@class='oxd-table-cell oxd-padding-cell']/div");
    WebDriver driver;
    WebDriverWait wait;
    public int TIMEWAIT = 10;
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void navigateUrl(String url) {
        driver.get(url);
    }

    public void loginAccount(String userName, String passWord) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEWAIT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameTextBox));
        driver.findElement(userNameTextBox).sendKeys(userName);
        driver.findElement(passWordTextBox).sendKeys(passWord);
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(breadCrumb));
    }

    public String getBreadCrumbContext() {
        return driver.findElement(breadCrumb).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getList(int listOrder) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEWAIT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstUserName));
        List<WebElement> elements = driver.findElements(firstUserName);
        List<String> listResult = elements.stream().map(WebElement::getText).collect(Collectors.toList());
        String[] arr = listResult.toArray(new String[0]);
        return  arr[listOrder];
    }
}