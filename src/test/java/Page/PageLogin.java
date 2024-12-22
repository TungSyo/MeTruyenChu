package Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;

public class PageLogin {

    private AndroidDriver driver;

    // Khai báo các phần tử của trang
    @FindBy(xpath = "//android.widget.Button[@content-desc='Tài Khoản\nTab 4 trong tổng số 4']")
    @CacheLookup
    public WebElement icTaiKhoan;

    @FindBy(xpath = "//android.view.View[@content-desc='Đăng nhập']")
    @CacheLookup
    public WebElement txtLogin;

    @FindBy(xpath = "(//android.widget.Button)[1]")
    @CacheLookup
    public WebElement Back;

    @FindBy(xpath = "(//android.widget.Button)[2]")
    @CacheLookup
    public WebElement Setting;

    @FindBy(xpath = "(//android.widget.EditText)[1]")
    @CacheLookup
    public WebElement txtEmail;

    @FindBy(xpath = "(//android.widget.EditText)[2]")
    @CacheLookup
    public WebElement txtPass;

    @FindBy(xpath = "//android.widget.Button[@content-desc='Đăng nhập']")
    @CacheLookup
    public WebElement btnLogin;

    @FindBy(xpath = "(//android.widget.ImageView)")
    @CacheLookup
    public WebElement Google;

    @FindBy(xpath = "//android.widget.Button[@content-desc='Đăng ký']")
    @CacheLookup
    public WebElement Register;

    @FindBy(xpath = "//android.view.View[@content-desc='Quên mật khẩu']")
    @CacheLookup
    public WebElement ForgotPass;

    @FindBy(xpath = "//android.view.View[@content-desc='Enter Valid Email']")
    @CacheLookup
    public WebElement MessageEmail;

    @FindBy(xpath = "//android.view.View[@content-desc='Mật khẩu must be more than 2 charater']")
    @CacheLookup
    public WebElement MessagePass;

    @FindBy(xpath = "//android.view.View[@content-desc='Lỗi đăng nhập: Thông tin đăng nhập không đúng.']")
    @CacheLookup
    public WebElement FaileLogin;

    @FindBy(xpath = "//android.view.View[@content-desc='Tủ Truyện']")
    @CacheLookup
    public WebElement Infor;

    @FindBy(xpath = "//android.widget.Button[@content-desc='Khám Phá\nTab 2 trong tổng số 4']")
    @CacheLookup
    public WebElement icKhamPha;

    // Constructor khởi tạo trang
    public PageLogin(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);  // Khởi tạo các phần tử của trang
    }
}
