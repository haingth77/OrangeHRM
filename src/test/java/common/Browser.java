package common;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.Color;

import java.awt.*;
import java.awt.Robot;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Browser {
    private static final By usernameTextBox  = By.xpath("//input[@name='username']");
    private static final By passwordTextBox  = By.xpath("//input[@name='password']");
    private static final By loginButton      = By.xpath("//div[@class='oxd-form-actions orangehrm-login-action']/button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']");
    private static final By accountNamefield = By.xpath("//span[@class='oxd-userdropdown-tab']/p[@class='oxd-userdropdown-name']");

    private static WebDriver driver;
    private static final int TIME_OUT_IN_SECONDS = 10;
    private static WebDriverWait wait ;

    public static Robot initialRobot() {
        java.awt.Robot robot = null;
        try {
            robot = new java.awt.Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return robot;
    }

    public static void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static void visit(String url) {
        driver.get(url);
    }

    public static String currentUrl() {
        return driver.getCurrentUrl();
    }

    public static WebElement element(By locator) {
        return driver.findElement(locator);
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

    public static void waitContentOfElement (By locator, String content) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.textToBe(locator,content));
    }

    public static void waitUntilElementClickable(By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
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

    public static void clickByJavaScript(By locator) throws InterruptedException {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()",element);
        Thread.sleep(1000);
    }
    
    public static void clearAllContent(By locator) {
        driver.findElement(locator).sendKeys(Keys.CONTROL+"a");
        driver.findElement(locator).sendKeys(Keys.DELETE);
    }

    public static void closeBrowser() {
        if (driver!=null) {
            driver.quit();
        }
    }

    public static String checkColorCodeHex(By locator, String value) {
        String strg = driver.findElement(locator).getCssValue(value);
        String result;
        return result = Color.fromString(strg).asHex();
    }

    public static void dragAndDropBy(By locator, int xOffset, int yOffset) {
        Actions action = new Actions(driver);
        Browser.waitElement(locator);
        WebElement element = Browser.element(locator);
        action.dragAndDropBy(element, xOffset, yOffset).perform();
    }

    public static void uploadFile(By locator, String filePath) throws InterruptedException {
        Browser.click(locator);
        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        Thread.sleep(500);
        Robot robot = initialRobot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(500);
    }

    public static void keyboardPaste(By locator,String string) throws InterruptedException{
        Browser.click(locator);
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        Robot robot = initialRobot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_A);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(500);
    }
}
