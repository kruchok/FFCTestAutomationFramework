package driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AndroidDriverFactory implements DriverFactory {
    AppiumDriverLocalService service;
    DesiredCapabilities capabilities;

    public AndroidDriverFactory(AppiumDriverLocalService service, DesiredCapabilities capabilities) {
        this.service = service;
        this.capabilities = capabilities;
    }

    @Override
    public WebDriver createDriver() {

        return new AndroidDriver<>(service.getUrl(), capabilities);
    }
}
