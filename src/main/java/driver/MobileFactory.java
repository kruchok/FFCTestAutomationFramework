package driver;

import infrastructure.Driver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MobileFactory implements DriverFactory {
    private AppiumDriverLocalService service;
    private DesiredCapabilities capabilities;
    public String platform = System.getProperty("platform");

    @Override
    public WebDriver createDriver() {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        setDesiredCapabilites();

        if (platform.equalsIgnoreCase("android")) {
            AndroidDriverFactory androidDriverFactory = new AndroidDriverFactory(service, capabilities);
            return androidDriverFactory.createDriver();
        } else if (platform.equalsIgnoreCase("ios")) {
            IOSDriverFactory iosDriverFactory = new IOSDriverFactory(service, capabilities);
            return iosDriverFactory.createDriver();
        }
        return null;
    }

    private void setDesiredCapabilites() {
        String platformVersion = System.getProperty("platform_version");
        String browserName = System.getProperty("browser");
        String device = null;
        String avd = null;
        capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        if (browserName != null) {
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
        } else {
            if (platform.equalsIgnoreCase("android")) {
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
            } else if (platform.equalsIgnoreCase("ios")){
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
            }
        }
        if (platform.equalsIgnoreCase("android")) {
            if (platformVersion.equals("7.0")) {
                device = "Nexus 6P";
                avd = "Nexus_6P_API_24";
            } else if (platformVersion.equals("6.0")) {
                device = "Samsung Galaxy S5";
                avd = "Samsung_Galaxy_S5_API_23";
            } else if (platformVersion.equals("8.0")) {
                device = "Pixel 2 XL";
                avd = "Pixel_2_XL_API_26";
            } else {
                Driver.logger.debug("There is no emulator for specified Android device");
            }
        } else if (platform.equalsIgnoreCase("ios")) {
            if (platformVersion.equals("11.0")) {
                device = "iPhone 8";
            } else if (platformVersion.equals("10.2")) {
                device = "iPhone 7";
            } else if (platformVersion.equals("9.0")) {
                device = "iPhone SE";
            } else {
                Driver.logger.debug("There is no emulator for specified iOS device");
            }
        }
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
        if (platform.equalsIgnoreCase("android")) {
            capabilities.setCapability("avd", avd);
        }

    }
}
