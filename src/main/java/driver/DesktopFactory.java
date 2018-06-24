package driver;

import org.openqa.selenium.WebDriver;

public class DesktopFactory implements Factory {

    @Override
    public WebDriver factory() {
        String browser = System.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeDriverFactory factory = new ChromeDriverFactory();
            return factory.factory();
        } else if (browser.equalsIgnoreCase("firefox")) {
            FireFoxDriverFactory factory = new FireFoxDriverFactory();
            return factory.factory();
        }
        return null;
    }
}
