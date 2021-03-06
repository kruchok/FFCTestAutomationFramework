package pages;

import infrastructure.Driver;
import infrastructure.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by eugeniya.kruchok on 01.03.2018.
 */
public class SignInPage extends SignPage {

    public SignInPage() {
        super();
    }

    private static String url = Driver.baseUrl + Config.getProperty("signIn");

    @FindBy(id = "flash-content")
    private WebElement successText;

    @Step("Open Sign In page")
    public void open() {
        Driver.driver.get(url);
    }
}
