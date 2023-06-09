package ImportAndExtractData;

import com.opencsv.exceptions.CsvException;
import common.Browser;
import common.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static common.Browser.*;

public class ImportAndExtractDataTest extends TestBase {
    By selectProvinceButton = By.xpath("//div[@class='btn-group bootstrap-select form-control']/button[@class='btn dropdown-toggle btn-default']");
    By provinceDropdownList = By.xpath("//ul[@class='dropdown-menu inner']/li");

    @Test
    public void ExtractData() throws IOException, CsvException {
        visit("https://baohiemxahoi.gov.vn/tracuu/Pages/tra-cuu-ho-gia-dinh.aspx");
        Browser.waitElement(selectProvinceButton);
        click(selectProvinceButton);
        String actualContextResult = Browser.getTextOfWebElements(provinceDropdownList);
        String expectedContextResult = Browser.getDataFromCsvFile("D:/HaiNguyen_Private/IntelliJ/OrangeHRM/src/test/resources/64ProvinceNameList.csv");
        Assert.assertEquals(actualContextResult,expectedContextResult);
    }
}
