package orangeHRM;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import common.Browser;
import org.testng.Assert;
import org.testng.annotations.*;
import page.OrangeHRMPage;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static common.Browser.currentUrl;
import static common.Browser.visit;

public class LoginAdminAccountTest_csvfile {
    @DataProvider
    public Object[][] getCredentialsObjectFromCsv() {
    String fileName = "F:/Automation Class/Project/OrangeHRMTest/src/main/resources/username&password.csv";
        try {
        FileReader fileReader = new FileReader(fileName);
        CSVReader csvReader = new CSVReaderBuilder(fileReader)
                .withSkipLines(1)
                .build();
        List<String[]> r = csvReader.readAll();
        return r.toArray(new Object[0][]);
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (CsvException e) {
        throw new RuntimeException(e);
    }
    }

    OrangeHRMPage orangeHRMPage;


    @BeforeClass
    void createPage() {
        orangeHRMPage = new OrangeHRMPage();
    }

    @BeforeMethod
    void openBrowser() {
        Browser.openBrowser();
        visit("https://opensource-demo.orangehrmlive.com/");
    }

    @Test(dataProvider = "getCredentialsObjectFromCsv")
    public void loginAdminAccount(String username, String password) {
        orangeHRMPage.login(username, password);
        Assert.assertEquals(currentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfWidgetCard());
        Assert.assertTrue(orangeHRMPage.checkVisibilityOfDashBoardTitle());
    }

    @AfterMethod
    void closeBrowser() {
        Browser.closeBrowser();
    }
}
