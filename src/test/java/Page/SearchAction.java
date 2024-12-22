package Page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Report.Extend_Report;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class SearchAction {
	public AndroidDriver driver;
	Actions actions;
	public PageSearch pageSearch;

    public SearchAction(AndroidDriver driver) {
        this.driver = driver;
        this.pageSearch = new PageSearch(driver);
    }
    public void searchStory(String Search) throws InterruptedException {
        Extend_Report.startTest("Tìm kiếm câu chuyện: " + Search);

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOf(pageSearch.icKhamPha));
            pageSearch.icKhamPha.click();
            Extend_Report.logInfo("Đã nhấn vào biểu tượng Khám Phá.");

            // Chờ cho phần tử txtLogin xuất hiện và click vào nó
            wait.until(ExpectedConditions.visibilityOf(pageSearch.BtnSearch));
            pageSearch.BtnSearch.click();
            Extend_Report.logInfo("Đã nhấn vào nút Tìm kiếm.");

            pageSearch.BtnTim.click();
            Extend_Report.logInfo("Đã nhấn vào nút tìm.");

            Thread.sleep(1000);
            pageSearch.TxtSearch.sendKeys(Search);
            Extend_Report.logInfo("Đã nhập từ khóa tìm kiếm: " + Search);

            Thread.sleep(1000);
            new Actions(driver)
                .click(pageSearch.TxtSearch) // Đảm bảo trường tìm kiếm đang được chọn
                .sendKeys(Keys.ENTER) // Nhấn Enter một lần
                .perform();
            Extend_Report.logInfo("Đã nhấn Enter để tìm kiếm.");

            Extend_Report.logPass("Tìm kiếm câu chuyện thành công: " + Search);
        } catch (Exception e) {
            Extend_Report.logFail("Lỗi khi tìm kiếm câu chuyện: " + e.getMessage());
        } finally {
            Extend_Report.endReport();
        }
    }

   
    public boolean isNotificationSearch(String ResultSearch) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        StringBuilder allTexts = new StringBuilder();  // Để lưu trữ tất cả văn bản nhìn thấy
        boolean isTextFound = false;  // Theo dõi nếu văn bản mong đợi được tìm thấy

        try {
            // Chuyển từ khoá tìm kiếm sang chữ thường để so sánh không phân biệt hoa thường
            String resultSearchLower = ResultSearch.toLowerCase();

            // Chờ toàn bộ màn hình tải (điều chỉnh locator để phù hợp với cấu trúc của ứng dụng)
            List<WebElement> allElements = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*"))
            );

            // Vòng lặp qua tất cả các phần tử và thu thập văn bản của chúng
            for (WebElement element : allElements) {
                String elementText = element.getText().trim();  // Lấy văn bản từ từng phần tử

                if (!elementText.isEmpty()) {
                    allTexts.append(elementText).append("\n");  // Thêm vào log

                    // Chuyển văn bản của phần tử sang chữ thường để so sánh không phân biệt hoa thường
                    if (elementText.toLowerCase().equals(resultSearchLower)) {
                        isTextFound = true;  // Đánh dấu là đã tìm thấy nếu văn bản khớp
                    }
                }
            }
            
            // Ghi lại tất cả văn bản nhìn thấy (hữu ích cho việc gỡ lỗi)
            System.out.println("Visible Texts on Screen:\n" + allTexts.toString());

            // Trả về kết quả kiểm tra văn bản mong đợi
            return isTextFound;  // Trả về true nếu văn bản mong đợi được tìm thấy, ngược lại là false

        } catch (Exception e) {
            System.err.println("Lỗi khi lấy các phần tử trên màn hình: " + e.getMessage());
            return false;  // Trả về false trong trường hợp có lỗi
        }
    }

    }


   
   

