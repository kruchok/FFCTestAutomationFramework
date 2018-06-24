package driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by eugeniya.kruchok on 26.02.2018.
 */
public class ChromeDriverFactory implements Factory {

    @Override
    public WebDriver factory() {

        ChromeDriverManager.getInstance().setup();
        return new ChromeDriver();
    }
}
