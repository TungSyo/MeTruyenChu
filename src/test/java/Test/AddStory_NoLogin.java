package Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import Report.Extend_Report;
import Utils.Excel_Util;
import Utils.ScreenShotUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Page.PageLogin; // Đảm bảo bạn đã có lớp PageLogin
import Page.PageStorage;
import io.appium.java_client.android.AndroidDriver;
import io.netty.handler.timeout.TimeoutException;

public class AddStory_NoLogin {

	public WebDriver driver;

	@Test
	public void testLogin() throws MalformedURLException, InterruptedException {

	    AndroidDriver driver = startApp(); 
	    Extend_Report.startTest("Add Story and Login");

	    PageLogin pageLogin = new PageLogin(driver);
	    PageStorage pageStorage = new PageStorage(driver);

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    
	    // Chờ cho phần tử BtnDoc xuất hiện và click vào nó
	    try {
	        // Step 1: Click on icKhamPha
	        wait.until(ExpectedConditions.visibilityOf(pageLogin.icKhamPha));
	        pageLogin.icKhamPha.click();
	        Extend_Report.logInfo("Đã nhấn vào biểu tượng Khám Phá.");

	        // Step 2: Click "Đọc" button
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@content-desc='Đọc']")));
	        WebElement BtnDoc = driver.findElement(By.xpath("//android.widget.Button[@content-desc='Đọc']"));
	        BtnDoc.click();
	        Extend_Report.logInfo("Đã nhấn vào nút Đọc.");

	        // Step 3: Click "Thêm vào Tủ Truyện"
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc='Thêm vào\nTủ Truyện']")));
	        WebElement BtnThem = driver.findElement(By.xpath("//android.view.View[@content-desc='Thêm vào\nTủ Truyện']"));
	        BtnThem.click();
	        Extend_Report.logInfo("Đã nhấn vào nút Thêm vào Tủ Truyện.");

	        // Step 4: Enter Email
	        wait.until(ExpectedConditions.visibilityOf(pageLogin.txtEmail));
	        pageLogin.txtEmail.click();
	        pageLogin.txtEmail.sendKeys("Dactung1910@gmail.com");
	        Extend_Report.logInfo("Đã nhập email: Dactung1910@gmail.com");

	        // Step 5: Enter Password
	        Thread.sleep(1000);
	        wait.until(ExpectedConditions.visibilityOf(pageLogin.txtPass));
	        pageLogin.txtPass.click();
	        pageLogin.txtPass.sendKeys("s2kutebaby");
	        Extend_Report.logInfo("Đã nhập mật khẩu.");

	        // Step 6: Click on login button
	        pageLogin.btnLogin.click();
	        Extend_Report.logInfo("Đã nhấn vào nút đăng nhập.");

	        // Step 7: Click "Thêm vào Tủ Truyện" again after login
	        BtnThem.click();
	        Extend_Report.logInfo("Đã nhấn vào nút Thêm vào Tủ Truyện lần nữa.");

	        // Step 8: Check for chapter marking success message
	        try {
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc='Đã đánh dấu chương']")));
	            Extend_Report.logPass("Chapter has been successfully marked!");
	        } catch (TimeoutException e) {
	            Extend_Report.logFail("Chapter marking notification not found.");
	        }

	    } catch (Exception e) {
	        // Handle errors and log failure
	        Extend_Report.logFail("Đã xảy ra lỗi: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        if (driver != null) {
	            driver.quit();
	            Extend_Report.logInfo("Đã đóng trình duyệt.");
	        }
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
