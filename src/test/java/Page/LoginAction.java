package Page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Page.PageLogin;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import Report.Extend_Report;

public class LoginAction {
    private AndroidDriver driver;
    private PageLogin pageLogin;

    public LoginAction(AndroidDriver driver) {
        this.driver = driver;
        this.pageLogin = new PageLogin(driver);
    }

    public void login(String User, String Pass) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Extend_Report.startTest("Test started : / " + User + " --- " + Pass);
        try {
            // Chờ cho phần tử icTaiKhoan xuất hiện và click vào nó
            wait.until(ExpectedConditions.visibilityOf(pageLogin.icTaiKhoan));
            pageLogin.icTaiKhoan.click();
            Extend_Report.logInfo("Đã nhấn vào tab tài khoản.");

            // Chờ cho phần tử txtLogin xuất hiện và click vào nó
            wait.until(ExpectedConditions.visibilityOf(pageLogin.txtLogin));
            pageLogin.txtLogin.click();
            Extend_Report.logInfo("Đã nhấn vào ô đăng nhập.");
            Thread.sleep(1000);

            // Bắt đầu nhập Email
            wait.until(ExpectedConditions.visibilityOf(pageLogin.txtEmail));
            pageLogin.txtEmail.click();
            pageLogin.txtEmail.sendKeys(User);
            Extend_Report.logInfo("Đã nhập email: " + User);
            Thread.sleep(1000);

            // Nhập mật khẩu
            wait.until(ExpectedConditions.visibilityOf(pageLogin.txtPass));
            pageLogin.txtPass.click();
            pageLogin.txtPass.sendKeys(Pass);
            Extend_Report.logInfo("Đã nhập mật khẩu.");

            // Click vào nút đăng nhập
            pageLogin.btnLogin.click();
            Extend_Report.logInfo("Đã nhấn nút đăng nhập.");
            Thread.sleep(1000); // Chờ để quá trình đăng nhập hoàn tất

            Extend_Report.logPass("Login thành công với tài khoản: " + User);
        } catch (Exception e) {
            Extend_Report.logFail("Đăng nhập thất bại: " + e.getMessage());
        } finally {
            Extend_Report.endReport();
        }
    }

    public void clickAtCoordinates(int x, int y) {
        Extend_Report.startTest("Click tại tọa độ: (" + x + ", " + y + ")");
        try {
            new TouchAction<>(driver)
                .tap(PointOption.point(x, y))
                .perform();
            Extend_Report.logInfo("Đã nhấn vào tọa độ: (" + x + ", " + y + ").");
            Extend_Report.logPass("Click thành công.");
        } catch (Exception e) {
            Extend_Report.logFail("Click thất bại tại tọa độ: (" + x + ", " + y + "). Lỗi: " + e.getMessage());
        } finally {
            Extend_Report.endReport();
        }
    }

    public boolean isNotificationMessagePresent(String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        boolean isTextFound = false;

        try {
            // Tìm tất cả phần tử trên màn hình
            List<WebElement> allElements = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*"))
            );

            for (WebElement element : allElements) {
                String elementText = element.getText().trim();  // Lấy text và loại bỏ khoảng trắng

                if (!elementText.isEmpty()) {
                    // Kiểm tra nếu văn bản chứa expectedText
                    if (elementText.contains(expectedText)) {
                        isTextFound = true;
                        break;  // Thoát sớm khi tìm thấy
                    }
                }
            }
        } catch (Exception e) {
            isTextFound = false;
        }

        return isTextFound;
    }


    public boolean clickGoogleLoginButton() {
        Extend_Report.startTest("Click nút đăng nhập Google");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(pageLogin.Google));
            pageLogin.Google.click();
            Extend_Report.logInfo("Đã nhấn nút đăng nhập Google.");

            boolean isGooglePage = ExpectedConditions.urlContains("accounts.google.com").apply(driver) != null;
            if (isGooglePage) {
                Extend_Report.logPass("Đã chuyển hướng tới trang Google Login.");
            } else {
                Extend_Report.logFail("Không chuyển hướng tới trang Google Login.");
            }
            return isGooglePage;
        } catch (Exception e) {
            Extend_Report.logFail("Thất bại khi nhấn nút đăng nhập Google: " + e.getMessage());
            return false;
        } finally {
            Extend_Report.endReport();
        }
    }

    public void forgotPasswordLinkVisibleOrNot() {
        Extend_Report.startTest("Kiểm tra liên kết quên mật khẩu");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(pageLogin.ForgotPass));
            pageLogin.ForgotPass.click();
            Extend_Report.logPass("Đã nhấn vào liên kết quên mật khẩu.");
        } catch (Exception e) {
            Extend_Report.logFail("Không tìm thấy hoặc không thể nhấn vào liên kết quên mật khẩu: " + e.getMessage());
        } finally {
            Extend_Report.endReport();
        }
    }
}
