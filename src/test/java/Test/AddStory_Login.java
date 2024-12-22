package Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import Page.PageStorage;
import Page.PageLogin;
import Report.Extend_Report;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.TouchAction;
import io.netty.handler.timeout.TimeoutException;

public class AddStory_Login {
    
    @Test
    public void testLogin() throws MalformedURLException, InterruptedException {
        // Start the app and initialize driver
        AndroidDriver driver = startApp();
        Extend_Report.startTest("Add Story and Login");

        // Initialize Page objects
        PageLogin pageLogin = new PageLogin(driver);
        PageStorage pageStorage = new PageStorage(driver);

        // Set up WebDriverWait with a timeout of 15 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Step 1: Click on icTaiKhoan
            wait.until(ExpectedConditions.visibilityOf(pageLogin.icTaiKhoan));
            pageLogin.icTaiKhoan.click();
            Extend_Report.logInfo("Đã nhấn vào biểu tượng tài khoản.");

            // Step 2: Click at specific coordinates (if necessary)
            clickAtCoordinates(driver, 1000, 266);
            Extend_Report.logInfo("Đã nhấn vào tọa độ xác định.");

            // Step 3: Click on txtLogin
            wait.until(ExpectedConditions.visibilityOf(pageLogin.txtLogin));
            pageLogin.txtLogin.click();
            Extend_Report.logInfo("Đã nhấn vào trường đăng nhập.");

            // Step 4: Enter email and password
            wait.until(ExpectedConditions.visibilityOf(pageLogin.txtEmail));
            pageLogin.txtEmail.click();
            pageLogin.txtEmail.sendKeys("Dactung1910@gmail.com");
            Extend_Report.logInfo("Đã nhập email: Dactung1910@gmail.com");

            wait.until(ExpectedConditions.visibilityOf(pageLogin.txtPass));
            pageLogin.txtPass.click();
            pageLogin.txtPass.sendKeys("s2kutebaby");
            Extend_Report.logInfo("Đã nhập mật khẩu.");

            // Step 5: Click on login button
            pageLogin.btnLogin.click();
            Extend_Report.logInfo("Đã nhấn vào nút đăng nhập.");

            // Step 6: Click "DongY" after login
            wait.until(ExpectedConditions.visibilityOf(pageStorage.BtnDongY));
            pageStorage.BtnDongY.click();
            Extend_Report.logInfo("Đã nhấn vào nút Đồng Ý.");

            // Step 7: Click on icKhamPha
            wait.until(ExpectedConditions.visibilityOf(pageLogin.icKhamPha));
            pageLogin.icKhamPha.click();
            Extend_Report.logInfo("Đã nhấn vào biểu tượng Khám Phá.");

            // Step 8: Click "Đọc" button
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@content-desc='Đọc']")));
            WebElement BtnDoc = driver.findElement(By.xpath("//android.widget.Button[@content-desc='Đọc']"));
            BtnDoc.click();
            Extend_Report.logInfo("Đã nhấn vào nút Đọc.");

            // Step 9: Click "Thêm vào Tủ Truyện"
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc='Thêm vào\nTủ Truyện']")));
            WebElement BtnThem = driver.findElement(By.xpath("//android.view.View[@content-desc='Thêm vào\nTủ Truyện']"));
            BtnThem.click();
            Extend_Report.logInfo("Đã nhấn vào nút Thêm vào Tủ Truyện.");

            // Step 10: Check if success message appears for marking chapter
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc='Đã đánh dấu chương']")));
                Extend_Report.logPass("Chương đã được đánh dấu thành công!");
            } catch (TimeoutException e) {
                Extend_Report.logFail("Không tìm thấy thông báo đánh dấu chương.");
            }

            // Step 11: Undo adding story to library (if needed)
            // BtnThem.click(); // Uncomment if undo is required.

        } catch (Exception e) {
            // Handle exceptions gracefully and log errors
            Extend_Report.logFail("Đã xảy ra lỗi: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Quit the driver and end report
            driver.quit();
            Extend_Report.endReport();
        }
    }


    // Method to click at specific screen coordinates (x, y)
    public void clickAtCoordinates(AndroidDriver driver, int x, int y) {
        new TouchAction<>(driver)
            .tap(PointOption.point(x, y))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))  // Optional wait after tap
            .perform();
    }

    // Start the app and initialize the AndroidDriver
    public static AndroidDriver startApp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:deviceName", "29f271b80c3f7ece");  // Use actual device ID or emulator name
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "com.novelfever.app.android");
        capabilities.setCapability("appium:appActivity", "com.example.novelfeverx.MainActivity");
        capabilities.setCapability("noReset", false);

        // Return the AndroidDriver object
        return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
}
