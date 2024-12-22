package Report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;

public class Extend_Report {
    private static ExtentReports extent; // Singleton cho báo cáo tổng
    private static ExtentTest test; // Báo cáo từng test case

    // Khởi tạo báo cáo tổng
    public static void startReport() {
        if (extent == null) {
            String baseFileName = "extentReport";
            String fileExtension = ".html";
            int fileCounter = 1;
            String fileName = baseFileName + fileCounter + fileExtension;
            
            // Increment the file name until we find a non-existing file
            while (new File(fileName).exists()) {
                fileCounter++;
                fileName = baseFileName + fileCounter + fileExtension;
            }

            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);

            System.out.println("Report initialized: " + fileName);
        }
    }

    // Bắt đầu một test case mới
    public static void startTest(String testName) {
        if (extent == null) {
            startReport(); // Nếu báo cáo tổng chưa được khởi tạo, khởi tạo trước
        }
        test = extent.createTest(testName);
        System.out.println("Test started: " + testName);
    }
    
    public static void logPass(String message) {
        if (test != null) {
            test.log(Status.PASS, message);
        } else {
            System.err.println("Test chưa được khởi tạo. Gọi startTest() trước khi ghi log.");
        }
    }
    // Ghi log trạng thái FAIL
    public static void logFail(String message) {
        if (test != null) {
            test.log(Status.FAIL, message);
        } else {
            System.err.println("Test chưa được khởi tạo. Gọi startTest() trước khi ghi log.");
        }
    }

    // Ghi log trạng thái PASS


    // Ghi thông tin (INFO) cho báo cáo
    public static void logInfo(String message) {
        if (test != null) {
            test.log(Status.INFO, message);
        } else {
            System.err.println("Test chưa được khởi tạo. Gọi startTest() trước khi ghi log.");
        }
    }

    // Đính kèm ảnh chụp màn hình vào báo cáo
    public static void attachScreenshot(String screenshotPath) {
        try {
            if (test != null) {
                test.addScreenCaptureFromPath(screenshotPath);
            } else {
                System.err.println("Test chưa được khởi tạo. Gọi startTest() trước khi đính kèm ảnh.");
            }
        } catch (Exception e) {
            System.err.println("Không thể đính kèm ảnh: " + e.getMessage());
        }
    }

    // Kết thúc và xuất báo cáo
    public static void endReport() {
        if (extent != null) {
            extent.flush();
            System.out.println("Report has been successfully generated.");
        } else {
            System.err.println("Báo cáo chưa được khởi tạo. Không thể kết thúc.");
        }
    }
}
