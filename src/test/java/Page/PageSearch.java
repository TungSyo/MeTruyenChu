package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageSearch {
    private AndroidDriver driver;

    @FindBy(xpath = "//android.widget.Button[@content-desc='Khám Phá\nTab 2 trong tổng số 4']")
    @CacheLookup
    public WebElement icKhamPha;

    @FindBy(xpath = "(//android.widget.Button)[1]")
    @CacheLookup
    public WebElement BtnSearch;
    
    @FindBy(xpath = "//android.view.View[@content-desc='Tìm']")
    @CacheLookup
    public WebElement BtnTim;
    
    @FindBy(xpath = "//android.widget.Button[@content-desc='Quay lại']")
    @CacheLookup
    public WebElement BtnQuayLai;

    @FindBy(xpath = "//android.widget.EditText")
    @CacheLookup
    public WebElement TxtSearch;
 
    @FindBy(xpath = "//android.view.View[@content-desc='Không tìm thấy']")
    @CacheLookup
    public WebElement Faile;

    public PageSearch(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    // Phương thức để click vào các nút trong View có content-desc là "Tất cả"
//    public void clickButtonsInView() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        
//        // Chờ cho View có content-desc là "Tất cả" xuất hiện
//        WebElement viewElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc='Tất cả']")));
//        
//        // Tìm tất cả các nút trong View
//        List<WebElement> buttons = viewElement.findElements(By.xpath(".//android.widget.Button"));
//
//        // Kiểm tra số lượng nút và thực hiện click
//        if (buttons.size() >= 2) {
//            buttons.get(0).click(); // Click nút đầu tiên
//            buttons.get(1).click(); // Click nút thứ hai
//        } else {
//            System.out.println("Không đủ nút để tương tác.");
//        }
//    }
}
