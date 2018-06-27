package driver;

import org.openqa.selenium.WebDriver;

public class DesktopFactory implements DriverFactory {

    @Override
    public WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equalsIgnoreCase("firefox")) {
            FireFoxDriverFactory factory = new FireFoxDriverFactory();
            return factory.createDriver();
        } else {
            ChromeDriverFactory factory = new ChromeDriverFactory();
            return factory.createDriver();
        }
    }
}
