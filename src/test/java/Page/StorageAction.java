//package Page;
//
//import java.time.Duration;
//
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import io.appium.java_client.android.AndroidDriver;
//
//public class StorageAction {
//    private AndroidDriver driver;
//    private PageStorage pageStorage;
//  
//
//
//    public void AddStory() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//
//     
//            // Chờ cho phần tử Doc xuất hiện và click vào nó
//          wait.until(ExpectedConditions.visibilityOf(pageStorage.BtnDoc));
//            pageStorage.BtnDoc.click();
//
//            // Chờ cho phần tử BtnThem xuất hiện và click vào nó
//            wait.until(ExpectedConditions.visibilityOf(pageStorage.BtnThem));
//            pageStorage.BtnThem.click();
//
//    }
//
//
//
//}
//
