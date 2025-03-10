package PageObject.testArchitect;

import wrapper.CustomAlert;
import wrapper.Element;

import static java.lang.Thread.sleep;

public class PanelsPage extends DashBoardPage {
    //locators
    private final Element addNewPanel = new Element("//a[.='Add New']");
    private final String pathBtnDelete = "//tr[./td[2][.='%s']]//a[.='Delete']";
    private final String pathBtnEdit = "//tr[./td[2][.='%s']]//a[.='Edit']";
    private final String pathPanelRow = "//tr[./td[2][.='%s']]//a[.='Edit']";
    private final Element dialogNewPanel = new Element("//div[contains(@class,'ui-dialog editpanelDlg')][.//span[@class='ui-dialog-title'][.='Add New Panel']]");
    private final Element dialogEditPanel = new Element("//div[contains(@class,'ui-dialog editpanelDlg')][.//span[@class='ui-dialog-title'][.='Edit Panel']]");

    private Element getSpecificBtnDelete(String pathName) {
        return new Element(pathBtnDelete, pathName);
    }

    private Element getSpecificBtnEdit(String pathName) {
        return new Element(pathBtnEdit, pathName);
    }

    private Element getSpecificPanelRow(String panelName) {
        return new Element(pathPanelRow, panelName);
    }

    public PanelsPage deletePanel(String panelName) {
        getSpecificBtnDelete(panelName).click();
        CustomAlert.acceptAlert();
        return this;
    }

    public NewOrEditPanelDialog_DisplaySetting openAddNewPanelDialog() {
        addNewPanel.waitForElementStaleness().click();
        dialogNewPanel.waitForElementVisible();
        return new NewOrEditPanelDialog_DisplaySetting();
    }

    public NewOrEditPanelDialog_DisplaySetting openEditPanelDialog(String panelName) {
        getSpecificBtnEdit(panelName).waitForElementEnable().click();
        dialogEditPanel.waitForElementVisible();
        return new NewOrEditPanelDialog_DisplaySetting();
    }

    public boolean isSpecificPanelExist(String panelName) {
        try {
            getSpecificPanelRow(panelName).waitForElementExist();
        } catch (Exception e) {
        }
        return getSpecificPanelRow(panelName).isDisplayed();
    }
}
