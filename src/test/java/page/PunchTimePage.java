package page;

import common.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.StrictMath.abs;

public class PunchTimePage {
    By punchTitle = By.xpath("//div[@class='orangehrm-card-container']/h6[@class='oxd-text oxd-text--h6 orangehrm-main-title']");
    By dateTextbox = By.xpath("//div[@class='oxd-date-input']/input[@class]");
    By timeTextbox = By.xpath("//div[@class='oxd-time-input']/input[@class]");
    By noteTextbox = By.xpath("//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical']");
    By submitButton = By.xpath("//div[@class='oxd-form-actions']/button[@type='submit']");
    By timeZoneField = By.xpath("//p[@class='oxd-text oxd-text--p']/span[@class='oxd-text oxd-text--span timezone']");
    By toggleButton = By.xpath("//div[@class='--toggle']/button[@class='oxd-icon-button']");
    By clearFormButton = By.xpath("//button[@type='button']/i[@class='oxd-icon bi-trash-fill oxd-button-icon']");
    By numberOfRecordField = By.xpath("//span[@class='oxd-text oxd-text--span']");
    By confirmClearRecordButton = By.xpath("//button[@type='button']/i[@class='oxd-icon bi-trash oxd-button-icon']");
    By punchTimeCheckbox = By.xpath("//i[@class='oxd-icon bi-check oxd-checkbox-input-icon']");
    By dateButton = By.xpath("//div[@class='oxd-date-input']/i[@class='oxd-icon bi-calendar oxd-date-input-icon']");
    By yearButton = By.xpath("//div[@class='oxd-calendar-selector-year-selected']/p[@class='oxd-text oxd-text--p']");
    By yearDropdownSelection = By.xpath("//ul[@class='oxd-calendar-dropdown']/li[@class='oxd-calendar-dropdown--option']");
    By yearDropdownCurrent = By.xpath("//ul[@class='oxd-calendar-dropdown']/li[@class='oxd-calendar-dropdown--option --selected']");
    By monthButton = By.xpath("//div[@class='oxd-calendar-selector-month-selected']/p[@class='oxd-text oxd-text--p']");
    By monthDropdownSelection = By.xpath("//ul[@class='oxd-calendar-dropdown']/li[@class='oxd-calendar-dropdown--option']");
    By monthDropdownCurrent = By.xpath("//ul[@class='oxd-calendar-dropdown']/li[@class='oxd-calendar-dropdown--option --selected']");
    By dateSelection = By.xpath("//div[@class='oxd-calendar-dates-grid']/div/div");
    By timeButton = By.xpath("//div[@class='oxd-time-input']/i[@class='oxd-icon bi-clock oxd-time-input--clock']");
    By hourPlusButton = By.xpath("//div[@class='oxd-time-hour-input']/i[@class='oxd-icon bi-chevron-up oxd-icon-button__icon oxd-time-hour-input-up']");
    By hourMinusButton = By.xpath("//div[@class='oxd-time-hour-input']/i[@class='oxd-icon bi-chevron-down oxd-icon-button__icon oxd-time-hour-input-down']");
    By minutePlusButton = By.xpath("//div[@class='oxd-time-minute-input']/i[@class='oxd-icon bi-chevron-up oxd-icon-button__icon oxd-time-minute-input-up']");
    By minuteMinusButton = By.xpath("//div[@class='oxd-time-minute-input']/i[@class='oxd-icon bi-chevron-down oxd-icon-button__icon oxd-time-minute-input-down']");
    By AMButton = By.xpath("//div[@class='oxd-time-period-label']/input[@name='am']");
    By PMButton = By.xpath("//div[@class='oxd-time-period-label']/input[@name='pm']");
    public void fillPunchDate(String punchInOrPunchOut, String dateIn) throws InterruptedException {
        Thread.sleep(1000);
        Browser.waitContentOfElement(punchTitle,punchInOrPunchOut);
        Browser.waitElement(dateTextbox);
        Browser.clickByJavaScript(dateTextbox);
        Browser.clearAllContent(dateTextbox);
        Browser.fill(dateTextbox, dateIn);
    }

    public void fillPunchTime (String punchInOrPunchOut, String timeIn) throws InterruptedException {
        Thread.sleep(1000);
        Browser.waitContentOfElement(punchTitle,punchInOrPunchOut);
        Browser.waitElement(timeTextbox);
        Browser.clearAllContent(timeTextbox);
        Browser.fill(timeTextbox, timeIn);
    }

    public void  fillNote (String punchInOrPunchOut, String noteIn) throws InterruptedException {
        Browser.waitContentOfElement(punchTitle,punchInOrPunchOut);
        Browser.fill(noteTextbox, noteIn);
        Thread.sleep(2000);
        Browser.clickByJavaScript(submitButton);
    }

    public void completePreviousPunchTime() throws InterruptedException {
        Thread.sleep(2000);
        if (Browser.getText(punchTitle).equals("Punch Out")) {
            Browser.waitElement(submitButton);
            Browser.clickByJavaScript(submitButton);
        }
    }

    public void clearOtherRecord() throws InterruptedException {
        Thread.sleep(2000);
        if (!Browser.getText(numberOfRecordField).equals("No Records Found")) {
            Browser.click(punchTimeCheckbox);
            Browser.click(clearFormButton);
            Browser.clickByJavaScript(confirmClearRecordButton);
        }
    }

    public void showPunchTimeRecord(String dateIn) throws InterruptedException {
        Thread.sleep(2000);
        if (!Browser.isDisplayed(dateTextbox)) {Browser.click(toggleButton);}
        Browser.waitElement(dateTextbox);
        Browser.clickByJavaScript(dateTextbox);
        Browser.clearAllContent(dateTextbox);
        Browser.fill(dateTextbox, dateIn);
        Browser.click(submitButton);
    }

    public String getContentOfPunchTime(By locator) {
        Browser.waitElement(locator);
        String timeZone = Browser.getText(timeZoneField);
        return Browser.listWebElement(locator).stream().map(WebElement::getText).collect(Collectors.toList()).toString().replace(timeZone,"");
    }

    public String getContentOfNote(By locator) {
        return Browser.listWebElement(locator).stream().map(WebElement::getText).collect(Collectors.toList()).toString();
    }

    public void chooseYear(String punchInOrPunchOut, String dateIn) {
        Integer targetYear = Integer.parseInt(dateIn.split("-")[0]);
        Browser.click(dateButton);
        Browser.waitElement(yearButton);
        Browser.click(yearButton);
        Browser.waitElement(yearDropdownSelection);
        Integer currentYear = Integer.parseInt(Browser.getText(yearDropdownCurrent));
//Select year by comparing value from list and targetYear
        List<WebElement> yearSelections = Browser.listWebElement(yearDropdownSelection);
        if (targetYear != currentYear) {
            for (WebElement year: yearSelections) {
                if (Integer.parseInt(year.getText()) == targetYear) {
                    year.click();}
            }
        }
    }

    public void chooseMonth(String punchInOrPunchOut, String dateIn) {
//Convert from number into month
        HashMap<String,String> map = new HashMap();
        map.put("01", "January");
        map.put("02", "February");
        map.put("03", "March");
        map.put("04", "April");
        map.put("05", "May");
        map.put("06", "June");
        map.put("07", "July");
        map.put("08", "August");
        map.put("09", "September");
        map.put("10", "October");
        map.put("11", "November");
        map.put("12", "December");
        String numberMonth = dateIn.split("-")[1];
        String targetMonth = map.get(numberMonth);
        Browser.waitElement(monthButton);
        Browser.click(monthButton);
        Browser.waitElement(monthDropdownSelection);
//Select month by comparing value from list and targetMonth
        String currentMonth = Browser.getText(monthDropdownCurrent);
        List<WebElement> monthSelections = Browser.listWebElement(monthDropdownSelection);
        if (!targetMonth.equals(currentMonth)) {
            for (WebElement month: monthSelections) {
                if (month.getText().equals(targetMonth)) {
                    month.click();}
            }
        }
    }

    public void chooseDate(String punchInOrPunchOut, String dateIn) {
        Integer targetDate = Integer.parseInt(dateIn.split("-")[2]);
        Browser.waitElement(dateSelection);
//Select date by comparing value from list and targetDate
        List<WebElement> dateSelections = Browser.listWebElement(dateSelection);
        for (WebElement date: dateSelections) {
            if (Integer.parseInt(date.getText()) == targetDate) {
                date.click();
                break;
            }
        }
    }

    public void chooseTime(String punchInOrPunchOut, String time){
        Integer differenceHour, differenceMinute;
        String timePeriod;
        Calendar calendar = Calendar.getInstance();
        Integer hour = Integer.valueOf(calendar.get(Calendar.HOUR));
        Integer minute = Integer.valueOf(calendar.get(Calendar.MINUTE));
        Browser.waitElement(timeButton);
        Browser.click(timeButton);
        Browser.waitElement(hourPlusButton);
//Set "AM" or "PM" for punch time
        timePeriod = time.split(" ")[1];
        if (timePeriod.equals("AM")) {
            Browser.click(AMButton);
        } else {
            Browser.click(PMButton);
        }
//Set hour for punch time by clicking button for each hour
        differenceHour = Integer.parseInt((time.split("// ")[0]).split(":")[0]) - hour;
        if (differenceHour > 0) {
            for (int count = 0; count < abs(differenceHour); count++) {
                Browser.click(hourPlusButton);
            }
        } else {
            for (int count = 0; count < abs(differenceHour); count++) {
                Browser.click(hourMinusButton);
            }
        }
//Set minute for punch time by clicking button for each minute
        differenceMinute = Integer.parseInt((time.split(" ")[0]).split(":")[1]) - minute;
        if (differenceMinute > 0) {
            for (int count = 0; count < abs(differenceMinute); count++) {
                Browser.click(minutePlusButton);
            }
        } else {
            for (int count = 0; count < abs(differenceMinute); count++) {
                Browser.click(minuteMinusButton);
            }
        }
    }
}
