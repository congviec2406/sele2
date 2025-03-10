package PageObject.testArchitect;

import Common.common.Log;
import org.openqa.selenium.WebElement;
import wrapper.CustomAlert;
import wrapper.Element;

import java.util.List;

public class DashBoardPage extends GeneralPage {
    //elements
    private final Element GlobalSetting = new Element("//li[@class='mn-setting']");
    private final Element DeletePage = new Element("//a[contains(text(),'Delete')]");
    private final String Page = "//div[@id='main-menu']//a[contains(text(),'%s')]";
    private final String PanelCreated = "//form[@id='form1']//a[contains(text(),'%s')]";
    private final Element currentRepo = new Element("//a[@href='#Repository']/span");
    private final Element getRepo = new Element("//ul[@id='ulListRepositories']//a[contains(text(),'TestRepository')]");
    private final Element GlobalSetting_AddPage = new Element("//li[@class='mn-setting']//a[.='Add Page']");
    private final Element GlobalSetting_CreatePanel = new Element("//li[@class='mn-setting']//a[.='Create Panel']");
    private final Element GlobalSetting_Edit = new Element("//li[@class='mn-setting']//a[.='Edit']");
    private final Element GlobalSetting_Delete = new Element("//li[@class='mn-setting']//a[.='Delete']");
    private final Element NewOrEditPageDialog_txtName = new Element("//input[@id='name']");
    private final Element NewOrEditPageDialog_selectParentPage = new Element("//select[@id='parent']");
    private final Element NewOrEditPageDialog_selectColumnNumber = new Element("//select[@id='columnnumber']");
    private final Element NewOrEditPageDialog_cbxPublic = new Element("//input[@id='ispublic']");
    private final Element NewOrEditPageDialog_selectDisplayAfter = new Element("//select[@id='afterpage']");
    private final Element NewOrEditPageDialog_btnOK = new Element("//input[@id='OK']");
    private final Element NewOrEditPageDialog_btnCancel = new Element("//input[@id='Cancel']");
    private final Element dialogNewPage = new Element("//div[@id='div_popup'][.//h2[.='New Page']]");
    private final Element dialogEditPage = new Element("//div[@id='div_popup'][.//h2[.='Edit Page']]");
    private final Element Overview = new Element("//div[@id='main-menu']//a[contains(text(),'Overview')]");
    private final Element dialogNewOrEditPage = new Element("//div[@id='div_popup'][.//h2[.='New Page' or 'Edit Page']]");
    private final String pathTab = "//div[@id='main-menu']//li[./a[.='%s']]";
    private final String pathTabBesideOverview = "//div[@id='main-menu']//li[.='Overview']/following-sibling::li[.='%s']";
    private final String pathTabBesideOtherTab = "//div[@id='main-menu']//li[.='%s']/following-sibling::li[.='%s']";
    private final Element folderName = new Element("//table//tr//span");
    private final Element cPanelBtn = new Element("//div[@id='main-menu']//a[@id='btnChoosepanel']");
    private final String pageColumnPath = "//div[@id='columns']/ul[@id='column%s']";

    //methods
    //main menu
    public DashBoardPage clickOnBtnGlobalSetting() {
        GlobalSetting.waitForElementStaleness().click();
        return new DashBoardPage();
    }

    public DashBoardPage openAddPageDialog() {
        clickOnBtnGlobalSetting();
        GlobalSetting_AddPage.click();
        return this;
    }

    public NewOrEditPanelDialog_DisplaySetting openCreatePanel() {
        clickOnBtnGlobalSetting();
        GlobalSetting_CreatePanel.click();
        return new NewOrEditPanelDialog_DisplaySetting();
    }

    public DashBoardPage openEditPageDialog() {
        GlobalSetting.click();
        GlobalSetting_Edit.click();
        return this;
    }

    private Element getSpecificMenuTab(String tabName) {
        return new Element(pathTab, tabName.replace(" ", " "));
    }

    private Element getSpecificTabBesideOverview(String tabName) {
        return new Element(pathTabBesideOverview, tabName.replace(" ", " "));
    }

    private Element getSpecificTabBesideOtherTab(String tab, String otherTab) {
        return new Element(pathTabBesideOtherTab, otherTab.replace(" ", " "), tab.replace(" ", " "));
    }

    public DashBoardPage clickOnSpecificMenuTab(String tabName) {
        getSpecificMenuTab(tabName).waitForElementStaleness().click();
        return this;
    }

    public DashBoardPage clickOnSpecificMenuTab_Child(String parentTab, String childTab) {
        getSpecificMenuTab(parentTab).waitForElementStaleness().hover();
        getSpecificMenuTab(childTab).waitForElementStaleness().click();
        return this;
    }

    public DashBoardPage clickOnSpecificMenuTab_Child(List<String> parentTab, String childTab) {
        for (String str : parentTab) {
            getSpecificMenuTab(str).waitForElementStaleness().hover();
        }
        getSpecificMenuTab(childTab).waitForElementStaleness().click();
        return this;
    }

    public boolean isSpecifyMenuTabExist(String tabName) {
        try {
            getSpecificMenuTab(tabName).waitForElementVisible();
        } catch (Exception e) {
        }
        return getSpecificMenuTab(tabName).hardWait(1000).isDisplayed();
    }

    public boolean isSpecifyMenuTabExist_Child(String parentTab, String childTab) {
        getSpecificMenuTab(parentTab).waitForElementVisible().waitForElementStaleness().hover();
        try {
            getSpecificMenuTab(childTab).waitForElementExist();
        } catch (Exception e) {
        }
        return getSpecificMenuTab(childTab).isDisplayed();
    }

    public boolean isSpecifyMenuTabExist_Child(List<String> parentTab, String childTab) {
        for (String str : parentTab) {
            getSpecificMenuTab(str).waitForElementStaleness().hover();
        }
        try {
            getSpecificMenuTab(childTab).waitForElementExist();
        } catch (Exception e) {
        }
        return getSpecificMenuTab(childTab).isDisplayed();
    }

    public DashBoardPage deleteSpecificMenuTab(String tabName, boolean confirm) {
        getSpecificMenuTab(tabName).waitForElementStaleness().waitForElementEnable().click();
        GlobalSetting.click();
        GlobalSetting_Delete.click();
        if (confirm == true)
            CustomAlert.acceptAlert();
        return this;
    }

    public DashBoardPage deleteSpecificMenuTab_Child(String parentTab, String ChildTab, boolean confirm) {
        getSpecificMenuTab(parentTab).waitForElementStaleness().hover();
        getSpecificMenuTab(ChildTab).waitForElementStaleness().click();
        GlobalSetting.click();
        GlobalSetting_Delete.click();
        if (confirm == true)
            CustomAlert.acceptAlert();
        return this;
    }

    public DashBoardPage deleteSpecificMenuTab_Child(List<String> parentTab, String ChildTab, boolean confirm) {
        for (String str : parentTab) {
            getSpecificMenuTab(str).waitForElementStaleness().hover();
        }
        getSpecificMenuTab(ChildTab).waitForElementStaleness().click();
        GlobalSetting.click();
        GlobalSetting_Delete.click();
        if (confirm == true)
            CustomAlert.acceptAlert();
        return this;
    }

    //dialog
    public boolean isDialogNewPageVisible() {
        try {
            dialogNewPage.waitForElementExist();
        } catch (Exception e) {
        }
        return dialogNewPage.isDisplayed();
    }

    public boolean isDialogEditPageVisible() {
        try {
            dialogEditPage.waitForElementExist();
        } catch (Exception e) {
        }
        return dialogEditPage.isDisplayed();
    }

    public DashBoardPage enterDialogNewOrEditPage(String pageName) {
        dialogNewOrEditPage.waitForElementVisible();
        NewOrEditPageDialog_txtName.enter(pageName);
        NewOrEditPageDialog_btnOK.click();
        return this;
    }

    public DashBoardPage enterDialogNewOrEditPage_DisplayAfter(String pageName, String colNum, String afterPage, String isPublic) {
        dialogNewOrEditPage.waitForElementVisible();
        NewOrEditPageDialog_txtName.enter(pageName);
        if (colNum.equals("") == false) {
            NewOrEditPageDialog_selectColumnNumber.getSelectObject().selectByIndex(Integer.parseInt(colNum));
        }
        if (afterPage.equals("") == false) {
            NewOrEditPageDialog_selectDisplayAfter.getSelectObject().selectByVisibleText(afterPage);
        }
        if (isPublic.equals("") == false) {
            NewOrEditPageDialog_cbxPublic.setCheckBox(Boolean.parseBoolean(isPublic));
        }
        NewOrEditPageDialog_btnOK.click();
        return this;
    }

    public DashBoardPage enterDialogNewOrEditPage_ParentPage(String pageName, String colNum, String parentPage, String isPublic) {
        dialogNewOrEditPage.waitForElementVisible();
        NewOrEditPageDialog_txtName.enter(pageName);
        if (colNum.equals("") == false) {
            NewOrEditPageDialog_selectColumnNumber.getSelectObject().selectByIndex(Integer.parseInt(colNum) - 1);
        }
        if (parentPage.equals("") == false) {
            NewOrEditPageDialog_selectParentPage.click();
            NewOrEditPageDialog_selectParentPage.getSelectObject().selectByVisibleText(parentPage);
            NewOrEditPageDialog_selectParentPage.waitForElementStaleness();
        }
        if (isPublic.equals("") == false) {
            NewOrEditPageDialog_cbxPublic.setCheckBox(Boolean.parseBoolean(isPublic));
        }
        NewOrEditPageDialog_btnOK.waitForElementStaleness().click();
        return this;
    }

    public void cancelDialog() {
        dialogNewOrEditPage.waitForElementVisible();
        NewOrEditPageDialog_btnCancel.click();
        dialogNewOrEditPage.waitForElementNotExist();
    }
    //add

    public boolean isDeleteLinkOnGlobalSetting() {
        clickOnBtnGlobalSetting();
        try {
            GlobalSetting_Delete.waitForElementExist();
        } catch (Exception e) {
        }
        return GlobalSetting_Delete.isDisplayed();
    }

    public void selectRePos() {
        currentRepo.moveTo();
        getRepo.click();
    }

    public String getCurrentRepo() {
        return currentRepo.getText();
    }

    public void deletePage(String pageName) {
        selectDeleteParentPage(pageName);
        CustomAlert.acceptAlert();
    }

    private Element getPageName(String pathName) {
        return new Element(Page, pathName.replace(" ", " "));
    }

    private Element getPanelName(String pathName) {
        return new Element(PanelCreated, pathName.replace(" ", " "));
    }

    public void selectDeleteParentPage(String pageName) {
        getPageName(pageName).click();
        GlobalSetting.click();
        DeletePage.click();
    }

    public String getTextPanelName(String panelCreated) {
        getPanelName(panelCreated).waitForElementVisible();
        return getPanelName(panelCreated).getText();
    }

    public boolean isGlobalSettingEnabel() {
        return GlobalSetting.isEnable();
    }

    public boolean isAddpageEnable() {
        return GlobalSetting_AddPage.isEnable();
    }

    public boolean isOverViewEnable() {
        return Overview.isEnable();
    }

    public boolean checkPageOrder_Parents(List<String> listOrdered) {
        String path = "//a[text()='Overview']";
        String path2 = "/../following-sibling::li[1]";
        String path3 = "";
        for (String str : listOrdered) {
            path3 = path3 + path2 + "/a[text()='" + str + "']";
        }
        path = (path + path3).replace(" ", " ");
        return new Element(path).waitForElementExist().isDisplayed();
    }

    public boolean isFolderNameDisplayed(String folder) {
        try {
            folderName.waitForElementExist();
        } catch (Exception e) {
        }
        for (WebElement folderNames : folderName.getElements())
            if (folderNames.getText().equals(folder))
                return true;
        return false;
    }

    public ChoosePanelPage openChoosePanelPage() {
        cPanelBtn.waitForElementStaleness().waitForElementVisible();
        cPanelBtn.click();
        return new ChoosePanelPage();
    }

    public boolean isTabBesideOverview(String tabName) {
        try {
            getSpecificTabBesideOverview(tabName).waitForElementVisible();
        } catch (Exception e) {
        }
        return getSpecificTabBesideOverview(tabName).isDisplayed();
    }

    public boolean isTabBesideOtherTab(String tab, String otherTab) {
        try {
            getSpecificTabBesideOtherTab(tab, otherTab).waitForElementVisible();
        } catch (Exception e) {
        }
        return getSpecificTabBesideOtherTab(tab, otherTab).isDisplayed();
    }

    public boolean isNumberOfColumnExist(int colNumber) {
        String colPath = String.format(pageColumnPath, colNumber - 1);
        Log.info("check element" + String.format(colPath, colNumber));
        return new Element(colPath).waitForElementExist().isDisplayed();
    }
}
