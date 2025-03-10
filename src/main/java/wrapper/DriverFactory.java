package wrapper;

import Common.constant.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public static void createWebDriver(String browser, boolean remote) {
        if (remote) {
            switch (browser) {
                case "chrome":
                    createRemoteChromeDriver();
                    break;
                case "firefox":
                    createRemoteFirefoxDriver();
                    break;
            }
        } else {
            switch (browser) {
                case "chrome":
                    createLocalChromeDriver();
                    break;
                case "firefox":
                    createLocalFirefoxDriver();
                    break;
            }
        }
    }

    private static void createLocalChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver.set(new ChromeDriver(options));
    }

    private static void createLocalFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1920", "--height=1080");
        driver.set(new FirefoxDriver(options));
    }

    private static void createRemoteChromeDriver() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName("chrome");
            driver.set(new RemoteWebDriver(new URL(Constant.HUB_URL), caps));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void createRemoteFirefoxDriver() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName("firefox");
            driver.set(new RemoteWebDriver(new URL(Constant.HUB_URL), caps));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
