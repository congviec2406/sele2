package wrapper;

import Common.common.Log;

import static java.lang.Thread.sleep;

public class CustomAlert {

    public static String getTextAlert() {
        Log.info("Get alert text");
        try {
            sleep(1000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Driver.getAlert().getText();
    }

    public static void acceptAlert() {
        Log.info("Click ok on alert");
        Driver.getAlert().accept();
    }
}
