package infrastructure;

import java.io.*;
import java.util.Properties;

/**
 * Created by eugeniya.kruchok on 05.03.2018.
 */
public class AllureEnvironment {
    String path = "./allure-results/freeCodeCamp.properties";

    Properties property = new Properties();
    OutputStream stream;
    String osName = System.getProperty("os.name");
    String osVersion = System.getProperty("os.version");
    String osArch = System.getProperty("os.arch");
    String environment = System.getProperty("env");

    AllureEnvironment allureEnvironment;

    public AllureEnvironment() {
        if (allureEnvironment == null) {
            try {
                stream = new FileOutputStream(new File(path));

                property.setProperty("OS Arch", osArch);
                property.setProperty("OS Version", osVersion);
                property.setProperty("OS", osName);
                property.setProperty("Environment", environment);

                property.store(stream, null);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
