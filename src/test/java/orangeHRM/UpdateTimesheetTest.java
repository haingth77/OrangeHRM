package orangeHRM;

import common.Browser;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

    @DataProvider
    public Object[][] timesheetData() {
        return new Object[][]{
            {"ac", "", "8", "8", "1", "2", "3", "8", "9"}
        };
    }
    @BeforeClass
    public void open(){
        Browser.openBrowser();
        visit("https://opensource-demo.orangehrmlive.com/web/index.php/time/viewMyTimesheet");
        Browser.waitElement(usernameTextBox);
        login("Admin","admin123");
        waitElement(timesheetTitle);
    }

    @Test
    public void UpdateTimesheetTest() {
        Browser.getTimesheetTotalBefore();
        System.out.println("123");
        Browser.getTimesheetTable();
        System.out.println("123");
        Browser.getTimesheetTotalAfter();


//        List<TimesheetTable> timesheetTotalBefore = rowsTable.stream().map(row -> {
//
//        });
//        List<TimesheetTable> timesheetTotalAfter = rowsTable.stream().map(row -> {
//
//        });

    }
}
