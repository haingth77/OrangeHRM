package common;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static common.Browser.*;

public class TestBase {
    public Object[][] getCredentialsObjectFromCsv() {
        String fileName = "D:/HaiNguyen_Private/IntelliJ/OrangeHRM/src/test/resources/username&password.csv";
        String[] csvCells;
        try {
            FileReader fileReader = new FileReader(fileName);
            CSVReader csvReader = new CSVReaderBuilder(fileReader)
                    .withSkipLines(1)
                    .build();
            List<String[]> r = csvReader.readAll();
            return r.toArray(new Object[0][0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    @DataProvider
    public Object[][] testData() {
        return getCredentialsObjectFromCsv();
    }

    @DataProvider
    public Object[][] testDataForPunchTime () {
        return new Object[][]{
                {"Admin", "admin123", "2022-05-02", "12:00 AM", "test123", "12:30 AM", "test456"}
        };
    }

    @DataProvider
    public Object[][] timesheetData() {
        return new Object[][]{
                {"Admin", "admin123", "ac", "ACME Ltd - ACME Ltd", "Bug Fixes", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "28:00"}
        };
    }

    @BeforeClass(alwaysRun = true)
    public void open() {
        openBrowser();
    }

//    @AfterClass(alwaysRun = true)
//    public void close() {
//        closeBrowser();
//    }
}
