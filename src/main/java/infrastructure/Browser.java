package infrastructure;

import driver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by eugeniya.kruchok on 26.02.2018.
 */
public class Browser {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static ArrayList<String> windows;
    private static int defaultWait = 20;

    public static void setDriver() {
        if (driver == null) {
            driver = WebDriverFactory.getDriver(Property.getProperty("browser"));
            driver.manage().timeouts().implicitlyWait(defaultWait, TimeUnit.SECONDS);
        }
    }

    public static void wait(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public static void defaultWait() {
        driver.manage().timeouts().implicitlyWait(defaultWait, TimeUnit.SECONDS);
    }

    public static WebDriverWait waitForElement(int seconds) {
        if (driver != null) {
            wait = new WebDriverWait(driver, seconds);
        } else {
            throw new NullPointerException("Driver isn't initialized.");
        }
        return wait;
    }

    public static void close() {
        Browser.driver.quit();
    }

    public static void switchToWindow(int winNumber) {
        windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(winNumber));
    }

    public static void switchToLastWindow() {
        windows = new ArrayList<>(driver.getWindowHandles());
        int lastWinIndex = windows.size() - 1;
        driver.switchTo().window(windows.get(lastWinIndex));
    }
}
