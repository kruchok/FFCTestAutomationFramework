package utils;

import java.util.Random;

/**
 * Created by eugeniya.kruchok on 07.03.2018.
 */
public class StringGenerator {

    private static String getSaltString(int stringLength) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < stringLength) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public static String generateUsername() {
        return getSaltString(20);
    }

    public static String generateEmail() {
        return getSaltString(10) + "@mail.com";
    }
}
