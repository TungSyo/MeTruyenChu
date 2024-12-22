package Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Page.PageLogin;
import Page.PageRead;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.netty.handler.timeout.TimeoutException;
import Report.Extend_Report;
import Utils.Excel_Util;
import Utils.ScreenShotUtil;
@Test
public class RemoveStory {

	private AndroidDriver driver;
	private String contentDescAbove;

	public RemoveStory() throws MalformedURLException, InterruptedException {
		// Bắt đầu báo cáo
		Extend_Report.startReport();
		Extend_Report.startTest("Remove Story Test");

		driver = startApp(); // Khởi động ứng dụng
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		PageLogin pageLogin = new PageLogin(driver);
		PageRead pageRead = new PageRead(driver);

		try {
			// Chờ và click vào 'icTaiKhoan'
			wait.until(ExpectedConditions.visibilityOf(pageLogin.icTaiKhoan));
			pageLogin.icTaiKhoan.click();
			Extend_Report.logPass("Clicked on 'icTaiKhoan' successfully.");

			// Chờ và click vào 'txtLogin'
			wait.until(ExpectedConditions.visibilityOf(pageLogin.txtLogin));
			pageLogin.txtLogin.click();
			Extend_Report.logPass("Clicked on 'txtLogin'.");

			// Nhập thông tin đăng nhập
			wait.until(ExpectedConditions.visibilityOf(pageLogin.txtEmail));
			pageLogin.txtEmail.click();
			pageLogin.txtEmail.sendKeys("Dactung1910@gmail.com");
			Extend_Report.logPass("Entered email.");

			wait.until(ExpectedConditions.visibilityOf(pageLogin.txtPass));
			pageLogin.txtPass.click();
			pageLogin.txtPass.sendKeys("s2kutebaby");
			Extend_Report.logPass("Entered password.");

			// Click vào nút đăng nhập
			pageLogin.btnLogin.click();
			Extend_Report.logPass("Clicked on 'btnLogin'.");

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.widget.Button[@content-desc='Đồng ý']")));
			WebElement btnDongY = driver.findElement(By.xpath("//android.widget.Button[@content-desc='Đồng ý']"));
			btnDongY.click();
			Extend_Report.logPass("Clicked on 'Đồng ý'.");

			Thread.sleep(3000);
			clickAtCoordinates(1000, 266); // Chuyển tọa độ vào đây
			Thread.sleep(1000);

			// Thay thế Thread.sleep(1000) bằng WebDriverWait
			wait.until(ExpectedConditions.elementToBeClickable(pageRead.DanhDau)).click();
			Extend_Report.logPass("Clicked on 'DanhDau'.");

			wait.until(ExpectedConditions.elementToBeClickable(pageRead.BtnDY)).click();
			Extend_Report.logPass("Clicked on 'BtnDY'.");

			Thread.sleep(1000);
			clickAtCoordinates(1020, 350); // Chuyển tọa độ vào đây
			Thread.sleep(1000);

			// Xử lý phần tử và kiểm tra nội dung
			WebElement targetView = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc='Tải truyện']")));

			WebElement aboveView = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[contains(@content-desc, 'Tải truyện')]/preceding-sibling::android.view.View[1]")));

			contentDescAbove = aboveView.getAttribute("content-desc");
			Extend_Report.logPass("Extracted content-desc from above element: " + contentDescAbove);

			// Thực hiện thao tác xóa
			WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(pageRead.Delete));
			
			Thread.sleep(1000);
			deleteButton.click();
			Extend_Report.logPass("Clicked on 'Delete'.");
			
			if (contentDescAbove != null && !contentDescAbove.isEmpty()) {
			    // Tạo WebDriverWait với thời gian chờ là 10 giây
			    WebDriverWait wait10Seconds = new WebDriverWait(driver, Duration.ofSeconds(10));

			    // Kiểm tra xem phần tử đã bị xóa có còn tồn tại tại XPath 1
			    try {
			        // Sử dụng ExpectedConditions để kiểm tra phần tử theo XPath 1
			        WebElement checkElement = wait10Seconds.until(ExpectedConditions.visibilityOfElementLocated(
			            By.xpath("//android.view.View[@content-desc='" + contentDescAbove + "']")
			        ));

			        // Lấy giá trị content-desc thực tế của phần tử và so sánh với contentDescAbove
			        String actualContentDesc = checkElement.getAttribute("content-desc");

			        // So sánh nếu content-desc của phần tử thực tế giống hệt contentDescAbove
			        if (contentDescAbove.equals(actualContentDesc)) {
			            // Nếu phần tử còn tồn tại và giá trị content-desc giống hệt, báo lỗi (vì nó không nên xuất hiện sau khi bị xóa)
			            Extend_Report.logFail("Kiểm tra thất bại: Phần tử có contentDescAbove vẫn còn tại vị trí trước đó.");
			        } else {
			            // Nếu content-desc không khớp, báo thành công (vì đã bị thay đổi hoặc không còn tồn tại)
			            Extend_Report.logPass("Kiểm tra thành công: Phần tử với contentDescAbove không còn tồn tại tại vị trí trước đó.");
			        }
			        
			    } catch (TimeoutException e) {
			        // Nếu không tìm thấy phần tử trong vòng 10 giây, coi như phần tử đã bị xóa và không còn ở vị trí XPath
			        Extend_Report.logPass("Kiểm tra thành công: Phần tử với contentDescAbove không còn tồn tại tại vị trí trước đó.");
			    } catch (NoSuchElementException e) {
			        // Nếu không tìm thấy phần tử, coi như phần tử đã bị xóa và không còn ở vị trí XPath
			        Extend_Report.logPass("Kiểm tra thành công: Phần tử với contentDescAbove không còn tồn tại tại vị trí trước đó.");
			    }
			} else {
			    Extend_Report.logFail("contentDescAbove là null hoặc rỗng. Kiểm tra không thể tiếp tục.");
			}

		} catch (Exception e) {
			Extend_Report.logFail("An error occurred: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Đóng driver và kết thúc báo cáo
			driver.quit();
			Extend_Report.endReport();
		}
	}

	// Thêm @DataProvider để cung cấp tọa độ
	@DataProvider(name = "coordinatesProvider")
	public Object[][] coordinatesProvider() {
		return new Object[][] { { 1000, 266 }, // Tọa độ 1
				{ 1020, 350 } // Tọa độ 2
		};
	}

	// Bỏ @Test khỏi phương thức clickAtCoordinates
	@Test(dataProvider = "coordinatesProvider")
	public void clickAtCoordinates(int x, int y) {
		System.out.println("Attempting to click at coordinates: " + x + ", " + y);

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

		return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}
}
