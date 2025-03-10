package PageObject.testArchitect;
//import DataObject.enums.AllPanelName;

import DataObject.enums.AllPanelName;
import wrapper.Element;
import org.openqa.selenium.WebElement;

public class ChoosePanelPage {
    private final Element panelChart = new Element("//div[@class='cpanels']//div[@class='ptit pchart']");
    private final Element panelChartSubItems = new Element("//div[@class='cpanels']//div[@class='ptit pchart']//following-sibling::table//a[@href='#']");
    private final Element panelIndicatorSubItems = new Element("//div[@class='cpanels']//div[@class='ptit pindicator']//following-sibling::table//a[@href='#']");
    private final Element panelReportSubItems = new Element("//div[@class='pitem']//div[contains(text(),'Reports')]//following-sibling::table//a[@href='#']");
    private final Element panelHeartMapSubItems = new Element("//div[@class='pitem']//div[contains(text(),'Heat Maps')]//following-sibling::table//a[@href='#']");
    private final Element btnHide = new Element("//span[@id='btnHidePanel']");
    private final Element btnPanelSettingFolder = new Element("//img[@class='panel_setting_treefolder']");
    private final Element panelSettingFolder = new Element("//td[@class='general_white_space']//a[2]");
    private final Element btnOK = new Element("//input[@id='btnFolderSelectionOK']");
    private final Element btnCreateNewPanel = new Element("//span[contains(@onclick,'Dashboard.openAddPanel')]");

    public boolean isPreSetPanelDisplayed(Element a, String item) {
        a.getElement();
        a.waitForElementVisible();
        if (a.getText() == item)
            return true;
        else return false;
    }

    public boolean isPanelChartDisplayed(AllPanelName item) {
        return isPreSetPanelDisplayed(panelChart, item.getValue());
    }

    public boolean isPanelIndicatorDisplayed(AllPanelName item) {
        return isPanelChartDisplayed(item);
    }

    public boolean isPanelReportDisplayed(AllPanelName item) {
        return isPanelChartDisplayed(item);
    }

    public boolean isPanelHeartMapDisplayed(AllPanelName item) {
        return isPanelChartDisplayed(item);
    }

    public boolean isPanelChartSubItemsDisplayed(AllPanelName subitem) {
        return isPanelSubItemsDisplayed(panelChartSubItems, subitem.getValue());
    }

    public boolean isPanelIndicatorSubItemsDisplayed(AllPanelName subitem) {
        return isPanelSubItemsDisplayed(panelIndicatorSubItems, subitem.getValue());
    }

    public boolean isPanelReportSubItemsDisplayed(AllPanelName subitem) {
        return isPanelSubItemsDisplayed(panelReportSubItems, subitem.getValue());
    }

    public boolean isPanelHeartMapSubItemsDisplayed(AllPanelName subitem) {
        return isPanelSubItemsDisplayed(panelHeartMapSubItems, subitem.getValue());
    }

    public String printErrorPanelName(AllPanelName pName, AllPanelName err) {
        return pName.getValue() + err.getValue();
    }

    public boolean isPanelSubItemsDisplayed(Element b, String subitem) {
        b.getElements();
        b.waitForElementVisible();
        for (WebElement subitems : b.getElements())
            if (subitems.getText().equals(subitem))
                return true;
        return false;
    }

    public void clickHideButton() {
        btnHide.waitForElementVisible();
        btnHide.click();
    }

    public void selectPanelChartSubItems(String subitem) {
        panelChartSubItems.waitForElementVisible();
        for (WebElement subitems : panelChartSubItems.getElements())
            if (subitems.getText().equals(subitem))
                panelChartSubItems.click();
    }

    public void choosePanelSettingFolder(String folderName) {
        btnPanelSettingFolder.waitForElementVisible();
        btnPanelSettingFolder.click();
        panelSettingFolder.waitForElementVisible();
        for (WebElement folderNames : panelSettingFolder.getElements())
            if (folderNames.getText().equals(folderName))
                panelSettingFolder.click();
        btnOK.click();
    }

    public NewOrEditPanelDialog_DisplaySetting openAddNewPanelDialog() {
        btnCreateNewPanel.click();
        return new NewOrEditPanelDialog_DisplaySetting();
    }
}
