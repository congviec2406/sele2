package wrapper;

import Common.common.Log;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Common.constant.Constant.Timeout;


public class Driver extends DriverFactory {

    public static void navigateTo(String url) {
        getDriver().navigate().to(url);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(String browser, boolean remote) {
        createWebDriver(browser, remote);
    }

    public static void quitDriver() {
        Log.info("Quit driver");
        getDriver().quit();
    }

    public static Alert getAlert() {
        Log.info("Get alert");
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Timeout));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = getDriver().switchTo().alert();
        return alert;
    }


}
