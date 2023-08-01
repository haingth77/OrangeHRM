package orangeHRM;

import common.Browser;
import common.TestBase;
import common.TimesheetTable;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.TimesheetTablePage;

import static common.Browser.*;

public class TimesheetTableTest extends TestBase {
    TimesheetTablePage timesheetTablePage;
    By timesheetTitle = By.xpath("//div[@class='orangehrm-timesheet-header']/div[@class='orangehrm-timesheet-header--title']");
    By notificationFrame = By.xpath("//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']");
    By notificationTitle = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-title oxd-toast-content-text']");
    By notificationContent = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']");

    @Test (dataProvider = "timesheetData")
    public void UpdateTimesheetTest(String username, String password, String briefProjectName, String projectName, String activityName, String monday, String tuesday, String wednesday, String thursday, String friday, String satuday, String sunday, String total) throws InterruptedException {
        visit("https://opensource-demo.orangehrmlive.com/web/index.php/time/viewMyTimesheet");
        login(username,password);
        waitElement(timesheetTitle);
        timesheetTablePage = new TimesheetTablePage();
        TimesheetTable Data = new TimesheetTable(
                projectName,
                activityName,
                monday,
                tuesday,
                wednesday,
                thursday,
                friday,
                satuday,
                sunday,
                total
        );
        timesheetTablePage.startToUpdateTimeSheet();
        timesheetTablePage.selectProject(briefProjectName,projectName);
        timesheetTablePage.selectActivity(activityName);
        timesheetTablePage.fillTimesheet(monday, tuesday, wednesday, thursday, friday, satuday, sunday);
        Assert.assertTrue(Browser.isDisplayed(notificationFrame));
        Assert.assertEquals(Browser.getText(notificationTitle),"Success");
        Assert.assertEquals(Browser.getText(notificationContent),"Successfully Saved");
        TimesheetTable timesheetTable = timesheetTablePage.getTimesheetTable();
        boolean result = timesheetTablePage.compareTimesheetTable(timesheetTable,Data);
        Assert.assertTrue(result);
    }
}
