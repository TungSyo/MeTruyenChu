//package Base;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.time.Duration;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.AndroidElement;
//
//public class DriverManager {
//
//    private AndroidDriver<AndroidElement> driver;
//
//    // Thay đổi cấu hình thiết bị Android
//    private String deviceName = "29f271b80c3f7ece";
//    private String platformVersion = "11.0";
//    private String appPackage = "com.example.app";
//    private String appActivity = "com.example.app.MainActivity";
//
//    public AndroidDriver<AndroidElement> initDriver() throws MalformedURLException {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        
//        capabilities.setCapability("deviceName", deviceName);
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("platformVersion", platformVersion);
//        capabilities.setCapability("automationName", "UiAutomator2");
//        capabilities.setCapability("appPackage", appPackage);
//        capabilities.setCapability("appActivity", appActivity);
//        capabilities.setCapability("noReset", true);
//
//        // Khởi tạo AndroidDriver với URL của Appium Server
//        driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
//        
//        // Cấu hình timeout
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//        
//        return driver;
//    }
//}
