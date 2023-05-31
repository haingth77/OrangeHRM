package page;

import common.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.stream.Collectors;

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

    public void fillPunchDate(String punchInOrPunchOut, String dateIn) throws InterruptedException {
        Thread.sleep(1000);
        Browser.waitElement(punchTitle);
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
        if (Browser.checkVisibility(dateTextbox) == false) {Browser.click(toggleButton);}
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
}
