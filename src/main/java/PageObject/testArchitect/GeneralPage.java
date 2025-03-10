package PageObject.testArchitect;

import wrapper.Element;

public class GeneralPage {
    //locators
    private final Element UserName = new Element("//div[@id='header']//a[@href='#Welcome']");
    private final Element Logout = new Element("//div[@id='header']//a[.='Logout']");
    private final Element Administer = new Element("//div[@id='header']//a[.='Administer']");
    private final Element DataProfiles = new Element("//div[@id='header']//a[.='Data Profiles']");
    private final Element Panels = new Element("//div[@id='header']//a[.='Panels']");

    //methods
    public GeneralPage clickOnUserName() {
        UserName.waitForElementStaleness().click();
        return this;
    }

    public LoginPage clickOnLogOut() {
        Logout.click();
        return new LoginPage();
    }

    public LoginPage logOut() {
        clickOnUserName();
        clickOnLogOut();
        return new LoginPage();
    }

    public GeneralPage clickOnAdminister() {
        Administer.waitForElementExist().waitForElementStaleness().click();
        return this;
    }

    public PanelsPage navigateToPanelsPage() {
        clickOnAdminister();
        Panels.click();
        return new PanelsPage();
    }

    public ProfilesPage navigateToProfilesPage() {
        clickOnAdminister();
        DataProfiles.click();
        return new ProfilesPage();
    }
}
