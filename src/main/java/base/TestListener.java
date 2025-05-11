package base;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverManager;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenshotOnFailure();
        saveLogs(result.getThrowable().toString());
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshotOnFailure() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Stacktrace", type = "text/plain")
    public String saveLogs(String message) {
        return message;
    }
}