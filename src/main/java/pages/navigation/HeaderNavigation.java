package pages.navigation;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageObject;

/**
 * Created by eugeniya.kruchok on 28.02.2018.
 */
public class HeaderNavigation extends PageObject {

    public HeaderNavigation() {super();}

    @FindBy(css = "a[href$=\"/settings\"]")
    private WebElement settingsLink;

    @Step("Open Account Settings page")
    public void openAccountSettings() {
        settingsLink.click();
    }
}
