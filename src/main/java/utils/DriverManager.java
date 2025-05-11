package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {
    private static WebDriver driver;

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getProperty("browser");
            boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) {
                        firefoxOptions.addArguments("--headless");
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case "chrome":
                default:
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("profile.default_content_setting_values.cookies", 1); // 1=Allow, 2=Block
                    prefs.put("profile.cookie_controls_mode", 0); // 0=Allow all cookies

                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (headless) {
                        chromeOptions.addArguments("--headless");
                    }
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.setExperimentalOption("w3c", true);
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("--disable-extensions");
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("--disable-popup-blocking");
                    chromeOptions.addArguments("--accept-cookies");
                    chromeOptions.addArguments("--incognito");
                    chromeOptions.setExperimentalOption("prefs", prefs);
//                    chromeOptions.addArguments("--disable-default-apps");
//                    chromeOptions.addArguments("--disable-infobars");
//                    chromeOptions.addArguments("--disable-web-security");
//                    chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
//                    chromeOptions.setExperimentalOption("useAutomationExtension", false);
//                    chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//
//// Add this to block all cookies
//                    chromeOptions.setExperimentalOption("profile.default_content_setting_values.cookies", 2);
                    driver = new ChromeDriver(chromeOptions);
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}