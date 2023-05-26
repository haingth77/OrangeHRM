package orangeHRM;

import common.Browser;
import common.TimesheetTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.TimesheetTablePage;

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
    TimesheetTablePage timesheetTablePage;
    By timesheetTitle = By.xpath("//div[@class='orangehrm-timesheet-header']/div[@class='orangehrm-timesheet-header--title']");
    By editButton = By.xpath("//div[@class='orangehrm-timesheet-footer--options']/button[@class='oxd-button oxd-button--medium oxd-button--ghost']");
    By addRowButton = By.xpath("//button[@class='oxd-icon-button orangehrm-timesheet-icon']/i[@class='oxd-icon bi-plus']");
    By rowTable = By.xpath("//tbody/tr[@class='orangehrm-timesheet-table-body-row']");
    By notificationFrame = By.xpath("//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']");
    By clearTimesheetButton = By.xpath("//button[@class='oxd-icon-button orangehrm-timesheet-icon']/i[@class='oxd-icon bi-trash']");
    By notificationTitle = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-title oxd-toast-content-text']");
    By notificationContent = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']");
    By saveButton = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
    @DataProvider
    public Object[][] timesheetData() {
        return new Object[][]{
            {"ac", "ACME Ltd - ACME Ltd", "Bug Fixes", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "28:00"}
        };
    }

    @BeforeClass
    public void open(){
        Browser.openBrowser();
        visit("https://opensource-demo.orangehrmlive.com/web/index.php/time/viewMyTimesheet");
        login("Admin","admin123");
        waitElement(timesheetTitle);
    }

    @Test (dataProvider = "timesheetData")
    public void UpdateTimesheetTest(String briefProjectname, String projectName, String activityName, String monday, String tuesday, String wednesday, String thursday, String friday, String satuday, String sunday, String total) throws InterruptedException {
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
        Browser.waitElement(editButton);
        Browser.click(editButton);
        Browser.waitElement(addRowButton);
        List<WebElement> clearTimesheets = Browser.listWebElement(clearTimesheetButton);
        if (listWebElement(rowTable).size() < 1) {
            Browser.click(addRowButton);
        } else {
            clearTimesheets.forEach(clearTimesheet -> clearTimesheet.click());
        }
        timesheetTablePage.addNewTimesheet(briefProjectname,projectName);
        timesheetTablePage.selectDropdown(activityName);
        timesheetTablePage.fillTimesheet(monday, tuesday, wednesday, thursday, friday, satuday, sunday);
        Browser.click(saveButton);
        Browser.waitElement(notificationFrame);
        Assert.assertTrue(Browser.checkVisibility(notificationFrame));
        Assert.assertEquals(Browser.getText(notificationTitle),"Success");
        Assert.assertEquals(Browser.getText(notificationContent),"Successfully Saved");
        TimesheetTable timesheetTable = timesheetTablePage.getTimesheetTable();
        boolean result = timesheetTablePage.compareObject(timesheetTable,Data);
        Assert.assertTrue(result);
    }
}
