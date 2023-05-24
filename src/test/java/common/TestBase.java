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

    @BeforeClass(alwaysRun = true)
    public void open() {
        openBrowser();
        var url = getCredentialsObjectFromCsv()[0][0];
        visit((String) url);
    }

    @AfterClass(alwaysRun = true)
    public void close() {
        closeBrowser();
    }
}
