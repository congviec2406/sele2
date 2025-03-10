package TestCase.testArchitect;

import Common.common.Log;
import Common.constant.Constant;
import DataObject.dataObject.Account;
import wrapper.CustomAlert;
import PageObject.testArchitect.DashBoardPage;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    @Description("Verify that user can login specific repository successfully via Dashboard login page with correct credentials")
    public void TC01() {
        Account account = new Account(Constant.UserAd, Constant.BLANK);
        Log.info("Step 1. Navigate to Dashboard login page");
        loginPage.openLoginPage()
                .loginSuccess(account);
        Log.info("Step 4. Verify that Dashboard Mainpage appears");
        Assert.assertTrue(loginPage.isDisplayedHomePage(), "Dashboard Main Page does not appear");
        loginPage.logOut();
    }

    @Test
    @Description("Verify that user fails to login specific repository successfully via Dashboard login page with incorrect credentials")
    public void TC02() {
        Account account = new Account(Constant.UserAbc, Constant.PassAbc);
        Log.info("Step 1. Navigate to Dashboard login page");
        loginPage.openLoginPage()
                .loginFalse(account);
        Log.info("Step 4. Verify that Dashboard Error message \"Username or password is invalid\" appears");
        Assert.assertEquals(CustomAlert.getTextAlert(), "Username or password is invalid", "Dashboard Error message \"Username or password is invalid\" does not appear");
        CustomAlert.acceptAlert();
    }

    @Test
    @Description("Verify that user fails to log in specific repository successfully via Dashboard login page with correct username and incorrect password")
    public void TC03() {
        Account account = new Account(Constant.UserAd, Constant.PassAbc);
        Log.info("Step 1. Navigate to Dashboard login page");
        Log.info("Step 2. Enter valid username and invalid password");
        loginPage.openLoginPage()
                .loginFalse(account);
        Log.info("Verify that Dashboard Error message \"Username or password is invalid\" appears");
        Assert.assertEquals(CustomAlert.getTextAlert(), "Username or password is invalid", "Dashboard Error message \"Username or password is invalid\" does not appear");
        CustomAlert.acceptAlert();
    }

    @Test
    @Description("Verify that user is able to log in different repositories successfully after logging out current repository")
    public void TC04() throws InterruptedException {
        Account account = new Account(Constant.UserAd, Constant.BLANK);
        Log.info("Step 1. Navigate to Dashboard login page");
        Log.info("Step 2. Enter valid username and password of default repository");
        loginPage.openLoginPage()
                .loginSuccess(account)
                .logOut()
                .loginOtherRepo("TestRepository", account);
        Log.info("Step 4. Verify that Dashboard Mainpage appears");
        Assert.assertTrue(loginPage.isDisplayedHomePage(), "Dashboard Main Page does not appear");
        loginPage.logOut();
    }

    @Test
    @Description("Verify that there is no Login dialog when switching between 2 repositories with the same account")
    public void TC05() {
        Account account = new Account(Constant.UserAd, Constant.BLANK);
        Log.info("Step 1. Navigate to Dashboard login page");
        Log.info("Step 2. Login with valid account for the first repository");
        DashBoardPage dashBoardPage = loginPage.openLoginPage()
                .loginSuccess(account);
        dashBoardPage.selectRePos();
        Log.info("Step 4. Observe the current page");
        Assert.assertFalse(loginPage.isDisplayedLoginForm(), "Login Form is displayed");
        Log.info("Step 4. Observe the current page");
        Assert.assertEquals(dashBoardPage.getCurrentRepo(), "TestRepository", "The Repository menu does not display name of switched repository");
        loginPage.logOut();
    }

    @Test
    @Description("Verify that Password input is case sensitive")
    public void TC06() {
        Account account1 = new Account(Constant.UserName, Constant.Password);
        Account account2 = new Account(Constant.UserName, Constant.Password.toLowerCase());
        Log.info("1. Login Success with valid Password (Uppercase)");
        loginPage.openLoginPage()
                .loginSuccess(account1)
                .clickOnUserName().clickOnLogOut()
                .loginFalse(account2);
        Log.info("VP. Login Fail with lowercase Password");
        CustomAlert.acceptAlert();
    }

    @Test
    @Description("Verify that Username is not case sensitive")
    public void TC07() {
        Account account1 = new Account(Constant.UserName.toUpperCase(), Constant.Password);
        Account account2 = new Account(Constant.UserName.toLowerCase(), Constant.Password);
        Log.info("1. Login success with uppercase user name / valid password");
        Log.info("2. Log out");
        Log.info("3. Login success with lowercase user name / valid password");
        Log.info("VP. Login successful");
        loginPage.openLoginPage()
                .loginSuccess(account1)
                .clickOnUserName().clickOnLogOut()
                .loginSuccess(account2);
    }

    @Test
    @Description("Verify that password with special characters is working correctly")
    public void TC08() {
        Account account = new Account("tc008", "p@ssw0rd");
        Log.info("1. Login success with password contains special characters is working");
        Log.info("VP. Login successful");
        loginPage.openLoginPage()
                .loginSuccess(account);
    }

    @Test
    @Description("Verify that username with special characters is working correctly")
    public void TC09() {
        Account account = new Account("tc009!", "taadmin");
        Log.info("1. Login success with username contains special characters is working");
        Log.info("VP. Login successful");
        loginPage.openLoginPage()
                .loginSuccess(account);
    }

    @Test
    @Description("Verify that the page works correctly for the case when no input entered to Password and Username field")
    public void TC10() {
        Account account = new Account(Constant.BLANK, Constant.BLANK);
        Log.info("1. Login with blank values");
        Log.info("VP. Login unsuccessful");
        loginPage.openLoginPage()
                .loginFalse(account);
        Log.warn("Bug here: There is a message 'Please enter username!'");
        Assert.assertEquals(CustomAlert.getTextAlert(), Constant.BlankValueWarning);
        CustomAlert.acceptAlert();
    }
}
