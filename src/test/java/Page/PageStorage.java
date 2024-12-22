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
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageStorage {
    private AndroidDriver driver;
    // Constructor
  
    
    @FindBy(xpath = "//android.widget.Button[@content-desc='Đọc']")
    @CacheLookup
    public WebElement BtnDoc;

    @FindBy(xpath = "//android.view.View[@content-desc='Thêm vào Tủ Truyện']")
    @CacheLookup
    public WebElement BtnThem;

    @FindBy(xpath = "(//android.view.View[@content-desc])[1]")  // Kiểm tra xpath để đảm bảo đúng
    @CacheLookup
    public WebElement Story;
    @FindBy(xpath = "//android.widget.Button[@content-desc='Đồng ý']")
    @CacheLookup
    public WebElement BtnDongY;
  
    @FindBy(xpath = "//android.widget.Button[@content-desc='Đọc truyện']")
    @CacheLookup
    public WebElement BtnDT;
    
    @FindBy(xpath = "//android.widget.Button[@content-desc='ĐÃ HIỂU']")
    @CacheLookup
    public WebElement BtnDH;
    @FindBy(xpath = "//android.view.View[@content-desc='Bỏ qua']")
    @CacheLookup
    public WebElement BtnBoqua;
    
    public PageStorage(AndroidDriver driver) {
		
 		this.driver = driver;
         PageFactory.initElements(driver, this);
 	}
    }