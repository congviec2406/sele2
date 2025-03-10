package Common.common;

import java.util.Random;

public class Utilities {
    public static int ramdomInt(int min, int max) {
        return (int) ((Math.random() * (max - min + 1)) + min);
    }
}
