package PageObject.testArchitect;

import DataObject.enums.PanelSeriesType;
import wrapper.Element;

import java.util.List;

public class NewOrEditPanelDialog_DisplaySetting {
    //locators
    private final String formSetting = "//fieldset[@id='fdSettings']//legend[contains(text(),'%s')]";
    private final Element txtDisplayName = new Element("//input[@id='txtDisplayName']");
    private final Element txtChartTitle = new Element("//input[@id='txtChartTitle']");
    private final Element txtChartType = new Element("//select[@id='cbbChartType']");
    private final Element selectDataProfile = new Element("//select[@id='cbbProfile']");
    private final String txtDataProfile = "//select[@id='cbbProfile']//option[@value='%s']";
    private final Element selectChartType = new Element("//select[@id='cbbChartType']");
    private final Element selectChartCategory = new Element("//select[@id='cbbCategoryField']");
    private final Element selectChartCategoryCaption = new Element("//input[@id='txtCategoryXAxis']");
    private final Element selectChartSeries = new Element("//select[@id='cbbSeriesField']");
    private final Element selectChartSeriesCaption = new Element("//input[@id='txtValueYAxis']");
    private final Element checkboxShow = new Element("//input[@id='chkShowTitle']");
    private final Element valueCheckbox = new Element("//input[@id='chkValue']");
    private final Element seriesCheckbox = new Element("//input[@id='chkSeriesName']");
    private final Element categoryCheckbox = new Element("//input[@id='chkCategoriesName']");
    private final Element percentCheckbox = new Element("//input[@id='chkPercentage']");
    private final Element btnOK = new Element("//input[@id='OK']");
    private final Element btnCancel = new Element("//input[@id='Cancel']");
    private final Element radioChart = new Element("//table[@id='infoSettings']//input[@id='radPanelType0']");
    private final Element radioIndi = new Element("//table[@id='infoSettings']//input[@id='radPanelType1']");
    private final Element radioReport = new Element("//table[@id='infoSettings']//input[@id='radPanelType2']");
    private final Element radioHearMap = new Element("//table[@id='infoSettings']//input[@id='radPanelType3']");
    private final Element dialogNewOrEditPanel = new Element("//div[contains(@class,'ui-dialog editpanelDlg')]");
    private final String RadioName = "//input[@value='%s']";
    private final String editPanel = "//div[@title='%s']/ancestor::div/following-sibling::div/ul/li[@title='Edit Panel']";

    //methods
    //gesture
    private Element FormName(String pathName) {
        return new Element(formSetting, pathName.replace(" ", " "));
    }

    private Element RadioName(String radioName) {
        return new Element(RadioName, radioName.replace(" ", " "));
    }

    private Element PanelNameEdit(String panelName) {
        return new Element(editPanel, panelName.replace(" ", " "));
    }

    private Element TxtDataProfile() {
        String a = selectDataProfile.getAttribute("value");
        return new Element(txtDataProfile, a.replace(" ", " "));
    }

    public NewOrEditPanelDialog_DisplaySetting enterChartSeries(String seriesType) {
        selectChartSeries.selectOptionValue(seriesType);
        return this;
    }

    public NewOrEditPanelDialog_DisplaySetting enterCategory(String category) {
        selectChartCategory.selectOptionValue(category);
        return this;
    }

    public NewOrEditPanelDialog_DisplaySetting enterChartSeriesRandom() {
        selectChartSeries.waitForElementEnable().selectRandomOption(1, 12);
        return this;
    }

    public NewOrEditPanelDialog_DisplaySetting enterChartType(String chartName) {
        selectChartType.waitForElementVisible().selectOptionValue(chartName);
        return this;
    }

    public NewOrEditPanelDialog_DisplaySetting enterDataProfile(int index) {
        selectDataProfile.waitForElementVisible();
        selectDataProfile.getSelectObject().selectByIndex(index);
        return this;
    }

    public NewOrEditPanelDialog_DisplaySetting selectShowCb() {
        checkboxShow.waitForElementVisible().click();
        return this;
    }

    public NewOrEditPanelDialog_DisplaySetting enterChartTitle(String chartTitle) {
        txtChartTitle.waitForElementVisible();
        txtChartTitle.enter(chartTitle);
        return this;
    }

    public NewOrEditPanelDialog_DisplaySetting clickOkButton() {
        btnOK.click();
        return this;
    }

    public NewOrEditPanelDialog_DisplaySetting clickCancelButton() {
        btnCancel.click();
        return this;
    }

    public NewOrEditPanelDialog_DisplaySetting enterDisplayName(String chartTitle) {
        txtDisplayName.enter(chartTitle);
        return this;
    }

    public void enterDialog(String displayName) {
        dialogNewOrEditPanel.waitForElementVisible();
        enterDisplayName(displayName);
        enterChartSeriesRandom();
        btnOK.click();
    }

    public void enterDialog(String DisplayName, PanelSeriesType seriesType) {
        dialogNewOrEditPanel.waitForElementVisible();
        enterDisplayName(DisplayName);
        enterChartSeries(seriesType.getValue());
        btnOK.click();
    }

    public void cancelDialog() {
        dialogNewOrEditPanel.waitForElementVisible();
        btnCancel.click();
        dialogNewOrEditPanel.waitForElementNotExist();
    }

    public NewOrEditPanelDialog_DisplaySetting clickOnChartType() {
        txtChartType.click();
        return this;
    }

    //check
    public boolean isDataProfileListAlphabetOrder() {
        return selectDataProfile.isSelectAlphabetOrder();
    }

    public boolean isSpecificDataProfileExist(String profileName) {
        return selectDataProfile.isSelectOptionExist(profileName);
    }

    public boolean isChartTypeListExist(List<String> list) {
        Boolean result = true;
        for (String str : list) {
            if (!selectChartType.isSelectOptionExist(str)) {
                result = false;
            }
        }
        return result;
    }

    public boolean isFieldEnable(String fieldName) {
        if (fieldName.equals("Category")) {
            return selectChartCategory.waitForElementStaleness().isEnable();
        }
        if (fieldName.equals("Series")) {
            return selectChartSeries.waitForElementStaleness().isEnable();
        }
        if (fieldName.equals("Caption")) {
            if (selectChartCategoryCaption.waitForElementStaleness().isEnable() && selectChartSeriesCaption.waitForElementStaleness().isEnable()) {
                return true;
            }
        }
        return false;
    }

    public void selectRadioChart() {
        radioChart.click();
    }

    public boolean isRadioChartSelect() {
        return radioChart.isSelected();
    }

    public void selectRadioIndi() {
        radioIndi.click();
    }

    public void selectRadioReport() {
        radioReport.click();
    }

    public void selectRadioHeartMap() {
        radioHearMap.click();
    }

    public boolean isDataProFileUnchange(String name) {
        if (TxtDataProfile().getText().equals(name))
            return true;
        else return false;
    }

    public boolean isFormSettingDisplayed(String formName, String formTitle) {
        if (FormName(formName).isDisplayed() == false)
            return false;
        else if (FormName(formName).getText().equals(formName))
            return true;
        else return false;
    }

    public boolean isChartTitleUnchange(String name) {
        return txtChartTitle.getAttribute("value").equals(name);
    }

    public boolean isChartTypeUnchange(String name) {
        return selectChartType.getAttribute("value").equals(name);
    }

    public boolean isNameUnchange(String name) {
        return txtDisplayName.getAttribute("value").equals(name);
    }

    public boolean isSeriesUnchange(PanelSeriesType seriesType) {
        return selectChartSeries.getAttribute("value").equals(seriesType.getValue());
    }

    public boolean isShowTitleUnchange(boolean state) {
        boolean isOn = checkboxShow.isSelected();
        if (state && isOn) {
            return true;
        } else return !state && !isOn;
    }

    public boolean isRadioUnchange(String legends) {
        return RadioName(legends).isSelected();
    }

    public NewOrEditPanelDialog_DisplaySetting selectRadio(String radio) {
        RadioName(radio).click();
        return this;
    }

    public NewOrEditPanelDialog_DisplaySetting enterOptionSeries(PanelSeriesType seriesType) {
        selectChartSeries.waitForElementVisible();
        enterChartSeries(seriesType.getValue());
        return this;
    }

    public NewOrEditPanelDialog_DisplaySetting enterOptionCatogy(PanelSeriesType seriesType) {
        selectChartCategory.waitForElementVisible();
        enterCategory(seriesType.getValue());
        return this;
    }

    public NewOrEditPanelDialog_DisplaySetting clickEditPanel(String name) {
        PanelNameEdit(name).waitForElementVisible().click();
        return this;
    }

    public boolean isCategoryEnable() {
        return selectChartCategory.isEnable();
    }

    public boolean isSeriesEnbale() {
        return selectChartSeries.isEnable();
    }

    public boolean isValueEnable() {
        return valueCheckbox.isEnable();
    }

    public boolean isPerceEnable() {
        return percentCheckbox.isEnable();
    }

    public void clickSeries() {
        seriesCheckbox.click();
    }

    public void clickCategory() {
        categoryCheckbox.click();
    }

    public void clickValue() {
        valueCheckbox.click();
    }

    public void clickPercentage() {
        percentCheckbox.click();
    }

    public boolean isSeriesSelect() {
        return seriesCheckbox.isSelected();
    }

    public boolean isCategoriesSelect() {
        return categoryCheckbox.isSelected();
    }

    public boolean isValueSelect() {
        return valueCheckbox.isSelected();
    }

    public boolean isPercentageSelect() {
        return percentCheckbox.isSelected();
    }
}