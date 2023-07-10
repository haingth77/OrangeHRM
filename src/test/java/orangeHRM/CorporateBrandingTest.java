package orangeHRM;

import common.Browser;
import common.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.CorporateBrandingPage;

import static common.Browser.*;

public class CorporateBrandingTest extends TestBase {
    By primaryColorButton = By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters']/div/div/div/div[@class='oxd-color-input-preview']");
    By pickerRangePointer = By.xpath("//input[@class='oxd-color-picker-range']");
    By pickerIndicatorPointer = By.xpath("//div[@class='oxd-color-picker-indicator']");
    By notificationTitle = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-title oxd-toast-content-text']");
    By notificationContent = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']");
    By notificationFrame = By.xpath("//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']");
    By clientLogoBrowseButton = By.xpath("//div[@class='oxd-file-div oxd-file-div--active']/div[@class='oxd-file-button']");
    By clientBannerBrowseButton = By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']/div[2]/div/div/div/div/div[@class='oxd-file-button']");
    By loginBannerBrowseButton = By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']/div[3]/div/div/div/div/div[@class='oxd-file-button']");
    By resetButton = By.xpath("//div[@class='orangehrm-actions-group']/button[@type='button']");
    By submitButton = By.xpath("//div[@class='orangehrm-actions-group']/button[@type='submit']");
    By clientLogo = By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']/div[1]/div[1]/div[2]/div[1]/div[1]/p[@class='oxd-text oxd-text--p orangehrm-file-name']");
    By clientBanner = By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']/div[2]/div[1]/div[2]/div[1]/div[1]/p[@class='oxd-text oxd-text--p orangehrm-file-name']");
    By logoBanner = By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']/div[3]/div[1]/div[2]/div[1]/div[1]/p[@class='oxd-text oxd-text--p orangehrm-file-name']");
    String filePath_clientLogo = "D:\\HaiNguyen_Private\\IntelliJ\\OrangeHRM\\src\\test\\resources\\pikachu.jpg";
    String filePath_clientBanner = "D:\\HaiNguyen_Private\\IntelliJ\\OrangeHRM\\src\\test\\resources\\pikachu_clientBanner.jpg";
    String filePath_loginBanner = "D:\\HaiNguyen_Private\\IntelliJ\\OrangeHRM\\src\\test\\resources\\pikachu_loginBanner.jpg";
    CorporateBrandingPage corporateBrandingPage;

    @Test
    public void CorporateBrandingTest() throws InterruptedException {
        corporateBrandingPage = new CorporateBrandingPage();
        Browser.visit("https://opensource-demo.orangehrmlive.com/web/index.php/admin/addTheme");
        Browser.login("Admin","admin123");
        corporateBrandingPage.resetToDefault(resetButton);
        waitElement(primaryColorButton);
        Browser.click(primaryColorButton);
        corporateBrandingPage.selectColorByDragAndDrop(pickerIndicatorPointer,550, 350);
        corporateBrandingPage.selectColorByDragAndDrop(pickerRangePointer,510, 499);
        Browser.uploadFile(clientLogoBrowseButton, filePath_clientLogo);
        Browser.uploadFile(clientBannerBrowseButton, filePath_clientBanner);
        Browser.uploadFile(loginBannerBrowseButton,filePath_loginBanner);
        Browser.click(submitButton);
        waitElement(notificationFrame);
        Assert.assertEquals(Browser.getText(notificationTitle),"Success");
        Assert.assertEquals(Browser.getText(notificationContent),"Successfully Saved");
        Browser.waitElement(clientLogo);
        Assert.assertEquals(Browser.checkColorCodeHex(primaryColorButton,"background-color"),"#834f97");
        Assert.assertEquals(Browser.getText(clientLogo), corporateBrandingPage.extractFileName(filePath_clientLogo));
        Assert.assertEquals(Browser.getText(clientBanner), corporateBrandingPage.extractFileName(filePath_clientBanner));
        Assert.assertEquals(Browser.getText(logoBanner), corporateBrandingPage.extractFileName(filePath_loginBanner));
    }
}
