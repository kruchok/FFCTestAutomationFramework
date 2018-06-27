package driver;

import org.openqa.selenium.WebDriver;

/**
 * Created by eugeniya.kruchok on 26.02.2018.
 */
public class WebDriverFactory {

    public static WebDriver createDriver() {
        String platform = System.getProperty("platform");
        if (platform == null) {
            return new DesktopFactory().createDriver();
        } else {
            return new MobileFactory().createDriver();
        }
    }
}
