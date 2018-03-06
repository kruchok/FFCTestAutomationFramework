package pages;

import infrastructure.Browser;
import infrastructure.Property;
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
        String mavenProperty = System.getProperty("env");
        if (mavenProperty.equalsIgnoreCase("localhost")) {
            baseUrl = Property.getProperty("localhost");
        } else if (mavenProperty.equalsIgnoreCase("demo")) {
            baseUrl = Property.getProperty("demo");
        }
    }

    public void close() {
        Browser.driver.close();
    }

    public static String getRelativeUrl(String pageName) {
        return Property.getProperty(pageName);
    }

    public boolean isCurrentUrl(String url) {
        return Browser.waitForElement(20).until(ExpectedConditions.urlToBe(url));
    }

}
