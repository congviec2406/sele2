package PageObject.testArchitect;

import wrapper.CustomAlert;
import wrapper.Element;

public class ProfilesPage extends DashBoardPage {

    //locators
    private final Element AddNew = new Element("//a[.='Add New']");
    private final String pathBtnDelete = "//tr[./td[2][.='%s']]//a[.='Delete']";

    private Element getSpecificBtnDelete(String pathName) {
        return new Element(pathBtnDelete, pathName.replace(" ", " "));
    }

    public GeneralSettingPage openAddNewProfilePage() {
        AddNew.waitForElementStaleness().click();
        return new GeneralSettingPage();
    }

    public ProfilesPage deleteProfile(String profileName) {
        getSpecificBtnDelete(profileName).click();
        CustomAlert.acceptAlert();
        return this;
    }
}
