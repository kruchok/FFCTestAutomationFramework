package pages;

import infrastructure.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.CurrentTime;

/**
 * Created by eugeniya.kruchok on 27.02.2018.
 */
public class HomePage extends PageObject {

    public HomePage() {super();}

    protected static String url = Driver.baseUrl;

    @FindBy(css = "a[href=\"/signin\"]")
    private WebElement sighInLink;


    /**
     * Relative URL is the same to Homapage URL, so this field is an
     * empty string.
    */


    @Step("Open Homepage by URL")
    public void open() {
        Driver.driver.get(Driver.baseUrl);
        Driver.logger.debug(this.getClass().getSimpleName() + " is opened at" + CurrentTime.getCurrentTime());
    }

    @Step("Open Sign Up page by clicking 'Start coding' button")
    public void openSignInPage() {
       sighInLink.click();
    }

    @Step("Verify that Home page is opened")
    public boolean isAt() {
        return Driver.isCurrentUrl(Driver.baseUrl);
    }

    public String getUrl() {
        return url;
    }
}
