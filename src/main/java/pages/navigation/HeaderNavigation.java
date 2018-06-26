package pages.navigation;

import infrastructure.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.PageObject;

/**
 * Created by eugeniya.kruchok on 28.02.2018.
 */
public class HeaderNavigation extends PageObject {

    public HeaderNavigation() {super();}

    @FindBy(css = "a[href$=\"/settings\"]")
    private WebElement settingsLink;

    @FindBy(css = "a[href=\"/signin\"]")
    private WebElement signUpLink;

    @Step("Open Account Settings page")
    public void openAccountSettings() {
        Driver.waitForElement(10).until(ExpectedConditions.visibilityOf(settingsLink));
        settingsLink.click();
    }

    public boolean isSignUpLinkPresent() {
        return Driver.isElementPresent(signUpLink);
    }
}
