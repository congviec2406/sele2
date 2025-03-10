package PageObject.testArchitect;

import wrapper.Element;

public class PanelConfigurationDialog {
    public final Element pageSelect = new Element("//select[@id='cbbPages']");
    public final Element txtFolder = new Element("//input[@id='txtFolder']");
    public final Element confirmConFig = new Element("//div[@id='div_panelConfigurationDlg']//input[@id='OK']");

    public void panelConfig() {
        confirmConFig.waitForElementVisible();
        confirmConFig.click();
    }

    public PanelConfigurationDialog selectPageCreated(String name) {
        pageSelect.waitForElementVisible().selectListOption(name);
        return this;
    }

    public PanelConfigurationDialog enterFolderName(String name) {
        txtFolder.waitForElementVisible();
        txtFolder.enter(name);
        return this;
    }
}
