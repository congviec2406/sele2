package TestCase.testArchitect;

import Common.common.Log;
import Common.constant.Constant;
import DataObject.dataObject.Account;
//import DataObject.enums.AllPanelName;
import DataObject.enums.AllPanelName;
import DataObject.enums.PanelSeriesType;
import wrapper.CustomAlert;
import PageObject.testArchitect.*;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class PanelTest extends BaseTest {
    Account defaultAccount = new Account(Constant.UserName, Constant.Password);

    @Test
    @Description("Verify that when \"Choose panels\" form is expanded all pre-set panels are populated and sorted correctly")
    public void TC27() {
        Log.info("Step 1. Navigate to Dashboard login page");
        Log.info("Step 2. Navigate to Dashboard login page");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        String PageName1 = Constant.getPageName();
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage(PageName1);
        NewOrEditPanelDialog_DisplaySetting newOrEditPanelDialog = dashBoardPage.openCreatePanel();
        String PanelName1 = Constant.getPanelName();
        newOrEditPanelDialog.enterDialog(PanelName1, PanelSeriesType.NAME);
        PanelConfigurationDialog panelConfigurationDialog = new PanelConfigurationDialog();
        panelConfigurationDialog.panelConfig();
        ChoosePanelPage choosePanelPage = dashBoardPage.openChoosePanelPage();
        SoftAssert softAssert = new SoftAssert();

        Log.info("Step 11. Verify that all pre-set panels are populated and sorted correctly");
        softAssert.assertTrue(choosePanelPage.isPanelChartDisplayed(AllPanelName.Chart), choosePanelPage.printErrorPanelName(AllPanelName.Chart, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelChartSubItemsDisplayed(AllPanelName.SubChart1), choosePanelPage.printErrorPanelName(AllPanelName.SubChart1, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelChartSubItemsDisplayed(AllPanelName.SubChart2), choosePanelPage.printErrorPanelName(AllPanelName.SubChart2, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelChartSubItemsDisplayed(AllPanelName.SubChart3), choosePanelPage.printErrorPanelName(AllPanelName.SubChart3, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelChartSubItemsDisplayed(AllPanelName.SubChart4), choosePanelPage.printErrorPanelName(AllPanelName.SubChart4, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelChartSubItemsDisplayed(AllPanelName.SubChart5), choosePanelPage.printErrorPanelName(AllPanelName.SubChart5, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelChartSubItemsDisplayed(AllPanelName.SubChart6), choosePanelPage.printErrorPanelName(AllPanelName.SubChart6, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelChartSubItemsDisplayed(AllPanelName.SubChart7), choosePanelPage.printErrorPanelName(AllPanelName.SubChart7, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelChartSubItemsDisplayed(AllPanelName.SubChart8), choosePanelPage.printErrorPanelName(AllPanelName.SubChart8, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelChartSubItemsDisplayed(AllPanelName.SubChart9), choosePanelPage.printErrorPanelName(AllPanelName.SubChart9, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelChartSubItemsDisplayed(AllPanelName.SubChart10), choosePanelPage.printErrorPanelName(AllPanelName.SubChart10, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelIndicatorDisplayed(AllPanelName.Indi), choosePanelPage.printErrorPanelName(AllPanelName.Indi, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelIndicatorSubItemsDisplayed(AllPanelName.SubIndi1), choosePanelPage.printErrorPanelName(AllPanelName.Indi, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelIndicatorSubItemsDisplayed(AllPanelName.SubIndi2), choosePanelPage.printErrorPanelName(AllPanelName.Indi, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelIndicatorSubItemsDisplayed(AllPanelName.SubIndi3), choosePanelPage.printErrorPanelName(AllPanelName.Indi, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelReportDisplayed(AllPanelName.Report), choosePanelPage.printErrorPanelName(AllPanelName.Report, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelReportSubItemsDisplayed(AllPanelName.SubReport1), choosePanelPage.printErrorPanelName(AllPanelName.SubReport1, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelHeartMapDisplayed(AllPanelName.HeatM), choosePanelPage.printErrorPanelName(AllPanelName.HeatM, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelHeartMapSubItemsDisplayed(AllPanelName.SubHeatM1), choosePanelPage.printErrorPanelName(AllPanelName.SubHeatM1, AllPanelName.Err));
        softAssert.assertTrue(choosePanelPage.isPanelHeartMapSubItemsDisplayed(AllPanelName.SubHeatM2), choosePanelPage.printErrorPanelName(AllPanelName.SubHeatM2, AllPanelName.Err));
        choosePanelPage.clickHideButton();
        Log.info("Post condition - Delete the created page");
        dashBoardPage.deletePage(PageName1);
        dashBoardPage
                .navigateToPanelsPage()
                .deletePanel(PanelName1);
        softAssert.assertAll();
        loginPage.logOut();
    }

    @Test
    @Description("Verify that when \"Add New Panel\" form is on focused all other control/form is disabled or locked.")
    public void TC28() {
        Log.info("Step 1. Navigate to Dashboard login page");
        Log.info("Step 2. Login with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        dashBoardPage
                .navigateToPanelsPage()
                .openAddNewPanelDialog();
        Log.info("Step 7. Check All control/form are disabled or locked when Add New Panel dialog is opening");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(dashBoardPage.isGlobalSettingEnabel(), "the Global Setting control is still enabled");
        softAssert.assertTrue(dashBoardPage.isOverViewEnable(), "the OverView control is still enabled");
        softAssert.assertTrue(dashBoardPage.isAddpageEnable(), "the Add Page control is still enabled");
        softAssert.assertAll();
        Log.info("Post Condition - Close TA Dashboard");
        loginPage.logOut();
    }

    @Test
    @Description("Verify that user is unable to create new panel when (*) required field is not filled")
    public void TC29() {
        Log.info("Step 1. Navigate to Dashboard");
        Log.info("Step 2. Select specific repository");
        Log.info("Step 3. Login with valid account");
        DashBoardPage dashBoardPage = loginPage.openLoginPage()
                .loginSuccess(defaultAccount);
        NewOrEditPanelDialog_DisplaySetting newOrEditPanelDialog = dashBoardPage.navigateToPanelsPage()
                .openAddNewPanelDialog();
        newOrEditPanelDialog.clickOkButton();
        Log.info("Check warning message show up");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(CustomAlert.getTextAlert(), "Display Name is a required field.", "Warning message does not show up the same as expected ");
        softAssert.assertAll();
        CustomAlert.acceptAlert();
        newOrEditPanelDialog.cancelDialog();
        loginPage.logOut();
    }

    @Test
    @Description("Verify that no special character except '@' character is allowed to be inputted into \"Display Name\" field")
    public void TC30() {
        Log.info("Step 1. Navigate to Dashboard login page");
        Log.info("Step 2. Login with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        NewOrEditPanelDialog_DisplaySetting newOrEditPanelDialog = dashBoardPage
                .navigateToPanelsPage()
                .openAddNewPanelDialog();
        Log.info("Step 6. Enter value into Display Name field with special characters except \"@\"");
        newOrEditPanelDialog.enterDialog("Logigear#$%", PanelSeriesType.NAME);
        SoftAssert softAssert = new SoftAssert();
        Log.info("Step 8. Observe the current page");
        softAssert.assertEquals(CustomAlert.getTextAlert(), "Invalid display name. The name cannot contain high ASCII characters or any of the following characters: /:*?<>|\"#[]{}=%;", "Warning message does not display");
        CustomAlert.acceptAlert();
        newOrEditPanelDialog.cancelDialog();
        PanelsPage panelsPage = new PanelsPage();
        panelsPage.openAddNewPanelDialog();
        Log.info("Step 11. Enter value into Display Name field with special character is @");
        newOrEditPanelDialog.enterDialog("Logigear@", PanelSeriesType.NAME);
        Log.info("Step 12. Observe the current page");
        softAssert.assertEquals(panelsPage.getTextPanelName("Logigear@"), "Logigear@", "New Panel does not create");
        //Post condition
        panelsPage
                .deletePanel("Logigear@");
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that correct panel setting form is displayed with corresponding panel type selected")
    public void TC31() {
        Log.info("Step 1. Navigate to Dashboard login page");
        Log.info("Step 2. Login with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        NewOrEditPanelDialog_DisplaySetting newOrEditPanelDialog = dashBoardPage
                .navigateToPanelsPage()
                .openAddNewPanelDialog();
        newOrEditPanelDialog.selectRadioChart();
        SoftAssert softAssert = new SoftAssert();
        Log.info("Step 5. Verify that chart panel setting form is displayed with corresponding panel type selected");
        softAssert.assertTrue(newOrEditPanelDialog.isFormSettingDisplayed("Chart Settings", "Chart Settings"), "Chart panel setting form is not displayed ");
        Log.info("Step 6. Select Indicator type");
        newOrEditPanelDialog.selectRadioIndi();
        Log.info("Step 7. Verify that indicator panel setting form is displayed with corresponding panel type selected");
        softAssert.assertTrue(newOrEditPanelDialog.isFormSettingDisplayed("Indicator Settings", "Indicator Settings"), "indicator panel setting form is not displayed Indicator Settings");
        Log.info("Step 8. Select Report type");
        newOrEditPanelDialog.selectRadioReport();
        Log.info("Step 9. Verify that report panel setting form is displayed with corresponding panel type selected");
        softAssert.assertTrue(newOrEditPanelDialog.isFormSettingDisplayed("Report Settings", "Report Settings"), "Report panel setting form is not displayed View mode");
        Log.info("Step 10. Select Heat Maps type");
        newOrEditPanelDialog.selectRadioHeartMap();
        Log.info("Step 11. Verify that heatmap panel setting form is displayed with corresponding panel type selected");
        softAssert.assertTrue(newOrEditPanelDialog.isFormSettingDisplayed("Heat Map Settings", "Heat Map Settings"), "Heat map panel setting form is not displayed Heat Map Settings");
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that user is not allowed to create panel with duplicated Display Name")
    public void TC32() {
        SoftAssert softAssert = new SoftAssert();
        String PanelName = "Duplicated panel" + Constant.getRandomNumber();
        String WarningMsg = PanelName + " already exists. Please enter a different name.";
        Log.info("1. Navigate to Dashboard login page");
        Log.info("2. Login with valid account");
        Log.info("3. Click on Administer/Panels link");
        PanelsPage panelsPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount)
                .navigateToPanelsPage();
        Log.info("4. Click on Add new link");
        Log.info("5. Enter display name to \"Display name\" field: " + PanelName);
        Log.info("6. Click on OK button");
        panelsPage
                .openAddNewPanelDialog()
                .enterDialog(PanelName);
        Log.info("7. Click on Add new link again.");
        Log.info("8. Enter display name same with previous display name to \"display name\" field. ");
        Log.info("9 Click on OK button");
        panelsPage
                .openAddNewPanelDialog()
                .enterDialog(PanelName);
        Log.info("10. Check warning message show up");
        softAssert.assertEquals(CustomAlert.getTextAlert(), WarningMsg);
        //post condition
        Log.info("Post condition: Delete \"Duplicated panel\" panel");
        CustomAlert.acceptAlert();
        NewOrEditPanelDialog_DisplaySetting newOrEditPanelDialog = new NewOrEditPanelDialog_DisplaySetting();
        newOrEditPanelDialog.cancelDialog();
        panelsPage.deletePanel(PanelName);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that Data Profile listing of Add New Panel and Edit Panel control/form are in alphabetical order")
    public void TC33() {
        SoftAssert softAssert = new SoftAssert();
        String PanelName = "giang - panel" + Constant.getRandomNumber();

        Log.info("1. Navigate to Dashboard login page");
        Log.info("2. Login with valid account");
        Log.info("3. Click on Administer/Panels link");
        Log.info("4. Click on Add new link");
        Log.info("5. Verify that Data Profile list is in alphabetical order");
        NewOrEditPanelDialog_DisplaySetting newOrEditPanelDialog = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount)
                .navigateToPanelsPage()
                .openAddNewPanelDialog();
        softAssert.assertTrue(newOrEditPanelDialog.isDataProfileListAlphabetOrder());
        Log.info("6. Enter a display name to display name field: " + PanelName);
        Log.info("7. Click on OK button");
        newOrEditPanelDialog
                .enterDialog(PanelName);
        Log.info("8. Click on Edit link");
        PanelsPage panelsPage = new PanelsPage();
        panelsPage
                .openEditPanelDialog(PanelName);
        Log.info("9. Verify that Data Profile list is in alphabetical order");
        softAssert.assertTrue(newOrEditPanelDialog.isDataProfileListAlphabetOrder());
        //post condition
        Log.info("Post condition: Delete \"" + PanelName + "\" panel");
        newOrEditPanelDialog.cancelDialog();
        panelsPage.deletePanel(PanelName);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that newly created data profiles are populated correctly under the Data Profile dropped down menu in Add New Panel and Edit Panel control/form")
    public void TC34() {
        SoftAssert softAssert = new SoftAssert();
        String number = Constant.getRandomNumber();
        String ProfileName = "giang - data" + number;
        String PanelName = "giang - panel" + number;

        Log.info("1. Navigate to Dashboard login page");
        Log.info("2. Login with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        Log.info("3. Click on Administer/Data Profiles link");
        Log.info("4. Click on add new link");
        Log.info("5. Enter name to Name textbox: " + ProfileName);
        Log.info("6. Click on Finish button");
        dashBoardPage
                .navigateToProfilesPage()
                .openAddNewProfilePage()
                .enterDataProfileThenFinish(ProfileName);
        Log.info("7. Click on Administer/Panels link");
        Log.info("8. Click on add new link");
        NewOrEditPanelDialog_DisplaySetting newOrEditPanelDialog = dashBoardPage
                .navigateToPanelsPage()
                .openAddNewPanelDialog();
        Log.info("9. Verify that \"giang - data\" data profiles are populated correctly under the \"Data Profile\" dropped down menu.");
        softAssert.assertTrue(newOrEditPanelDialog.isSpecificDataProfileExist(ProfileName));
        Log.info("10. Enter display name to Display Name textbox: " + PanelName);
        Log.info("11. Click Ok button to create a panel");
        newOrEditPanelDialog.enterDialog(PanelName);
        Log.info("12. Click on edit link");
        PanelsPage panelsPage = new PanelsPage();
        panelsPage.openEditPanelDialog(PanelName);
        Log.info("13. Verify that \"" + PanelName + "\" data profiles are populated correctly under the \"Data Profile\" dropped down menu.");
        softAssert.assertTrue(newOrEditPanelDialog.isSpecificDataProfileExist(ProfileName));
        //post condition
        Log.info("Post condition: Delete \"" + ProfileName + "\" data profiles");
        Log.info("Post condition: Delete \"" + PanelName + "\" data profiles");
        newOrEditPanelDialog.cancelDialog();
        panelsPage
                .deletePanel(PanelName)
                .navigateToProfilesPage()
                .deleteProfile(ProfileName);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that no special character except '@' character is allowed to be inputted into \"Chart Title\" field")
    public void TC35() {
        SoftAssert softAssert = new SoftAssert();
        String number = Constant.getRandomNumber();
        String DisplayName1 = "Logigear" + number;
        String DisplayName2 = "Logigear@" + number;
        String ChartTitle1 = "Chart#$%" + number;
        String ChartTitle2 = "Chart@" + number;
        String DialogMsg = "Invalid title name. The name cannot contain high ASCII characters or any of the following characters: /:*?<>|\\\"#[]{}=%;";

        Log.info("1. Navigate to Dashboard login page");
        Log.info("2. Login with valid account");
        Log.info("3. Click Administer link");
        Log.info("4. Click Panel link");
        Log.info("5. Click Add New link");
        NewOrEditPanelDialog_DisplaySetting newOrEditPanelDialog = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount)
                .navigateToPanelsPage()
                .openAddNewPanelDialog();
        Log.info("6. Enter value into Display Name field");
        Log.info("7. Enter value into Chart Title field with special characters except \"@\"");
        Log.info("8. Click Ok button");
        newOrEditPanelDialog
                .enterDisplayName(DisplayName1)
                .enterChartTitle(ChartTitle1)
                .clickOkButton();
        Log.info("9. Observe the current page");
        String ActualDialogMsg = CustomAlert.getTextAlert();
        softAssert.assertEquals(ActualDialogMsg, DialogMsg);
        Log.info("10. Close Warning Message box");
        CustomAlert.acceptAlert();
        newOrEditPanelDialog.cancelDialog();
        Log.info("11. Click Add New link");
        Log.info("12. Enter value into Display Name field");
        Log.info("13. Enter value into Chart Title field with special character is @");
        PanelsPage panelsPage = new PanelsPage();
        panelsPage
                .openAddNewPanelDialog()
                .enterDisplayName(DisplayName2)
                .enterChartTitle(ChartTitle2)
                .enterChartSeriesRandom()
                .clickOkButton();
        Log.info("14. Observe the current page");
        softAssert.assertTrue(panelsPage.isSpecificPanelExist(DisplayName2));
        //post condition
        Log.info("Post condition: Delete the newly created panel");
        panelsPage
                .deletePanel(DisplayName2);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that all chart types ( Pie, Single Bar, Stacked Bar, Group Bar, Line ) are listed correctly under Chart Type dropped down menu.")
    public void TC36() {
        SoftAssert softAssert = new SoftAssert();
        String PageName = "main_hung" + Constant.getRandomNumber();
        List<String> ChartList = Arrays.asList("Pie", "Single Bar", "Stacked Bar", "Group Bar", "Line");

        Log.info("1. Navigate to Dashboard login page");
        Log.info("2. Select a specific repository");
        Log.info("3. Enter valid Username and Password");
        Log.info("4. Click 'Login' button");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginOtherRepo(Constant.Repository_Test, defaultAccount);
        Log.info("5. Click 'Add Page' link");
        Log.info("6. Enter Page Name: " + PageName);
        Log.info("7. Click 'OK' button");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage(PageName);
        Log.info("8. Click 'Choose Panels' button");
        Log.info("9. Click 'Create new panel' button");
        Log.info("10. Click 'Chart Type' drop-down menu");
        NewOrEditPanelDialog_DisplaySetting newOrEditPanelDialog = dashBoardPage
                .openChoosePanelPage()
                .openAddNewPanelDialog()
                .clickOnChartType();
        Log.info("11. Check that 'Chart Type' are listed 5 options: 'Pie', 'Single Bar', 'Stacked Bar', 'Group Bar' and 'Line'");
        softAssert.assertTrue(newOrEditPanelDialog.isChartTypeListExist(ChartList));
        newOrEditPanelDialog.cancelDialog();
        //post condition
        Log.info("Post condition: Delete the newly created page");
        PanelsPage panelsPage = new PanelsPage();
        panelsPage
                .clickOnSpecificMenuTab(PageName)
                .deleteSpecificMenuTab(PageName, true);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that Category, Series and Caption field are enabled and disabled correctly corresponding to each type of the Chart Type")
    public void TC37() {
        SoftAssert softAssert = new SoftAssert();
        String PageName = "main_hung" + Constant.getRandomNumber();
        Log.info("1. Navigate to Dashboard login page");
        Log.info("2. Select a specific repository ");
        Log.info("3. Enter valid Username and Password");
        Log.info("4. Click 'Login' button");
        Log.info("5. Click 'Add Page' button");
        Log.info("6. Enter Page Name: " + PageName);
        Log.info("7. Click 'OK' button");
        Log.info("8. Click 'Choose Panels' button below '" + PageName + "' button");
        Log.info("9. Click 'Create new panel' button");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginOtherRepo(Constant.Repository_Test, defaultAccount);
        NewOrEditPanelDialog_DisplaySetting newOrEditPanelDialog = dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage(PageName)
                .openChoosePanelPage()
                .openAddNewPanelDialog();
        Log.info("10. Click 'Chart Type' drop-down menu");
        Log.info("11. Select 'Pie' Chart Type");
        Log.info("12. Check that 'Category' and 'Caption' are disabled, 'Series' is enabled");
        newOrEditPanelDialog.enterChartType("Pie");
        softAssert.assertFalse(newOrEditPanelDialog.isFieldEnable("Category"));
        softAssert.assertFalse(newOrEditPanelDialog.isFieldEnable("Caption"));
        softAssert.assertTrue(newOrEditPanelDialog.isFieldEnable("Series"));
        Log.info("13. Click 'Chart Type' drop-down menu");
        Log.info("14. Select 'Single Bar' Chart Type");
        Log.info("15. Check that 'Category' is disabled, 'Series' and 'Caption' are enabled");
        newOrEditPanelDialog.enterChartType("Single Bar");
        softAssert.assertFalse(newOrEditPanelDialog.isFieldEnable("Category"));
        softAssert.assertTrue(newOrEditPanelDialog.isFieldEnable("Caption"));
        softAssert.assertTrue(newOrEditPanelDialog.isFieldEnable("Series"));
        Log.info("16. Click 'Chart Type' drop-down menu");
        Log.info("17. Select 'Stacked Bar' Chart Type");
        Log.info("18. Check that 'Category' ,'Series' and 'Caption' are enabled");
        newOrEditPanelDialog.enterChartType("Stacked Bar");
        softAssert.assertTrue(newOrEditPanelDialog.isFieldEnable("Category"));
        softAssert.assertTrue(newOrEditPanelDialog.isFieldEnable("Caption"));
        softAssert.assertTrue(newOrEditPanelDialog.isFieldEnable("Series"));
        Log.info("19. Click 'Chart Type' drop-down menu");
        Log.info("20. Select 'Group Bar' Chart Type");
        Log.info("21. Check that 'Category' ,'Series' and 'Caption' are enabled");
        newOrEditPanelDialog.enterChartType("Group Bar");
        softAssert.assertTrue(newOrEditPanelDialog.isFieldEnable("Category"));
        softAssert.assertTrue(newOrEditPanelDialog.isFieldEnable("Caption"));
        softAssert.assertTrue(newOrEditPanelDialog.isFieldEnable("Series"));
        Log.info("22. Click 'Chart Type' drop-down menu");
        Log.info("23. Select 'Line' Chart Type");
        Log.info("24. Check that 'Category' ,'Series' and 'Caption' are enabled");
        newOrEditPanelDialog.enterChartType("Line");
        softAssert.assertTrue(newOrEditPanelDialog.isFieldEnable("Category"));
        softAssert.assertTrue(newOrEditPanelDialog.isFieldEnable("Caption"));
        softAssert.assertTrue(newOrEditPanelDialog.isFieldEnable("Series"));
        //post condition
        Log.info("Post condition: Delete the newly created page");
        newOrEditPanelDialog.cancelDialog();
        dashBoardPage
                .clickOnSpecificMenuTab(PageName)
                .deleteSpecificMenuTab(PageName, true);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that all settings within \"Add New Panel\" and \"Edit Panel\" form stay changed when user switches between \"2D\" and \"3D\" radio buttons")
    public void TC38() {
        SoftAssert softAssert = new SoftAssert();
        String PageName = Constant.getPageName();
        String PanelName = Constant.getPanelName();
        String ChartTitle = "hung_chart" + Constant.getRandomNumber();
        List<String> ChartList = Arrays.asList("Pie", "Single Bar", "Stacked Bar", "Group Bar", "Line");
        NewOrEditPanelDialog_DisplaySetting newOrEditPanelDialog = loginPage
                .openLoginPage()
                .loginOtherRepo("BaiTap", defaultAccount)
                .openAddPageDialog()
                .enterDialogNewOrEditPage(PageName)
                .openChoosePanelPage()
                .openAddNewPanelDialog()
                .enterChartType(ChartList.get(2))
                .enterDataProfile(1)
                .enterDisplayName(PanelName)
                .enterChartTitle(ChartTitle)
                .selectShowCb()
                .selectRadio("Top")
                .selectRadio("3D");
        Log.info("Step 17.Verify 1 - Check that settings of 'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' stay changed.");
        softAssert.assertTrue(newOrEditPanelDialog.isDataProFileUnchange("Test Case Execution"), "DataProfile is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isNameUnchange(PanelName), "PanelName is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isChartTitleUnchange(ChartTitle), "ChartTitle is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isChartTypeUnchange("Stacked Bar"), "ChartType is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isShowTitleUnchange(true), "ShowTitle is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isRadioUnchange("Top"), "Legends is changed");

        newOrEditPanelDialog.selectRadio("2D");
        Log.info("Step 17.Verify 2 - Check that settings of 'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' stay changed.");
        softAssert.assertTrue(newOrEditPanelDialog.isDataProFileUnchange("Test Case Execution"), "DataProfile is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isNameUnchange(PanelName), "PanelName is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isChartTitleUnchange(ChartTitle), "ChartTitle is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isChartTypeUnchange("Stacked Bar"), "ChartType is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isShowTitleUnchange(true), "ShowTitle is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isRadioUnchange("Top"), "Legends is changed");

        newOrEditPanelDialog.enterOptionSeries(PanelSeriesType.NAME)
                .enterOptionCatogy(PanelSeriesType.LOCATION)
                .clickOkButton();
        PanelConfigurationDialog panelConfigurationDialog = new PanelConfigurationDialog();
        panelConfigurationDialog.selectPageCreated(PageName)
                .enterFolderName("/Dashboard")
                .panelConfig();
        newOrEditPanelDialog.clickEditPanel(PanelName)
                .selectRadio("3D");
        Log.info("Step 24.Check that settings of 'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' stay changed.");
        softAssert.assertTrue(newOrEditPanelDialog.isDataProFileUnchange("Test Case Execution"), "DataProfile is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isNameUnchange(PanelName), "PanelName is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isChartTitleUnchange(ChartTitle), "ChartTitle is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isChartTypeUnchange("Stacked Bar"), "ChartType is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isShowTitleUnchange(true), "ShowTitle is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isRadioUnchange("Top"), "Legends is changed");

        newOrEditPanelDialog.selectRadio("2D");
        Log.info("Step 26.Check that settings of 'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' stay changed.");
        softAssert.assertTrue(newOrEditPanelDialog.isDataProFileUnchange("Test Case Execution"), "DataProfile is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isNameUnchange(PanelName), "PanelName is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isChartTitleUnchange(ChartTitle), "ChartTitle is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isChartTypeUnchange("Stacked Bar"), "ChartType is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isShowTitleUnchange(true), "ShowTitle is changed");
        softAssert.assertTrue(newOrEditPanelDialog.isRadioUnchange("Top"), "Legends is changed");
        newOrEditPanelDialog.clickCancelButton();
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that all settings within \"Add New Panel\" and \"Edit Panel\" form stay unchanged when user switches between \"Legends\" radio buttons")
    public void TC39() {
        SoftAssert softAssert = new SoftAssert();
        Log.info("Login with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        dashBoardPage.navigateToPanelsPage()
                .openAddNewPanelDialog();
        NewOrEditPanelDialog_DisplaySetting newOrEditPanel = new NewOrEditPanelDialog_DisplaySetting();
        Log.info("Click None radio button for Legend");
        newOrEditPanel.selectRadio("None");
        Log.info("All settings are unchange in Add New Panel dialog");
        softAssert.assertTrue(newOrEditPanel.isRadioChartSelect(), "Chart Radio is changed");
        softAssert.assertTrue(newOrEditPanel.isChartTypeUnchange("Pie"), "Chart Type is changed");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("2D"), "");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("None"), "Legends Radio is changed");

        Log.info("Click Top radio button for Legend");
        newOrEditPanel.selectRadio("Top");
        Log.info("All settings are unchange in Add New Panel dialog");
        softAssert.assertTrue(newOrEditPanel.isRadioChartSelect(), "Chart Radio is changed");
        softAssert.assertTrue(newOrEditPanel.isChartTypeUnchange("Pie"), "Chart Type is changed");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("2D"), "");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("Top"), "Legends Radio is changed");

        Log.info("Click Right radio button for Legend");
        newOrEditPanel.selectRadio("Right");
        Log.info("All settings are unchange in Add New Panel dialog");
        softAssert.assertTrue(newOrEditPanel.isRadioChartSelect(), "Chart Radio is changed");
        softAssert.assertTrue(newOrEditPanel.isChartTypeUnchange("Pie"), "Chart Type is changed");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("2D"), "");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("Right"), "Legends Radio is changed");

        Log.info("Click Bottom radio button for Legend");
        newOrEditPanel.selectRadio("Bottom");
        Log.info("All settings are unchange in Add New Panel dialog");
        softAssert.assertTrue(newOrEditPanel.isRadioChartSelect(), "Chart Radio is changed");
        softAssert.assertTrue(newOrEditPanel.isChartTypeUnchange("Pie"), "Chart Type is changed");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("2D"), "");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("Bottom"), "Legends Radio is changed");

        Log.info("Click Left radio button for Legend");
        newOrEditPanel.selectRadio("Left");
        Log.info("All settings are unchange in Add New Panel dialog");
        softAssert.assertTrue(newOrEditPanel.isRadioChartSelect(), "Chart Radio is changed");
        softAssert.assertTrue(newOrEditPanel.isChartTypeUnchange("Pie"), "Chart Type is changed");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("2D"), "");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("Left"), "Legends Radio is changed");

        String PanelName1 = Constant.getPanelName();
        newOrEditPanel.enterDialog(PanelName1, PanelSeriesType.NAME);
        PanelsPage panelsPage = new PanelsPage();
        panelsPage.openEditPanelDialog(PanelName1);
        Log.info("Click None radio button for Legend");
        newOrEditPanel.selectRadio("None");
        Log.info("All settings are unchange in Add New Panel dialog");
        softAssert.assertTrue(newOrEditPanel.isNameUnchange(PanelName1));
        softAssert.assertTrue(newOrEditPanel.isNameUnchange(PanelName1), "PanelName is changed");
        softAssert.assertTrue(newOrEditPanel.isChartTypeUnchange("Pie"), "Chart Type is changed");
        softAssert.assertTrue(newOrEditPanel.isSeriesUnchange(PanelSeriesType.NAME), "Series Type is changed");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("2D"), "");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("None"), "Legends Radio is changed");

        Log.info("Click Top radio button for Legend");
        newOrEditPanel.selectRadio("Top");
        Log.info("All settings are unchange in Add New Panel dialog");
        softAssert.assertTrue(newOrEditPanel.isNameUnchange(PanelName1), "PanelName is changed");
        softAssert.assertTrue(newOrEditPanel.isChartTypeUnchange("Pie"), "Chart Type is changed");
        softAssert.assertTrue(newOrEditPanel.isSeriesUnchange(PanelSeriesType.NAME), "Series Type is changed");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("2D"), "");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("Top"), "Legends Radio is changed");

        Log.info("Click Right radio button for Legend");
        newOrEditPanel.selectRadio("Right");
        Log.info("All settings are unchange in Add New Panel dialog");
        softAssert.assertTrue(newOrEditPanel.isNameUnchange(PanelName1), "PanelName is changed");
        softAssert.assertTrue(newOrEditPanel.isChartTypeUnchange("Pie"), "Chart Type is changed");
        softAssert.assertTrue(newOrEditPanel.isSeriesUnchange(PanelSeriesType.NAME), "Series Type is changed");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("2D"), "");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("Right"), "Legends Radio is changed");

        Log.info("Click Bottom radio button for Legend");
        newOrEditPanel.selectRadio("Bottom");
        Log.info("All settings are unchange in Add New Panel dialog");
        softAssert.assertTrue(newOrEditPanel.isNameUnchange(PanelName1), "PanelName is changed");
        softAssert.assertTrue(newOrEditPanel.isChartTypeUnchange("Pie"), "Chart Type is changed");
        softAssert.assertTrue(newOrEditPanel.isSeriesUnchange(PanelSeriesType.NAME), "Series Type is changed");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("2D"), "");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("Bottom"), "Legends Radio is changed");

        Log.info("Click Left radio button for Legend");
        newOrEditPanel.selectRadio("Left");
        Log.info("All settings are unchange in Add New Panel dialog");
        softAssert.assertTrue(newOrEditPanel.isNameUnchange(PanelName1), "PanelName is changed");
        softAssert.assertTrue(newOrEditPanel.isChartTypeUnchange("Pie"), "Chart Type is changed");
        softAssert.assertTrue(newOrEditPanel.isSeriesUnchange(PanelSeriesType.NAME), "Series Type is changed");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("2D"), "");
        softAssert.assertTrue(newOrEditPanel.isRadioUnchange("Left"), "Legends Radio is changed");
        newOrEditPanel.cancelDialog();
        panelsPage.deletePanel(PanelName1);
        softAssert.assertAll();
    }

    @Test
    @Description("")
    public void TC40() {
        SoftAssert softAssert = new SoftAssert();
        String PageName = Constant.getPageName();
        List<String> ChartList = Arrays.asList("Pie", "Single Bar", "Stacked Bar", "Group Bar", "Line");
        NewOrEditPanelDialog_DisplaySetting newOrEditPanelDialog = loginPage
                .openLoginPage()
                .loginOtherRepo("BaiTap", defaultAccount)
                .openAddPageDialog()
                .enterDialogNewOrEditPage(PageName)
                .openChoosePanelPage()
                .openAddNewPanelDialog()
                .enterChartType(ChartList.get(0));
        Log.info("Step 12.Check that 'Categories' checkbox is disabled, 'Series' checkbox, 'Value' checkbox and 'Percentage' checkbox are enabled");
        softAssert.assertFalse(newOrEditPanelDialog.isCategoryEnable(), "Pie - Category checkbox still enable");
        softAssert.assertTrue(newOrEditPanelDialog.isSeriesEnbale(), "Pie - Series checkbox is disable");
        softAssert.assertTrue(newOrEditPanelDialog.isValueEnable(), "Pie - Value checkbox is disbale");
        softAssert.assertTrue(newOrEditPanelDialog.isPerceEnable(), "Pie - Percentage checkbox is disable");

        newOrEditPanelDialog.enterChartType(ChartList.get(1));
        Log.info("Check that 'Categories' checkbox is disabled, 'Series' checkbox, 'Value' checkbox and 'Percentage' checkbox are enabled");
        softAssert.assertFalse(newOrEditPanelDialog.isCategoryEnable(), "Single Bar - Category checkbox still enable");
        softAssert.assertTrue(newOrEditPanelDialog.isSeriesEnbale(), "Single Bar - Series checkbox is disable");
        softAssert.assertTrue(newOrEditPanelDialog.isValueEnable(), "Single Bar - Value checkbox is disbale");
        softAssert.assertTrue(newOrEditPanelDialog.isPerceEnable(), "Single Bar - Percentage checkbox is disable");

        newOrEditPanelDialog.enterChartType(ChartList.get(2));
        Log.info("Check that 'Categories' checkbox, 'Series' checkbox, 'Value' checkbox and 'Percentage' checkbox are enabled");
        softAssert.assertTrue(newOrEditPanelDialog.isCategoryEnable(), "Stacked Bar - Category checkbox still disable");
        softAssert.assertTrue(newOrEditPanelDialog.isSeriesEnbale(), "Stacked Bar - Series checkbox is disable");
        softAssert.assertTrue(newOrEditPanelDialog.isValueEnable(), "Stacked Bar - Value checkbox is disbale");
        softAssert.assertTrue(newOrEditPanelDialog.isPerceEnable(), "Stacked Bar - Percentage checkbox is disable");

        newOrEditPanelDialog.enterChartType(ChartList.get(3));
        Log.info("Check that 'Categories' checkbox, 'Series' checkbox, 'Value' checkbox and 'Percentage' checkbox are enabled");
        softAssert.assertTrue(newOrEditPanelDialog.isCategoryEnable(), "Group Bar - Category checkbox still disable");
        softAssert.assertTrue(newOrEditPanelDialog.isSeriesEnbale(), "Group Bar - Series checkbox is disable");
        softAssert.assertTrue(newOrEditPanelDialog.isValueEnable(), "Group Bar - Value checkbox is disbale");
        softAssert.assertTrue(newOrEditPanelDialog.isPerceEnable(), "Group Bar - Percentage checkbox is disable");

        newOrEditPanelDialog.enterChartType(ChartList.get(4));
        Log.info("Check that 'Categories' checkbox, 'Series' checkbox, 'Value' checkbox and 'Percentage' checkbox are disabled");
        softAssert.assertFalse(newOrEditPanelDialog.isCategoryEnable(), "Line - Category checkbox still enable");
        softAssert.assertFalse(newOrEditPanelDialog.isSeriesEnbale(), "Line - Series checkbox is enable");
        softAssert.assertFalse(newOrEditPanelDialog.isValueEnable(), "Line - Value checkbox is enable");
        softAssert.assertFalse(newOrEditPanelDialog.isPerceEnable(), "Line - Percentage checkbox is enable");

        softAssert.assertAll();
    }

    @Test
    @Description("Verify that all settings within \"Add New Panel\" and \"Edit Panel\" form stay unchanged when user switches between \"Data Labels\" check boxes buttons")
    public void TC41() {
        SoftAssert softAssert = new SoftAssert();
        Log.info("Login with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        dashBoardPage.navigateToPanelsPage()
                .openAddNewPanelDialog();
        NewOrEditPanelDialog_DisplaySetting newOrEditPanel = new NewOrEditPanelDialog_DisplaySetting();
        Log.info("Check Series checkbox for Data Labels");
        newOrEditPanel.clickSeries();
        Log.info("All settings are unchange in Add New Panel dialog");
        softAssert.assertTrue(newOrEditPanel.isRadioChartSelect(), "Series - Chart Radio is changed");
        softAssert.assertTrue(newOrEditPanel.isSeriesSelect(), "Series - Series checkbox does not select");
        newOrEditPanel.clickSeries();

        Log.info("Check Value checkbox for Data Labels");
        newOrEditPanel.clickValue();
        Log.info("All settings are unchange in Add New Panel dialog");
        softAssert.assertTrue(newOrEditPanel.isRadioChartSelect(), "Value - Chart Radio is changed");
        softAssert.assertTrue(newOrEditPanel.isValueSelect(), "Value - Series checkbox does not select");
        newOrEditPanel.clickValue();

        Log.info("Check  Percentage checkbox for Data Labels");
        newOrEditPanel.clickPercentage();
        Log.info("All settings are unchange in Add New Panel dialog");
        softAssert.assertTrue(newOrEditPanel.isRadioChartSelect(), " Percentage - Chart Radio is changed");
        softAssert.assertTrue(newOrEditPanel.isPercentageSelect(), " Percentage - Series checkbox does not select");
        newOrEditPanel.clickPercentage();

        String PanelName1 = Constant.getPanelName();
        newOrEditPanel.enterDialog(PanelName1, PanelSeriesType.NAME);
        PanelsPage panelsPage = new PanelsPage();
        panelsPage.openEditPanelDialog(PanelName1);

        Log.info("Check Series checkbox for Data Labels");
        newOrEditPanel.clickSeries();
        Log.info("All settings are unchange in Add New Panel dialog");
        softAssert.assertTrue(newOrEditPanel.isNameUnchange(PanelName1), "PanelName is changed");
        softAssert.assertTrue(newOrEditPanel.isSeriesSelect(), "Series - Series checkbox does not select");
        newOrEditPanel.clickSeries();

        Log.info("Check Value checkbox for Data Labels");
        newOrEditPanel.clickValue();
        Log.info("All settings are unchange in Add New Panel dialog");
        softAssert.assertTrue(newOrEditPanel.isNameUnchange(PanelName1), "PanelName is changed");
        softAssert.assertTrue(newOrEditPanel.isValueSelect(), "Value - Series checkbox does not select");
        newOrEditPanel.clickValue();

        Log.info("Check  Percentage checkbox for Data Labels");
        newOrEditPanel.clickPercentage();
        Log.info("All settings are unchange in Add New Panel dialog");
        softAssert.assertTrue(newOrEditPanel.isNameUnchange(PanelName1), "PanelName is changed");
        softAssert.assertTrue(newOrEditPanel.isPercentageSelect(), " Percentage - Series checkbox does not select");

        newOrEditPanel.cancelDialog();
        panelsPage.deletePanel(PanelName1);
        softAssert.assertAll();


    }

    @Test
    @Description("Verify that user is able to navigate properly to folders with \"Select Folder\" form")
    public void TC47() {
        String PageName = "Page 1";
        String PanelName = "Panel 1";
        String ChartTitle = "ChartTitle 1";
        String FolderName = "Car Rental";
        PanelConfigurationDialog panelConfigurationDialog = new PanelConfigurationDialog();

        Log.info("Navigate to Dashboard login page");
        Log.info("Login with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        Log.info("Create a new page");
        Log.info("Click Choose Panel button");
        Log.info("Click Create New Panel button");
        Log.info("Enter all required fields on Add New Panel page");
        Log.info("Click Ok button");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage(PageName)
                .navigateToPanelsPage()
                .openAddNewPanelDialog()
                .enterDisplayName(PanelName)
                .enterChartTitle(ChartTitle)
                .enterChartSeriesRandom()
                .clickOkButton();
        PanelsPage panelsPage = new PanelsPage();
        Log.info("Click Select Folder button on Panel Configuration dialog");
        Log.info("Choose folder name in Folder Form");
        panelsPage.clickOnSpecificMenuTab(PageName);
        ChoosePanelPage choosePanelPage = panelsPage
                .openChoosePanelPage();
        choosePanelPage.selectPanelChartSubItems(PanelName);
        choosePanelPage.choosePanelSettingFolder(FolderName);
        Log.info("Click Ok button on Select Folder form");
        panelConfigurationDialog.panelConfig();
        Log.info("Observe the current page");
        Log.info("User is able to select properly folder with Select Folder form");
        Assert.assertTrue(dashBoardPage.isFolderNameDisplayed(FolderName));
        Log.info("Post Condition");
        panelsPage
                .clickOnSpecificMenuTab(PageName)
                .deleteSpecificMenuTab(PageName, true);
        dashBoardPage
                .navigateToPanelsPage()
                .deletePanel(PanelName);
    }
}
