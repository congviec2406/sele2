package PageObject.testArchitect;

import Common.constant.Constant;
import DataObject.dataObject.Account;
import wrapper.Driver;
import wrapper.Element;

public class LoginPage extends GeneralPage {
    private final Element txtUserName = new Element("//input[@id='username']");
    private final Element titleLogin = new Element("//div[@class='ltext']");
    private final Element txtRepo = new Element("//select[@id='repository']");
    private final Element tabExDash = new Element("//a[@class='active' and contains(@href,'/TADashboard')]");
    private final Element txtPassWord = new Element("//input[@id='password']");
    private final Element btnLogin = new Element("//div[@class='btn-login']");

    public LoginPage openLoginPage() {
        Driver.navigateTo(Constant.TA_URL);
        return new LoginPage();
    }

    public LoginPage loginFalse(Account account) {
        txtUserName.waitForElementVisible().enter(account.getUserName());
        txtPassWord.enter(account.getPassword());
        btnLogin.click();
        return new LoginPage();
    }

    public DashBoardPage loginSuccess(Account account) {
        txtUserName.waitForElementVisible().enter(account.getUserName());
        txtPassWord.enter(account.getPassword());
        btnLogin.click();
        return new DashBoardPage();
    }

    public DashBoardPage loginOtherRepo(String repoName, Account account) {
        txtRepo.selectOptionValue(repoName);
        loginSuccess(account);
        return new DashBoardPage();
    }

    public boolean isDisplayedHomePage() {
        try {
            tabExDash.waitForElementExist();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tabExDash.isDisplayed();
    }

    public boolean isDisplayedLoginForm() {
        return titleLogin.isDisplayed();
    }
}
