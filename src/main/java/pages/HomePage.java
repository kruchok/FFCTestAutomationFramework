package pages;

import infrastructure.Browser;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.CurrentTime;

/**
 * Created by eugeniya.kruchok on 27.02.2018.
 */
public class HomePage extends PageObject {

    public HomePage() {super();}

    protected static String url = baseUrl;

    @FindBy(css = "a[href=\"/signin\"].btn-block")
    private WebElement sighUpLink;


    /**
     * Relative URL is the same to Homapage URL, so this field is an
     * empty string.
    */


    @Step("Open Homepage by URL")
    public void open() {
        Browser.driver.get(baseUrl);
        Browser.logger.debug(this.getClass().getSimpleName() + " is opened at" + CurrentTime.getCurrentTime());
    }

    @Step("Open Sign Up page by clicking 'Start coding' button")
    public void openSignUpPage() {
       sighUpLink.click();
    }

    @Step("Verify that Home page is opened")
    public boolean isAt() {
        return isCurrentUrl(baseUrl);
    }

    public static String getUrl() {
        return url;
    }
}
