package TestCase.testArchitect;

import Common.constant.Constant;
import PageObject.testArchitect.LoginPage;
import wrapper.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    LoginPage loginPage;

    @Parameters({"browser", "remote"})
    @BeforeMethod
    public void beforeMethodBase(@Optional("chrome") String browser, @Optional("false") boolean remote) {
        System.out.println("Pre-condition");
        Driver.setDriver(browser, remote);
        loginPage = new LoginPage();
    }

    @AfterMethod
    public void afterMethodBase() {
        System.out.println("Post condition: Close TA Dashboard");
        Driver.quitDriver();
    }
}
