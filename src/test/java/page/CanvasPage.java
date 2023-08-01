package page;

import common.Browser;
import common.SubUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CanvasPage {
    List<SubUnit> subUnits;
    By canvasSubUnit = By.xpath("//div[@class='oxd-grid-3 orangehrm-dashboard-grid']/div[6]/div/div[2]/div[@class='emp-distrib-chart']//div[@class='oxd-pie-chart']/canvas");
    By PIMRecord_row1_checkbox = By.xpath("//div[@class='oxd-checkbox-wrapper']/label/input[@type='checkbox']");
    By PIMRecord_row1_subUnitText = By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']/div[7]/div");
    By PIMRecord_row1_trashButton = By.xpath("//div[@class='oxd-table-cell-actions']/button/i[@class='oxd-icon bi-trash']");
    By PIMButton = By.xpath("//ul[@class='oxd-main-menu']/li[2]");
    By PIM_deleteConfirmationButton = By.xpath("//div[@class='orangehrm-modal-footer']/button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']");
    By subUnitTextFields = By.xpath("//div[@class='oxd-grid-3 orangehrm-dashboard-grid']/div[6]/div/div[2]/div[@class='emp-distrib-chart']//ul[@class='oxd-chart-legend']/li/span[@class='oxd-text oxd-text--span']");
    By subUnitName = By.xpath("//span[@id='oxd-pie-chart-tooltip']/span[2]");
    By subUnitAmount = By.xpath("//span[@id='oxd-pie-chart-tooltip']/span[3]/b");
    By subUnitPercentage = By.xpath("//span[@id='oxd-pie-chart-tooltip']/span[3]/p");
    String[] subUnit_Engineering = new String[] {"Development", "Quality Assurance", "TechOps"};
    //Sales & Marketing
    String[] subUnit_SalesAndMarketing = {"Sales", "Marketing"};
    //Client Services
    String[] subUnit_clientServices = {"Technical Support"};

    public void undisplayAllSubUnit(List<WebElement> listWebElements) {
        for (WebElement listWebElement : listWebElements) {
            listWebElement.click();
        }
    }

    public List<SubUnit> getDetailOfSubUnit(List<WebElement> listWebElements) {
        Browser.waitElement(canvasSubUnit);
        subUnits = listWebElements.stream().map(listWebElement -> {
            listWebElement.click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Browser.hoverAndClickElement(canvasSubUnit);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Browser.waitElement(subUnitName);
            String name= Browser.getText(subUnitName);
            Integer amount = Integer.parseInt(Browser.getText(subUnitAmount));
            double percentage = Double.parseDouble(Browser.getText(subUnitPercentage).replace("%","").replace("(","").replace(")",""));
            listWebElement.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new SubUnit(name, amount, percentage);

        }).collect(Collectors.toList());
        return subUnits;
    }

    public String transferContextOfSubUnitName() throws InterruptedException {
        String transferContext;
        Thread.sleep(4000);
        if (Browser.isDisplayed(PIMRecord_row1_subUnitText)) {
            transferContext = Browser.getText(PIMRecord_row1_subUnitText);
        } else {
            transferContext = "Unassigned";
        }
        Browser.click(PIMRecord_row1_trashButton);
        Browser.waitElement(PIM_deleteConfirmationButton);
        Browser.click(PIM_deleteConfirmationButton);
        if (Arrays.asList(subUnit_Engineering).contains(transferContext)) {transferContext = "Engineering";}
        if (Arrays.asList(subUnit_SalesAndMarketing).contains(transferContext)) {transferContext = "Sales & Marketing";}
        if (Arrays.asList(subUnit_clientServices).contains(transferContext)) {transferContext = "Client Services";}
        return transferContext;
    }

    public Integer getSubUnitAmount(String subUnitName, List<SubUnit> subUnits) {
        Integer subUnitAmount =0;
        for (SubUnit subUnit : subUnits) {
            if (subUnit.getSubUnitName().equals(subUnitName)) {
                subUnitAmount = subUnit.getAmount();
            }
        }
        return subUnitAmount;
    }
}
