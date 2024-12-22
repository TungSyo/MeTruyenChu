package Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;
import Report.Extend_Report;
import Utils.Excel_Util;
import Utils.ScreenShotUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Page.PageLogin;  // Đảm bảo bạn đã có lớp PageLogin
import Page.PageRead;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.netty.handler.timeout.TimeoutException;

public class Read_TT {

    private AndroidDriver driver;  // Ensure this is AndroidDriver

    @Test
    public void testLogin() throws MalformedURLException, InterruptedException {
        // Bắt đầu báo cáo
        Extend_Report.startReport();
        Extend_Report.startTest("Test Login and Read");

        driver = startApp();  // Khởi động ứng dụng
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        PageLogin pageLogin = new PageLogin(driver);  
        PageRead pageRead = new PageRead(driver); 
        
        try {
            // Chờ cho phần tử icTaiKhoan xuất hiện và click vào nó
            wait.until(ExpectedConditions.visibilityOf(pageLogin.icTaiKhoan));
            pageLogin.icTaiKhoan.click();
            Extend_Report.logPass("Click vào icTaiKhoan thành công");

            // Chờ cho phần tử txtLogin xuất hiện và click vào nó
            wait.until(ExpectedConditions.visibilityOf(pageLogin.txtLogin));
            pageLogin.txtLogin.click();
            Extend_Report.logPass("Click vào txtLogin thành công");

            // Bắt đầu nhập Email
            wait.until(ExpectedConditions.visibilityOf(pageLogin.txtEmail));
            pageLogin.txtEmail.click();
            pageLogin.txtEmail.sendKeys("Dactung1910@gmail.com");
            Extend_Report.logPass("Nhập email: Dactung1910@gmail.com");

            // Nhập mật khẩu
            wait.until(ExpectedConditions.visibilityOf(pageLogin.txtPass));
            pageLogin.txtPass.click();
            pageLogin.txtPass.sendKeys("s2kutebaby");
            Extend_Report.logPass("Nhập password");

            // Click vào nút đăng nhập
            pageLogin.btnLogin.click();
            Extend_Report.logPass("Clicked on login button");

            // Tiếp tục các bước khác, ghi nhận các hành động như ở trên
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@content-desc='Đồng ý']")));
            WebElement btnDongY = driver.findElement(By.xpath("//android.widget.Button[@content-desc='Đồng ý']"));
            btnDongY.click();
            Extend_Report.logPass("Clicked on Đồng ý button");
            
            wait.until(ExpectedConditions.visibilityOf(pageRead.icKhamPha));
            pageRead.icKhamPha.click();
            Extend_Report.logPass("Clicked on 'Khám Phá' successfully.");

            // Chờ và nhấn vào "Đọc Truyện"
            wait.until(ExpectedConditions.visibilityOf(pageRead.BtnDoc));
            pageRead.BtnDoc.click();
            Extend_Report.logPass("Clicked on 'Đọc'.");
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc='Thêm vào\nTủ Truyện']")));
            WebElement BtnThem = driver.findElement(By.xpath("//android.view.View[@content-desc='Thêm vào\nTủ Truyện']"));
            BtnThem.click();  
            // Kiểm tra thông báo đánh dấu chương
            try {
                WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc='Đã đánh dấu chương']")));
                String contentDescription = notification.getAttribute("content-desc");
                if (contentDescription.equals("Thêm vào tủ truyện")) {
                    Extend_Report.logInfo("Đã đánh dấu chương, nhưng không cần hành động gì.");
                } else if (contentDescription.equals("Xóa khỏi tủ truyện")) {
                    notification.click();
                    Extend_Report.logPass("Clicked to remove chapter from bookshelf.");
                }
            } catch (TimeoutException | NoSuchElementException e) {
                Extend_Report.logFail("Không tìm thấy thông báo đánh dấu chương.");
            }
            clickAtCoordinates(1000, 266);
            clickAtCoordinates(57, 125);
            
            pageRead.BtnTT.click();
            Extend_Report.logPass("Clicked on BtnTT");
            
        	wait.until(ExpectedConditions.elementToBeClickable(pageRead.DanhDau)).click();
			Extend_Report.logPass("Clicked on 'DanhDau'.");

			wait.until(ExpectedConditions.elementToBeClickable(pageRead.BtnDY)).click();
			Extend_Report.logPass("Clicked on 'BtnDY'.");
            
            pageRead.Story.click();
            Extend_Report.logPass("Clicked on 'Story'.");
            
            pageRead.BtnDT.click();
            Extend_Report.logPass("Clicked on 'BtnDT'.");
            
            wait.until(ExpectedConditions.visibilityOf(pageRead.BtnDH));
            pageRead.BtnDH.click();
            Extend_Report.logPass("Clicked on 'Đã Hiểu'.");
            // Cuối cùng kết thúc báo cáo
            Extend_Report.endReport();

        } catch (Exception e) {
            Extend_Report.logFail("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
            Extend_Report.endReport();
        }
    }

    // Thêm @DataProvider để cung cấp tọa độ
    @DataProvider(name = "coordinatesProvider")
    public Object[][] coordinatesProvider() {
        return new Object[][] { { 57, 125 },{ 1000, 266 } // Tọa độ 1
        };
    }

    // Bỏ @Test khỏi phương thức clickAtCoordinates
    @Test(dataProvider = "coordinatesProvider")
    public void clickAtCoordinates(int x, int y) {
        try {
            new TouchAction<>(driver).tap(PointOption.point(x, y)).perform();
            Extend_Report.logPass("Clicked at coordinates: " + x + ", " + y);
        } catch (Exception e) {
            Extend_Report.logFail("Failed to click at coordinates: " + x + ", " + y);
            e.printStackTrace();
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
