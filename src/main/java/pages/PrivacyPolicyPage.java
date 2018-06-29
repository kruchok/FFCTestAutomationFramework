package pages;

import infrastructure.Config;
import infrastructure.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PrivacyPolicyPage extends PageObject {

    private static String url = Driver.baseUrl + Config.getProperty("privacy");

    @FindBy(css = "div.checkbox:nth-child(2) .cr")
    private WebElement terms;

    @FindBy(css = "div.checkbox:nth-child(3) .cr")
    private WebElement privacy;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "continue-button")
    private WebElement continueButton;

    public PrivacyPolicyPage() { super();}


    @Step("Verify that current page is Privacy Policy page")
    public boolean isAt() {
        return Driver.waitForElement(10).until(ExpectedConditions.urlToBe(url));
    }

    @Step("Accept terms and privacy policy")
    public void acceptTerms() {
        terms.click();
        privacy.click();
        Driver.waitForElement(3).until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        Driver.waitForElement(3).until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public static String getUrl() {
        return url;
    }
}
