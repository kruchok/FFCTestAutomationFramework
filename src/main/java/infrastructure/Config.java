package infrastructure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by eugeniya.kruchok on 26.02.2018.
 */
public class Config {

    private static Properties properties;

    private static File propertiesFile = new File("./src/main/resources/environment.properties");

    public static String getProperty(String property) {
        return properties.getProperty(property);
    }

    public static void initProperties() {

        if (properties == null) {

            properties = new Properties();

            try (FileInputStream inputStream = new FileInputStream(propertiesFile)) {
                properties.load(inputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
