package page;

import common.Browser;
import common.TimesheetTable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class TimesheetTablePage {
    By lastRowOfTable = By.xpath("//table[@class='orangehrm-timesheet-table']/tbody[@class='orangehrm-timesheet-table-body']/tr[@class='orangehrm-timesheet-table-body-row'][last()]");
    By projectTextbox = By.xpath("//input[@placeholder='Type for hints...']");
    By dropdownbox = By.xpath("//div[@role='listbox']");
    By activityButton = By.xpath("//div[@class='oxd-select-text-input']");
    By timeTextbox = By.xpath("//div[@data-v-957b4417]/input[@class='oxd-input oxd-input--active']");
    By editButton = By.xpath("//div[@class='orangehrm-timesheet-footer--options']/button[@class='oxd-button oxd-button--medium oxd-button--ghost']");
    By clearTimesheetButton = By.xpath("//button[@class='oxd-icon-button orangehrm-timesheet-icon']/i[@class='oxd-icon bi-trash']");
    By addRowButton = By.xpath("//button[@class='oxd-icon-button orangehrm-timesheet-icon']/i[@class='oxd-icon bi-plus']");
    By rowTableInEditForm = By.xpath("//tbody/tr[@class='orangehrm-timesheet-table-body-row']");
    By notificationFrame = By.xpath("//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']");
    By saveButton = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");

    List<TimesheetTable> timesheetTables;

    public TimesheetTable getTimesheetTable() {
        Browser.waitElement(By.xpath("//table[@class='orangehrm-timesheet-table']/tbody[@class='orangehrm-timesheet-table-body']/tr[@class='orangehrm-timesheet-table-body-row']"));
        List<WebElement> rowTables = Browser.listWebElement(lastRowOfTable);
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
        return null;
    }

    public void selectActivity(String activityName) throws InterruptedException {
            Browser.click(activityButton);
            Thread.sleep(3000);
            List<WebElement> optionActivitys = Browser.listWebElement(By.xpath("//div[@class='oxd-select-option']/span"));
            System.out.println(optionActivitys.size());
            for(WebElement optionActivity : optionActivitys) {
                System.out.println(optionActivity.getText());
                if (optionActivity.getText().equalsIgnoreCase(activityName)) {
                    optionActivity.click();
                    break;
                }
            }
    }

    public void selectProject(String briefProjectName, String projectName) throws InterruptedException {
        List<WebElement> projectTextboxes = Browser.listWebElement(projectTextbox);
        WebElement newProjectTextbox = projectTextboxes.get(((projectTextboxes.size())-1));
        Browser.waitElement(projectTextbox);
        System.out.println(projectTextboxes.size());
        newProjectTextbox.click();
        newProjectTextbox.sendKeys(Keys.CONTROL + "a");
        newProjectTextbox.sendKeys(Keys.DELETE);
        newProjectTextbox.sendKeys(briefProjectName);
        Browser.waitElement(dropdownbox);
        Browser.waitElement(dropdownbox);
        Thread.sleep(5000);
        List<WebElement> optionProjects = Browser.listWebElement(By.xpath("//div[@role='option']/span"));
        for(WebElement optionProject : optionProjects) {
            if (optionProject.getText().equalsIgnoreCase(projectName)) {
                optionProject.click();
                break;
            }
        }
    }

    public boolean compareTimesheetTable(TimesheetTable actualTimesheetTable, TimesheetTable expectTimesheetTable) {
        for (Field f : TimesheetTable.class.getFields()) {
            try {
                if (!f.get(actualTimesheetTable).equals(f.get(expectTimesheetTable)))
                    return false;
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public void fillTimesheet(String monday, String tuesday, String wednesday, String thursday, String friday, String satuday, String sunday) {
        List<WebElement> timeDays = Browser.listWebElement(timeTextbox);
        timeDays.forEach(timeDay -> {
            timeDay.sendKeys(Keys.CONTROL+"a");
            timeDay.sendKeys(Keys.DELETE);
        });
        Integer timeDayTextBoxOrder = timeDays.size();
        timeDays.get(timeDayTextBoxOrder-7).sendKeys(monday);
        timeDays.get(timeDayTextBoxOrder-6).sendKeys(tuesday);
        timeDays.get(timeDayTextBoxOrder-5).sendKeys(wednesday);
        timeDays.get(timeDayTextBoxOrder-4).sendKeys(thursday);
        timeDays.get(timeDayTextBoxOrder-3).sendKeys(friday);
        timeDays.get(timeDayTextBoxOrder-2).sendKeys(satuday);
        timeDays.get(timeDayTextBoxOrder-1).sendKeys(sunday);
        Browser.click(saveButton);
        Browser.waitElement(notificationFrame);
    }

    public void startToUpdateTimeSheet() {
        Browser.waitElement(editButton);
        Browser.click(editButton);
        Browser.waitElement(addRowButton);
        List<WebElement> clearTimesheets = Browser.listWebElement(clearTimesheetButton);
        if (Browser.listWebElement(rowTableInEditForm).size() < 1) {
            Browser.click(addRowButton);
        } else {
            clearTimesheets.forEach(clearTimesheet -> clearTimesheet.click());
        }
    }
}
