package orangeHRM;

import common.SubUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CanvasTest {
    By usernameTextBox  = By.xpath("//input[@name='username']");
    By passwordTextBox  = By.xpath("//input[@name='password']");
    By loginButton      = By.xpath("//div[@class='oxd-form-actions orangehrm-login-action']/button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']");
    By accountNamefield = By.xpath("//span[@class='oxd-userdropdown-tab']/p[@class='oxd-userdropdown-name']");
    By subUnitDetail = By.xpath("//span[@id='oxd-pie-chart-tooltip']");
    By canvasSubUnit = By.xpath("//div[@class='oxd-grid-3 orangehrm-dashboard-grid']/div[6]/div/div[2]/div[@class='emp-distrib-chart']//div[@class='oxd-pie-chart']/canvas");
    By PIMRecord_row1_checkbox = By.xpath("//div[@class='oxd-checkbox-wrapper']/label/input[@type='checkbox']");
    By PIMRecord_row1_subUnitText = By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']/div[7]/div");
    By PIMRecord_row1_trashButton = By.xpath("//div[@class='oxd-table-cell-actions']/button/i[@class='oxd-icon bi-trash']");
    By PIMButton = By.xpath("//ul[@class='oxd-main-menu']/li[2]");
    By PIM_deleteConfirmationButton = By.xpath("//div[@class='orangehrm-modal-footer']/button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']");

    WebDriverWait wait;
    Integer TIME_OUT_IN_SECONDS = 10;
    Integer expectedResult_amount =0;
    Integer obtainedResult_amount =0;

    List<SubUnit> subUnits;
    List<SubUnit> subUnits1;

    String[] subUnit_Engineering = new String[] {"Development", "Quality Assurance", "TechOps"};
    //Sales & Marketing
    String[] subUnit_SalesAndMarketing ={"Sales", "Marketing"};
    //Client Services
    String[] subUnit_clientServices ={"Technical Support"};
    @Test
    public void CanvasTest() throws InterruptedException {
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        Actions hover = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        //login
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameTextBox));
        driver.findElement(usernameTextBox).sendKeys("Admin");
        driver.findElement(passwordTextBox).sendKeys("admin123");
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountNamefield));
        //Undisplay all Sub Unit
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@class='oxd-grid-3 orangehrm-dashboard-grid']/div[6]/div/div[2]/div[@class='emp-distrib-chart']//ul[@class='oxd-chart-legend']/li/span[@class='oxd-text oxd-text--span']"))));
        List<WebElement> subUnitsTextFields = driver.findElements(By.xpath("//div[@class='oxd-grid-3 orangehrm-dashboard-grid']/div[6]/div/div[2]/div[@class='emp-distrib-chart']//ul[@class='oxd-chart-legend']/li/span[@class='oxd-text oxd-text--span']"));
        int sum = subUnitsTextFields.size();
        System.out.println("Total sub unit: " + sum);

        for (WebElement subUnit : subUnitsTextFields) {
            subUnit.click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(canvasSubUnit));

        WebElement canvas = driver.findElement(canvasSubUnit);
        var canvas_dimensions = canvas.getSize();
        System.out.println("canvas dimnension: " + canvas_dimensions);

        //Get number and percentage of each sub unit
//        for (WebElement subUnit : subUnitsTextFields) {
//            subUnit.click();
//            Thread.sleep(2000);
//            hover.moveToElement(driver.findElement(canvasSubUnit)).clickAndHold().build().perform();
//            Thread.sleep(2000);
//            //driver.findElement(canvasSubUnit).click();
//            System.out.println("loop");
//            String name= driver.findElement(By.xpath("//span[@id='oxd-pie-chart-tooltip']/span[2]")).getText();
//            System.out.println("name: " + name);
//
//            //String name = "1";
//            Integer amount = Integer.parseInt(driver.findElement(By.xpath("//span[@id='oxd-pie-chart-tooltip']/span[3]/b")).getText());
//            double percentage = Double.parseDouble(driver.findElement(By.xpath("//span[@id='oxd-pie-chart-tooltip']/span[3]/p")).getText().replace("%","").replace("(","").replace(")",""));
//
//            SubUnit unit1 = new SubUnit (name, amount, percentage);
//            subUnit.click();
//            Thread.sleep(1000);
//        }


        subUnits = subUnitsTextFields.stream().map(subUnitsTextField -> {
            subUnitsTextField.click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hover.moveToElement(driver.findElement(canvasSubUnit)).clickAndHold().build().perform();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='oxd-pie-chart-tooltip']/span[2]")));
            String name= driver.findElement(By.xpath("//span[@id='oxd-pie-chart-tooltip']/span[2]")).getText();
            Integer amount = Integer.parseInt(driver.findElement(By.xpath("//span[@id='oxd-pie-chart-tooltip']/span[3]/b")).getText());
            double percentage = Double.parseDouble(driver.findElement(By.xpath("//span[@id='oxd-pie-chart-tooltip']/span[3]/p")).getText().replace("%","").replace("(","").replace(")",""));
            subUnitsTextField.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new SubUnit(name, amount, percentage);

        }).collect(Collectors.toList());
        subUnits.forEach(SubUnit::infor);
        System.out.println("loop1111");

        //Navigate to PIM screen
        driver.findElement(PIMButton).click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(PIMRecord_row1_subUnitText));
        Thread.sleep(4000);
        if (driver.findElement(PIMRecord_row1_subUnitText).isDisplayed()) {
            String deleted_subUnit = driver.findElement(PIMRecord_row1_subUnitText).getText();
        } else {
            String deleted_subUnit = "Unassigned";
        }
        String deleted_subUnit = driver.findElement(PIMRecord_row1_subUnitText).getText();
        //driver.findElement(PIMRecord_row1_checkbox).click();
        driver.findElement(PIMRecord_row1_trashButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(PIM_deleteConfirmationButton));
        driver.findElement(PIM_deleteConfirmationButton).click();
        if (Arrays.asList(subUnit_Engineering).contains(deleted_subUnit)) {deleted_subUnit = "Engineering";}
        if (Arrays.asList(subUnit_SalesAndMarketing).contains(deleted_subUnit)) {deleted_subUnit = "Sales & Marketing";}
        if (Arrays.asList(subUnit_clientServices).contains(deleted_subUnit)) {deleted_subUnit = "Client Services";}

        for (SubUnit subUnit : subUnits) {
            if (subUnit.getSubUnitName().equals(deleted_subUnit)) {
                //subUnit.infor();
                expectedResult_amount = subUnit.getAmount() -1;
            }
        }
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        wait.until(ExpectedConditions.visibilityOfElementLocated((accountNamefield)));
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@class='oxd-grid-3 orangehrm-dashboard-grid']/div[6]/div/div[2]/div[@class='emp-distrib-chart']//ul[@class='oxd-chart-legend']/li/span[@class='oxd-text oxd-text--span']"))));
        List<WebElement> subUnitsTextFields1 = driver.findElements(By.xpath("//div[@class='oxd-grid-3 orangehrm-dashboard-grid']/div[6]/div/div[2]/div[@class='emp-distrib-chart']//ul[@class='oxd-chart-legend']/li/span[@class='oxd-text oxd-text--span']"));
        System.out.println("Total sub unit: " + sum);

        for (WebElement subUnit : subUnitsTextFields1) {
            subUnit.click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(canvasSubUnit));

        subUnits1 = subUnitsTextFields1.stream().map(subUnitsTextField1 -> {
            subUnitsTextField1.click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hover.moveToElement(driver.findElement(canvasSubUnit)).clickAndHold().build().perform();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='oxd-pie-chart-tooltip']/span[2]")));
            String name1= driver.findElement(By.xpath("//span[@id='oxd-pie-chart-tooltip']/span[2]")).getText();
            Integer amount1 = Integer.parseInt(driver.findElement(By.xpath("//span[@id='oxd-pie-chart-tooltip']/span[3]/b")).getText());
            double percentage1 = Double.parseDouble(driver.findElement(By.xpath("//span[@id='oxd-pie-chart-tooltip']/span[3]/p")).getText().replace("%","").replace("(","").replace(")",""));
            subUnitsTextField1.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new SubUnit(name1, amount1, percentage1);

        }).collect(Collectors.toList());

        for (SubUnit subUnit1 : subUnits1) {
            if (subUnit1.getSubUnitName().equals(deleted_subUnit)) {
                //subUnit.infor();
                obtainedResult_amount = subUnit1.getAmount();
            }
        }
        subUnits1.forEach(SubUnit::infor);

        Assert.assertEquals(expectedResult_amount, obtainedResult_amount);
    }
}
//*[@id="oxd-pie-chart-tooltip"]/span[3]/b
