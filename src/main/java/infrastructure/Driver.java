package infrastructure;

import driver.WebDriverFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by eugeniya.kruchok on 26.02.2018.
 */
public class Driver {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static ArrayList<String> windows;
    public static String baseUrl;
    private static int defaultWait = 20;
    public static Logger logger;

    public static void setDriver() {
        if (driver == null) {
            driver = WebDriverFactory.createDriver();
            driver.manage().timeouts().implicitlyWait(defaultWait, TimeUnit.SECONDS);
            driver.manage().window().setSize(new Dimension(1024, 768));
        }
    }

    public static WebDriver getDriver() {
        return driver;
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
        Driver.driver.quit();
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

    public static void refreshPage() {
        Driver.driver.navigate().refresh();
    }

    public static boolean isElementPresent(String css) {
        return Driver.driver.findElements(By.cssSelector(css)).size() > 0;
    }

    public static boolean isElementPresent(String css, int timeout) {
        Driver.wait(timeout);
        boolean isPresent = Driver.driver.findElements(By.cssSelector(css)).size() > 0;
        Driver.defaultWait();
        return isPresent;
    }

    public static boolean isElementPresent(WebElement element) {
        try {
            element.getTagName();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isElementPresent(WebElement element, int timeout) {
        try {
            Driver.wait(timeout);
            element.getTagName();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            Driver.defaultWait();
        }
    }

    public static void initBaseUrl() {
        String environment = System.getProperty("env");
        if (environment.equalsIgnoreCase("localhost")) {
            baseUrl = Config.getProperty("localhost");
        } else if (environment.equalsIgnoreCase("demo")) {
            baseUrl = Config.getProperty("demo");
        } else if (environment.equalsIgnoreCase("external")) {
            baseUrl = Config.getProperty("external");
        }
    }

    public static String getRelativeUrl(String pageName) {
        return Config.getProperty(pageName);
    }

    public static boolean isCurrentUrl(String url) {
        return waitForElement(20).until(ExpectedConditions.urlToBe(url));
    }
}
