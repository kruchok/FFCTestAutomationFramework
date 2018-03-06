package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by eugeniya.kruchok on 26.02.2018.
 */
public class CurrentTime {

    public static String getCurrentTime() {
        return new SimpleDateFormat(" " + "yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }
}
