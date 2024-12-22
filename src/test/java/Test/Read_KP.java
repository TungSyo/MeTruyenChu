package Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import Page.PageRead;
import io.appium.java_client.android.AndroidDriver;
import io.netty.handler.timeout.TimeoutException;
import Report.Extend_Report;

@Test
public class Read_KP {

    public WebDriver driver;

    public void ReadKP() throws MalformedURLException, InterruptedException {
        // Bắt đầu báo cáo
        Extend_Report.startReport();
        Extend_Report.startTest("Read Story KP");

        AndroidDriver driver = startApp(); // Khởi động ứng dụng
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageRead pageRead = new PageRead(driver); // Khởi tạo đối tượng PageRead

        try {
            // Chờ và nhấn vào "Khám Phá"
            wait.until(ExpectedConditions.visibilityOf(pageRead.icKhamPha));
            pageRead.icKhamPha.click();
            Extend_Report.logPass("Clicked on 'Khám Phá' successfully.");

            // Chờ và nhấn vào "Đọc Truyện"
            wait.until(ExpectedConditions.visibilityOf(pageRead.BtnDoc));
            pageRead.BtnDoc.click();
            Extend_Report.logPass("Clicked on 'Đọc'.");

            // Đánh dấu chương
            wait.until(ExpectedConditions.visibilityOf(pageRead.BtnDT));
            pageRead.BtnDT.click();
            Extend_Report.logPass("Clicked on 'Đọc Truyện'.");

            // Chờ thông báo đánh dấu chương
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@content-desc='ĐÃ HIỂU']")));
                Extend_Report.logPass("Chapter marked successfully!");
            } catch (TimeoutException e) {
                Extend_Report.logFail("Failed to find chapter marking notification.");
            }

            // Hoàn thành
            wait.until(ExpectedConditions.visibilityOf(pageRead.BtnDH));
            pageRead.BtnDH.click();
            Extend_Report.logPass("Clicked on 'Đã Hiểu'.");

        } catch (Exception e) {
            Extend_Report.logFail("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Đóng driver và kết thúc báo cáo
            driver.quit();
            Extend_Report.endReport();
        }
    }

    public static AndroidDriver startApp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:deviceName", "emulator-5554");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "com.novelfever.app.android");
        capabilities.setCapability("appium:appActivity", "com.example.novelfeverx.MainActivity");
        capabilities.setCapability("noReset", false);

        // Khởi tạo và trả về AndroidDriver
        return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
}
