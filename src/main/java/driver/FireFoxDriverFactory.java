package driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by eugeniya.kruchok on 26.02.2018.
 */
public class FireFoxDriverFactory extends GeneralWebDriver {

    @Override
    public WebDriver factory() {

        FirefoxDriverManager.getInstance().setup();
        return new FirefoxDriver();
    }
}
