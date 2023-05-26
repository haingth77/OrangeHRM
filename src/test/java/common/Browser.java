package common;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.TimesheetTablePage;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Browser {
    private static By usernameTextBox  = By.xpath("//input[@name='username']");
    private static By passwordTextBox  = By.xpath("//input[@name='password']");
    private static By loginButton      = By.xpath("//div[@class='oxd-form-actions orangehrm-login-action']/button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']");
    private static By widgetCard       = By.xpath("//div[@class='orangehrm-dashboard-widget-name']/p[@class='oxd-text oxd-text--p']");
    private static By accountNamefield = By.xpath("//span[@class='oxd-userdropdown-tab']/p[@class='oxd-userdropdown-name']");

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

    public static void waitElement(By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public static Boolean checkVisibility(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public static String getTextOfWebElements(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return elements.stream().map(WebElement::getText).collect(Collectors.toList()).toString();
    }

    public static void login(String username, String password) {
        Browser.waitElement(usernameTextBox);
        Browser.fill(usernameTextBox, username);
        Browser.fill(passwordTextBox, password);
        Browser.click(loginButton);
        Browser.waitElement(accountNamefield);
    }

    public static String getDataFromCsvFile(String fileLoction) throws IOException, CsvValidationException {
        String[] csvCells;
        List<String> provinceNameCsv = new ArrayList<String>();
        CSVReader readcsv = new CSVReader(new FileReader(fileLoction));

        while ((csvCells = readcsv.readNext()) != null) {
            provinceNameCsv.add(csvCells[0]);
        }
        return provinceNameCsv.toString().replace("\uFEFF","");
    }


    public static List<WebElement> listWebElement(By locator) {
        return driver.findElements(locator);
    }




    public static void closeBrowser() {
        if (driver!=null) {
            driver.quit();
        }
    }

}
