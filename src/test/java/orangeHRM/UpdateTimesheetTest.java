package orangeHRM;

import common.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.OrangeHRMPage;
import page.TimesheetTable;

import java.util.List;

import static common.Browser.*;

public class UpdateTimesheetTest {
    /*
    Precondition: if there is total -> get total to compare
    s1: click "edit" button - wait
    s2: fill "project" and "activity" by action
    s3: fill monday to sunday
    s4: click "save" button
    s5: click "submit"
    s6: check total moi row
    s7: check total tang len dung so (compare precondition 1)
    s8: check 1 action performed on timesheet is added
     */
    By usernameTextBox  = By.xpath("//input[@name='username']");
    By timesheetTitle = By.xpath("//div[@class='orangehrm-timesheet-header']/div[@class='orangehrm-timesheet-header--title']");
    OrangeHRMPage orangeHRMPage;
    WebDriver driver;
    @BeforeClass
    public void open(){
        Browser.openBrowser();
        visit("https://opensource-demo.orangehrmlive.com/web/index.php/time/viewMyTimesheet");
    }

    @Test
    public void UpdateTimesheetTest() {
        orangeHRMPage = new OrangeHRMPage();
        Browser.waitElement(usernameTextBox);
        orangeHRMPage.login("Admin","admin123");
        waitElement(timesheetTitle);
        List<WebElement> rows = driver.findElements(By.xpath("//tbody[@class='orangehrm-timesheet-table-body']/tr[@class='orangehrm-timesheet-table-body-row']"));
        List<TimesheetTable> timesheetTable = rows.stream().map(row -> {

        });
    }
}
