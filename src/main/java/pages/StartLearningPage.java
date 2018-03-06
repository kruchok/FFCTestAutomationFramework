package pages;

import infrastructure.Browser;
import infrastructure.Property;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by eugeniya.kruchok on 01.03.2018.
 */
public class StartLearningPage extends PageObject {

    private static String url = baseUrl + Property.getProperty("challenges") +
            Property.getProperty("basicHtmlAndHtml5") +
            Property.getProperty("learnHow");

    @Step("Verify that current page is Start Learning page")
    public boolean isAt() {
        return Browser.waitForElement(10).until(ExpectedConditions.urlToBe(url));
    }

    public static String getUrl() {
        return url;
    }
}
