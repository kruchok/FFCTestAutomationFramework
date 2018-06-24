package pages;

import infrastructure.Browser;
import infrastructure.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.interfaces.SignIn;

/**
 * Created by eugeniya.kruchok on 01.03.2018.
 */
public class SignInPage extends SignPage implements SignIn {

    public SignInPage() {
        super();
    }

    private static String url = baseUrl + Config.getProperty("signIn");

    @FindBy(id = "flash-content")
    private WebElement successText;

    @Override
    @Step("Open Sign In page")
    public void open() {
        Browser.driver.get(url);
    }

    @Step("Verify that success Sign In message is displayed")
    public boolean isSuccess() {
        Browser.waitForElement(10).until(ExpectedConditions.visibilityOf(successText));
        return successText.getText().contains("If you entered a valid email, a magic link is on its way");
    }
}
