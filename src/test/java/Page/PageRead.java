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

public class PageRead {
    private AndroidDriver driver;

    @FindBy(xpath = "//android.widget.Button[@content-desc='Khám Phá\nTab 2 trong tổng số 4']")
    @CacheLookup
    public WebElement icKhamPha;

    @FindBy(xpath = "//android.widget.Button[@content-desc='Tài Khoản\nTab 4 trong tổng số 4']")
    @CacheLookup
    public WebElement icTaiKhoan;
    
    @FindBy(xpath = "(//android.widget.Button)[1]")
    @CacheLookup
    public WebElement BtnSearch;
    
    @FindBy(xpath = "//android.view.View[@content-desc='Tìm']")
    @CacheLookup
    public WebElement BtnTim;
    
    @FindBy(xpath = "//android.widget.EditText")
    @CacheLookup
    public WebElement TxtSearch;
 
  
    @FindBy(xpath = "//android.widget.Button[@content-desc='Đọc']")
    @CacheLookup
    public WebElement BtnDoc;
  
    @FindBy(xpath = "//android.widget.Button[@content-desc='Đọc truyện']")
    @CacheLookup
    public WebElement BtnDT;
    
    @FindBy(xpath = "//android.widget.Button[@content-desc='ĐÃ HIỂU']")
    @CacheLookup
    public WebElement BtnDH;
//    
//    @FindBy(xpath = "//android.view.View[@content-desc='#TIÊN HIỆP #DARKHERO Nhất Niệm Vĩnh Hằng Nhĩ Căn 4.6 1314']")
//    @CacheLookup
//    public WebElement Story;


    @FindBy(xpath = "//android.widget.Button[@content-desc='Tủ Truyện\nTab 1 trong tổng số 4']")
    @CacheLookup
    public WebElement BtnTT;
    
    @FindBy(xpath = "//android.view.View[@content-desc='Đánh dấu\nTab 2 trong tổng số 2']")
    @CacheLookup
    public WebElement DanhDau;
    
    @FindBy(xpath = "(//android.widget.Button[@content-desc])[1]")  // Kiểm tra xpath để đảm bảo đúng
    @CacheLookup
    public WebElement BtnQL;
  
    @FindBy(xpath = "//android.widget.Button[@content-desc='Đồng ý']")
    @CacheLookup
    public WebElement BtnDY;

    @FindBy(xpath = "(//android.view.View[@content-desc])[4]")  // Kiểm tra xpath để đảm bảo đúng
    @CacheLookup
    public WebElement Story;
    
    @FindBy(xpath = "(//android.view.View)[12]")  // Kiểm tra xpath để đảm bảo đúng
    @CacheLookup
    public WebElement OptStory;
 
    @FindBy(xpath = "//android.view.View[@content-desc='Xóa khỏi Tủ Truyện']")
    @CacheLookup
    public WebElement Delete;
    
    public PageRead(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
