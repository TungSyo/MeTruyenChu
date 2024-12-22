package Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import Page.PageRead;
import io.appium.java_client.android.AndroidDriver;
import io.netty.handler.timeout.TimeoutException;
import Report.Extend_Report;

@Test
public class Read_TK {

	public WebDriver driver;

	public void ReadTK
	() throws MalformedURLException, InterruptedException {
		// Bắt đầu báo cáo
		Extend_Report.startReport();
		Extend_Report.startTest("Read Story TK");

		AndroidDriver driver = startApp(); // Khởi động ứng dụng
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageRead pageRead = new PageRead(driver); // Khởi tạo đối tượng PageRead

		try {
			
			wait.until(ExpectedConditions.visibilityOf(pageRead.icKhamPha));
			pageRead.icKhamPha.click();
			Extend_Report.logPass("Clicked on 'Khám Phá' successfully.");

			pageRead.BtnSearch.click();
			Extend_Report.logInfo("Opened search box.");

			pageRead.BtnTim.click();
			Extend_Report.logPass("Clicked on search button.");

			pageRead.TxtSearch.sendKeys("Nhĩ Căn");
			new Actions(driver).click(pageRead.TxtSearch).sendKeys(Keys.ENTER).perform();
			Extend_Report.logPass("Entered search term 'Nhĩ Căn'.");

			WebElement storyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//android.view.View[contains(@content-desc, '#TIÊN HIỆP') and "
							+ "contains(@content-desc, '#DARKHERO') and contains(@content-desc, 'Nhất Niệm Vĩnh Hằng')]")));
			if (storyElement != null) {
				storyElement.click();
				Extend_Report.logPass("Found and clicked on 'Nhất Niệm Vĩnh Hằng'.");
			} else {
				Extend_Report.logFail("Story 'Nhất Niệm Vĩnh Hằng' not found.");
			}

			// Đánh dấu chương
			wait.until(ExpectedConditions.visibilityOf(pageRead.BtnDT));
			pageRead.BtnDT.click();
			Extend_Report.logPass("Clicked on 'Đọc Truyện'.");

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
