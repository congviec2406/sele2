package TestCase.testArchitect;

import Common.common.Log;
import Common.constant.Constant;
import DataObject.dataObject.Account;
import wrapper.CustomAlert;
import PageObject.testArchitect.DashBoardPage;
import jdk.jfr.Description;
import org.openqa.selenium.ElementClickInterceptedException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Common.common.Log;

import java.util.Arrays;
import java.util.List;

public class DashBoardTest extends BaseTest {

    Account defaultAccount = new Account(Constant.UserName, Constant.Password);

    @Test
    @Description("Verify that user is unable open more than 1 New Page dialog")
    public void TC11() {
        Log.info("1. Navigate to Dashboard login page");
        Log.info("2. Login with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        Log.info("3. Go to Global Setting -> Add page");
        dashBoardPage.openAddPageDialog();
        Boolean GlobalSettingClickable = true;
        try {
            Log.info("4. Try to go to Global Setting -> Add page again");
            dashBoardPage.openAddPageDialog();
        } catch (ElementClickInterceptedException e) {
            GlobalSettingClickable = false;
        }
        Log.info("5. Observe the current page");
        Assert.assertTrue(dashBoardPage.isDialogNewPageVisible());
        Assert.assertFalse(GlobalSettingClickable);
        //Post Condition
        Log.info("Post condition: Logout");
        dashBoardPage.cancelDialog();
        dashBoardPage.logOut();
    }

    @Test
    @Description("Verify that user is able to add additional pages besides Overview page successfully")
    public void TC12() {
        SoftAssert softAssert = new SoftAssert();
        String PageName = "Test" + Constant.getRandomNumber();

        Log.info("1. Navigate to Dashboard login page");
        Log.info("2. Login with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        Log.info("3. Go to Global Setting -> Add page");
        Log.info("4. Enter Page Name field: " + PageName);
        Log.info("5. Click OK button");
        dashBoardPage.openAddPageDialog()
                .enterDialogNewOrEditPage(PageName);
        Log.info("6. Check \"" + PageName + "\" page is displayed besides \"Overview\" page");
        softAssert.assertTrue(dashBoardPage.isTabBesideOverview(PageName));
        //Post Condition
        Log.info("Post condition: Delete newly added page");
        dashBoardPage.deleteSpecificMenuTab(PageName, true);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that the newly added main parent page is positioned at the location specified as set with Displayed After field of New Page form on the main page bar/Parent Page dropped down menu")
    public void TC13() {
        SoftAssert softAssert = new SoftAssert();
        String number = Constant.getRandomNumber();
        String PageName1 = "Page1" + number;
        String PageName2 = "Page2" + number;

        Log.info("1. Navigate to Dashboard login page");
        Log.info("2. Log in specific repository with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginOtherRepo(Constant.Repository_Test, defaultAccount);
        Log.info("3. Go to Global Setting -> Add page");
        Log.info("4. Enter Page Name field: " + PageName1);
        Log.info("5. Click OK button");
        Log.info("6. Go to Global Setting -> Add page");
        Log.info("7. Enter Page Name field: " + PageName2);
        Log.info("8. Click on Displayed After dropdown list");
        Log.info("9. Select specific page: " + PageName1);
        Log.info("10. Click OK button");
        dashBoardPage.openAddPageDialog()
                .enterDialogNewOrEditPage(PageName1)
                .openAddPageDialog()
                .enterDialogNewOrEditPage_DisplayAfter(PageName2, "", PageName1, "");
        Log.info("11. Check \"" + PageName2 + "\" page is positioned besides the \"" + PageName1 + "\" page");
        softAssert.assertTrue(dashBoardPage.isTabBesideOtherTab(PageName2, PageName1));
        //Post Condition
        Log.info("Post condition: Delete newly added main child page and its parent page");
        dashBoardPage.deleteSpecificMenuTab(PageName1, true);
        dashBoardPage.deleteSpecificMenuTab(PageName2, true);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that Public pages can be visible and accessed by all users of working repository")
    public void TC14() {
        SoftAssert softAssert = new SoftAssert();
        String PageName = "Test" + Constant.getRandomNumber();
        Account account1 = new Account(Constant.UserName, Constant.Password);
        Account account2 = new Account("administrator", "");

        Log.info("1. Navigate to Dashboard login page");
        Log.info("2. Log in specific repository with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginOtherRepo(Constant.Repository_Test, account1);
        Log.info("3. Go to Global Setting -> Add page");
        Log.info("4. Enter Page Name field: " + PageName);
        Log.info("5. Check Public checkbox");
        Log.info("6. Click OK button");
        dashBoardPage.openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(PageName, "", "", "True");
        Log.info("7. Click on Log out link");
        dashBoardPage.logOut();
        Log.info("8. Log in with another valid account");
        loginPage
                .loginOtherRepo(Constant.Repository_Test, account2);
        Log.info("9. Check newly added page is visible");
        softAssert.assertTrue(dashBoardPage.isSpecifyMenuTabExist(PageName));
        //Post Condition
        Log.info("Post condition: Log in  as creator page account and delete newly added page");
        dashBoardPage
                .logOut()
                .loginOtherRepo(Constant.Repository_Test, account1)
                .deleteSpecificMenuTab(PageName, true);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that non Public pages can only be accessed and visible to their creators with condition that all parent pages above it are Public")
    public void TC15() {
        SoftAssert softAssert = new SoftAssert();
        String number = Constant.getRandomNumber();
        String ParentPage = "Test" + number;
        String ChildPage = "Test Child" + number;
        Account account1 = new Account(Constant.UserName, Constant.Password);
        Account account2 = new Account(Constant.UserAd, Constant.BLANK);

        Log.info("1. Navigate to Dashboard login page");
        Log.info("2. Log in specific repository with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginOtherRepo(Constant.Repository_Test, account1);
        Log.info("3. Go to Global Setting -> Add page");
        Log.info("4. Enter Page Name field: " + ParentPage);
        Log.info("5. Check Public checkbox");
        Log.info("6. Click OK button");
        Log.info("7. Go to Global Setting -> Add page");
        Log.info("8. Enter Page Name field: " + ChildPage);
        Log.info("9. Click on  Select Parent dropdown list");
        Log.info("10. Select specific page: " + ParentPage);
        Log.info("11. Click OK button");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(ParentPage, "", "", "True")
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(ChildPage, "", ParentPage, "");
        Log.info("12. Click on Log out link");
        dashBoardPage.logOut();
        Log.info("13. Log in with another valid account");
        loginPage.loginOtherRepo(Constant.Repository_Test, account2);
        Log.info("14. Check children is invisible");
        softAssert.assertFalse(dashBoardPage.isSpecifyMenuTabExist_Child(ParentPage, ChildPage));
        //Post Condition
        Log.info("Post condition: Log in  as creator page account and delete newly added page and its parent page");
        dashBoardPage
                .logOut()
                .loginOtherRepo(Constant.Repository_Test, account1)
                .deleteSpecificMenuTab_Child(ParentPage, ChildPage, true)
                .deleteSpecificMenuTab(ParentPage, true);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that user is able to edit the Public setting of any page successfully")
    public void TC16() {
        SoftAssert softAssert = new SoftAssert();
        String number = Constant.getRandomNumber();
        String Page1 = "Test" + number;
        String Page2 = "Another Test" + number;
        Account account1 = new Account(Constant.UserName, Constant.Password);
        Account account2 = new Account("administrator", "");

        Log.info("1. Navigate to Dashboard login page");
        Log.info("2. Log in specific repository with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginOtherRepo(Constant.Repository_Test, account1);
        Log.info("3. Go to Global Setting -> Add page");
        Log.info("4. Enter Page Name: " + Page1);
        Log.info("5. Click OK button");
        Log.info("6. Go to Global Setting -> Add page");
        Log.info("7. Enter Page Name: " + Page2);
        Log.info("8. Check Public checkbox");
        Log.info("9. Click OK button");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(Page1, "", "", "false")
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(Page2, "", "", "true");
        Log.info("10. Click on \"" + Page1 + "\" page");
        Log.info("11. Click on \"Edit\" link");
        dashBoardPage
                .clickOnSpecificMenuTab(Page1)
                .openEditPageDialog();
        Log.info("12. Check \"Edit Page\" pop up window is displayed");
        Assert.assertTrue(dashBoardPage.isDialogEditPageVisible());
        Log.info("13. Check Public checkbox");
        Log.info("14. Click OK button");
        dashBoardPage
                .enterDialogNewOrEditPage_ParentPage(Page1, "", "", "true");
        Log.info("15. Click on \"" + Page2 + "\" page");
        Log.info("16. Click on \"Edit\" link");
        dashBoardPage.clickOnSpecificMenuTab(Page2)
                .openEditPageDialog();
        Log.info("17. Check \"Edit Page\" pop up window is displayed");
        Assert.assertTrue(dashBoardPage.isDialogEditPageVisible());
        Log.info("18. Uncheck Public checkbox");
        Log.info("19. Click OK button");
        dashBoardPage
                .enterDialogNewOrEditPage_ParentPage(Page2, "", "", "false");
        Log.info("20. Click Log out link");
        dashBoardPage.logOut();
        Log.info("21. Log in with another valid account");
        loginPage.loginOtherRepo(Constant.Repository_Test, account2);
        Log.info("22. Check \"" + Page1 + "\" Page is visible and can be accessed");
        softAssert.assertTrue(dashBoardPage.isSpecifyMenuTabExist(Page1));
        Log.info("23. Check \"" + Page2 + "\" page is invisible");
        softAssert.assertFalse(dashBoardPage.isSpecifyMenuTabExist(Page2));
        //Post Condition
        Log.info("Post condition: Log in  as creator page account and delete newly added page");
        dashBoardPage
                .logOut()
                .loginOtherRepo(Constant.Repository_Test, account1)
                .deleteSpecificMenuTab(Page1, true)
                .deleteSpecificMenuTab(Page2, true);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that user can remove any main parent page except Overview page successfully and the order of pages stays persistent as long as there is not children page under it")
    public void TC17() {
        String ParentPage = "Test";
        String ChildPage = "Test Child";
        String ConfirmMessage1 = "Are you sure you want to remove this page?";
        String ConfirmMessage2 = "Cannot delete page '" + ParentPage + "' since it has child page(s).\n";

        Log.info("Navigate to Dashboard login page");
        Log.info("Log in specific repository with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        Log.info("Add a new parent page");
        Log.info("Add a children page of newly added page");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(ParentPage, "", "", "false")
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(ChildPage, "", ParentPage, "false");
        Log.info("Click on parent page");
        Log.info("Click \"Delete\" link");
        dashBoardPage.deleteSpecificMenuTab(ParentPage, false);
        Log.info("Check confirm message \"Are you sure you want to remove this page?\" appears");
        Log.info("Confirm message \"Are you sure you want to remove this page?\" appears");
        Assert.assertEquals(ConfirmMessage1, CustomAlert.getTextAlert());
        CustomAlert.acceptAlert();
        Log.info("Check warning message \"Cannot delete page 'Test' since it has child page(s).\" appears");
        Log.info("Warning message \"Cannot delete page 'Test' since it has child page(s).\" appears");
        Assert.assertEquals(ConfirmMessage2, CustomAlert.getTextAlert());
        CustomAlert.acceptAlert();
        Log.info("Click on  children page");
        Log.info("Click \"Delete\" link");
        dashBoardPage.deleteSpecificMenuTab_Child(ParentPage, ChildPage, false);
        Log.info("Check confirm message \"Are you sure you want to remove this page?\" appears");
        Log.info("Confirm message \"Are you sure you want to remove this page?\" appears");
        Assert.assertEquals(ConfirmMessage1, CustomAlert.getTextAlert());
        CustomAlert.acceptAlert();
        Log.info("Check children page is deleted");
        Log.info("Children page is deleted");
        Assert.assertFalse(dashBoardPage.isSpecifyMenuTabExist_Child(ParentPage, ChildPage));
        Log.info("Click on  parent page");
        Log.info("Click \"Delete\" link");
        dashBoardPage.deleteSpecificMenuTab(ParentPage, false);
        Log.info("Check confirm message \"Are you sure you want to remove this page?\" appears");
        Log.info("Confirm message \"Are you sure you want to remove this page?\" appears");
        Assert.assertEquals(ConfirmMessage1, CustomAlert.getTextAlert());
        CustomAlert.acceptAlert();
        Log.info("Check parent page is deleted");
        Log.info("Parent page is deleted");
        Assert.assertFalse(dashBoardPage.isSpecifyMenuTabExist(ParentPage));
        Log.info("Click on \"Overview\" page");
        dashBoardPage.clickOnSpecificMenuTab(Constant.Overview);
        Log.info("Check \"Delete\" link disappears");
        Log.info("\"Delete\" link disappears");
        Assert.assertFalse(dashBoardPage.isDeleteLinkOnGlobalSetting());
    }

    @Test
    @Description("Verify that user is able to add additional sibling pages to the parent page successfully")
    public void TC18() {
        String ParentPage = "Test";
        String ChildPage1 = "Test Child 1";
        String ChildPage2 = "Test Child 2";

        Log.info("Navigate to Dashboard login page");
        Log.info("Log in specific repository with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        Log.info("Go to Global Setting -> Add page");
        Log.info("Enter Page Name");
        Log.info("Click OK button");
        Log.info("Go to Global Setting -> Add page");
        Log.info("Enter Page Name");
        Log.info("Click on  Parent Page dropdown list");
        Log.info("Select a parent page");
        Log.info("Click OK button");
        Log.info("Go to Global Setting -> Add page");
        Log.info("Enter Page Name");
        Log.info("Click on  Parent Page dropdown list");
        Log.info("Select a parent page");
        Log.info("Click OK button");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(ParentPage, "", "", "false")
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(ChildPage1, "", ParentPage, "false")
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(ChildPage2, "", ParentPage, "false");
        Log.info("Check \"Test Child 2\" is added successfully");
        Log.info("\"Test Child 2\" is added successfully");
        dashBoardPage.clickOnSpecificMenuTab(ParentPage);
        Assert.assertTrue(dashBoardPage.isSpecifyMenuTabExist_Child(ParentPage, ChildPage2));
        Log.info("");
        dashBoardPage
                .deleteSpecificMenuTab_Child(ParentPage, ChildPage1, true)
                .deleteSpecificMenuTab_Child(ParentPage, ChildPage2, true)
                .deleteSpecificMenuTab(ParentPage, true);
    }

    @Test
    @Description("Verify that user is able to add additional sibbling page levels to the parent page successfully.")
    public void TC19() {
        String Page1 = "Page 1";

        Log.info("Navigate to Dashboard login page");
        Log.info("Login with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        Log.info("Go to Global Setting -> Add page");
        Log.info("Enter info into all required fields on New Page dialog");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(Page1, "", Constant.Overview, "false");
        Log.info("Observe the current page");
        Log.info("User is able to add additional sibling page levels to parent page successfully.");
        Log.info("In this case: Overview is parent page of page 1");
        dashBoardPage.clickOnSpecificMenuTab(Constant.Overview);
        Assert.assertTrue(dashBoardPage.isSpecifyMenuTabExist_Child(Constant.Overview, Page1));
        Log.info("Post Condition");
        dashBoardPage.deleteSpecificMenuTab_Child(Constant.Overview, Page1, true);
    }

    @Test
    @Description("Verify that user is able to delete sibling page as long as that page has not children page under it")
    public void TC20() {
        String Page1 = "Page 1";
        String Page2 = "Page 2";
        String ConfirmMessage1 = "Cannot delete page '" + Page1 + "' since it has child page(s).\n";
        List<String> parentListOfPage2 = Arrays.asList(Constant.Overview, Page1);

        Log.info("Navigate to Dashboard login page");
        Log.info("Login with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        Log.info("Go to Global Setting -> Add page");
        Log.info("Enter info into all required fields on New Page dialog");
        Log.info("Go to Global Setting -> Add page");
        Log.info("Enter info into all required fields on New Page dialog");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(Page1, "", Constant.Overview, "false")
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(Page2, "", Page1, "false");
        Log.info("Go to the first created page");
        Log.info("Click Delete link");
        Log.info("Click Ok button on Confirmation Delete page");
        dashBoardPage.deleteSpecificMenuTab_Child(Constant.Overview, Page1, true);
        Log.info("Observe the current page");
        Log.info("There is a message \"Cannot delete page \"page 1\" since it has child page(s).\"");
        Assert.assertEquals(ConfirmMessage1, CustomAlert.getTextAlert());
        Log.info("Close confirmation dialog");
        CustomAlert.acceptAlert();
        Log.info("Go to the second page");
        Log.info("Click Delete link");
        Log.info("Click Ok button on Confirmation Delete page");
        dashBoardPage.deleteSpecificMenuTab_Child(parentListOfPage2, Page2, true);
        Log.info("Observe the current page");
        Log.info("Page 2 is deleted successfully");
        Assert.assertFalse(dashBoardPage.isSpecifyMenuTabExist_Child(parentListOfPage2, Page2));
        Log.info("Post Condition");
        dashBoardPage.deleteSpecificMenuTab_Child(Constant.Overview, Page1, true);
    }

    @Test
    @Description("Verify that user is able to edit the name of the page (Parent/Sibling) successfully")
    public void TC21() {
        String Page1 = "Page 1";
        String Page2 = "Page 2";
        String Page3 = "Page 3";
        String Page4 = "Page 4";
        List<String> parentList1 = Arrays.asList(Constant.Overview);
        List<String> parentList2 = Arrays.asList(Constant.Overview, Page3);

        Log.info("Navigate to Dashboard login page");
        Log.info("Login with valid account");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        Log.info("Go to Global Setting -> Add page");
        Log.info("Enter info into all required fields on New Page dialog");
        Log.info("Go to Global Setting -> Add page");
        Log.info("Enter info into all required fields on New Page dialog");
        Log.info("Go to the first created page");
        Log.info("Click Edit link");
        Log.info("Enter another name into Page Name field");
        Log.info("Click Ok button on Edit Page dialog");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(Page1, "", Constant.Overview, "false")
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(Page2, "", Page1, "false")
                .clickOnSpecificMenuTab_Child(parentList1, Page1)
                .openEditPageDialog()
                .enterDialogNewOrEditPage_ParentPage(Page3, "", Constant.Overview, "false");
        Log.info("Observe the current page");
        Log.info("User is able to edit the name of parent page successfully");
        Assert.assertTrue(dashBoardPage.isSpecifyMenuTabExist_Child(Constant.Overview, Page3));
        Log.info("Go to the second created page");
        Log.info("Click Edit link");
        Log.info("Enter another name into Page Name field");
        Log.info("Click Ok button on Edit Page dialog");
        dashBoardPage
                .clickOnSpecificMenuTab_Child(parentList2, Page2)
                .openEditPageDialog()
                .enterDialogNewOrEditPage_ParentPage(Page4, "", Page3, "false");
        Log.info("Observe the current page");
        Log.info("User is able to edit the name of sibling page successfully");
        Assert.assertTrue(dashBoardPage.isSpecifyMenuTabExist_Child(parentList2, Page4));
        Log.info("Post Condition");
        dashBoardPage
                .deleteSpecificMenuTab_Child(parentList2, Page4, true)
                .deleteSpecificMenuTab_Child(parentList1, Page3, true);
    }

    @Test
    @Description("Verify that user is unable to duplicate the name of sibling page under the same parent page")
    public void TC22() {
        String parentPageName = "TC22_1" + Constant.getPageName();
        String childPageName = "TC22_2" + Constant.getPageName();
        String warningMessage = childPageName + " already exists. Please enter a different name.";

        Log.info("1. Login\n");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        Log.info("2. Add a new page - Test");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(parentPageName, "", "", "false");
        Assert.assertTrue(dashBoardPage.isSpecifyMenuTabExist(parentPageName));
        Log.info("3. Add a sibling page of new page - Test Child 1");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(childPageName, "", parentPageName, "false");
        Assert.assertTrue(dashBoardPage.isSpecifyMenuTabExist_Child(parentPageName, childPageName));
        Log.info("4. Go to Global Setting -> Add page\n" +
                "5. Enter Page Name - Test Child 1\n" +
                "6. lick on Parent Page dropdown list\n" +
                "7. Select a parent page - Test");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(childPageName, "", parentPageName, "false");
        Log.info("VP: Check warning message \"Test child already exist. Please enter a different name\" appears");
        Assert.assertEquals(warningMessage, CustomAlert.getTextAlert());
        CustomAlert.acceptAlert();
        Log.info("post condition");
        dashBoardPage.cancelDialog();
        dashBoardPage
                .deleteSpecificMenuTab_Child(parentPageName, childPageName, true)
                .deleteSpecificMenuTab(parentPageName, true);
        Assert.assertFalse(dashBoardPage.isSpecifyMenuTabExist(parentPageName));
    }

    @Test
    @Description("Verify that user is able to edit the parent page of the sibbling page successfully")
    public void TC23() {
        String pageName1 = "TC23_1" + Constant.getPageName();
        String pageName2 = "TC23_2" + Constant.getPageName();
        String pageName3 = "TC23_3" + Constant.getPageName();
        List<String> listString1 = Arrays.asList(Constant.Overview, pageName1);
        List<String> listString2 = Arrays.asList(Constant.Overview, pageName3);

        Log.info("1. Login\n");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        Log.info("2. Go to Global Setting -> Add page\n" +
                "3. Enter info into all required fields on New Page dialog:\n" +
                "-Page name: Page 1\n" +
                "-Parent page: Overview\n");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(pageName1, "", Constant.Overview, "false");
        Assert.assertTrue(dashBoardPage.isSpecifyMenuTabExist_Child(Constant.Overview, pageName1));
        Log.info("4. Go to Global Setting -> Add page\n" +
                "5. Enter info into all required fields on New Page dialog:\n" +
                "-Page name: Page 2\n" +
                "-Parent page: Page 1\n");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(pageName2, "", pageName1, "false");
        Assert.assertTrue(dashBoardPage.isSpecifyMenuTabExist_Child(listString1, pageName2));
        Log.info("6. Go to the first created page - Page 1\n" +
                "7. Click Edit link\n" +
                "8. Enter another name into Page Name field - Page name: Page 3\n" +
                "9. Click Ok button on Edit Page dialog\n");
        dashBoardPage.clickOnSpecificMenuTab_Child(Constant.Overview, pageName1)
                .openEditPageDialog().enterDialogNewOrEditPage(pageName3);
        Log.info(" VP: User is able to edit the parent page of the sibbling page successfully\n");
        Assert.assertTrue(dashBoardPage.isSpecifyMenuTabExist_Child(listString2, pageName2));
        Log.info("post condition\n");
        dashBoardPage
                .deleteSpecificMenuTab_Child(listString2, pageName2, true)
                .deleteSpecificMenuTab_Child(Constant.Overview, pageName3, true);
        Assert.assertFalse(dashBoardPage.isSpecifyMenuTabExist_Child(Constant.Overview, pageName3));
    }

    @Test
    @Description("Verify that \"Bread Crums\" navigation is correct")
    public void TC24() {
        String pageName1 = "TC24_1" + Constant.getPageName();
        String pageName2 = "TC24_2" + Constant.getPageName();
        List<String> listString1 = Arrays.asList(Constant.Overview, pageName1);

        Log.info("1. Login\n");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        Log.info("2. Go to Global Setting -> Add page\n" +
                "3. Enter info into all required fields on New Page dialog:\n" +
                "- Page name: Page 1\n" +
                "- Parent page: Overview\n");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(pageName1, "", Constant.Overview, "false");
        Assert.assertTrue(dashBoardPage.isSpecifyMenuTabExist_Child(Constant.Overview, pageName1));
        Log.info("4. Go to Global Setting -> Add page\n" +
                "5. Enter info into all required fields on New Page dialog:\n" +
                " -Page name: Page 2\n" +
                " -Parent page: Page 1\n");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(pageName2, "", pageName1, "false");
        Assert.assertTrue(dashBoardPage.isSpecifyMenuTabExist_Child(listString1, pageName2));
        Log.info("6. Click the first breadcrums - Page 1\n");
        Log.info("7. Click the second breadcrums\n");
        dashBoardPage.clickOnSpecificMenuTab_Child(Constant.Overview, pageName1);
        dashBoardPage.clickOnSpecificMenuTab_Child(listString1, pageName2);
        Log.info("VP. The first page is navigated -> coverd in post condition (can only delete opening page)\n" +
                "The second page is navigated -> coverd in post condition (can only delete opening page\n");
        dashBoardPage
                .deleteSpecificMenuTab_Child(listString1, pageName2, true)
                .deleteSpecificMenuTab_Child(Constant.Overview, pageName1, true);
        Assert.assertFalse(dashBoardPage.isSpecifyMenuTabExist_Child(Constant.Overview, pageName1));
    }

    @Test
    @Description("Verify that page listing is correct when user edit \"Display After\" field of a specific page")
    public void TC25() {
        String pageName1 = "TC25_1" + Constant.getPageName();
        String pageName2 = "TC25_2" + Constant.getPageName();
        List<String> listString = Arrays.asList(pageName2, pageName1);

        Log.info("1. Login");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        Log.info("2. Go to Global Setting -> Add page" +
                "3. Enter info into all required fields on New Page dialog: Page name: Page 1");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(pageName1, "", "", "false");
        Assert.assertTrue(dashBoardPage.isSpecifyMenuTabExist(pageName1));
        Log.info("4. Go to Global Setting -> Add page\n" +
                "5. Enter info into all required fields on New Page dialog: Page 2");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(pageName2, "", "", "false");
        Assert.assertTrue(dashBoardPage.isSpecifyMenuTabExist(pageName2));
        dashBoardPage
                .clickOnSpecificMenuTab(pageName2).openEditPageDialog()
                .enterDialogNewOrEditPage_DisplayAfter(pageName2, "", Constant.Overview, "false");
        Log.info("VP. Position of the second page follow Overview page");
        Assert.assertTrue(dashBoardPage.checkPageOrder_Parents(listString));
        Log.info("post condition");
        dashBoardPage
                .deleteSpecificMenuTab(pageName1, true)
                .deleteSpecificMenuTab(pageName2, true);
        Assert.assertFalse(dashBoardPage.isSpecifyMenuTabExist(pageName1));
        Assert.assertFalse(dashBoardPage.isSpecifyMenuTabExist(pageName2));
    }

    @Test
    @Description("Verify that page column is correct when user edit \"Number of Columns\" field of a specific page")
    public void TC26() {
        String pageName1 = "TC26" + Constant.getPageName();

        Log.info("1. Login");
        DashBoardPage dashBoardPage = loginPage
                .openLoginPage()
                .loginSuccess(defaultAccount);
        Log.info("2. Go to Global Setting -> Add page\n" +
                "3. Enter info into all required fields on New Page dialog: \n" +
                "-Page name: Page 1\nc" +
                "Verify that page column is correct when user edit \"Number of Columns\" field of a specific page");
        dashBoardPage
                .openAddPageDialog()
                .enterDialogNewOrEditPage_ParentPage(pageName1, "2", "", "false");
        Assert.assertTrue(dashBoardPage.isSpecifyMenuTabExist(pageName1));
        Log.info("4. Go to Global Setting -> Edit link\n" +
                "5. Edit Number of Columns for the above created page\n" +
                "Column = 3\n");
        dashBoardPage
                .clickOnSpecificMenuTab(pageName1).openEditPageDialog()
                .enterDialogNewOrEditPage_ParentPage(pageName1, "3", "", "false");
        Assert.assertTrue(dashBoardPage.isNumberOfColumnExist(3));
        Log.info("post condition");
        dashBoardPage
                .deleteSpecificMenuTab(pageName1, true);
        Assert.assertFalse(dashBoardPage.isSpecifyMenuTabExist(pageName1));
    }
}
