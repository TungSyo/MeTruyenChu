package Base;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;

public class BaseTest {
	public AndroidDriver driver;

	@BeforeClass
//	public void setUp() throws MalformedURLException {
//		DesiredCapabilities caps = new DesiredCapabilities();
//		caps.setCapability("platformName", "Android");
//		caps.setCapability("deviceName", "emulator-5554"); // Thay thế bằng tên thiết bị của bạn
//		caps.setCapability("appPackage", "com.novelfever.app.android");
//		caps.setCapability("appActivity", "com.example.novelfeverx.MainActivity");
//		caps.setCapability("noReset", false);
//
//		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
//	}

	@AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            resetApp(); // Gọi resetApp trước khi đóng driver
//            driver.quit();
//        }
//    }
	public void resetApp() {
		try {
			// Lệnh ADB để reset ứng dụng
			Process exec = Runtime.getRuntime().exec("adb shell pm clear com.novelfever.app.android");
			System.out.println("App reset successfully.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to reset app.");
		}
	}
}
