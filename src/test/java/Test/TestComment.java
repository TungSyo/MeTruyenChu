package Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Base.BaseTest;
import Page.CommentAction;
import Report.Extend_Report;
import Utils.Excel_Util;
import Utils.ScreenShotUtil;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class TestComment extends BaseTest {

    public AndroidDriver driver; 

    @DataProvider(name = "commentData")
    public Object[][] getSearchData() throws IOException, InvalidFormatException {
        Excel_Util excel = new Excel_Util("src/test/resources/data/Project4_Data.xlsx", "Comment");
        int rowCount = excel.getRowCount();
        Object[][] data = new Object[rowCount - 1][2];
        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = excel.getCellData(i, "Coment");
            data[i - 1][1] = excel.getCellData(i, "Result");
        }
        return data;
    }

    @Test(dataProvider = "commentData")
    public void testLogin(String Coment, String Result) throws Exception {
        try {
            Excel_Util excelSteps = new Excel_Util("src/test/resources/step/Project4_Step.xlsx", "Comment");
            int rowCount = excelSteps.getRowCount();
            CommentAction commentActions = null;

            for (int i = 1; i < rowCount; i++) {
                String action = excelSteps.getCellData(i, "Action Keyword");
                String page = excelSteps.getCellData(i, "Page");
                String testData = excelSteps.getCellData(i, "Test Data");

                switch (action.toLowerCase()) {
                    case "open app":
                        driver = startApp();
                        break;

                    case "navigate":
                        if (driver != null) {
                           
                            driver.get(testData);
                        }
                        break;

                    case "comment":
                        if (driver != null) {
                        	commentActions = new CommentAction(driver);
                        	commentActions.CommentSTR(Coment, Result);
                        }
                        break;

                    case "istextpresent":
                        if (commentActions != null) {
                            boolean expectedText = commentActions.isNotificationMessagePresent(Result);
                            
                            Thread.sleep(1000);
                            if (expectedText) {
                                Extend_Report.logPass("Coment test passed for: " + Coment + " - " + Result);
                           
                            }
                        }
                        break;

                    case "close app":
                        if (driver != null) {
                            resetApp();
                            driver.quit();
                        }
                        break;

                    default:
                        throw new IllegalArgumentException("Unknown action: " + action);
                }
            }

        } catch (Exception e) {
            if (driver != null) {
                String screenshotPath = ScreenShotUtil.captureScreenshot(driver, "testComent_Exception", "ComentTest");
                Extend_Report.attachScreenshot(screenshotPath);
            }
            Extend_Report.logFail("Test failed for: " + Coment + Result + ". Exception: " + e.getMessage());
            throw e;
        } finally {
            if (driver != null) {
                driver.quit();
            }
            Extend_Report.endReport();
        }
    }

    public void clickAtCoordinates(int x, int y) {
        new TouchAction<>(driver)
            .tap(PointOption.point(x, y))
            .perform();
    }

    public AndroidDriver startApp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:deviceName", "emulator-5554");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "com.novelfever.app.android");
        capabilities.setCapability("appium:appActivity", "com.example.novelfeverx.MainActivity");
        capabilities.setCapability("noReset", false);

        return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
}
