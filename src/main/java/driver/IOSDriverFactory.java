package driver;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IOSDriverFactory implements Factory {
    private AppiumDriverLocalService service;
    private DesiredCapabilities capabilities;

    public IOSDriverFactory(AppiumDriverLocalService service, DesiredCapabilities capabilities) {
        this.service = service;
        this.capabilities = capabilities;
    }

    @Override
    public WebDriver factory() {
        return new IOSDriver<>(service.getUrl(), capabilities);
    }
}
