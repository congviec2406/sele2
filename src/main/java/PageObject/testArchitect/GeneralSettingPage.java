package PageObject.testArchitect;

import wrapper.Element;

public class GeneralSettingPage {

    //locators
    private final Element txtName = new Element("//input[@id='txtProfileName']");
    private final Element btnFinish = new Element("//input[@value='Finish']");

    //methods
    public ProfilesPage enterDataProfileThenFinish(String name) {
        txtName.enter(name);
        btnFinish.click();
        return new ProfilesPage();
    }
}
