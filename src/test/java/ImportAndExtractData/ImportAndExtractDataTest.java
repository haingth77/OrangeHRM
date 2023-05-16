package ImportAndExtractData;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImportAndExtractDataTest {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    void openBrowser() {
        driver = new ChromeDriver();
        driver.get("https://baohiemxahoi.gov.vn/tracuu/Pages/tra-cuu-ho-gia-dinh.aspx");
    }

//    @DataProvider
//    public Object[][] ProvinceNameListFromCsv() throws IOException, CsvException {
//        CSVReader readcsv = new CSVReader(new FileReader("F:/Automation Class/Project/AK39/src/test/java/64ProvinceNameList.csv"));
//        List<String[]> provinceNameCsv = readcsv.readAll();
//        return provinceNameCsv.toArray(new Object[0][]);
//    }

    @Test()
    public void ExtractData() throws IOException, CsvException {
        String[] csvCells;
        List<String> provinceNameCsv = new ArrayList<String>();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        CSVReader readcsv = new CSVReader(new FileReader("F:/Automation Class/Project//OrangeHRMTest/src/test/java/64ProvinceNameList.csv"));

        while ((csvCells = readcsv.readNext()) != null) {
            provinceNameCsv.add(csvCells[0]);
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='btn-group bootstrap-select form-control']/button[@class='btn dropdown-toggle btn-default']")));
        driver.findElement(By.xpath("//div[@class='btn-group bootstrap-select form-control']/button[@class='btn dropdown-toggle btn-default']")).click();
        List<WebElement> provinces =driver.findElements(By.xpath("//ul[@class='dropdown-menu inner']/li"));
        //provinces.forEach(province -> System.out.println(province.getText()));
        List<String> provinceName = provinces.stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertEquals(provinceName,provinceNameCsv.toString().replace("\uFEFF",""));
    }
}
