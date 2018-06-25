package infrastructure;

import driver.WebDriverFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private static int defaultWait = 20;
    public static Logger logger;

    public static void setDriver() {
        if (driver == null) {
            driver = WebDriverFactory.getDriver();
            driver.manage().timeouts().implicitlyWait(defaultWait, TimeUnit.SECONDS);
            driver.manage().window().fullscreen();
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
}