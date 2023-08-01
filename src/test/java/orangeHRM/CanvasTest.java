package orangeHRM;

import common.Browser;
import common.SubUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.CanvasPage;

import java.util.List;

public class CanvasTest {
    By canvasSubUnit = By.xpath("//div[@class='oxd-grid-3 orangehrm-dashboard-grid']/div[6]/div/div[2]/div[@class='emp-distrib-chart']//div[@class='oxd-pie-chart']/canvas");
    By PIMButton = By.xpath("//ul[@class='oxd-main-menu']/li[2]");
    By subUnitTexts = By.xpath("//div[@class='oxd-grid-3 orangehrm-dashboard-grid']/div[6]/div/div[2]/div[@class='emp-distrib-chart']//ul[@class='oxd-chart-legend']/li/span[@class='oxd-text oxd-text--span']");
    Integer expectedResult_amount =0;
    Integer obtainedResult_amount =0;
    List<SubUnit> subUnits_Before;
    List<SubUnit> subUnits_After;
    String deleted_subUnit;
    CanvasPage canvasPage;
    @Test
    public void CanvasTest() throws InterruptedException {
        canvasPage = new CanvasPage();
        Browser.openBrowser();
        Browser.visit("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        Browser.login("Admin", "admin123");
        Browser.waitElement(subUnitTexts);
        List<WebElement> subUnitsTextFields = Browser.listWebElement(subUnitTexts);
        canvasPage.undisplayAllSubUnit(subUnitsTextFields);
        Browser.waitElement(canvasSubUnit);
        subUnits_Before = canvasPage.getDetailOfSubUnit(subUnitsTextFields);
        Browser.click(PIMButton);
        deleted_subUnit = canvasPage.transferContextOfSubUnitName();
        expectedResult_amount = canvasPage.getSubUnitAmount(deleted_subUnit, subUnits_Before) -1;
        Browser.visit("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        Browser.waitElement(subUnitTexts);
        List<WebElement> subUnitsTextFieldsAfter = Browser.listWebElement(subUnitTexts);
        canvasPage.undisplayAllSubUnit(subUnitsTextFieldsAfter);
        Browser.waitElement(canvasSubUnit);
        subUnits_After = canvasPage.getDetailOfSubUnit(subUnitsTextFieldsAfter);
        obtainedResult_amount = canvasPage.getSubUnitAmount(deleted_subUnit, subUnits_After);
        Assert.assertEquals(obtainedResult_amount, expectedResult_amount);
    }
}
