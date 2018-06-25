package pages;

import infrastructure.Driver;
import infrastructure.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by eugeniya.kruchok on 01.03.2018.
 */
public class StartLearningPage extends PageObject {

    private static String url = baseUrl + Config.getProperty("challenges") +
            Config.getProperty("basicHtmlAndHtml5") +
            Config.getProperty("learnHow");

    @Step("Verify that current page is Start Learning page")
    public boolean isAt() {
        return Driver.waitForElement(10).until(ExpectedConditions.urlToBe(url));
    }

    public static String getUrl() {
        return url;
    }
}
