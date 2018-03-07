package utils;

/**
 * Created by eugeniya.kruchok on 07.03.2018.
 */
public class UsernameGenerator extends StringGenerator {

    public static String generateUsername() {
        return getSaltString(20);
    }
}
