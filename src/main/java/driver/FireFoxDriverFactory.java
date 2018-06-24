package driver;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by eugeniya.kruchok on 26.02.2018.
 */
public class FireFoxDriverFactory implements Factory {

    @Override
    public WebDriver factory() {

        FirefoxDriverManager.getInstance().setup();
        return new FirefoxDriver();
    }
}
