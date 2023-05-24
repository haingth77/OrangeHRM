package common;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.TimesheetTable;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Browser {
    private static By usernameTextBox  = By.xpath("//input[@name='username']");
    private static By passwordTextBox  = By.xpath("//input[@name='password']");
    private static By loginButton      = By.xpath("//div[@class='oxd-form-actions orangehrm-login-action']/button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']");
    private static By widgetCard       = By.xpath("//div[@class='orangehrm-dashboard-widget-name']/p[@class='oxd-text oxd-text--p']");


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
        Browser.waitElement(widgetCard);
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
    static List<TimesheetTable> timesheetTables;
    static List<TimesheetTable> timesheetTotal;
    static List<TimesheetTable> timesheetTotalBefore;
    static List<TimesheetTable> timesheetTotalAfter;

    public static void getTimesheetTable() {
        Browser.waitElement(By.xpath("//table[@class='orangehrm-timesheet-table']/tbody[@class='orangehrm-timesheet-table-body']/tr[@class='orangehrm-timesheet-table-body-row']"));
        List<WebElement> rowTables = driver.findElements(By.xpath("//table[@class='orangehrm-timesheet-table']/tbody[@class='orangehrm-timesheet-table-body']/tr[@class='orangehrm-timesheet-table-body-row']"));
        timesheetTables = rowTables.stream().map(rowTable -> {
            List<WebElement> cells = rowTable.findElements(By.tagName("td"));
            String project = cells.get(0).getText();
            String activity = cells.get(1).getText();
            String monday = cells.get(2).getText();
            String tuesday = cells.get(3).getText();
            String wednesday = cells.get(4).getText();
            String thursday = cells.get(5).getText();
            String friday = cells.get(6).getText();
            String satuday = cells.get(7).getText();
            String sunday = cells.get(8).getText();
            String total = cells.get(9).getText();
            return new TimesheetTable(project, activity, monday, tuesday, wednesday, thursday, friday, satuday, sunday, total);
        }).collect(Collectors.toList());
        timesheetTables.forEach(TimesheetTable::infor);
        System.out.println(timesheetTables.size());
    }

    public static void getTimesheetTotalBefore() {
        Browser.waitElement(By.xpath("//table[@class='orangehrm-timesheet-table']/tbody[@class='orangehrm-timesheet-table-body']/tr[@class='orangehrm-timesheet-table-body-row --total']"));
        List<WebElement> rowTables = driver.findElements(By.xpath("//table[@class='orangehrm-timesheet-table']/tbody[@class='orangehrm-timesheet-table-body']/tr[@class='orangehrm-timesheet-table-body-row --total']"));
        timesheetTotalBefore = rowTables.stream().map(rowTable -> {
            List<WebElement> cells = rowTable.findElements(By.tagName("td"));
            String project = cells.get(0).getText();
            String activity = cells.get(1).getText();
            String monday = cells.get(2).getText();
            String tuesday = cells.get(3).getText();
            String wednesday = cells.get(4).getText();
            String thursday = cells.get(5).getText();
            String friday = cells.get(6).getText();
            String satuday = cells.get(7).getText();
            String sunday = cells.get(8).getText();
            String total = cells.get(9).getText();
            return new TimesheetTable(project, activity, monday, tuesday, wednesday, thursday, friday, satuday, sunday, total);
        }).collect(Collectors.toList());
        timesheetTotalBefore.forEach(TimesheetTable::infor);
        System.out.println(timesheetTotalBefore.size());
    }

    public static void getTimesheetTotalAfter() {
        Browser.waitElement(By.xpath("//table[@class='orangehrm-timesheet-table']/tbody[@class='orangehrm-timesheet-table-body']/tr[@class='orangehrm-timesheet-table-body-row --total']"));
        List<WebElement> rowTables = driver.findElements(By.xpath("//table[@class='orangehrm-timesheet-table']/tbody[@class='orangehrm-timesheet-table-body']/tr[@class='orangehrm-timesheet-table-body-row --total']"));
        timesheetTotalAfter = rowTables.stream().map(rowTable -> {
            List<WebElement> cells = rowTable.findElements(By.tagName("td"));
            String project = cells.get(0).getText();
            String activity = cells.get(1).getText();
            String monday = cells.get(2).getText();
            String tuesday = cells.get(3).getText();
            String wednesday = cells.get(4).getText();
            String thursday = cells.get(5).getText();
            String friday = cells.get(6).getText();
            String satuday = cells.get(7).getText();
            String sunday = cells.get(8).getText();
            String total = cells.get(9).getText();
            return new TimesheetTable(project, activity, monday, tuesday, wednesday, thursday, friday, satuday, sunday, total);
        }).collect(Collectors.toList());
        timesheetTotalAfter.forEach(TimesheetTable::infor);
        System.out.println(timesheetTotalAfter.size());
    }

    public static void closeBrowser() {
        if (driver!=null) {
            driver.quit();
        }
    }

}
