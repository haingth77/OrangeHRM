package orangeHRM;

import common.Browser;
import common.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.CorporateBrandingPage_KeyEvent;

import static common.Browser.waitElement;

public class CorporateBrandingTest_KeyEvent extends TestBase {
    By primaryColorButton = By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters']/div/div/div/div[@class='oxd-color-input-preview']");
    By secondaryColorButton = By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters --offset-column-3']/div/div/div/div[@class='oxd-color-input-preview']");
    By primaryGradientColor1Button = By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters --offset-row-3']/div/div/div/div[@class='oxd-color-input-preview']");
    By primaryGradientColor2Button = By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters --offset-row-3 --offset-column-3']/div/div/div/div[@class='oxd-color-input-preview']");
    By notificationTitle = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-title oxd-toast-content-text']");
    By notificationContent = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']");
    By notificationFrame = By.xpath("//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']");
    By hexColorCodeTextbox = By.xpath("//div[@role='alert']/input[@class='oxd-input oxd-input--active']");
    By clientLogoBrowseButton = By.xpath("//div[@class='oxd-file-div oxd-file-div--active']/div[@class='oxd-file-button']");
    By clientBannerBrowseButton = By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']/div[2]/div/div/div/div/div[@class='oxd-file-button']");
    By loginBannerBrowseButton = By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']/div[3]/div/div/div/div/div[@class='oxd-file-button']");
    By resetButton = By.xpath("//div[@class='orangehrm-actions-group']/button[@type='button']");
    By submitButton = By.xpath("//div[@class='orangehrm-actions-group']/button[@type='submit']");
    By clientLogo = By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']/div[1]/div[1]/div[2]/div[1]/div[1]/p[@class='oxd-text oxd-text--p orangehrm-file-name']");
    By clientBanner = By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']/div[2]/div[1]/div[2]/div[1]/div[1]/p[@class='oxd-text oxd-text--p orangehrm-file-name']");
    By logoBanner = By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']/div[3]/div[1]/div[2]/div[1]/div[1]/p[@class='oxd-text oxd-text--p orangehrm-file-name']");
    By mainMenuButton = By.xpath("//div[@class='oxd-main-menu-search']/button[@class='oxd-icon-button oxd-main-menu-button']");
    By topbarHeader = By.xpath("//header[@class='oxd-topbar']/div[@class='oxd-topbar-header']");
    String filePath_clientLogo = "D:\\HaiNguyen_Private\\IntelliJ\\OrangeHRM\\src\\test\\resources\\pikachu.jpg";
    String filePath_clientBanner = "D:\\HaiNguyen_Private\\IntelliJ\\OrangeHRM\\src\\test\\resources\\pikachu_clientBanner.jpg";
    String filePath_loginBanner = "D:\\HaiNguyen_Private\\IntelliJ\\OrangeHRM\\src\\test\\resources\\pikachu_loginBanner.jpg";
    CorporateBrandingPage_KeyEvent corporateBrandingPage_keyEvent;

    @Test
    public void CorporateBrandingTest_KeyEvent() throws InterruptedException {
        corporateBrandingPage_keyEvent = new CorporateBrandingPage_KeyEvent();
        Browser.visit("https://opensource-demo.orangehrmlive.com/web/index.php/admin/addTheme");
        Browser.login("Admin", "admin123");
        Browser.waitElement(resetButton);
        Thread.sleep(500);
        Browser.click(resetButton);
        Thread.sleep(1000);
        corporateBrandingPage_keyEvent.inputColorCode(primaryColorButton, hexColorCodeTextbox, "#834f97");
        corporateBrandingPage_keyEvent.inputColorCode(secondaryColorButton, hexColorCodeTextbox, "#9fa7cf");
        corporateBrandingPage_keyEvent.inputColorCode(primaryGradientColor1Button, hexColorCodeTextbox, "#7be0f4");
        corporateBrandingPage_keyEvent.inputColorCode(primaryGradientColor2Button, hexColorCodeTextbox, "#d691d2");
        Browser.uploadFile(clientLogoBrowseButton, filePath_clientLogo);
        Browser.uploadFile(clientBannerBrowseButton, filePath_clientBanner);
        Browser.uploadFile(loginBannerBrowseButton, filePath_loginBanner);
        Browser.click(submitButton);
        waitElement(notificationFrame);
        Assert.assertEquals(Browser.getText(notificationTitle), "Success");
        Assert.assertEquals(Browser.getText(notificationContent), "Successfully Saved");
        Browser.waitElement(primaryColorButton);
        Browser.waitElement(clientLogo);
        Assert.assertEquals(Browser.checkColorCodeHex(primaryColorButton,"background-color"),"#834f97");
        Assert.assertEquals(Browser.checkColorCodeHex(secondaryColorButton,"background-color"),"#9fa7cf");
        Assert.assertEquals(Browser.checkColorCodeHex(primaryGradientColor1Button,"background-color"),"#7be0f4");
        Assert.assertEquals(Browser.checkColorCodeHex(primaryGradientColor2Button,"background-color"),"#d691d2");
        Assert.assertEquals(Browser.checkColorCodeHex(submitButton,"background-color"),"#9fa7cf");
        Assert.assertEquals(Browser.checkColorCodeHex(mainMenuButton,"background-color"),"#834f97");
        Assert.assertEquals(Browser.checkColorCodeHex(topbarHeader,"background-color"),"#7be0f4");
        Assert.assertEquals(Browser.getText(clientLogo), corporateBrandingPage_keyEvent.extractFileName(filePath_clientLogo));
        Assert.assertEquals(Browser.getText(clientBanner), corporateBrandingPage_keyEvent.extractFileName(filePath_clientBanner));
        Assert.assertEquals(Browser.getText(logoBanner), corporateBrandingPage_keyEvent.extractFileName(filePath_loginBanner));
    }
}
