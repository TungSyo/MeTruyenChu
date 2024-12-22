package Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class PageComment {
	@FindBy(xpath = "//android.view.View[@content-desc='Bình Luận\nTab 3 trong tổng số 4']")
	@CacheLookup
	public WebElement BtnCmt;

	@FindBy(xpath = "//android.widget.EditText")
	@CacheLookup
	public WebElement TxtCmt;

	@FindBy(xpath = "android.widget.Button[1]")
	@CacheLookup
	public WebElement BtnQL;
	
	@FindBy(xpath = "//android.widget.Button[@content-desc='Tài Khoản\nTab 4 trong tổng số 4']")
	@CacheLookup
	public WebElement icTaiKhoan;

	@FindBy(xpath = "//android.view.View[@content-desc='Đăng nhập']")
	@CacheLookup
	public WebElement txtLogin;

	@FindBy(xpath = "(//android.widget.EditText)[1]")
	@CacheLookup
	public WebElement txtEmail;

	@FindBy(xpath = "(//android.widget.EditText)[2]")
	@CacheLookup
	public WebElement txtPass;

	@FindBy(xpath = "//android.widget.Button[@content-desc='Đăng nhập']")
	@CacheLookup
	public WebElement btnLogin;

	@FindBy(xpath = "//android.widget.Button[@content-desc='Đọc']")
	@CacheLookup
	public WebElement BtnDoc;
	
	@FindBy(xpath = "//android.view.View[@content-desc='Đánh dấu\nTab 2 trong tổng số 2']")
	@CacheLookup
	public WebElement DanhDau;
	
	@FindBy(xpath = "//android.widget.Button[@content-desc='Đồng ý']")
    @CacheLookup
    public WebElement BtnDY;
	
	@FindBy(xpath = "//android.view.View[@content-desc='Trùng Sinh, Bạn Gái Của Ta Là Yandere']")
	@CacheLookup
	public WebElement Story1;

	@FindBy(xpath = "//android.view.View[@content-desc='Tủ Truyện']")
	@CacheLookup
	public WebElement Infor;

	@FindBy(xpath = "//android.widget.Button[@content-desc='Khám Phá\nTab 2 trong tổng số 4']")
	@CacheLookup
	public WebElement icKhamPha;

	@FindBy(xpath = "//android.widget.Button[@content-desc='Đồng ý']")
	@CacheLookup
	public WebElement BtnDongY;

	public PageComment(AndroidDriver driver) {
		PageFactory.initElements(driver, this); // Khởi tạo các phần tử của trang
	}
}
