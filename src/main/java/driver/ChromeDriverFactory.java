package driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by eugeniya.kruchok on 26.02.2018.
 */
public class ChromeDriverFactory implements DriverFactory {

    @Override
    public WebDriver createDriver() {

        ChromeDriverManager.getInstance().setup();
        ChromeOptions options;
        if (System.getProperty("mode", "none").equals("headless")) {
            options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("window-size=1200,1100");
            return new ChromeDriver(options);
        } else {
            return new ChromeDriver();
        }
    }
}
