package Common.constant;

import java.util.Random;

public class Constant {
    public static final String TA_URL = "http://localhost/TADashboard/";
    public static final String HUB_URL = "http://{Hub_IP_Address}:4444/grid/console";
    public static final String Repository_Test = "TestRepository";
    public static final String UserAd = "administrator";
    public static final String UserAbc = "abc";
    public static final String PassAbc = "abc";
    public static final String BLANK = "";
    public static final String UserName = "test";
    public static final String Password = "TEST";
    public static final String BlankValueWarning = "Please enter username";
    public static final String Overview = "Overview";
    public static final String PageName = "Page";
    public static final String PanelName = "Panel";
    public static final int Timeout = 5;

    public static String getPageName() {
        Random random = new Random();
        return PageName + random.nextInt(100);
    }

    public static String getPanelName() {
        Random random = new Random();
        return PanelName + random.nextInt(100);
    }

    public static String getRandomNumber() {
        Random random = new Random();
        return Integer.toString(random.nextInt(100));
    }
}
