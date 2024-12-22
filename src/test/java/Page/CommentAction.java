package Page;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Page.PageComment;
import Page.PageRead;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import io.netty.handler.timeout.TimeoutException;
import Report.Extend_Report;

public class CommentAction {
	public AndroidDriver driver;
	Actions actions;
	public PageComment pageComment;

	public CommentAction(AndroidDriver driver) {
		this.driver = driver;
		this.pageComment = new PageComment(driver);
	}

	public void CommentSTR(String Coment,String Result) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		Extend_Report.startTest("Test started : / " + Coment + " --- " + Result);
		
		try {
			wait.until(ExpectedConditions.visibilityOf(pageComment.icTaiKhoan)).click();
			Extend_Report.logInfo("Đã mở tab tài khoản.");

			// Chờ cho phần tử txtLogin xuất hiện và click vào nó
			wait.until(ExpectedConditions.visibilityOf(pageComment.txtLogin));
			pageComment.txtLogin.click();
			Extend_Report.logInfo("Đã nhấn vào ô đăng nhập.");

			wait.until(ExpectedConditions.visibilityOf(pageComment.txtEmail));
			pageComment.txtEmail.click();
			pageComment.txtEmail.sendKeys("Tungtet2003@gmail.com");
			Extend_Report.logInfo("Đã nhập email.");

			// Nhập Password
			wait.until(ExpectedConditions.visibilityOf(pageComment.txtPass));
			pageComment.txtPass.click();
			pageComment.txtPass.sendKeys("s2kutebaby");
			Extend_Report.logInfo("Đã nhập mật khẩu.");

			// Click vào nút đăng nhập
			pageComment.btnLogin.click();
			Extend_Report.logInfo("Đã nhấn đăng nhập.");
			Thread.sleep(1000); // Chờ để hoàn tất đăng nhập

			wait.until(ExpectedConditions.visibilityOf(pageComment.BtnDongY));
			pageComment.BtnDongY.click();
			Extend_Report.logInfo("Đã nhấn nút đồng ý.");

			clickAtCoordinates(1000, 266);

			wait.until(ExpectedConditions.visibilityOf(pageComment.DanhDau));
			pageComment.DanhDau.click();
			Extend_Report.logInfo("Đã mở mục đánh dấu.");

			wait.until(ExpectedConditions.visibilityOf(pageComment.BtnDY));
			pageComment.BtnDY.click();
			Extend_Report.logInfo("Đã nhấn nút đồng ý.");

			wait.until(ExpectedConditions.visibilityOf(pageComment.Story1));
			pageComment.Story1.click();
			Extend_Report.logInfo("Đã chọn 1 truyện .");

			pageComment.BtnCmt.click();
			Extend_Report.logInfo("Đã mở phần bình luận.");

			pageComment.TxtCmt.click();
			pageComment.TxtCmt.sendKeys(Coment);
			Extend_Report.logInfo("Đã nhập bình luận: " + Coment);

			clickAtCoordinates(1000, 1464);
			Extend_Report.logInfo("Đã nhấn vào tọa độ để gửi bình luận.");

			Extend_Report.logPass("CommentSTR hoàn thành thành công.");
		} catch (Exception e) {
			Extend_Report.logFail("CommentSTR thất bại: " + e.getMessage());
		} finally {
			Extend_Report.endReport();
		}
	}

	public void clickAtCoordinates(int x, int y) {
		new TouchAction<>(driver).tap(PointOption.point(x, y)).perform();
		Extend_Report.logInfo("Đã nhấn vào tọa độ: (" + x + ", " + y + ").");
	}

	public boolean isNotificationMessagePresent(String expectedText) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		boolean isTextFound = false;

		try {
			// Lấy tất cả phần tử hiện tại trên màn hình
			List<WebElement> allElements = driver.findElements(By.xpath("//*"));

			StringBuilder allTexts = new StringBuilder();
			for (WebElement element : allElements) {
				String elementText = element.getText().trim(); // Lấy văn bản từ phần tử
				if (!elementText.isEmpty()) {
					allTexts.append(elementText).append("\n"); // Ghi lại tất cả các văn bản để debug nếu cần

					if (elementText.contains(expectedText)) { // So khớp với expectedText
						isTextFound = true;
						break;
					}
				}
			}

			// Debug: In tất cả văn bản tìm thấy trên màn hình (tuỳ chọn)
			System.out.println("Visible Texts on Screen:\n" + allTexts);

		} catch (Exception e) {
			System.err.println("Lỗi khi kiểm tra thông báo: " + e.getMessage());
		}

		return isTextFound;
	}
}
