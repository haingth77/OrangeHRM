package orangeHRM;

import common.Browser;
import common.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.PunchTimePage;

import static common.Browser.*;

public class PuchTimeTest extends TestBase {
    PunchTimePage punchTimePage;
    By timeField = By.xpath("//div[@class='data']/p[@class='oxd-text oxd-text--p']");
    By punchNoteField = By.xpath("//div[@class='oxd-table-card-cell']/span[@class='oxd-text oxd-text--span']");
    By notificationFrame = By.xpath("//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']");
    By notificationTitle = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-title oxd-toast-content-text']");
    By notificationContent = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']");

    @Test(dataProvider = "testDataForPunchTime")
    public void PunchTimeTest(String username, String password, String dateIn, String timeIn, String noteIn, String timeOut, String noteOut)  throws InterruptedException {
        punchTimePage = new PunchTimePage();
        String expectedTimeResult = "[" + dateIn + " " + timeIn + " , " + dateIn + " " + timeOut + " ]";
        String expectedNoteResult = "[" + noteIn + ", " + noteOut + "]";
        visit("https://opensource-demo.orangehrmlive.com/web/index.php/attendance/viewMyAttendanceRecord");
        login(username, password);
        punchTimePage.showPunchTimeRecord(dateIn);
        punchTimePage.clearOtherRecord();
        visit("https://opensource-demo.orangehrmlive.com/web/index.php/attendance/punchIn");
        punchTimePage.completePreviousPunchTime();
        punchTimePage.chooseYear(dateIn);
        punchTimePage.chooseMonth(dateIn);
        punchTimePage.chooseDate(dateIn);
        punchTimePage.chooseTime(timeIn);

        punchTimePage.fillNote("Punch In", noteIn);
        punchTimePage.fillPunchDate("Punch Out",dateIn);
        punchTimePage.fillPunchTime("Punch Out", timeOut);
        punchTimePage.fillNote("Punch Out", noteOut);
        waitElement(notificationFrame);
        Assert.assertTrue(Browser.checkVisibility(notificationFrame));
        Assert.assertEquals(Browser.getText(notificationTitle),"Success");
        Assert.assertEquals(Browser.getText(notificationContent),"Successfully Saved");
        visit("https://opensource-demo.orangehrmlive.com/web/index.php/attendance/viewMyAttendanceRecord");
        punchTimePage.showPunchTimeRecord(dateIn);
        String obtainedTimeResult = punchTimePage.getContentOfPunchTime(timeField);
        String obtainedNoteResult = punchTimePage.getContentOfNote(punchNoteField);
        Assert.assertEquals(obtainedTimeResult, expectedTimeResult);
        Assert.assertEquals(obtainedNoteResult, expectedNoteResult);
    }
}
