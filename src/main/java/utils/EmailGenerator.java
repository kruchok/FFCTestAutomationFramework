package utils;

import java.util.Random;

/**
 * Created by eugeniya.kruchok on 06.03.2018.
 */
public class EmailGenerator extends StringGenerator {

    public static String generateEmail() {
        return getSaltString(10) + "@mail.com";
    }
}
