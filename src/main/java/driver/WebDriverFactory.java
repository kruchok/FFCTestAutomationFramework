package driver;

import infrastructure.Browser;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;

/**
 * Created by eugeniya.kruchok on 26.02.2018.
 */
public class WebDriverFactory {

    public static WebDriver getDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeDriverFactory factory = new ChromeDriverFactory();
            return factory.factory();
        } else if (browser.equalsIgnoreCase("firefox")) {
            FireFoxDriverFactory factory = new FireFoxDriverFactory();
            return factory.factory();
        } else {
            return null;
        }
    }
}
