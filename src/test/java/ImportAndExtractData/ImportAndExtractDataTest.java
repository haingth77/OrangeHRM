package ImportAndExtractData;

import com.opencsv.exceptions.CsvException;
import common.Browser;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static common.Browser.*;

public class ImportAndExtractDataTest {
    By selectProvinceButton = By.xpath("//div[@class='btn-group bootstrap-select form-control']/button[@class='btn dropdown-toggle btn-default']");
    By provinceDropdownList = By.xpath("//ul[@class='dropdown-menu inner']/li");

    @BeforeClass
    void open() {
        openBrowser();
        visit("https://baohiemxahoi.gov.vn/tracuu/Pages/tra-cuu-ho-gia-dinh.aspx");
    }

    @Test
    public void ExtractData() throws IOException, CsvException {
        Browser.wait(selectProvinceButton);
        click(selectProvinceButton);
        String actualContextResult = Browser.getTextOfWebElements(provinceDropdownList);
        String expectedContextResult = Browser.getDataFromCsvFile("F:/Automation Class/Project/OrangeHRMTest/src/main/resources/64ProvinceNameList.csv");
        Assert.assertEquals(actualContextResult,expectedContextResult);
    }

    @AfterClass
    void closeBrowser() {
        closeBrowser();
    }
}
