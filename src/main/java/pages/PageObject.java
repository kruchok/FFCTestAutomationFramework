package pages;

import infrastructure.Browser;
import infrastructure.Config;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by eugeniya.kruchok on 26.02.2018.
 */
public abstract class PageObject {

    public static String baseUrl;

    public PageObject() {
        PageFactory.initElements(Browser.driver, this);
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

    public void close() {
        Browser.driver.close();
    }

    public static String getRelativeUrl(String pageName) {
        return Config.getProperty(pageName);
    }

    public boolean isCurrentUrl(String url) {
        return Browser.waitForElement(20).until(ExpectedConditions.urlToBe(url));
    }

}
